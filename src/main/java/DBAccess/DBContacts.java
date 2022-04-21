package DBAccess;

import Database.DBConnection;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContacts {
    /**
     * This method is used to pull all contacts from the database and return them in an Observable List of Contacts
     * @return clist
     */
    public static ObservableList<Contacts> getContacts(){
        ObservableList<Contacts> clist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM Contacts;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts c = new Contacts(contactID, contactName, email);
                clist.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clist;
    }

    /**
     * This method takes in an int that is the contact ID and returns the name of that contact.
     * @param contactID
     * @return name
     */
    public static String getContactName(int contactID){
        String name = "";
        try{
            String sql = "SELECT Contact_Name FROM Contacts WHERE Contact_ID ='" + contactID + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("Contact_Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static Contacts getSpecificContact(int contactID){
        try{
            String sql = "SELECT * FROM Contacts WHERE Contact_ID = '" +contactID + "';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               int cID = rs.getInt("Contact_ID");
               String name = rs.getString("Contact_Name");
               String email = rs.getString("Email");
               Contacts c = new Contacts(cID, name, email);
               return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
