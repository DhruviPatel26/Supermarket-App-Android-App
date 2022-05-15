package com.all_product.commerce.Models;

import java.io.Serializable;

public class ItemProduct implements Serializable {

    private String id, name, description, price, categoryName, qty = "1";
    private int imageId;

    public ItemProduct(String id, String name, String description, String price, String categoryName, int imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.imageId = imageId;
    }

    public ItemProduct(String id, String name, String description, String price, String categoryName, String qty, int imageId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.qty = qty;
        this.imageId = imageId;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getQty() {
        return qty;
    }

    public String getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getImageId() {
        return imageId;
    }
}
