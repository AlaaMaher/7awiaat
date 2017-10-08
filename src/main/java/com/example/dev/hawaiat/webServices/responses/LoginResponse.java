package com.example.dev.hawaiat.webServices.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by medo on 17-Sep-17.
 */

public class LoginResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    //private String ad;
    private Profile profile;
    private List<String> upperSlider;
    private List<String> lowerSlider;


  /*  public String getAd() {
        return ad;
    }*/

    public Profile getProfile() {
        return profile;
    }

    public List<String> getUpperSlider() {
        return upperSlider;
    }

    public void setUpperSlider(List<String> upperSlider) {
        this.upperSlider = upperSlider;
    }

    public List<String> getLowerSlider() {
        return lowerSlider;
    }

    public void setLowerSlider(List<String> lowerSlider) {
        this.lowerSlider = lowerSlider;
    }
/*
    public void setProfile(Profile profile) {
        this.profile = profile;
    }*/

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return " Status : " + getStatus() + " upper slider : " + getUpperSlider();
    }
}
