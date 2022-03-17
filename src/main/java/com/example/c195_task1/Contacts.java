package com.example.c195_task1;

public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    public Contacts(int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }
    /**
     * @return contactID
     */
    public int getContactID() {return contactID;}
    /**
     * @param contactID to contactID to set
     */
    public void setContactID(int contactID) {this.contactID = contactID;}
    /**
     * @return contactName
     */
    public String getContactName() {return contactName;}
    /**
     * @param contactName to contactName to set
     */
    public void setContactName(String contactName) {this.contactName = contactName;}
    /**
     * @return email
     */
    public String getEmail() {return email;}
    /**
     * @param email to email to set
     */
    public void setEmail(String email) {this.email = email;}
}
