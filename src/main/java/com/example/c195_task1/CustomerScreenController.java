package com.example.c195_task1;
import DBAccess.DBCustomers;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class CustomerScreenController implements Initializable {
    public TableView customerTable;
    public TableColumn nameColumn;
    public TableColumn addressColumn;
    public TableColumn postalCodeColumn;
    public TableColumn phoneColumn;
    public TableColumn customerIDColumn;
    public TextField nameTextField;
    public TextField addressTextField;
    public TextField postalCodeTextField;
    public TextField phoneNumberTextField;
    public TextField customerIDTextField;
    public Button addButton;
    public static int uniqueID = 3;
    public static int division = 4;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized");
        customerTable.setItems(DBCustomers.getCustomers());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    }
    
    @FXML
    public void Add(ActionEvent actionEvent) throws IOException {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneNumberTextField.getText();
        int customerID = uniqueID + 1;
        int divisionID = division + 1;
        DBCustomers.addCustomer(new Customers(customerID, name, address, postalCode, phone, divisionID));
        customerTable.setItems(DBCustomers.getCustomers());
        uniqueID = uniqueID +1;
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        Customers select = (Customers)customerTable.getSelectionModel().getSelectedItem();
        DBCustomers.deleteCustomer(select);
        customerTable.setItems(DBCustomers.getCustomers());
    }

    @FXML
    public void saveAndExit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent root = loader.load();
        MainScreenController controller = loader.getController();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }
}
