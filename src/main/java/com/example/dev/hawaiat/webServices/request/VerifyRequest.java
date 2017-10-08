package com.example.dev.hawaiat.webServices.request;

/**
 * Created by medo on 16-Sep-17.
 */

public class VerifyRequest {

    private String phone;
    private String code;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
