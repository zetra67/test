package com.supinfo.geekquotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.supinfo.geekquotes.models.Quote;

import java.util.Calendar;

public class QuoteDetailsActivity extends AppCompatActivity {

    private Quote recievedQuote;
    private int recievedQuoteIndex;

    TextView quoteContent;
    RatingBar quoteRating;
    CalendarView quoteCreationDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quoteContent = (TextView) findViewById(R.id.quote_content);
        quoteRating = (RatingBar) findViewById(R.id.quote_rating);
        quoteCreationDate = (CalendarView) findViewById(R.id.quote_creation_date);

        initializeQuoteFromIntent();
        initializeViews();
    }

    private void initializeQuoteFromIntent() {
        assert getIntent().getSerializableExtra("quote") != null;
        recievedQuote = (Quote) getIntent().getSerializableExtra("quote");
        recievedQuoteIndex = getIntent().getIntExtra("quoteIndex", -1);
    }

    private void initializeViews() {
        quoteContent.setText(recievedQuote.getContent());
        quoteRating.setRating(recievedQuote.getRating());
        quoteCreationDate.setDate(recievedQuote.getCreationDate().getTime().getTime(), true, true);
    }

    public void finishEdition(View source) {
        Intent returnIntent = new Intent();
        switch (source.getId()) {
            case R.id.quote_edition_discard:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.quote_edition_save:
                recievedQuote.setContent(quoteContent.getText().toString());
                recievedQuote.setRating(quoteRating.getRating());
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(quoteCreationDate.getDate());
                recievedQuote.setCreationDate(c);
                returnIntent.putExtra("quote", recievedQuote);
                returnIntent.putExtra("quoteIndex", recievedQuoteIndex);
                setResult(RESULT_OK, returnIntent);
                finish();
                break;
        }
    }
}