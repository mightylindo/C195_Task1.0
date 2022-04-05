package DBAccess;

import Database.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;

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
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                Appointments a = new Appointments(appointmentID, description, location, type, start, end, customerID, userID, contactID);
                alist.add(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    public static void addAppointment(Appointments newAppointment){
        try{
            String sqlCommand = "INSERT INTO Appointments(Appointment_ID, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(";
            int appointmentID = newAppointment.getAppointmentID();
            String description = newAppointment.getDescription();
            String location = newAppointment.getLocation();
            String type = newAppointment.getType();
            int customerID = newAppointment.getCustomerID();
            int userID = newAppointment.getUserID();
            int contactID = newAppointment.getContactID();
            Timestamp start = Timestamp.valueOf(newAppointment.getStart());
            Timestamp end = Timestamp.valueOf(newAppointment.getEnd());
            String sql = sqlCommand + "'" + appointmentID + "', '" + description + "', '" + location + "', '" + type + "', '" + start + "', '" + end + "', '"
                    + customerID + "', '" + userID + "', '" + contactID + "');";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(Appointments selectedAppointment){
        try{
            String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = "+ selectedAppointment.getAppointmentID() + ";";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointments selectedAppointment){
        try{
            String sqlCommand = "UPDATE Appointments SET Description = '" + selectedAppointment.getDescription() + "', Location = '" +
                    selectedAppointment.getLocation() + "', Type = '" +selectedAppointment.getType() + "', Contact_ID = '" + selectedAppointment.getContactID() + "' WHERE Appointment_ID = '" +
                    selectedAppointment.getAppointmentID() + "';";
            System.out.println(sqlCommand);
            PreparedStatement ps =DBConnection.getConnection().prepareStatement(sqlCommand);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
