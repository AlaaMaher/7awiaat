package com.example.dev.hawaiat.models;

public class Containers {


    private String companyname;
    private String logo;
    private float rate;
    private String percentage;

    public Containers() {

    }

    public Containers(String companyname, String logo, float rate, String percentage) {
        this.companyname = companyname;
        this.logo = logo;
        this.rate = rate;
        this.percentage = percentage;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

}

