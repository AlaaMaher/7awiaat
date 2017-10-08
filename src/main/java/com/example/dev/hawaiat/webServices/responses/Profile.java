package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;

/**
 * Created by medo on 17-Sep-17.
 */

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String apiToken;
    private String name;
    private String phone;
    private String photo;


    public String getApiToken() {
        return apiToken;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return " apiToken : " + apiToken + " name : " + name + " phone : " + phone + " photo  : " + photo;
    }
}
