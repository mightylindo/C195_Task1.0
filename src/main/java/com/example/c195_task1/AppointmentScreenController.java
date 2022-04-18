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
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.TimerTask;

import static java.time.ZoneId.systemDefault;

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
    public int aID = alist.size() + 2;
    public ToggleGroup DateRange;
    public RadioButton allRadioButton;
    public TextField titleTextField;
    public TableColumn titleColumn;
    private String username;
    private LocalTime open = LocalTime.of(8,00);
    private LocalTime close = LocalTime.of(22,00);
    public void username(String username){this.username = username;}

    ScheduleInterface lambdaAppointments = days -> {
        ObservableList<Appointments> allAppointments = DBAppointments.getAppointments();
        ObservableList<Appointments> aAppointments = FXCollections.observableArrayList();
        for(Appointments a : allAppointments){
            LocalDateTime current = LocalDateTime.now();
            LocalDateTime appointment = a.getStart();
            long timeDifference = ChronoUnit.DAYS.between(current, appointment);
            if(timeDifference < days && timeDifference > 0){
                aAppointments.add(a);
            }
        }
        return aAppointments;
    };

    HoursTestInterface lambdaHours = (start, end, id)-> {
        ObservableList<Appointments> alist = DBAppointments.getAppointments();
        boolean noOverlap = false;
        for(Appointments a : alist){
            if(a.getCustomerID() == id){
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
        return noOverlap;
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("initialized!");
        System.out.println(aID);
        appointmentsTableview.setItems(DBAppointments.getAppointments());
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
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
        controller.username(username);
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
        String title = titleTextField.getText();
        boolean bhours;
        boolean startOK = false;
        boolean endOK = false;
        LocalDateTime start = LocalDateTime.parse(startDateAndTimeTextField.getText());
        ZonedDateTime zstart = LocalDateTime.parse(startDateAndTimeTextField.getText()).atZone(ZoneId.systemDefault());
        if(zstart.isBefore(ZonedDateTime.of(zstart.toLocalDate(), open, ZoneId.of("America/New_York")))
                || zstart.isAfter(ZonedDateTime.of(zstart.toLocalDate(), close, ZoneId.of("America/New_York")))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment start outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        if(bhours == true){
            startOK = true;
        }
        LocalDateTime end = LocalDateTime.parse(endDateAndTimeTextField.getText());
        ZonedDateTime zend = LocalDateTime.parse(endDateAndTimeTextField.getText()).atZone(ZoneId.systemDefault());
        if(zend.isBefore(ZonedDateTime.of(zend.toLocalDate(), open, ZoneId.of("America/New_York")))
                || zend.isAfter(ZonedDateTime.of(zend.toLocalDate(), close, ZoneId.of("America/New_York")))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment end outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        if(bhours == true){
            endOK = true;
        }
        boolean noOverlap = lambdaHours.checkHours(start, end, customerID);
        if(noOverlap == true && startOK == true && endOK == true){ //still has some errors let the system create an appointment even though noOverlap was false.
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = username;
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdatedBy = username;
            int userID = DBUsers.getUser(username);
            int appointmentID = aID + 1;
            DBAppointments.addAppointment(new Appointments(appointmentID, title, description, location, type, zstart, zend, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID , userID, contactID));
            appointmentsTableview.setItems(DBAppointments.getAppointments());
            aID = aID + 1;
        }

    }

    @FXML
    public void deleteButton(ActionEvent actionEvent) throws IOException {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setContentText("Do you really want to delete appointment: " + select.getAppointmentID() + ", " + select.getType() + "?");
        if(alert.showAndWait().get() == ButtonType.OK) {
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
        select.setStart(LocalDateTime.parse(startDateAndTimeTextField.getText()));
        select.setTitle(titleTextField.getText());
        boolean bhours;
        boolean startOK = false;
        boolean endOK = false;

        if(select.getStart().isBefore(ChronoLocalDateTime.from(ZonedDateTime.of(select.getEnd().toLocalDate(), open, ZoneId.of("America/New_York"))))
                || select.getStart().isAfter(ChronoLocalDateTime.from(ZonedDateTime.of(select.getEnd().toLocalDate(), close, ZoneId.of("America/New_York"))))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment start outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        if(bhours == true){
            startOK = true;
        }
        select.setEnd(LocalDateTime.parse(endDateAndTimeTextField.getText()));
        if(select.getEnd().isBefore(ChronoLocalDateTime.from(ZonedDateTime.of(select.getEnd().toLocalDate(), open, ZoneId.of("America/New_York"))))
                || select.getEnd().isAfter(ChronoLocalDateTime.from(ZonedDateTime.of(select.getEnd().toLocalDate(), close, ZoneId.of("America/New_York"))))){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Appointment end outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
            alert.showAndWait();
            bhours = false;
        }
        else{
            bhours = true;
        }
        if(bhours == true){
            endOK =true;
        }
        boolean noOverlap = lambdaHours.checkHours(select.getStart(), select.getEnd(), select.getCustomerID());
        if(noOverlap == true && startOK == true && endOK == true) {
            Customers customer = (Customers) customerSelectComboBox.getSelectionModel().getSelectedItem();
            select.setCustomerID(customer.getCustomerID());
            select.setLastUpdate(LocalDateTime.now());
            select.setLastUpdatedBy(username);
            DBAppointments.updateAppointment(select);
            appointmentsTableview.setItems(DBAppointments.getAppointments());
        }
    }

    public void selectAppointment(MouseEvent mouseEvent) {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        appointmentIDTextField.setText(Integer.toString(select.getAppointmentID()));
        titleTextField.setText(select.getTitle());
        appointmentDescriptionTextField.setText(select.getDescription());
        locationTextField.setText(select.getLocation());
        typeTextField.setText(select.getType());
        contactTextField.setText(Integer.toString(select.getContactID()));
        customerIDTextField.setText(Integer.toString(select.getCustomerID()));
        userIDTextField.setText(Integer.toString(select.getUserID()));
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        startDateAndTimeTextField.setText(select.getStart().format(dtf));
        endDateAndTimeTextField.setText((select.getEnd().format(dtf)));
        Customers c = DBCustomers.getSpecificCustomer(select.getCustomerID());
        customerSelectComboBox.setValue(c);
    }

    public void selectRadioButton(ActionEvent actionEvent) {
        if(weeklyRadioButton.isSelected()){
            appointmentsTableview.setItems(lambdaAppointments.addAppointments(7));
            System.out.println("Week");
        }
        else if(monthlyRadioButton.isSelected()){
            appointmentsTableview.setItems(lambdaAppointments.addAppointments(31));
            System.out.println("Month");
        }
        else if(allRadioButton.isSelected()){
            appointmentsTableview.setItems(DBAppointments.getAppointments());
        }
    }
}
