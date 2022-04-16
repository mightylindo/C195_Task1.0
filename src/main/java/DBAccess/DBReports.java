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

}
