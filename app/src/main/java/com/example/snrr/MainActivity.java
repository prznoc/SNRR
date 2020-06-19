package com.example.snrr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {

    SearchView editsearch;
    ArrayList<Product> products = new ArrayList<>();
    MyListAdapter adapter;
    RecyclerView recyclerView;
    DataBaseHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this,BroadcastReceiverService.class));

        setContentView(R.layout.activity_main);

        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        dataBase = new DataBaseHelper(MainActivity.this);

        dataBase.deleteAllData();
        dataBase.prepopulateDb();

        if (savedInstanceState != null) {
            products = savedInstanceState.getParcelableArrayList("products");
        }
        else{
            updateProducts(dataBase.getAllProducts());
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new MyListAdapter(this ,products);
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
    public boolean onQueryTextSubmit(String query) {
        editsearch.onActionViewCollapsed();

        updateProducts(dataBase.getProducts(query));
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
                        cursor.getInt(4)));
            }
        }
    }
}