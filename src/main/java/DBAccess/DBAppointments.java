package DBAccess;

import Database.DBConnection;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class DBAppointments {

    /**
     * This getAppointments method is used to get just the appointments for a specific user. It takes in a userID and it returns an observable list of all that users appointments.
     * @param userID
     * @return Observable List of appointments for a specific user
     */
    public static ObservableList<Appointments> getAppointments(int userID) {
        ObservableList<Appointments> alist = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Appointments WHERE User_ID ='" + userID +"';";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int customerID = rs.getInt("Customer_ID");
                int user = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                ZonedDateTime zstart = start.atZone(ZoneId.systemDefault());
                ZonedDateTime zend =end.atZone(ZoneId.systemDefault());
                String createdBy = rs.getString("Created_By");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                Appointments a = new Appointments(appointmentID, title, description, location, type, zstart, zend, createDate, createdBy, lastUpdate, lastUpdatedBy,customerID, user, contactID);
                alist.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }
    /**
     * This method returns an ObserverableList of Appointments by selecting all appointments from the database.
     * @return alist
     */
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
                ZonedDateTime zstart = start.atZone(ZoneId.systemDefault());
                ZonedDateTime zend =end.atZone(ZoneId.systemDefault());
                String createdBy = rs.getString("Created_By");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                Appointments a = new Appointments(appointmentID, title, description, location, type, zstart, zend, createDate, createdBy, lastUpdate, lastUpdatedBy,customerID, userID, contactID);
                alist.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    /**
     * This method adds a new appointment to the database. An appointments object is passed in and then the objects values are inserted into the appointments table in the database.
     * @param newAppointment
     */
    public static void addAppointment(Appointments newAppointment){
        try{
            String sqlCommand = "INSERT INTO Appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, " +
                    "Last_Update, Last_Updated_By,Customer_ID, User_ID, Contact_ID) VALUES(";
            int appointmentID = newAppointment.getAppointmentID();
            String title = newAppointment.getTitle();
            String description = newAppointment.getDescription();
            String location = newAppointment.getLocation();
            String type = newAppointment.getType();
            int customerID = newAppointment.getCustomerID();
            int userID = newAppointment.getUserID();
            int contactID = newAppointment.getContactID();
            Timestamp start = Timestamp.valueOf(newAppointment.getStart().toLocalDateTime());
            Timestamp end = Timestamp.valueOf(newAppointment.getEnd().toLocalDateTime());
            Timestamp createDate = Timestamp.valueOf(newAppointment.getCreateDate());
            String createdBy = newAppointment.getCreatedBy();
            Timestamp lastUpdate = Timestamp.valueOf(newAppointment.getLastUpdate());
            String lastUpdatedBy = newAppointment.getLastUpdatedBy();
            String sql = sqlCommand + " '" + appointmentID + "', '" + title + "', '"
                    + description + "', '" + location + "', '" + type + "', " + "?" + ", " + "?" + ", '"
                   + createDate + "', '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', '" + customerID + "', '" + userID + "', '" + contactID + "');";
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
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

    /**
     * This method updates a specific appointment in the database. An appointments object is passed in and the values in the database are overritten by the new values
     * for a specific appointment based on the appointmentID.
     * @param selectedAppointment
     */
    public static void updateAppointment(Appointments selectedAppointment){
        try{
            Timestamp start = Timestamp.valueOf(selectedAppointment.getStart().toLocalDateTime());
            Timestamp end = Timestamp.valueOf(selectedAppointment.getEnd().toLocalDateTime());
            String sqlCommand = "UPDATE Appointments SET Description = '" + selectedAppointment.getDescription() + "', Title = '" + selectedAppointment.getTitle() + "', Location = '" +
                    selectedAppointment.getLocation() + "', Type = '" +selectedAppointment.getType() + "', Start = ?"  +
                    ", End = ?" +  ", Last_Update = '" + Timestamp.valueOf(LocalDateTime.now())
                    + "', Last_Updated_By = '" + selectedAppointment.getLastUpdatedBy() + "', Customer_ID = '" + selectedAppointment.getCustomerID() + "', User_ID = '"
                    + selectedAppointment.getUserID() + "', Contact_ID = '" + selectedAppointment.getContactID() + "' WHERE Appointment_ID = '" +
                    selectedAppointment.getAppointmentID() + "';";
            System.out.println(sqlCommand);
            PreparedStatement ps =DBConnection.getConnection().prepareStatement(sqlCommand);
            ps.setTimestamp(1, start);
            ps.setTimestamp(2, end);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
