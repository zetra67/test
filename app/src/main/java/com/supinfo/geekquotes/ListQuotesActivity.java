package com.supinfo.geekquotes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.supinfo.geekquotes.models.Quote;

import java.util.ArrayList;

public class ListQuotesActivity extends AppCompatActivity {

    private ArrayList<Quote> quotes;

    private QuoteAdapter quoteAdapter;
    private ListViewCompat quotesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        quotes = new ArrayList<>();
        populateQuotesList();

        quoteAdapter = new QuoteAdapter(quotes, this);
        quotesListView = (ListViewCompat) findViewById(R.id.quotes_list_view);
        quotesListView.setAdapter(quoteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_quotes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addQuote(String quoteContent) {
        quotes.add(new Quote(quoteContent));
    }

    private void populateQuotesList() {
        for (String quoteContent : getResources().getStringArray(R.array.example_quotes)) {
            addQuote(quoteContent);
        }
    }
}
