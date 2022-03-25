package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBFirstLevelDivisions {
    public static  ObservableList<FirstLevelDivisions> getFirstLevel(){
        ObservableList<FirstLevelDivisions> clist = FXCollections.observableArrayList();

        try{
            String sqlCommand = "SELECT * FROM First_Level_Divisions;";
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
