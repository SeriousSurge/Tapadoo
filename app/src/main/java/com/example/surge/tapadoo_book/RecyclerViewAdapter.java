package com.example.surge.tapadoo_book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Book> mData ;


    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.tv_book_price.setText(mData.get(position).getPriceString());
        Picasso.get().load(mData.get(position).getCoverImg()).into(holder.img_book_cover);

        //Attaches a listener to the cardviews and sends the data through to the BookActivity. Also handles some formatting.
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,BookActivity.class);

                // passing data to the book activity
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  intent.putExtra("id",mData.get(position).getId());

                // start the activity
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //Sets up the ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        TextView tv_book_price;
        ImageView img_book_cover;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            tv_book_price = (TextView) itemView.findViewById(R.id.book_price_id) ;
            img_book_cover = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}