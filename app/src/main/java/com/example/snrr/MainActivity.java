package com.example.snrr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {

    SearchView editsearch;
    ArrayList<Product> products = new ArrayList<>();
    MyListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.recycler_content);

        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        if (savedInstanceState != null) {
            products = savedInstanceState.getParcelableArrayList("products");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyListAdapter(products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("products", products);
    }

    private int getIdFromName(String name){
        return getResources().getIdentifier(name , "drawable", getPackageName());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        editsearch.onActionViewCollapsed();
        products.clear();
        ArrayList<Product> new_products = new ArrayList<Product>(Arrays.asList(
                new Product(22.2, getIdFromName("chochla"), "chochla", "To jest chochla"),
                new Product(22.2, getIdFromName("mortar"), "mortar", "This is MORTAR!!!")
        ));
        products.addAll(new_products);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        //adapter.filter(text);
        return false;
    }
}