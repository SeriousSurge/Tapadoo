package com.example.surge.tapadoo_book;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private int price;
    private String currencyCode;
    private String author;
    private String coverImg;

    //Book Constructor for the API data collected
    public Book(int id, String title, String isbn, int price, String currencyCode, String author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.currencyCode = currencyCode;
        this.author = author;
        this.coverImg = "http://covers.openlibrary.org/b/isbn/" + isbn.replaceAll("[^\\d.]", "") + "-M.jpg";
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
}
