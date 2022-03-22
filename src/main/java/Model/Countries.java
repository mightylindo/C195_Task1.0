package Model;

import java.security.Timestamp;
import java.util.Date;

public class Countries {
    private int countryID;
    private String country;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    public Countries(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
        //this.createDate = createDate;
        //this.createdBy = createdBy;
        //this.lastUpdated = lastUpdated;
        //this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getCountryID(){
        return countryID;
    }
    public String getCountry(){
        return country;
    }
}
