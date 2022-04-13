package DBAccess;

import Database.DBConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

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
                String createdBy = rs.getString("Created_By");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                Appointments a = new Appointments(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy,customerID, userID, contactID);
                alist.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

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
            Timestamp start = Timestamp.valueOf(newAppointment.getStart());
            Timestamp end = Timestamp.valueOf(newAppointment.getEnd());
            Timestamp createDate = Timestamp.valueOf(newAppointment.getCreateDate());
            String createdBy = newAppointment.getCreatedBy();
            Timestamp lastUpdate = Timestamp.valueOf(newAppointment.getLastUpdate());
            String lastUpdatedBy = newAppointment.getLastUpdatedBy();
            String sql = sqlCommand + " '" + appointmentID + "', '" + title + "', '"
                    + description + "', '" + location + "', '" + type + "', '" + start + "', '" + end + "', '"
                   + createDate + "', '" + createdBy + "', '" + lastUpdate + "', '" + lastUpdatedBy + "', '" + customerID + "', '" + userID + "', '" + contactID + "');";
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
            String sqlCommand = "UPDATE Appointments SET Description = '" + selectedAppointment.getDescription() + "', Title = '" + selectedAppointment.getTitle() + "', Location = '" +
                    selectedAppointment.getLocation() + "', Type = '" +selectedAppointment.getType() + "', Start = '" + Timestamp.valueOf(selectedAppointment.getStart()) +
                    "', End = '" + Timestamp.valueOf(selectedAppointment.getEnd()) + "', Last_Update = '" + Timestamp.valueOf(LocalDateTime.now())
                    + "', Last_Updated_By = '" + selectedAppointment.getLastUpdatedBy() + "', Customer_ID = '" + selectedAppointment.getCustomerID() +
                    "', Contact_ID = '" + selectedAppointment.getContactID() + "' WHERE Appointment_ID = '" +
                    selectedAppointment.getAppointmentID() + "';";
            System.out.println(sqlCommand);
            PreparedStatement ps =DBConnection.getConnection().prepareStatement(sqlCommand);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void checkAppointments(){
        ObservableList<Appointments> alist = DBAppointments.getAppointments();
        boolean myAptmt = true;
        for(Appointments a : alist){
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = a.getStart();
            long timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime);
            System.out.println(timeDifference);
            if(timeDifference < 15 && timeDifference >= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Imminent");
                alert.setContentText("Appointment: " + a.getAppointmentID() + " starts at: " + a.getStart());
                alert.showAndWait();
                myAptmt = true;
            }
            else{
                myAptmt = false;
            }
        }
        if(myAptmt == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointment Imminent");
            alert.setContentText("No Appointments within 15 Minutes.");
            alert.showAndWait();
        }
    }
}
