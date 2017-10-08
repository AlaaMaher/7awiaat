package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;

/**
 * Created by medo on 25-Sep-17.
 */

public class Company implements Serializable {
    private int id;
    private String name;
    private double rate;
    private String logo;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getLogo() {
        return logo;
    }
}
