package com.example.snrr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.myViewHolder>{
    private ArrayList<Product> listdata;

    public MyListAdapter(ArrayList<Product> listdata) {
        this.listdata = listdata;
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
        final Product myListData = listdata.get(position);
        holder.textView.setText(listdata.get(position).getName());
        holder.imageView.setImageResource (listdata.get(position).getImage());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
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
        public FrameLayout cardview;
        public myViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            cardview = (FrameLayout)itemView.findViewById(R.id.linearLayout);
        }
    }
}