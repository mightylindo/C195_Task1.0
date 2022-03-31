package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class DBUsers {

    public static ObservableList<Users> getUsers(){
        ObservableList<Users> ulist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM Users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                String createBy = rs.getString("Created_By");
                String lastUpdateBy = rs.getString("Last_Updated_By");
                Users u = new Users(userID, userName, password, createBy, lastUpdateBy);
                ulist.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ulist;
    }

    public static boolean testUsername(String username) {
        boolean valid = false;
        try {
            String sql = "Select * FROM users WHERE User_Name ='" + username + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                String createBy = rs.getString("Created_By");
                String lastUpdateBy = rs.getString("Last_Updated_By");
                Users u = new Users(userID, userName, password, createBy, lastUpdateBy);
                if (u.getUserName().contains(username)) {
                    valid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }
}
