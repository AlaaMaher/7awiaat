package com.example.dev.hawaiat.webServices.responses;

import com.example.dev.hawaiat.models.Company;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ahmed on 24/09/17.
 */

public class GetCompaniesResponse implements Serializable{
    private int status;
    private List<Company> companies;
    private String ad;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
}

