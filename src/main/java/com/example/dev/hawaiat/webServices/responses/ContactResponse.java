package com.example.dev.hawaiat.webServices.responses;

/**
 * Created by ahmed on 01/10/17.
 */

public class ContactResponse {
    private int status;
    private String email;
    private String[] phones;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }
}
