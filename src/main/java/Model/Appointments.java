package Model;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private ZonedDateTime zstart;
    private ZonedDateTime zend;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate,
                         String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy,int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }
    public Appointments(int appointmentID, String title, String description, String location, String type, ZonedDateTime start, ZonedDateTime end, LocalDateTime createDate,
                        String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.zstart = start;
        this.zend = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
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
    public LocalDateTime getStart() {return start;}
    /**
     * @param start to start to set
     */
    public void setStart(LocalDateTime start) {this.start = start;}
    /**
     * @return end
     */
    public LocalDateTime getEnd() {return end;}
    /**
     * @param end to end to set
     */
    public void setEnd(LocalDateTime end) {this.end = end;}
    /**
     * @return createDate
     */
    public LocalDateTime getCreateDate() {return createDate;}
    /**
     * @param createDate to createDate to set
     */
    public void setCreateDate(LocalDateTime createDate) {this.createDate = createDate;}
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
    public LocalDateTime getLastUpdate() {return lastUpdate;}
    /**
     * @param lastUpdate to lastUpdate to set
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {this.lastUpdate = lastUpdate;}
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
    /**
     * @param zstart to zstart to set
     */
    public void setZstart(ZonedDateTime zstart) {this.zstart = zstart;}
    /**
     * @return zstart
     */
    public ZonedDateTime getZstart() {return zstart;}
    /**
     * @param zend to zend to set
     */
    public void setZend(ZonedDateTime zend) {this.zend = zend;}
    /**
     * @return zend
     */
    public ZonedDateTime getZend() {return zend;}
}
