package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by medo on 26-Sep-17.
 */

public class CompanyProfile implements Serializable {

    private int id;
    private String name;
    private String phone;
    private String email;
    private double rate;
    private String logo;
    private List<String> images;
    private List<String> branchPhones;
    private List<Container> containers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getBranchPhones() {
        return branchPhones;
    }

    public void setBranchPhones(List<String> branchPhones) {
        this.branchPhones = branchPhones;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
}
