package com.example.dev.hawaiat.webServices.request;

/**
 * Created by medo on 24-Sep-17.
 */

public class RatingRequest {
    private String apiToken;
    private String companyID;
    private String rate;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
