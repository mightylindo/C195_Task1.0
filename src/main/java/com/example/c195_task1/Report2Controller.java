package com.example.c195_task1;

import DBAccess.DBReports;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Report2Controller implements Initializable {
    public Button returnButton;
    public TableView scheduleTableView;
    public TableColumn appointmentIDColumn;
    public TableColumn titleColumn;
    public TableColumn typeColumn;
    public TableColumn descriptionColumn;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn customerIDColumn;
    public RadioButton contact1Button;
    public ToggleGroup contact;
    public RadioButton contact2Button;
    public RadioButton contact3Button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
       System.out.println("Init");
        scheduleTableView.setItems(DBReports.getReport2(1));
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    }

    @FXML
    public void returnR2(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportsScreen.fxml"));
            Parent root = loader.load();
            ReportScreenController controller = loader.getController();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("C195 Task1");
            stage.setScene(scene);
            stage.show();
    }

    public void contactSelected(ActionEvent actionEvent) {
        if(contact1Button.isSelected()){
            scheduleTableView.setItems(DBReports.getReport2(1));
            appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
        else if(contact2Button.isSelected()){
            scheduleTableView.setItems(DBReports.getReport2(2));
            appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
        else if(contact3Button.isSelected()){
            scheduleTableView.setItems(DBReports.getReport2(3));
            appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
    }
}
