package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBFirstLevelDivisions {
    /**
     * This method takes a string division and selects a divisionID from the database based on the division name. It then returns that divisionID.
     * @param Division
     * @return division
     */
    public static int getDivisionID(String Division){
        int division = 0;
        try{
            String sql = "SELECT Division_ID FROM First_Level_Divisions WHERE Division = '" + Division +"';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while ((rs.next())){
                division = rs.getInt("Division_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return division;
    }

    /**
     * This method returns an observable list of first level divisions based on the countryID.
     * @return clist
     */
    public static  ObservableList<FirstLevelDivisions> getFirstLevel1(){
        ObservableList<FirstLevelDivisions> clist = FXCollections.observableArrayList();

        try{
            String sqlCommand = "SELECT * FROM First_Level_Divisions WHERE Country_ID = 1;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
               int divisionID = rs.getInt("Division_ID");
               String division = rs.getString("Division");
               int countryID = rs.getInt("Country_ID");
               FirstLevelDivisions c = new FirstLevelDivisions(divisionID, division, countryID);
               clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return clist;
    }

    /**
     * This method returns an observable list of first level divisions based on the countryID.
     * @return clist
     */
    public static  ObservableList<FirstLevelDivisions> getFirstLevel2(){
        ObservableList<FirstLevelDivisions> clist = FXCollections.observableArrayList();

        try{
            String sqlCommand = "SELECT * FROM First_Level_Divisions WHERE Country_ID = 2;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                FirstLevelDivisions c = new FirstLevelDivisions(divisionID, division, countryID);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }

    /**
     * This method returns an observable list of first level divisions based on the countryID.
     * @return clist
     */
    public static  ObservableList<FirstLevelDivisions> getFirstLevel3(){
        ObservableList<FirstLevelDivisions> clist = FXCollections.observableArrayList();

        try{
            String sqlCommand = "SELECT * FROM First_Level_Divisions WHERE Country_ID = 3;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                FirstLevelDivisions c = new FirstLevelDivisions(divisionID, division, countryID);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }
}
