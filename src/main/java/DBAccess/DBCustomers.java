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
                int division = rs.getInt("Division_ID");
                Customers c = new Customers(customerID, customerName, address, postalCode, phone, division);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clist;
    }
    public static void addCustomer(Customers newCustomer){

        try {
            String sqlCommand = "INSERT INTO Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(";
            int customerID = newCustomer.getCustomerID();
            String customerName = newCustomer.getCustomerName();
            String address = newCustomer.getAddress();
            String postalCode = newCustomer.getPostalCode();
            String phone = newCustomer.getPhone();
            int divisionID = newCustomer.getDivisionID();
            String sql = sqlCommand + "'" + customerID + "', '" +customerName + "', '" + address + "', '" + postalCode + "', '" + phone + "', '" + divisionID + "');";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void deleteCustomer(Customers selectedCustomer){

        try{
            String sqlCommand = "DELETE FROM Customers WHERE Customer_ID = '" + selectedCustomer.getCustomerID() + "';";
            System.out.println(sqlCommand);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateCustomer(Customers selectedCustomer){

        try{
            String sqlCommand = "UPDATE Customers SET Customer_Name = '" + selectedCustomer.getCustomerName() + "', Address = '" + selectedCustomer.getAddress() +
                    "', Postal_Code = '" + selectedCustomer.getPostalCode() + "', Phone = '" + selectedCustomer.getPhone() + "' WHERE Customer_ID = " +
                    selectedCustomer.getCustomerID() + ";";
            System.out.println(sqlCommand);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
