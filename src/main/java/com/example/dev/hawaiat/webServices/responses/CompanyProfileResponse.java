package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;

/**
 * Created by medo on 26-Sep-17.
 */

public class CompanyProfileResponse implements Serializable {

    private int status;
    private CompanyProfile company;

    public int getStatus() {
        return status;
    }

    public CompanyProfile getCompany() {
        return company;
    }
}

