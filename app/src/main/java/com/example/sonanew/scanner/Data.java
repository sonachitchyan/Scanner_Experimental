package com.example.sonanew.scanner;

/**
 * Created by SonaNew on 25.05.2017.
 */

public class Data {
    private String barcode;
    private int count;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Data(String barcode, int count) {
        this.barcode = barcode;
        this.count = count;
    }

    public Data() {
    }
}
