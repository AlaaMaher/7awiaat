package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;

/**
 * Created by medo on 26-Sep-17.
 */

public class Container implements Serializable {

    private int id;
    private String capacity;
    private double cost;
    private String color;
    private String type;

    public int getId() {
        return id;
    }

    public String getCapacity() {
        return capacity;
    }

    public double getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }
}
