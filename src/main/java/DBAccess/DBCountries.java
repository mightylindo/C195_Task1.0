package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class DBCountries {

    public static ObservableList<Countries> getCountries(){
        ObservableList<Countries> clist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM Countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries c = new Countries(countryID, countryName);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clist;
    }
}
