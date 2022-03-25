package com.example.c195_task1;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import Model.Countries;
import Model.Customers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
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
    public static int division = 1;
    public Button saveAndExitButton;
    public Button updateButton;
    public Button deleteButton;
    public ComboBox<Countries> countryComboBox;
    public ComboBox stateComboBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized");
        customerTable.setItems(DBCustomers.getCustomers());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ObservableList<Countries> clist = DBCountries.getCountries();
        countryComboBox.setItems(clist);
    }
    
    @FXML
    public void Add(ActionEvent actionEvent) throws IOException {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneNumberTextField.getText();
        int customerID = uniqueID + 1;
        DBCustomers.addCustomer(new Customers(customerID, name, address, postalCode, phone, division));
        customerTable.setItems(DBCustomers.getCustomers());
        uniqueID = uniqueID +1;
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setContentText("Do you really want to delete this customer?");

        //Need to add a protection against deleting a customer that has an appointment
        if(alert.showAndWait().get() == ButtonType.OK){
            Customers select = (Customers)customerTable.getSelectionModel().getSelectedItem();
            DBCustomers.deleteCustomer(select);
            customerTable.setItems(DBCustomers.getCustomers());
        }

    }

    @FXML
    public void update(ActionEvent actionEvent) throws IOException {
        Customers select = (Customers)customerTable.getSelectionModel().getSelectedItem();
        select.setCustomerName(nameTextField.getText());
        select.setAddress(addressTextField.getText());
        select.setPostalCode(postalCodeTextField.getText());
        select.setPhone(phoneNumberTextField.getText());
        DBCustomers.updateCustomer(select);
        customerTable.setItems((DBCustomers.getCustomers()));
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

    public void select(MouseEvent mouseEvent) {
        Customers select = (Customers)customerTable.getSelectionModel().getSelectedItem();
        customerIDTextField.setText(Integer.toString(select.getCustomerID()));
        nameTextField.setText(select.getCustomerName());
        addressTextField.setText(select.getAddress());
        postalCodeTextField.setText(select.getPostalCode());
        phoneNumberTextField.setText(select.getPhone());
    }
}
