package com.example.c195_task1;

import java.security.Timestamp;
import java.util.Date;

public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;

    public Customers(int customerID, String customerName, String address, String postalCode, String phone, Date createDate,
                     String createdBy , Timestamp lastUpdate, String lastUpdatedBy, int divisionID){
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }
    /**
     * @return customerID
     */
    public int getCustomerID(){return customerID;}
    /**
     * @param customerID to customerID to set
     */
    public void setCustomerID(int customerID){this.customerID = customerID;}
    /**
     * @return customerName
     */
    public String getCustomerName(){return customerName;}
    /**
     * @param customerName to customerName to set
     */
    public void setCustomerName(String customerName){this.customerName = customerName;}
    /**
     * @return address
     */
    public String getAddress(){return address;}
    /**
     * @param address to address to set
     */
    public void setAddress(String address){this.address = address;}
    /**
     * @return postalCode
     */
    public String getPostalCode(){return postalCode;}
    /**
     * @param postalCode to postalCode to set
     */
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}
    /**
     * @return phone
     */
    public String getPhone(){return phone;}
    /**
     * @param phone to phone to set
     */
    public void setPhone(String phone){this.phone = phone;}
    /**
     * @return createDate
     */
    public Date getCreateDate(){return createDate;}
    /**
     * @param createDate to createDate to set
     */
    public void setCreateDate(Date createDate) {this.createDate = createDate;}
    /**
     * @return createdBy
     */
    public String getCreatedBy(){return createdBy;}
    /**
     * @param createdBy to createdBy to set
     */
    public void setCreatedBy(String createdBy) {this.createdBy = createdBy;}
    /**
     * @return lastUpdate
     */
    public Timestamp getLastUpdate(){return lastUpdate;}
    /**
     * @param lastUpdate to lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate){this.lastUpdate = lastUpdate;}
    /**
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy(){return lastUpdatedBy;}
    /**
     * @param lastUpdatedBy to lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {this.lastUpdatedBy = lastUpdatedBy;}
    /**
     * @return divisionID
     */
    public int getDivisionID() {return divisionID;}
    /**
     * @param divisionID to divisionID to set
     */
    public void setDivisionID(int divisionID){this.divisionID = divisionID;}
}

