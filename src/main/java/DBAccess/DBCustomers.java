package DBAccess;
import Database.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;

import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBCustomers {

    public static Customers getSpecificCustomer(int customerID){
        try{
            String sql = "SELECT * FROM Customers WHERE  Customer_ID = '" + customerID + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int cID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int division = rs.getInt("Division_ID");
                Customers c = new Customers(cID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, division);
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int division = rs.getInt("Division_ID");
                Customers c = new Customers(customerID, customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, division);
                clist.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clist;
    }
    public static void addCustomer(Customers newCustomer){

        try {
            String sqlCommand = "INSERT INTO Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(";
            int customerID = newCustomer.getCustomerID();
            String customerName = newCustomer.getCustomerName();
            String address = newCustomer.getAddress();
            String postalCode = newCustomer.getPostalCode();
            String phone = newCustomer.getPhone();
            int divisionID = newCustomer.getDivisionID();
            Timestamp createDate =  Timestamp.valueOf(newCustomer.getCreateDate());
            String createdBy = newCustomer.getCreatedBy();
            Timestamp lastUpdate = Timestamp.valueOf(newCustomer.getLastUpdate());
            String lastUpdatedBy = newCustomer.getLastUpdatedBy();
            String sql = sqlCommand + "'" + customerID + "', '" +customerName + "', '" + address + "', '" + postalCode + "', '" + phone + "', '"
                    + createDate + "', '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', '" + divisionID + "');";
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

    public static String getDivision(int divisionID){
        String name = "There is no match.";
        try{
            String sqlCommand = "SELECT Division FROM first_level_divisions WHERE Division_ID = '" + divisionID +"';";
            System.out.println(sqlCommand);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlCommand);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                name = rs.getString("Division");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}
