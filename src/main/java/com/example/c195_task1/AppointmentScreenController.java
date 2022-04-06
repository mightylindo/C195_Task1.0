package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {
    public Label titleLabel;
    public TableColumn appointmentIDColumn;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn contactColumn;
    public TableColumn typeColumn;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn customerIDColumn;
    public TableColumn userIDColumn;
    public Button addButton;
    public Button updateButton;
    public Button deleteButton;
    public Button saveAndExitButton;
    public RadioButton weeklyRadioButton;
    public RadioButton monthlyRadioButton;
    public ComboBox customerSelectComboBox;
    public TextField appointmentIDTextField;
    public TextField locationTextField;
    public TextField contactTextField;
    public TextField startDateAndTimeTextField;
    public TextField endDateAndTimeTextField;
    public TextField customerIDTextField;
    public TextField userIDTextField;
    public TextField typeTextField;
    public TextField appointmentDescriptionTextField;
    public TableView appointmentsTableview;
    public ObservableList<Appointments> alist = DBAppointments.getAppointments();
    public int aID = alist.size() + 1;
    private String username;

    public void username(String username){this.username = username;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("initialized!");
        appointmentsTableview.setItems(DBAppointments.getAppointments());
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ObservableList<Customers> clist = DBCustomers.getCustomers();
        customerSelectComboBox.setItems(clist);
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
    @FXML
    public void addButton(ActionEvent actionEvent) throws IOException{
        Customers customer = (Customers) customerSelectComboBox.getSelectionModel().getSelectedItem();
        int customerID = customer.getCustomerID();
        String description = appointmentDescriptionTextField.getText();
        String location = locationTextField.getText();
        int contactID = Integer.parseInt(contactTextField.getText());
        String type = typeTextField.getText();
        LocalDateTime start = LocalDateTime.parse(startDateAndTimeTextField.getText());
        LocalDateTime end = LocalDateTime.parse(endDateAndTimeTextField.getText());
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = username;
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdatedBy = username;
        int userID = DBUsers.getUser(username);
        int appointmentID = aID;
        DBAppointments.addAppointment(new Appointments(appointmentID, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID , userID, contactID));
        appointmentsTableview.setItems(DBAppointments.getAppointments());
        aID = aID + 1;


    }
    @FXML
    public void deleteButton(ActionEvent actionEvent) throws IOException {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        DBAppointments.deleteAppointment(select);
        appointmentsTableview.setItems(DBAppointments.getAppointments());
    }
    @FXML
    public void updateButton(ActionEvent actionEvent) throws IOException {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        select.setDescription(appointmentDescriptionTextField.getText());
        select.setLocation(locationTextField.getText());
        select.setType(typeTextField.getText());
        select.setContactID(Integer.parseInt(contactTextField.getText()));
        DBAppointments.updateAppointment(select);
        appointmentsTableview.setItems(DBAppointments.getAppointments());
    }

    public void selectAppointment(MouseEvent mouseEvent) {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        appointmentIDTextField.setText(Integer.toString(select.getAppointmentID()));
        appointmentDescriptionTextField.setText(select.getDescription());
        locationTextField.setText(select.getLocation());
        typeTextField.setText(select.getType());
        contactTextField.setText(Integer.toString(select.getContactID()));
        customerIDTextField.setText(Integer.toString(select.getCustomerID()));
        userIDTextField.setText(Integer.toString(select.getUserID()));
        // startDateAndTimeTextField.setText; //Need to figure out the formatter to get this to work
        Customers c = DBCustomers.getSpecificCustomer(select.getCustomerID());
        customerSelectComboBox.setValue(c);
    }
}
