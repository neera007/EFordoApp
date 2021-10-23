package com.example.e_fordoapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Setting {
    @SerializedName("BaseIP")
    @Expose
    private String baseIP;

    public String getBaseIP() {
        return baseIP;
    }
    public void setBaseIP(String userID) {
        this.baseIP = baseIP;
    }
}
