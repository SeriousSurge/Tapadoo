package com.example.surge.tapadoo_book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


//The book activity Shows individual book data on a seperate activity
public class BookActivity extends AppCompatActivity {

    private TextView title,author,price,isbn;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_book);

        title = (TextView) findViewById(R.id.txt_title);
        author = (TextView) findViewById(R.id.txt_auth);
        price = (TextView) findViewById(R.id.txt_price);
        isbn = (TextView) findViewById(R.id.txt_isbn);
        img = (ImageView) findViewById(R.id.cover_img);

        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Author = intent.getExtras().getString("Author");
        String Price = intent.getExtras().getString("Price");
        String ISBN = intent.getExtras().getString("ISBN");
        String Img = intent.getExtras().getString("Img");
        //Log.i("MEME", Img);

        // Setting values

        title.setText(Title);
        author.setText(Author);
        price.setText(Price);
        isbn.setText(ISBN);
        Picasso.get().load(Img).into(img);


    }
}
