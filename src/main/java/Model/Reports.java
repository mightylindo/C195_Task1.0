package Model;

import java.time.LocalDateTime;
import java.time.Month;

public class Reports {
   private String type;
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
   private int jan = 0;
   private int feb = 0;
   private int mar = 0;
   private int apr = 0;
   private int may = 0;
   private int june = 0;
   private int july = 0;
   private int aug = 0;
   private int sep = 0;
   private int oct = 0;
   private int nov = 0;
   private int dec = 0;


    public Reports(String type, LocalDateTime start){
        this.type = type;
        this.start = start;
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

    public int getJan() {
        return jan;
    }
    public int getFeb() {
        return feb;
    }
    public int getMar() {
        return mar;
    }
    public int getApr() {
        return apr;
    }
    public int getMay() {
        return may;
    }
    public int getJune() {
        return june;
    }
    public int getJuly() {
        return july;
    }
    public int getAug() {
        return aug;
    }
    public int getSep() {
        return sep;
    }
    public int getOct() {
        return oct;
    }
    public int getNov() {
        return nov;
    }
    public int getDec() {
        return dec;
    }
    public void setJan(int jan) {
        this.jan = jan;
    }
    public void setFeb(int feb) {
        this.feb = feb;
    }
    public void setMar(int mar) {
        this.mar = mar;
    }
    public void setApr(int apr) {
        this.apr = apr;
    }
    public void setMay(int may) {
        this.may = may;
    }
    public void setJune(int june) {
        this.june = june;
    }
    public void setJuly(int july) {
        this.july = july;
    }
    public void setAug(int aug) {
        this.aug = aug;
    }
    public void setSep(int sep) {
        this.sep = sep;
    }
    public void setOct(int oct) {
        this.oct = oct;
    }
    public void setNov(int nov) {
        this.nov = nov;
    }
    public void setDec(int dec) {
        this.dec = dec;
    }
    public int getAppointmentID(){return appointmentID;}

    public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}

    public String getTitle(){return title;}

    public void setTitle(String title) {this.title = title;}

    public String getType(){return type;}

    public void setType(String type){this.type = type;}

    public String getDescription(){return description;}

    public void setDescription(String description) {this.description = description;}

    public void setStart(LocalDateTime start) {this.start = start;}

    public LocalDateTime getStart(){return start;}

    public void setEnd(LocalDateTime end) {this.end = end;}

    public LocalDateTime getEnd(){return end;}

    public int getCustomerID() {return customerID;}

    public void setCustomerID(int customerID) {this.customerID = customerID;}

    public String getCustomerName() {return customerName;}

    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getAddress(){return address;}

    public void setAddress(String address) {this.address = address;}

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public String getPostal(){return postal;}

    public void setPostal(String postal) {this.postal = postal;}
}
