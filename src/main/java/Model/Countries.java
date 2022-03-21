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

    public Countries(int countryID, String country, Date createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy){
        this.countryID = countryID;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
