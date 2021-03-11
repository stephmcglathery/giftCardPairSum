package com.example.demo.model;

import com.opencsv.bean.CsvBindByName;

public class Product  {

    @CsvBindByName
    private String name;

    @CsvBindByName(column = "price")
    private int priceInCents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return priceInCents;
    }

    public void setPrice(int price) {
        this.priceInCents = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + priceInCents +
                '}';
    }
}
