package Model;

import java.security.Timestamp;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.Date;
import java.util.zip.DataFormatException;

public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private int countryID;

    /**
     * This is a simple constructor to create a new FirstLevelDivisions object based on divisionID, division, and countryID
     * @param divisionID
     * @param division
     * @param countryID
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID){
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * @return divisionID
     */
    public int getDivisionID(){return divisionID;}

    /**
     * @return division
     */
    public String getDivision(){return division;}

    /**
     * @return countryID
     */
    public int getCountryID(){return countryID;}

    /**
     * This is an override so that the division name will appear in the combobox.
     * @return division
     */
    @Override
    public String toString(){
        return (division);
    }
}
