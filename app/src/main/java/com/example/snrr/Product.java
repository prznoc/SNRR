package com.example.snrr;

import android.media.Image;

public class Product {

    private Double price;
    private Image image;
    private String name;
    private String description;

    public Product(Double price, Image img, String name, String description){
        this.image = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}