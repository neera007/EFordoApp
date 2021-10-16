package com.example.e_fordoapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("RecID")
    @Expose
    private String recID;
    @SerializedName("ItemCode")
    @Expose
    private String itemCode;
    @SerializedName("ItemBar")
    @Expose
    private String itemBar;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("ItemGroup")
    @Expose
    private String itemGroup;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("IsFraction")
    @Expose
    private String isFraction;
    @SerializedName("IsItem")
    @Expose
    private String isItem;
    @SerializedName("IsAssembledItem")
    @Expose
    private String isAssembledItem;
    @SerializedName("ReOrderQty")
    @Expose
    private String reOrderQty;
    @SerializedName("CurrentQty")
    @Expose
    private String currentQty;
    @SerializedName("MaxQty")
    @Expose
    private String maxQty;
    @SerializedName("ItemImage")
    @Expose
    private String itemImage;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("VatPercent")
    @Expose
    private String vatPercent;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("LastUpdatedBy")
    @Expose
    private String lastUpdatedBy;

    public String getRecID() {
        return recID;
    }

    public void setRecID(String recID) {
        this.recID = recID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemBar() {
        return itemBar;
    }

    public void setItemBar(String itemBar) {
        this.itemBar = itemBar;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIsFraction() {
        return isFraction;
    }

    public void setIsFraction(String isFraction) {
        this.isFraction = isFraction;
    }

    public String getIsItem() {
        return isItem;
    }

    public void setIsItem(String isItem) {
        this.isItem = isItem;
    }

    public String getIsAssembledItem() {
        return isAssembledItem;
    }

    public void setIsAssembledItem(String isAssembledItem) {
        this.isAssembledItem = isAssembledItem;
    }

    public String getReOrderQty() {
        return reOrderQty;
    }

    public void setReOrderQty(String reOrderQty) {
        this.reOrderQty = reOrderQty;
    }

    public String getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(String currentQty) {
        this.currentQty = currentQty;
    }

    public String getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(String maxQty) {
        this.maxQty = maxQty;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
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

}