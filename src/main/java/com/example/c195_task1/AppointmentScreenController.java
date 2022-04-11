package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Customers;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public ToggleGroup DateRange;
    private String username;
    //private LocalDateTime open = LocalDateTime.of(LocalDate.now(), LocalTime.of(8,00));
    private LocalTime open = LocalTime.of(8,00);
    //private LocalDateTime close = LocalDateTime.of(LocalDate.now(), LocalTime.of(22,00));
    private LocalTime close = LocalTime.of(22,00);
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
        boolean bhours = false;
        LocalDateTime start = LocalDateTime.parse(startDateAndTimeTextField.getText());
        if(start.toLocalTime().isBefore(open) || start.toLocalTime().isAfter(close)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment outside business hours. Please select hours within the hours of 8AM and 10PM.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        LocalDateTime end = LocalDateTime.parse(endDateAndTimeTextField.getText());
        if(end.toLocalTime().isBefore(open) || end.toLocalTime().isAfter(close)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment outside business hours. Please select hours within the hours of 8AM and 10PM.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        ObservableList<Appointments> alist = DBAppointments.getAppointments();
        boolean noOverlap = false;
        for(Appointments a : alist){
            if(a.getCustomerID() == customerID){
                if((start.isAfter(a.getStart()) || start.isEqual(a.getStart())) && start.isBefore(a.getEnd())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap1: Please select a different time.");
                    alert.showAndWait();
                }
                else if(end.isAfter(a.getStart()) && (end.isBefore(a.getEnd()) || end.isEqual(a.getEnd()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap2: Please select a different time.");
                    alert.showAndWait();
                }
                else if((start.isBefore(a.getStart()) || start.isEqual(a.getStart())) && (end.isAfter(a.getEnd()) || end.isEqual(a.getEnd()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap3: Please select a different time.");
                    alert.showAndWait();
                }
            }
            else{
                noOverlap = true;
            }
        }
        if(noOverlap == true && bhours == true){ //still has some errors let the system create an appointment even though noOverlap was false.
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = username;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = username;
            int userID = DBUsers.getUser(username);
            System.out.println(userID);
            int appointmentID = aID;
            DBAppointments.addAppointment(new Appointments(appointmentID, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID , userID, contactID));
            appointmentsTableview.setItems(DBAppointments.getAppointments());
            aID = aID + 1;
        }

    }

    @FXML
    public void deleteButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setContentText("Do you really want to delete this appointment?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
            DBAppointments.deleteAppointment(select);
            appointmentsTableview.setItems(DBAppointments.getAppointments());
        }
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

    public void selectRadioButton(ActionEvent actionEvent) {
        if(weeklyRadioButton.isSelected()){
            ObservableList<Appointments> allAppointments = DBAppointments.getAppointments();
            ObservableList<Appointments> weekAppointments = FXCollections.observableArrayList();
            for(Appointments a : allAppointments){
                if(a.getStart().isAfter(a.getStart().minusDays(7)) || a.getStart().isEqual(a.getStart().minusDays(7))){
                weekAppointments.add(a);
                }
            }
            appointmentsTableview.setItems(weekAppointments);
            System.out.println("Week");
        }
        else if(monthlyRadioButton.isSelected()){
            ObservableList<Appointments> allAppointments = DBAppointments.getAppointments();
            ObservableList<Appointments> monthAppointments = FXCollections.observableArrayList();
            for(Appointments a : allAppointments){
                if(a.getStart().isAfter(a.getStart().minusDays(31)) || a.getStart().isEqual(a.getStart().minusDays(31))){
                    monthAppointments.add(a);
                }
            }
            appointmentsTableview.setItems(monthAppointments);
            System.out.println("Month");
        }
    }
}
