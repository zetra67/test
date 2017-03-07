package com.supinfo.geekquotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.supinfo.geekquotes.models.Quote;

import java.util.ArrayList;

/**
 * Created by Quentin on 07/03/2017.
 */

public class QuoteAdapter extends BaseAdapter {
    private ArrayList<Quote> quotes;
    private Context context;

    public QuoteAdapter(ArrayList<Quote> quotes, Context context) {
        this.quotes = quotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int i) {
        return quotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_quote, null);
        }

        Quote quote = (Quote) getItem(i);

        TextView quoteContent = (TextView) view.findViewById(R.id.quote_content);
        RatingBar quoteRating = (RatingBar) view.findViewById(R.id.quote_rating);
        TextView quoteCreationDate = (TextView) view.findViewById(R.id.quote_creation_date);

        quoteContent.setText(quote.getContent());
        quoteRating.setRating(quote.getRating());
        quoteCreationDate.setText(quote.getCreationDate().getTime().toString());

        return view;
    }
}
