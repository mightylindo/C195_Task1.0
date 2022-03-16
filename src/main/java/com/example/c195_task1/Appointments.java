package com.example.c195_task1;

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

    public Appointments(int appointmentID, String title, String description, String location, String type, Date start, Date end, Date createDate,
                        String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID, int contactID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.description =description;
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
}
