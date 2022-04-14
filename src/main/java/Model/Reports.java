package Model;

import java.time.LocalDateTime;

public class Reports {
   private String type;
   private Customers customer;
   private int appointmentID;
   private String title;
   private String description;
   private LocalDateTime start;
   private LocalDateTime end;
   private int customerID;
   private String customerName;
   private String address;
   private String state;
   private String postal;


    public Reports(String type, Customers customer){
        this.customer = customer;
        this.type = type;
    }

    public Reports(int appointmentID, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
    }

    public Reports(String customerName, String address, String state, String postal, int customerID){
        this.customerName = customerName;
        this.address = address;
        this.state = state;
        this.postal = postal;
        this.customerID = customerID;
    }

}