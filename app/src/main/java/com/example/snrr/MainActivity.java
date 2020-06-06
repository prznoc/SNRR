package com.example.snrr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener  {

    SearchView editsearch;
    Product[] products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        products = new Product[] {
                new Product(22.2, getIdFromName("chochla"), "chochla", "To jest chochla"),
                new Product(22.2, getIdFromName("mortar"), "mortar", "This is MORTAR!!!")
        };
    }

    private int getIdFromName(String name){
        return getResources().getIdentifier(name , "drawable", getPackageName());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        //adapter.filter(text);
        return false;
    }
}