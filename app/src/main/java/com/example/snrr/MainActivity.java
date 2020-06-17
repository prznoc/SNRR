package com.example.snrr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this,BroadcastReceiverService.class));

        setContentView(R.layout.activity_main);

        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        if (savedInstanceState != null) {
            products = savedInstanceState.getParcelableArrayList("products");
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

    private int getIdFromName(String name){
        return getResources().getIdentifier(name , "drawable", getPackageName());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        editsearch.onActionViewCollapsed();
        products.clear();
        ArrayList<Product> new_products = new ArrayList<Product>(Arrays.asList(
                new Product(22.2, getIdFromName("chochla"), "chochla", "To jest chochlabashdbajuhsydgyuashdiuashdniuahduiashdiuashdiuashduiashduiashdiauhdaiusdhiausd" +
                        "shndiuahduisahjdiadaushdiuashdiuashduiahdiuashdiuadhaisudhasuidhasuidhauisdhaiusdhsiaudhjaiuhsu"),
                new Product(12.2, getIdFromName("mortar"), "mortar", "This is MORTAR!!!")
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