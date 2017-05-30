package com.example.sonanew.scanner;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SonaNew on 30.05.2017.
 */

public class Data extends RealmObject {
    private String code;
    @PrimaryKey
    private String barcode;
    private String name;
    private int count;
    private int article;
    private double price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
