package com.example.dev.hawaiat.models;

import java.io.Serializable;

/**
 * Created by medo on 16-Sep-17.
 */

public class HeaderDrawerModel implements Serializable {

    private String mName;
    private String navImage;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getNavImage() {
        return navImage;
    }

    public void setNavImage(String navImage) {
        this.navImage = navImage;
    }
}
