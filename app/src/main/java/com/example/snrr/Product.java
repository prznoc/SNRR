package com.example.snrr;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

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

    protected Product(Parcel in) {
        price = in.readDouble();
        imageId = in.readInt();
        name  = in.readString();
        description = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(price);
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeString(description);
    }
}