package com.example.snrr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class FirstFragment extends Fragment {

    private Product product;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        product = getArguments().getParcelable("product");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public static FirstFragment newInstance(Product product) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", product);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView name = view.findViewById(R.id.textView2);
        TextView price = view.findViewById(R.id.textView3);
        ImageView photo = view.findViewById(R.id.imageView2);
        String _price = String.format("%.2f", product.getPrice()) + "zł/dzień";
        name.setText(product.getName());
        price.setText(_price);

        Picasso.get()
                .load(product.getImage())
                .resize(600,0)
                .onlyScaleDown()
                .into(photo);
    }
}