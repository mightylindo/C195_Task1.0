package com.example.c195_task1;


import java.security.Timestamp;
import java.util.Date;

public class User {
    private int userID;
    private String userName;
    private String password;
    private Date createDate;
    private String createBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public User(int userID, String userName, String password, Date createDate, String createBy, Timestamp lastUpdate, String lastUpdatedBy){
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createBy = createBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
