package com.example.snrr;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = getIntent();
        product = intent.getParcelableExtra("product");

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

}
