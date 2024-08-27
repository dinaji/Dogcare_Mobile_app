package com.example.dogcare;



public class product2 {
    private String name;
    private String price;
    private int imageResourceId;

    public product2(String name, String price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}


