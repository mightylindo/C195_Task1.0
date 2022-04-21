package Model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * This is a constructor to create a new contact based on contactID, contactName, and email. Currently not used.
     * @param contactID
     * @param contactName
     * @param email
     */
    public Contacts(int contactID, String contactName, String email){
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * This is an override so the contacts name appears in the comboBox
     * @return contactName
     */
    @Override
    public String toString(){return contactName;}
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
