package com.example.razu.newcsitproject.Model;

/**
 * Created by Razu on 11/29/2017.
 */

public class Quotes_model {
    private String author;
    private String quote;

    public Quotes_model(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
