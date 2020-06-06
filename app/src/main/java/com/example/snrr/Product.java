package com.example.snrr;

import android.media.Image;

public class Product {

    private Double price;
    private int imageId;
    private String name;
    private String description;

    public Product(Double price, int img, String name, String description){
        this.imageId = img;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getImage(){
        return this.imageId;
    }

    public Double getPrice(){
        return this.price;
    }

    public String getDescription(){
        return this.description;
    }
}