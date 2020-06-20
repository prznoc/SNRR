package com.example.snrr;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    private Product product;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        product = getArguments().getParcelable("product");
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public static SecondFragment newInstance(Product product) {
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("product", product);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView description = view.findViewById(R.id.textView4);
        description.setText(product.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
    }
}