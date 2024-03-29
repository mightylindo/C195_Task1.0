package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class AppointmentScreenController implements Initializable {
    public Label titleLabel;
    public TableColumn<Object, Object> appointmentIDColumn;
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
    public ComboBox<Customers> customerSelectComboBox;
    public TextField appointmentIDTextField;
    public TextField locationTextField;
    public TextField startDateAndTimeTextField;
    public TextField endDateAndTimeTextField;
    public TextField typeTextField;
    public TextField appointmentDescriptionTextField;
    public TableView<Appointments> appointmentsTableview;
    public ObservableList<Appointments> alist = DBAppointments.getAppointments();
    public static int aID;
    public ToggleGroup DateRange;
    public RadioButton allRadioButton;
    public TextField titleTextField;
    public TableColumn titleColumn;
    public ComboBox<Contacts> contactComboBox;
    public ComboBox<Users> userComboBox;
    private String username;
    private LocalTime open = LocalTime.of(8,00);
    private LocalTime close = LocalTime.of(22,00);

    /**
     * This method catches the username and sets the username String.
     * @param username
     */
    public void username(String username){this.username = username;}

    /**
     *  This lambda expression is used to select all the appointments based on the number of days input.
     *  This lambda is currently used by the radio buttons to select all appointments within the week or month.
     *  By using a lambda we can add other buttons easily such as quarterly or yearly, and we just use the lambda with different number of days.
     *  This lambda returns a list of appointments.
     */
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
    /**
     * This lambda expression takes a start, end, customerID, and appointmentID, and it determines if there is an overlap in the start and end times between all the appointments.
     * This lambda is used by both the add and update appointments methods.
     * This lambda allows us to reuse the same code and decrease the overall clutter of the add and update methods.
     * This lambda returns a boolean which is used by the add or update buttons, so it knows whether ,or not to commit the hours to the database.
     */
    HoursTestInterface lambdaHours = (start, end, cID, id)-> {
        ObservableList<Appointments> alist = DBAppointments.getAppointments();
        boolean noOverlap = true;
        for(Appointments a : alist){
            if(a.getCustomerID() == cID && a.getAppointmentID() != id){
                if((start.isAfter(a.getStart()) || start.isEqual(a.getStart())) && start.isBefore(a.getEnd())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap1: Please select a different time.");
                    alert.showAndWait();
                    return noOverlap;
                }
                else if(end.isAfter(a.getStart()) && (end.isBefore(a.getEnd()) || end.isEqual(a.getEnd()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap2: Please select a different time.");
                    alert.showAndWait();
                    return noOverlap;
                }
                else if((start.isBefore(a.getStart()) || start.isEqual(a.getStart()))
                        && (end.isAfter(a.getEnd()) || end.isEqual(a.getEnd()))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    noOverlap = false;
                    alert.setContentText("Appointment Overlap3: Please select a different time.");
                    alert.showAndWait();
                    return noOverlap;
                }
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
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        userIDColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        ObservableList<Customers> clist = DBCustomers.getCustomers();
        customerSelectComboBox.setItems(clist);
        ObservableList<Contacts> contact = DBContacts.getContacts();
        contactComboBox.setItems(contact);
        ObservableList<Appointments> appointments = DBAppointments.getAppointments();
        ObservableList<Users> ulist = DBUsers.getUsers();
        userComboBox.setItems(ulist);
        for(Appointments a : appointments){
            aID = a.getAppointmentID();
        }
        aID = aID + 1;
        System.out.println(aID);
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
        Customers customer = customerSelectComboBox.getSelectionModel().getSelectedItem();
        int customerID = customer.getCustomerID();
        String description = appointmentDescriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        String title = titleTextField.getText();
        boolean bhours;
        boolean startOK = false;
        boolean endOK = false;
        boolean contact = true;
        try {
            int contactID = (contactComboBox.getSelectionModel().getSelectedIndex() + 1);
            if(contactID == 0){
                contact = false;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please select a contact");
                alert.showAndWait();
            }
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
            if (bhours) {
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
            if (bhours) {
                endOK = true;
            }
            boolean noOverlap = lambdaHours.checkHours(start, end, customerID, aID);

            if (noOverlap && startOK && endOK && contact) { //still has some errors let the system create an appointment even though noOverlap was false.
                LocalDateTime createDate = LocalDateTime.now();
                String createdBy = username;
                LocalDateTime lastUpdate = LocalDateTime.now();
                String lastUpdatedBy = username;
                Users user = userComboBox.getSelectionModel().getSelectedItem();
                int userID = user.getUserID();
                int appointmentID = aID;
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
        Appointments select = appointmentsTableview.getSelectionModel().getSelectedItem();
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
        try {
        Appointments select = appointmentsTableview.getSelectionModel().getSelectedItem();
        select.setDescription(appointmentDescriptionTextField.getText());
        select.setLocation(locationTextField.getText());
        select.setType(typeTextField.getText());
            LocalDateTime s = LocalDateTime.parse(startDateAndTimeTextField.getText());
            select.setStart(s.atZone(ZoneId.systemDefault()));
            LocalDateTime e = LocalDateTime.parse(endDateAndTimeTextField.getText());
            select.setEnd(e.atZone(ZoneId.systemDefault()));
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
            if (bhours) {
                startOK = true;
            }
            bhours = false;
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
            if (bhours) {
                endOK = true;
            }
            boolean noOverlap = lambdaHours.checkHours(select.getStart(), select.getEnd(), select.getCustomerID(), select.getAppointmentID());
            if (noOverlap  && startOK  && endOK ) {
                Contacts contact = contactComboBox.getSelectionModel().getSelectedItem();
                select.setContactID(contact.getContactID());
                Customers customer = customerSelectComboBox.getSelectionModel().getSelectedItem();
                select.setCustomerID(customer.getCustomerID());
                Users user = userComboBox.getSelectionModel().getSelectedItem();
                select.setUserID(user.getUserID());
                select.setLastUpdate(LocalDateTime.now());
                select.setLastUpdatedBy(username);
                DBAppointments.updateAppointment(select);
                appointmentsTableview.setItems(DBAppointments.getAppointments());
            }
        }catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter Date and Time in the following format: YYYY-MM-DDTHH:MM");
            alert.showAndWait();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Nothing updated, please select which appointment you would like to update.");
            alert.showAndWait();
        }
    }

    /**
     * This method is called when an appointment is selected from the tableview. It sets the textfields and combobox based on the values in the selected appointment.
     * @param mouseEvent
     */
    public void selectAppointment(MouseEvent mouseEvent) {
        Appointments select = appointmentsTableview.getSelectionModel().getSelectedItem();
        appointmentIDTextField.setText(Integer.toString(select.getAppointmentID()));
        titleTextField.setText(select.getTitle());
        appointmentDescriptionTextField.setText(select.getDescription());
        locationTextField.setText(select.getLocation());
        typeTextField.setText(select.getType());
        Users s = DBUsers.getUser(select.getUserID());
        userComboBox.setValue(s);
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        startDateAndTimeTextField.setText(select.getStart().format(dtf));
        endDateAndTimeTextField.setText((select.getEnd().format(dtf)));
        Customers c = DBCustomers.getSpecificCustomer(select.getCustomerID());
        customerSelectComboBox.setValue(c);
        Contacts contact = DBContacts.getSpecificContact(select.getContactID());
        contactComboBox.setValue(contact);
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
