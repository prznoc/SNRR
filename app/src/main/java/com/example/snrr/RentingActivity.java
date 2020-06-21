package com.example.snrr;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RentingActivity extends AppCompatActivity {

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting);

        Intent get_intent = getIntent();
        product = get_intent.getParcelableExtra("product");
        TextView test = findViewById(R.id.testTextView);
        test.setText(product.getName());
    }
    public void checkOutOnClick(View view){
        CheckBox checkBox = findViewById(R.id.checkBox);
        if(checkBox.isChecked()) {
            Boolean proper_data = true;
            EditText text = (EditText) findViewById(R.id.editTextTextPersonName);
            String name = text.getText().toString();
            if (name.isEmpty()) proper_data = false;
            String email = findViewById(R.id.editTextTextEmailAddress).toString();
            String phone = findViewById(R.id.editTextPhone).toString();
            String postalAddress = findViewById(R.id.editTextTextPostalAddress).toString();
            String dateFrom = findViewById(R.id.editTextDate).toString();
            String dateTo = findViewById(R.id.editTextDate2).toString();
            if(proper_data) {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
                Intent localIntent = new Intent("New Order")
                        .putExtra("name", name)
                        .putExtra("email", email)
                        .putExtra("phone", phone)
                        .putExtra("postalAddress", postalAddress)
                        .putExtra("dateFrom", dateFrom)
                        .putExtra("dateTo", dateTo)
                        .putExtra("product_name", product.getName());
                localBroadcastManager.sendBroadcast(localIntent);
                final Intent send_intent = new Intent(this, ConfirmActivity.class);
                send_intent.putExtra("product", product);
                startActivity(send_intent);
            }
            else Toast.makeText(getApplicationContext(),"Please enter proper data",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Please accept our policy",Toast.LENGTH_LONG).show();
        }
    }
}
