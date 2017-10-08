package com.example.dev.hawaiat.webServices.request;

/**
 * Created by ahmed on 26/09/17.
 */

public class ChangePasswordRequest {
    private String apiToken;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String apiToken, String oldPassword, String newPassword) {
        this.apiToken = apiToken;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
