package com.example.snrr;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

public class ProductActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent get_intent = getIntent();
        product = get_intent.getParcelableExtra("product");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = FirstFragment.newInstance(product);

        ft.replace(R.id.frame_placeholder, fragment);
        Fragment fragment2 = SecondFragment.newInstance(product);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ft.replace(R.id.frame_placeholder2, fragment2);
        } else {
            // In portrait
        }
        ft.commit();

    }

    public void rentProduct(View view){
        final Intent send_intent = new Intent(this, RentingActivity.class);
        send_intent.putExtra("product", product);
        startActivity(send_intent);
    }
}
