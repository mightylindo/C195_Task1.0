package com.example.c195_task1;

import DBAccess.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    private String username;

    public void username(String username){this.username = username;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Hello it is initialized!");
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

    @FXML
    public void test(ActionEvent actionEvent){
        /**
        getUser()
            System.out.println("UserID: " + u.getUserID()+ " UserName: " + u.getUserName() + " Password: " + u.getPassword() + " Created By: " + u.getCreateBy() +" Last Update By: " +
                    u.getLastUpdatedBy());
        }
         **/
    }

    @FXML
    public void customer(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
        Parent root = loader.load();
        CustomerScreenController controller = loader.getController();
        controller.username(username);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void appointments(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AppointmentScreen.fxml"));
        Parent root = loader.load();
        AppointmentScreenController controller = loader.getController();
        controller.username(username);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void reports(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportsScreen.fxml"));
        Parent root = loader.load();
        ReportScreenController controller = loader.getController();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }
}
