package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivisions;
import Model.Appointments;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
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
import java.time.LocalDateTime;
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
    public static int uniqueID;
    public Button saveAndExitButton;
    public Button updateButton;
    public Button deleteButton;
    public ComboBox<Countries> countryComboBox;
    public ComboBox<FirstLevelDivisions> stateComboBox;
    public int countryID = 0;
    public TableColumn stateColumn;
    private String username;


    public void username(String username){this.username = username;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized");
        customerTable.setItems(DBCustomers.getCustomers());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("Division"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ObservableList<Countries> clist = DBCountries.getCountries();
        uniqueID = DBCustomers.getCustomers().size();
        System.out.println(uniqueID);
        countryComboBox.setItems(clist);
    }
    
    @FXML
    public void Add(ActionEvent actionEvent) throws IOException {
        String name = nameTextField.getText();
        String country = (String)countryComboBox.getSelectionModel().getSelectedItem().toString();
        String state = (String)stateComboBox.getSelectionModel().getSelectedItem().toString();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneNumberTextField.getText();
        String createdBy =  username;
        LocalDateTime createDate = LocalDateTime.now();
        String lastUpdatedBy = username;
        LocalDateTime lastUpdate = LocalDateTime.now();
        int customerID = uniqueID + 1;
        int division = DBFirstLevelDivisions.getDivisionID(stateComboBox.getSelectionModel().getSelectedItem().toString());
        DBCustomers.addCustomer(new Customers(customerID, name, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, division));
        customerTable.setItems(DBCustomers.getCustomers());
        uniqueID = uniqueID +1;
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setContentText("Do you really want to delete this customer?");
        if(alert.showAndWait().get() == ButtonType.OK){
            Customers select = (Customers)customerTable.getSelectionModel().getSelectedItem();
            ObservableList<Appointments> alist = DBAppointments.getAppointments();
            ObservableList<Appointments> clist = FXCollections.observableArrayList();
            int count = 0;
            for(Appointments a : alist){
                if(a.getCustomerID() == select.getCustomerID()){
                    count = count + 1;
                    clist.add(a);
                }
            }
            if (count == 1) {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Appointment Error");
                alert2.setContentText("Appointment exists for this customer. Delete appointment for this customer?");
                if(alert2.showAndWait().get() == ButtonType.OK){
                    for(Appointments c : clist){
                        DBAppointments.deleteAppointment(c);
                    }
                }
            }
            else if(count > 1){
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Appointment Errors");
                alert2.setContentText(count + " appointments exists for this customer. Delete all appointments for this customer?");
                if(alert2.showAndWait().get() == ButtonType.OK){
                    for(Appointments c : clist){
                        DBAppointments.deleteAppointment(c);
                    }
                }
            }
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
        select.setLastUpdate(LocalDateTime.now());
        select.setLastUpdatedBy(username);
        select.setDivisionID(DBFirstLevelDivisions.getDivisionID(stateComboBox.getSelectionModel().getSelectedItem().toString()));
        DBCustomers.updateCustomer(select);
        customerTable.setItems((DBCustomers.getCustomers()));
    }

    @FXML
    public void saveAndExit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Parent root = loader.load();
        MainScreenController controller = loader.getController();
        controller.username(username);
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

    public void divisions(MouseEvent actionEvent) {
        if(countryID == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select from country combo box first.");
            alert.showAndWait();
        }
        else if(countryID == 1){
            System.out.println(countryID);
            ObservableList<FirstLevelDivisions> dlist = DBFirstLevelDivisions.getFirstLevel1();
            stateComboBox.setItems(dlist);
        }
        else if(countryID == 2){
            ObservableList<FirstLevelDivisions> dlist = DBFirstLevelDivisions.getFirstLevel2();
            System.out.println(countryID);
            stateComboBox.setItems(dlist);
        }
        else{
            ObservableList<FirstLevelDivisions> dlist = DBFirstLevelDivisions.getFirstLevel3();
            System.out.println(countryID);
            stateComboBox.setItems(dlist);
        }
    }

    public void selectedCountry(ActionEvent actionEvent) {
        Countries select = (Countries) countryComboBox.getSelectionModel().getSelectedItem();
        countryID = select.getCountryID();
    }
}
