package com.example.dev.hawaiat.webServices.request;

/**
 * Created by medo on 25-Sep-17.
 */

public class CompaniesRequest {

    private String apiToken;
    private String containerType;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }
}
