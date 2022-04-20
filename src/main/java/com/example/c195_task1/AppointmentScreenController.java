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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    /**
     * This method catches the username and sets the username String.
     * @param username
     */
    public void username(String username){this.username = username;}

    ScheduleInterface lambdaAppointments = days -> {
        ObservableList<Appointments> allAppointments = DBAppointments.getAppointments();
        ObservableList<Appointments> aAppointments = FXCollections.observableArrayList();
        for(Appointments a : allAppointments){
            LocalDateTime current = LocalDateTime.now();
            ZonedDateTime appointment = a.getStart();
            long timeDifference = ChronoUnit.DAYS.between(current, appointment);
            if(timeDifference < days && timeDifference > 0){
                aAppointments.add(a);
            }
        }
        return aAppointments;
    };

    HoursTestInterface lambdaHours = (start, end, cID, id)-> {
        ObservableList<Appointments> alist = DBAppointments.getAppointments();
        boolean noOverlap = false;
        for(Appointments a : alist){
            if(a.getCustomerID() == cID && a.getAppointmentID() != id){
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
                else if((start.isBefore(a.getStart()) || start.isEqual(a.getStart()))
                        && (end.isAfter(a.getEnd()) || end.isEqual(a.getEnd()))){
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

    /**
     * This method initializes the appointments screen. It sets the items for the appointments tableview as well as it sets the customer combobox with all the customers in the database.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("initialized!");
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

    /**
     * This method is called when the user clicks on the save and exit button. This method loads the main screen and passes the username back.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * This method is called when the user clicks on the add button. This method creates a new appointment object and passes it to DBAppointments.addAppointment method,
     * so it can be added to the database.
     * This method also checks that the proposed start and end are within business hours, and also checks that there are no overlaps.
     * After passing the appointments object to the database, the method then resets the tableview and iterates the appointmentID.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void addButton(ActionEvent actionEvent) throws IOException{
        Customers customer = (Customers) customerSelectComboBox.getSelectionModel().getSelectedItem();
        int customerID = customer.getCustomerID();
        String description = appointmentDescriptionTextField.getText();
        String location = locationTextField.getText();
        int contactID = Integer.parseInt(contactTextField.getText());
        if(contactID <1 || contactID>3){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select a contact between 1-3");
            alert.showAndWait();
        }
        String type = typeTextField.getText();
        String title = titleTextField.getText();
        boolean bhours;
        boolean startOK = false;
        boolean endOK = false;
        try {
            LocalDateTime s = LocalDateTime.parse(startDateAndTimeTextField.getText());
            ZonedDateTime start = s.atZone(ZoneId.systemDefault());
            ZonedDateTime openz = ZonedDateTime.of(start.toLocalDate(), open, ZoneId.of("America/New_York"));
            ZonedDateTime closez = ZonedDateTime.of(start.toLocalDate(), close, ZoneId.of("America/New_York"));
            if (start.isBefore(openz) || start.isAfter((closez))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Appointment start outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
                alert.showAndWait();
                bhours = false;
            } else {
                bhours = true;
            }
            if (bhours == true) {
                startOK = true;
            }
            bhours = false;
            LocalDateTime e = LocalDateTime.parse(endDateAndTimeTextField.getText());
            ZonedDateTime end = e.atZone(ZoneId.systemDefault());
            if (start.isAfter(end)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("End time is before Start time. Please correct.");
                alert.showAndWait();
            } else if (end.isBefore((openz)) || end.isAfter((closez))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Appointment end outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
                alert.showAndWait();
            } else {
                bhours = true;
            }
            if (bhours == true) {
                endOK = true;
            }
            boolean noOverlap = lambdaHours.checkHours(start, end, customerID, aID + 1);

            if (noOverlap == true && startOK == true && endOK == true) { //still has some errors let the system create an appointment even though noOverlap was false.
                LocalDateTime createDate = LocalDateTime.now();
                String createdBy = username;
                LocalDateTime lastUpdate = LocalDateTime.now();
                String lastUpdatedBy = username;
                int userID = DBUsers.getUser(username);
                int appointmentID = aID + 1;
                DBAppointments.addAppointment(new Appointments(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID));
                appointmentsTableview.setItems(DBAppointments.getAppointments());
                aID = aID + 1;
            }
        }catch (DateTimeParseException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter Date and Time in the following format: YYYY-MM-DDTHH:MM");
            alert.showAndWait();
        }


    }

    /**
     * This method is called when the user clicks the delete button. It prompts the user asking if they really want to delete it.
     * Once ok is clicked the appointment is deleted by DBAppointments.deleteAppointment. The method then resets the tableview.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * This method is called when the user clicks on the update button. This method updates an existing appointment object that was selected, and passes it to DBAppointments.updateAppointment method,
     * so it can be updated in the database.
     *This method also checks that the proposed start and end are within business hours, and also checks that there are no overlaps.
     *After passing the appointments object to the database, the method then resets the tableview and iterates the appointmentID.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void updateButton(ActionEvent actionEvent) throws IOException {
        Appointments select = (Appointments) appointmentsTableview.getSelectionModel().getSelectedItem();
        select.setDescription(appointmentDescriptionTextField.getText());
        select.setLocation(locationTextField.getText());
        select.setType(typeTextField.getText());
        try {
        select.setContactID(Integer.parseInt(contactTextField.getText()));
        if(Integer.parseInt(contactTextField.getText() )<1 || Integer.parseInt(contactTextField.getText())>3) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please select a contact between 1-3");
            alert.showAndWait();
        }
            LocalDateTime s = LocalDateTime.parse(startDateAndTimeTextField.getText());
            select.setStart(s.atZone(ZoneId.systemDefault()));
            select.setTitle(titleTextField.getText());
            boolean bhours;
            boolean startOK = false;
            boolean endOK = false;
            if (select.getStart().isBefore((ZonedDateTime.of(select.getEnd().toLocalDate(), open, ZoneId.of("America/New_York"))))
                    || select.getStart().isAfter((ZonedDateTime.of(select.getEnd().toLocalDate(), close, ZoneId.of("America/New_York"))))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Appointment start outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
                alert.showAndWait();
                bhours = false;
            } else {
                bhours = true;
            }
            if (bhours == true) {
                startOK = true;
            }
            bhours = false;
            LocalDateTime e = LocalDateTime.parse(endDateAndTimeTextField.getText());
            select.setEnd(e.atZone(ZoneId.systemDefault()));
            if (select.getStart().isAfter(select.getEnd())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("End time is before Start time. Please correct.");
                alert.showAndWait();
            } else if (select.getEnd().isBefore((ZonedDateTime.of(select.getEnd().toLocalDate(), open, ZoneId.of("America/New_York"))))
                    || select.getEnd().isAfter((ZonedDateTime.of(select.getEnd().toLocalDate(), close, ZoneId.of("America/New_York"))))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Appointment end outside business hours. Please select hours within the hours of 8AM and 10PM EST.");
                alert.showAndWait();
            } else {
                bhours = true;
            }
            if (bhours == true) {
                endOK = true;
            }
            boolean noOverlap = lambdaHours.checkHours(select.getStart(), select.getEnd(), select.getCustomerID(), select.getAppointmentID());
            if (noOverlap == true && startOK == true && endOK == true) {
                Customers customer = (Customers) customerSelectComboBox.getSelectionModel().getSelectedItem();
                select.setCustomerID(customer.getCustomerID());
                select.setLastUpdate(LocalDateTime.now());
                select.setLastUpdatedBy(username);
                DBAppointments.updateAppointment(select);
                appointmentsTableview.setItems(DBAppointments.getAppointments());
            }
        }catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter Date and Time in the following format: YYYY-MM-DDTHH:MM");
            alert.showAndWait();
        }
    }

    /**
     * This method is called when an appointment is selected from the tableview. It sets the textfields and combobox based on the values in the selected appointment.
     * @param mouseEvent
     */
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

    /**
     * This method is called when the user clicks on the radio buttons. Depending on the select button it pulls all appointments, or calls the lambda expression for the week or month
     * and resets the tableview to show appointments that fall within the day range.
     * @param actionEvent
     */
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
