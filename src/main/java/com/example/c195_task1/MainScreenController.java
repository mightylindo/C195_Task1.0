package com.example.c195_task1;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import Model.Countries;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
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

public class MainScreenController {

    @FXML
    public void test(ActionEvent actionEvent){
       // ObservableList<Countries> clist = DBCountries.getCountries();
        ObservableList<Customers> clist = DBCustomers.getCustomers();
        for(Customers c : clist){
            System.out.println("Customer ID : " + c.getCustomerID() + " Name : " + c.getCustomerName() + " Address: " + c.getAddress() + " Postal code: " + c.getPostalCode() + " phone: " + c.getPhone());
        }
    }

    @FXML
    public void customer(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerScreen.fxml"));
        Parent root = loader.load();
        CustomerScreenController controller = loader.getController();
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
