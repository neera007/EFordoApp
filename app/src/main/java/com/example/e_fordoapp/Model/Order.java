package com.example.e_fordoapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Order {

    @SerializedName("OrderID")
    @Expose
    private String orderID;
    @SerializedName("OrderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("DeliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("LastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("OrderDetailInfos")
    @Expose
    private List<Product> orderDetailInfos = null;
    @SerializedName("UserID")
    @Expose
    private String userID;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("ResponseText")
    @Expose
    private String responseText;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OrderAmount")
    @Expose
    private String orderAmount;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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

    public List<Product> getOrderDetailInfos() {
        return orderDetailInfos;
    }
    public void setOrderDetailInfos(List<Product> orderDetailInfos) {
        this.orderDetailInfos = orderDetailInfos;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }
}