package com.example.surge.tapadoo_book;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


//Building the API interface, only interacts with all the books and stores locally.
public interface Api {

    String id = "";
    String BASE_URL = "http://tpbookserver.herokuapp.com/";

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{id}")
    Call<Book> getBook(
            @Path("id") String id
    );
}
