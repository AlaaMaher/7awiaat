package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by medo on 26-Sep-17.
 */

public class CompanyProfile implements Serializable {

    private int id;
    private String name;
    private String phone;
    private String email;
    private double rate;
    private String logo;
    private List<String> images;
    private List<String> branchPhones;
    private List containers;


}
