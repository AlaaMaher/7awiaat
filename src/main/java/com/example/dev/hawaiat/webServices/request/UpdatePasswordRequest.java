package com.example.dev.hawaiat.webServices.request;

/**
 * Created by ahmed on 01/10/17.
 */

public class UpdatePasswordRequest {
    private int tmpToken;
    private int password;

    public UpdatePasswordRequest(int tmpToken, int password) {
        this.tmpToken = tmpToken;
        this.password = password;
    }
}
