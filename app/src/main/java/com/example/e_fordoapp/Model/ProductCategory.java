package com.example.e_fordoapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCategory {

    @SerializedName("ItemGroupID")
    @Expose
    private String itemGroupID;
    @SerializedName("RootItemGroupID")
    @Expose
    private String rootItemGroupID;
    @SerializedName("ItemGroupLevel")
    @Expose
    private String itemGroupLevel;
    @SerializedName("ItemGroupIndex")
    @Expose
    private String itemGroupIndex;
    @SerializedName("ItemGroupName")
    @Expose
    private String itemGroupName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("LastUpdatedBy")
    @Expose
    private String lastUpdatedBy;

    public String getItemGroupID() {
        return itemGroupID;
    }

    public void setItemGroupID(String itemGroupID) {
        this.itemGroupID = itemGroupID;
    }

    public String getRootItemGroupID() {
        return rootItemGroupID;
    }

    public void setRootItemGroupID(String rootItemGroupID) {
        this.rootItemGroupID = rootItemGroupID;
    }

    public String getItemGroupLevel() {
        return itemGroupLevel;
    }

    public void setItemGroupLevel(String itemGroupLevel) {
        this.itemGroupLevel = itemGroupLevel;
    }

    public String getItemGroupIndex() {
        return itemGroupIndex;
    }

    public void setItemGroupIndex(String itemGroupIndex) {
        this.itemGroupIndex = itemGroupIndex;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
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