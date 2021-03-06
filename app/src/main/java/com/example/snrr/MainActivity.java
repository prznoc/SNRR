package com.example.snrr;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView editsearch;

    ArrayList<Product> products = new ArrayList<>();

    ProductListAdapter adapter;

    RecyclerView recyclerView;

    DataBaseHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, BroadcastReceiverService.class));

        setContentView(R.layout.activity_main);

        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);


        dataBase = new DataBaseHelper(MainActivity.this);

        if (savedInstanceState != null) {
            products = savedInstanceState.getParcelableArrayList("products");
        }
        else{
            dataBase.deleteAllData();
            dataBase.prepopulateDb();
            updateProducts(dataBase.getAllProducts());
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new ProductListAdapter(this, products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("products", products);
    }

    @Override
    public boolean onQueryTextSubmit(String searchPhrase) {
        editsearch.onActionViewCollapsed();

        updateProducts(dataBase.getProducts(searchPhrase));

        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        //adapter.filter(text);
        return false;
    }

    void updateProducts(Cursor cursor) {
        products.clear();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                products.add(new Product(
                        cursor.getString(1),
                        cursor.getString(2),
                        Double.parseDouble(cursor.getString(3)),
                        cursor.getString(4)));
            }
        }
    }

}