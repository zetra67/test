package com.supinfo.geekquotes.models;

import java.io.Serializable;
import java.util.Calendar;

public class Quote implements Serializable {
    private String content;
    private float rating;
    private Calendar creationDate;

    public Quote(String content) {
        this.content = content;
        this.rating = 0;
        this.creationDate = Calendar.getInstance();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return content + ", rated " + rating + ", created on " + creationDate.getTime();
    }
}
