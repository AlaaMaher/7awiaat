package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by medo on 25-Sep-17.
 */

public class CompaniesResponse implements Serializable {

    private int status;
    private List<Company> companies;
    private String ad;

    public int getStatus() {
        return status;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public String getAd() {
        return ad;
    }
}
