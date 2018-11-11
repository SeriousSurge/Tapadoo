package com.example.surge.tapadoo_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are converting the json data to an object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //making the call object
        Call<List<Book>> call = api.getBooks();

        //populates the Recycler view only if the retrofit gets data, otherwise shows a toast of the error. **Not Ideal**

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> bookList = response.body();

                ArrayList<Book> books = new ArrayList<Book>();
                Log.i("arraySize", Integer.toString(bookList.size()));

                for (int i = 0; i < bookList.size(); i++) {
                    int id = bookList.get(i).getId();
                    Log.i("idxx", Integer.toString(id));
                    String title = bookList.get(i).getTitle();
                    String isbn = bookList.get(i).getIsbn();
                    int price = bookList.get(i).getPrice();
                    String currencyCode = bookList.get(i).getCurrencyCode();
                    String author = bookList.get(i).getAuthor();

                    books.add(new Book(id, title, isbn, price, currencyCode, author));
                }
                RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_id);
                RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getApplicationContext(),books);
                myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                myrv.setAdapter(myAdapter);

            }

            //Shows a Toast if we fail to get API data
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
