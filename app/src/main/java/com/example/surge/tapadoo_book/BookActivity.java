package com.example.surge.tapadoo_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//The book activity Shows individual book data on a seperate activity
public class BookActivity extends AppCompatActivity {

    private TextView title,author,price,isbn, desc;
    private ImageView img;

    String Title;
    String Author;
    String Price;
    String ISBN;
    String Desc;
    String Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_book);

        title = (TextView) findViewById(R.id.txt_title);
        author = (TextView) findViewById(R.id.txt_auth);
        price = (TextView) findViewById(R.id.txt_price);
        isbn = (TextView) findViewById(R.id.txt_isbn);
        desc = (TextView) findViewById(R.id.txt_detail);
        img = (ImageView) findViewById(R.id.cover_img);



        // Recieve data
        Intent intent = getIntent();
        int Id = intent.getExtras().getInt("id");
        Log.i("MEME", Integer.toString(Id));

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are converting the json data to an object
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //making the call object
        Call<Book> call = api.getBook(Integer.toString(Id));


        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                try {
                    if (response.body() != null) {
                        NumberFormat nf = NumberFormat.getCurrencyInstance();
                        nf.setCurrency(Currency.getInstance(response.body().getCurrencyCode()));
                        double currencyAmount = response.body().getPrice();
                        currencyAmount = currencyAmount / 100;
                        Price = nf.format(currencyAmount);

                        Img = "http://covers.openlibrary.org/b/isbn/" + response.body().getIsbn().replaceAll("[^\\d.]", "") + "-M.jpg";

                        Title = response.body().getTitle();
                        Author = response.body().getAuthor();
                        ISBN = response.body().getIsbn();
                        Desc = response.body().getDescription();

                        title.setText(Title);
                        author.setText(Author);
                        price.setText(Price);
                        isbn.setText(ISBN);
                        desc.setText(Desc);
                        Picasso.get().load(Img).into(img);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error with this book, try again later", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }

                }catch (Exception e){
                    e.printStackTrace();
                    onBackPressed();
                }
            }

            //Shows a Toast if we fail to get API data
            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("why", "conf");
            }
        });

    }
}
