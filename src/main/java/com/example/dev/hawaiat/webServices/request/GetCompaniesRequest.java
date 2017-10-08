package com.example.dev.hawaiat.webServices.request;

/**
 * Created by ahmed on 24/09/17.
 */

public class GetCompaniesRequest {
    private String apiToken;
    private String containerType;
//    private String companyName;
//    private String capacity;

    public GetCompaniesRequest(String apiToken, String containerType) {
        this.apiToken = apiToken;
        this.containerType = containerType;
//        this.companyName=companyName;
//        this.capacity=capacity;
    }
}
