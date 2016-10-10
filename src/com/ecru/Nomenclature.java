package com.ecru;

/**
 * Created by Vitaliy Ryvakov on 10.10.2016.
 */
public class Nomenclature {
    private  String kod;
    private String name;
    private double price;


    public Nomenclature(String kod, String name, double price) {
        this.kod = kod;
        this.name = name;
        this.price = price;
    }

    public String getKod() {
        return kod;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
