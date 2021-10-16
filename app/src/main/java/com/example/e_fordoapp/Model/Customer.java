package com.example.e_fordoapp.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("CategoryID")
    @Expose
    private String categoryID;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Division")
    @Expose
    private String division;
    @SerializedName("Department")
    @Expose
    private String department;
    @SerializedName("SubDepartment")
    @Expose
    private String subDepartment;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Limit")
    @Expose
    private String limit;
    @SerializedName("InvCount")
    @Expose
    private String invCount;
    @SerializedName("AllowLimit")
    @Expose
    private String allowLimit;
    @SerializedName("Activity")
    @Expose
    private String activity;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("LastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("Photo_Image")
    @Expose
    private String photoImage;
    @SerializedName("Signature_Image")
    @Expose
    private String signatureImage;
    @SerializedName("Finger_Image")
    @Expose
    private String fingerImage;
    @SerializedName("RFID_Card")
    @Expose
    private String rFIDCard;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getInvCount() {
        return invCount;
    }

    public void setInvCount(String invCount) {
        this.invCount = invCount;
    }

    public String getAllowLimit() {
        return allowLimit;
    }

    public void setAllowLimit(String allowLimit) {
        this.allowLimit = allowLimit;
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

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }

    public String getSignatureImage() {
        return signatureImage;
    }

    public void setSignatureImage(String signatureImage) {
        this.signatureImage = signatureImage;
    }

    public String getFingerImage() {
        return fingerImage;
    }

    public void setFingerImage(String fingerImage) {
        this.fingerImage = fingerImage;
    }

    public String getRFIDCard() {
        return rFIDCard;
    }

    public void setRFIDCard(String rFIDCard) {
        this.rFIDCard = rFIDCard;
    }

}