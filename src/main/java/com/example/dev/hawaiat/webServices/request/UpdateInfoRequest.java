package com.example.dev.hawaiat.webServices.request;


public class UpdateInfoRequest {//
    private String apiToken;
    private String name;
    private int phone;
    private String photo;

    public UpdateInfoRequest(String apiToken, String name, int phone, String photo) {
        this.apiToken = apiToken;
        this.name = name;
        this.phone = phone;
        this.photo = photo;
    }

}
