package com.example.e_fordoapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("SoftUser")
    @Expose
    private String softUser;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("LastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("Validated")
    @Expose
    private Boolean validated;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("PasswordWoEncrypt")
    @Expose
    private String passwordWoEncrypt;
    @SerializedName("RememberMe")
    @Expose
    private Boolean rememberMe;

    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSoftUser() {
        return softUser;
    }
    public void setSoftUser(String softUser) {
        this.softUser = softUser;
    }

    public String getActivity() {
        return activity;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Boolean getValidated() {
        return validated;
    }
    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordWoEncrypt() {
        return passwordWoEncrypt;
    }
    public void setPasswordWoEncrypt(String passwordWoEncrypt) {
        this.passwordWoEncrypt = passwordWoEncrypt;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
