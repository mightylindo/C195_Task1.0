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

public class Report3Controller implements Initializable {
    public Button returnButton;
    public TableView report3TableView;
    public TableColumn customerNameColumn;
    public TableColumn addressColumn;
    public TableColumn stateColumn;
    public TableColumn postalColumn;
    public TableColumn customerIDColumn;
    public RadioButton usaButton;
    public RadioButton ukButton;
    public RadioButton canadaButton;
    public ToggleGroup countries;

    /**
     * This method initializes the report 3 screen, and sets the tableview.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized");
        report3TableView.setItems(DBReports.getReport3(1));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
        postalColumn.setCellValueFactory(new PropertyValueFactory<>("Postal"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    }

    /**
     * This method is called when the return button is clicked, and loads the report screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void returnR3(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportsScreen.fxml"));
            Parent root = loader.load();
            ReportScreenController controller = loader.getController();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("C195 Task1");
            stage.setScene(scene);
            stage.show();
    }

    /**
     * This method is called when the country radio button is clicked. This will reset the tableview to only show customers from that country.
     * @param actionEvent
     */
    public void countrySelected(ActionEvent actionEvent) {
        if(usaButton.isSelected()){
            report3TableView.setItems(DBReports.getReport3(1));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
            postalColumn.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
        else if(ukButton.isSelected()){
            report3TableView.setItems(DBReports.getReport3(2));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
            postalColumn.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
        else if(canadaButton.isSelected()){
            report3TableView.setItems(DBReports.getReport3(3));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            stateColumn.setCellValueFactory(new PropertyValueFactory<>("State"));
            postalColumn.setCellValueFactory(new PropertyValueFactory<>("Postal"));
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        }
    }
}
