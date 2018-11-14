package com.example.surge.tapadoo_book;

import java.text.NumberFormat;
import java.util.Currency;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private int price;
    private String currencyCode;
    private String author;
    private String coverImg;
    private String priceString;
    private String description;


    public Book(){

    }


    public Book(int id, String title, String isbn, int price, String currencyCode, String author, String description) {
        this(id, title, isbn, price, currencyCode, author);
        this.description = description;
    }

    //Book Constructor for the API data collected
    public Book(int id, String title, String isbn, int price, String currencyCode, String author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.currencyCode = currencyCode;
        this.author = author;
        this.description = "";
        this.coverImg = "http://covers.openlibrary.org/b/isbn/" + isbn.replaceAll("[^\\d.]", "") + "-M.jpg";

        //Formats the currency from the price int and ISO CurrencyCode
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency(Currency.getInstance(currencyCode));
        double currencyAmount = price;
        currencyAmount = currencyAmount/100;
        this.priceString= nf.format(currencyAmount);
    }


    //Getters for book data



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public String getPriceString() {
        return priceString;
    }

    public String getDescription() {
        return description;
    }
}
