package com.example.surge.tapadoo_book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


//Building the API interface, only interacts with all the books and stores locally.
public interface Api {

    String BASE_URL = "http://tpbookserver.herokuapp.com/";

    @GET("books")
    Call<List<Book>> getBooks();
}
