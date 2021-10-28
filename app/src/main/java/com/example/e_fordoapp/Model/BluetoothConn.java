package com.example.e_fordoapp.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BluetoothConn {
    @SerializedName("logicalName")
    @Expose
    private String logicalName;


    @SerializedName("address")
    @Expose
    private String address;

    public void setLogicalName(String logicalName) {
        this.logicalName = logicalName;
    }

    public String getLogicalName() {
        return logicalName;
    }


    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
