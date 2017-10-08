package com.example.dev.hawaiat.webServices.request;

import java.io.Serializable;

/**
 * Created by medo on 26-Sep-17.
 */

public class LogRequest implements Serializable {
    private String apiToken;
    private String companyID;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
