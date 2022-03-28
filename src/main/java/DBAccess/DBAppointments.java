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
                Appointments a = new Appointments(appointmentID, title, description, location, type, customerID, userID, contactID);
                alist.add(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }
}
