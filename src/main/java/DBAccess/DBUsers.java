package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class DBUsers {

    /**
     * This method takes a string username and gets a customer from the database based on the username and returns a userID.
     * @param userName
     * @return userID
     */
    public static int getUser(String userName){
        int userID = 0;
        try {
            String sql = "Select * FROM users WHERE User_Name ='" + userName + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userID = rs.getInt("User_ID");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userID;
    }

    /**
     * This method returns an ObservableList of Users. This pulls all users from the database and adds them to the list which it then returns.
     * @return ulist
     */
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

    /**
     * This method tests a username and password combination. The method selects a user from the database based on the username. It then tests that the username and password match and returns
     * a true value.
     * @param username
     * @param password
     * @return valid
     */
    public static boolean testUsername(String username, String password) {
        boolean valid = false;
        try {
            String sql = "Select * FROM users WHERE User_Name ='" + username + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String passWord = rs.getString("Password");
                String createBy = rs.getString("Created_By");
                String lastUpdateBy = rs.getString("Last_Updated_By");
                Users u = new Users(userID, userName, passWord, createBy, lastUpdateBy);
                if (u.getUserName().contains(username) && u.getPassword().contains(password)) {
                    valid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valid;
    }
}
