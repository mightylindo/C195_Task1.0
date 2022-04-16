package DBAccess;

import Database.DBConnection;
import Model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class DBReports {

    public static ObservableList<Reports> getReport1(String type){
        ObservableList<Reports> alist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Start FROM Appointments WHERE Type ='" + type + "';";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                Reports r = new Reports(type, start);
                alist.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static ObservableList<Reports> getReport2(int contact){
        ObservableList<Reports> alist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM Appointments WHERE Contact_ID ='" + contact + "';";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int aID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int cID = rs.getInt("Customer_ID");
                Reports r = new Reports(aID, title, type, description, start, end, cID);
                alist.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static ObservableList<Reports> getReport3(int country){
        ObservableList<Reports> alist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Customers.Customer_Name, Customers.Address, Customers.Postal_Code, Customers.Customer_ID, First_Level_Divisions.Division FROM Customers" +
                    " INNER JOIN First_Level_Divisions ON Customers.Division_ID = First_Level_Divisions.Division_ID WHERE First_Level_Divisions.Country_ID = '" + country + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String state = rs.getString("Division");
                String postal = rs.getString("Postal_Code");
                int cID = rs.getInt("Customer_ID");
                Reports r = new Reports(name, address, state, postal, cID);
                alist.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }
}
