package DBAccess;
import Database.DBConnection;
import java.sql.*;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBCustomers {

    public static ObservableList<Customers> getCustomers(){
        ObservableList<Customers> clist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * FROM Customers";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Customers c = new Customers(customerID, customerName, address, postalCode, phone);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return clist;
    }
}
