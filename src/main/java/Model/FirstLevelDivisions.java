package Model;

import java.security.Timestamp;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.Date;
import java.util.zip.DataFormatException;

public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int countryID;

    public FirstLevelDivisions(int divisionID, String division, int countryID){
        this.divisionID = divisionID;
        this.division = division;
        //this.createDate = createDate;
        //this.createdBy = createdBy;
        //this.lastUpdate = lastUpdate;
        //this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }
    public int getDivisionID(){return divisionID;}
    public String getDivision(){return division;}
    public int getCountryID(){return countryID;}

    @Override
    public String toString(){
        return (division);
    }
}
