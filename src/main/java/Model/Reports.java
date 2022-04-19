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

    /**
     * This is an overloaded constructor for the reports object that takes the below parameters.
     * @param type
     * @param start
     */
    public Reports(String type, LocalDateTime start){
        this.type = type;
        this.start = start;
    }

    /**
     * This is an overloaded constructor for the reports object that takes the below parameters.
     * @param appointmentID
     * @param title
     * @param type
     * @param description
     * @param start
     * @param end
     * @param customerID
     */
    public Reports(int appointmentID, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
    }

    /**
     * This is an overloaded constructor for the reports object that takes the below parameters.
     * @param customerName
     * @param address
     * @param state
     * @param postal
     * @param customerID
     */
    public Reports(String customerName, String address, String state, String postal, int customerID){
        this.customerName = customerName;
        this.address = address;
        this.state = state;
        this.postal = postal;
        this.customerID = customerID;
    }

    /**
     * @return jan
     */
    public int getJan() {
        return jan;
    }
    /**
     * @return feb
     */
    public int getFeb() {
        return feb;
    }
    /**
     * @return mar
     */
    public int getMar() {
        return mar;
    }
    /**
     * @return apr
     */
    public int getApr() {
        return apr;
    }
    /**
     * @return may
     */
    public int getMay() {
        return may;
    }
    /**
     * @return june
     */
    public int getJune() {
        return june;
    }
    /**
     * @return july
     */
    public int getJuly() {
        return july;
    }
    /**
     * @return aug
     */
    public int getAug() {
        return aug;
    }
    /**
     * @return sep
     */
    public int getSep() {
        return sep;
    }
    /**
     * @return oct
     */
    public int getOct() {
        return oct;
    }
    /**
     * @return nov
     */
    public int getNov() {
        return nov;
    }
    /**
     * @return dec
     */
    public int getDec() {
        return dec;
    }
    /**
     * @param jan to set jan
     */
    public void setJan(int jan) {
        this.jan = jan;
    }
    /**
     * @param feb to set feb
     */
    public void setFeb(int feb) {
        this.feb = feb;
    }
    /**
     * @param mar to set mar
     */
    public void setMar(int mar) {
        this.mar = mar;
    }
    /**
     * @param apr to set apr
     */
    public void setApr(int apr) {
        this.apr = apr;
    }
    /**
     * @param may to set may
     */
    public void setMay(int may) {
        this.may = may;
    }
    /**
     * @param june to set june
     */
    public void setJune(int june) {
        this.june = june;
    }
    /**
     * @param july to set july
     */
    public void setJuly(int july) {
        this.july = july;
    }
    /**
     * @param aug to set aug
     */
    public void setAug(int aug) {
        this.aug = aug;
    }
    /**
     * @param sep to set sep
     */
    public void setSep(int sep) {
        this.sep = sep;
    }
    /**
     * @param oct to set oct
     */
    public void setOct(int oct) {
        this.oct = oct;
    }
    /**
     * @param nov to set nov
     */
    public void setNov(int nov) {
        this.nov = nov;
    }
    /**
     * @param dec to set dec
     */
    public void setDec(int dec) {
        this.dec = dec;
    }

    /**
     * @return appointmentID
     */
    public int getAppointmentID(){return appointmentID;}

    /**
     * @param appointmentID set appointmentID
     */
    public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}

    /**
     * @return title
     */
    public String getTitle(){return title;}

    /**
     * @param title to set title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * @return type
     */
    public String getType(){return type;}

    /**
     * @param type set type
     */
    public void setType(String type){this.type = type;}

    /**
     * @return description
     */
    public String getDescription(){return description;}

    /**
     * @param description set description
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * @param start set start
     */
    public void setStart(LocalDateTime start) {this.start = start;}

    /**
     * @return start
     */
    public LocalDateTime getStart(){return start;}

    /**
     * @param end set end
     */
    public void setEnd(LocalDateTime end) {this.end = end;}

    /**
     * @return end
     */
    public LocalDateTime getEnd(){return end;}

    /**
     * @return customerID
     */
    public int getCustomerID() {return customerID;}

    /**
     * @param customerID set customerID
     */
    public void setCustomerID(int customerID) {this.customerID = customerID;}

    /**
     * @return customerName
     */
    public String getCustomerName() {return customerName;}

    /**
     * @param customerName to set customerName
     */
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    /**
     * @return address
     */
    public String getAddress(){return address;}

    /**
     * @param address to set address
     */
    public void setAddress(String address) {this.address = address;}

    /**
     * @return state
     */
    public String getState() {return state;}

    /**
     * @param state to set state
     */
    public void setState(String state) {this.state = state;}

    /**
     * @return postal
     */
    public String getPostal(){return postal;}

    /**
     * @param postal to set postal
     */
    public void setPostal(String postal) {this.postal = postal;}
}
