package com.example.e_fordoapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("RoleID")
    @Expose
    private String roleID;
    @SerializedName("RoleName")
    @Expose
    private String roleName;
    @SerializedName("ModuleId")
    @Expose
    private String moduleId;
    @SerializedName("Cashregister")
    @Expose
    private String cashregister;
    @SerializedName("CashregisterID")
    @Expose
    private String cashregisterID;
    @SerializedName("Theme")
    @Expose
    private String theme;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;
    @SerializedName("PrinterAddress")
    @Expose
    private String printerAddress;

    @SerializedName("LastLoginDate")
    @Expose
    private String lastLoginDate;
    @SerializedName("LastLoginTime")
    @Expose
    private String lastLoginTime;
    @SerializedName("SalesPermission")
    @Expose
    private String salesPermission;

    @SerializedName("AuditPermission")
    @Expose
    private String auditPermission;

    @SerializedName("BkashWalletNumber")
    @Expose
    private String bkashWalletNumber;

    public String getBkashWalletNumber() {
        return bkashWalletNumber;
    }

    public void setBkashWalletNumber(String bkashWalletNumber) {
        this.bkashWalletNumber = bkashWalletNumber;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getCashregister() {
        return cashregister;
    }

    public void setCashregister(String cashregister) {
        this.cashregister = cashregister;
    }

    public String getCashregisterID() {
        return cashregisterID;
    }

    public void setCashregisterID(String cashregisterID) {
        this.cashregisterID = cashregisterID;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPrinterAddress() {
        return printerAddress;
    }

    public void setPrinterAddress(String printerAddress) {
        this.printerAddress = printerAddress;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSalesPermission() {
        return salesPermission;
    }

    public void setSalesPermission(String salesPermission) {
        this.salesPermission = salesPermission;
    }

    public String getAuditPermission() {
        return auditPermission;
    }

    public void setAuditPermission(String auditPermission) {
        this.auditPermission = auditPermission;
    }
}
