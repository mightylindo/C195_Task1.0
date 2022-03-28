package DBAccess;

import Database.DBConnection;
import java.sql.*;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBAppointments {

    public static ObservableList<Appointments> getAppointments() {
        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM Appointments;";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments a = new Appointments(appointmentID, description, location, type, customerID, userID, contactID);
                alist.add(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static void addAppointment(Appointments newAppointment){
        try{
            String sqlCommand = "INSERT INTO Appointments(Appointment_ID, Description, Location, Type, Customer_ID, User_ID, Contact_ID) VALUES(";
            int appointmentID = newAppointment.getAppointmentID();
            String description = newAppointment.getDescription();
            String location = newAppointment.getLocation();
            String type = newAppointment.getType();
            int customerID = newAppointment.getCustomerID();
            int userID = newAppointment.getUserID();
            int contactID = newAppointment.getContactID();
            String sql = sqlCommand + "'" + appointmentID + "', '" + description + "', '" + location + "', '" + type + "', '" + customerID + "', '" + userID + "', '" + contactID + "');";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
