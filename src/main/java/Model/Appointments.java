package Model;

import java.security.Timestamp;
import java.util.Date;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    public Appointments(int appointmentID, String title, String description, String location, String type,
                         int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        //this.start = start;
        //this.end = end;
        //this.createDate = createDate;
        //this.createdBy = createdBy;
        //this.lastUpdate = lastUpdate;
        //this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }
    /**
     * @return appointmentID
     */
    public int getAppointmentID() {return appointmentID;}
    /**
     * @param appointmentID to appointmentID to set
     */
    public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}
    /**
     * @return title
     */
    public String getTitle() {return title;}
    /**
     * @param title to title to set
     */
    public void setTitle(String title){this.title = title;}
    /**
     * @return description
     */
    public String getDescription() {return description;}
    /**
     * @param description to description to set
     */
    public void setDescription(String description){this.description = description;}
    /**
     * @return location
     */
    public String getLocation(){return location;}
    /**
     * @param location to location to set
     */
    public void setLocation(String location) {this.location = location;}
    /**
     * @return type
     */
    public String getType(){return type;}
    /**
     * @param type to type to set
     */
    public void setType(String type) {this.type = type;}
    /**
     * @return start
     */
    public Date getStart() {return start;}
    /**
     * @param start to start to set
     */
    public void setStart(Date start) {this.start = start;}
    /**
     * @return end
     */
    public Date getEnd() {return end;}
    /**
     * @param end to end to set
     */
    public void setEnd(Date end) {this.end = end;}
    /**
     * @return createDate
     */
    public Date getCreateDate() {return createDate;}
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
    public Timestamp getLastUpdate() {return lastUpdate;}
    /**
     * @param lastUpdate to lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {this.lastUpdate = lastUpdate;}
    /**
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy(){return lastUpdatedBy;}
    /**
     * @param lastUpdatedBy to lastUpdatedBy to set
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {this.lastUpdatedBy = lastUpdatedBy;}
    /**
     * @return customerID
     */
    public int getCustomerID() {return customerID;}
    /**
     * @param customerID to customerID to set
     */
    public void setCustomerID(int customerID) {this.customerID = customerID;}
    /**
     * @return userID
     */
    public int getUserID() {return userID;}
    /**
     * @param userID to userID to set
     */
    public void setUserID(int userID) {this.userID = userID;}
    /**
     * @return contactID
     */
    public int getContactID() {return contactID;}
    /**
     * @param contactID to contactID to set
     */
    public void setContactID(int contactID) {this.contactID = contactID;}
}
