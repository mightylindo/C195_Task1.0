package Model;

import java.security.Timestamp;
import java.util.Date;

public class Countries {
    private int countryID;
    private String country;

    /**
     * This is a simple constructor that creates a country object based on countryID and country.
     * @param countryID
     * @param country
     */
    public Countries(int countryID, String country){
        this.countryID = countryID;
        this.country = country;

    }

    /**
     * @return countryID
     */
    public int getCountryID(){
        return countryID;
    }

    /**
     * @return country
     */
    public String getCountry(){
        return country;
    }

    /**
     * This is an override so the country name appears in the combobox.
     * @return country
     */
    @Override
    public String toString(){
        return (country);
    }
}
