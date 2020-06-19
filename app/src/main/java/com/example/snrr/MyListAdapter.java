package com.example.snrr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.myViewHolder>{
    private ArrayList<Product> listdata;
    private Context context;

    public MyListAdapter(Context context, ArrayList<Product> listdata) {
        this.listdata = listdata;
        this.context = context;
    }
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_content, parent, false);
        myViewHolder viewHolder = new myViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        final Product currentProduct = listdata.get(position);

        holder.textView.setText(currentProduct.getName());

        holder.price.setText(String.format("%.2f", currentProduct.getPrice()) + "zł");

        Picasso.get()
                .load(currentProduct.getImage())
                .resize(600,800)
                .onlyScaleDown()
                .into(holder.imageView);

        final Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("product", currentProduct);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView price;
        public FrameLayout cardview;
        public myViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.price = itemView.findViewById(R.id.price);
            cardview = (FrameLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}