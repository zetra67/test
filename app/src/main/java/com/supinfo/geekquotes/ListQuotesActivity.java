package com.supinfo.geekquotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.supinfo.geekquotes.models.Quote;
import com.supinfo.geekquotes.models.QuoteAdapter;

import java.util.ArrayList;

public class ListQuotesActivity extends AppCompatActivity implements ListViewCompat.OnItemClickListener {

    public final static int REQUEST_CODE = 123;

    private ArrayList<Quote> quotes;

    private QuoteAdapter quoteAdapter;
    private ListView quotesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quotes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListQuotesActivity.this);
                builder.setTitle(getString(R.string.add_quote));

                final EditText input = new EditText(ListQuotesActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addQuote(input.getText().toString());
                        quoteAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        quotes = new ArrayList<>();
        populateQuotesList();

        quoteAdapter = new QuoteAdapter(quotes, this);
        quotesListView = (ListView) findViewById(R.id.quotes_list_view);
        quotesListView.setAdapter(quoteAdapter);
        quotesListView.setOnItemClickListener(this);
    }

    private void addQuote(String quoteContent) {
        quotes.add(new Quote(quoteContent));
    }

    private void populateQuotesList() {
        for (String quoteContent : getResources().getStringArray(R.array.example_quotes)) {
            addQuote(quoteContent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(ListQuotesActivity.this, QuoteDetailsActivity.class);
        intent.putExtra("quote", quoteAdapter.getItem(i));
        intent.putExtra("quoteIndex", i);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        int quoteIndex = data.getIntExtra("quoteIndex", -1);
                        Quote quote = (Quote) data.getSerializableExtra("quote");
                        assert quoteIndex != -1;
                        assert quote != null;

                        quotes.set(quoteIndex, quote);
                        quoteAdapter.notifyDataSetChanged();

                        Toast.makeText(this, getString(R.string.quote_saved), Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, getString(R.string.quote_changes_discarded), Toast.LENGTH_SHORT).show();
                        break;;;
                }
                break;
        }
    }
}
