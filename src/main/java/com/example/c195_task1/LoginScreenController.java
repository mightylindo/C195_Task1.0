package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {
    public Button loginButton;
    public TextField username;
    public TextField password;
    public Label zoneIDLabel;
    public Label loginScreenLabel;
    private String userName = "";
    private String passWord = "";
    private boolean uvalid = false;
    private boolean check = false;

    /**
     * This method is called when the user clicks the login button.
     * The method takes the text from the username and password textfields. It then tests them using DBUsers.testUsername.
     * If uvalid comes back true then the printwriter writes the data into the login tracker and then loads the main screen and passes the username to the main screen.
     * If false it writes to the login tracker and then prompts the user that the username or password is incorrect.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        userName = username.getText();
        passWord = password.getText();
        uvalid = DBUsers.testUsername(userName, passWord);
        File file = new File("login_activity.txt");
        FileWriter fw = new FileWriter(file,true);
        PrintWriter pw = new PrintWriter(fw);
        ResourceBundle rb = ResourceBundle.getBundle("NAT", Locale.getDefault());
        if(uvalid){
            pw.println("User: " + userName + " attempted login at: " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + " Login Successful");
            pw.close();
            checkAppointments(userName);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = loader.load();
            MainScreenController controller = loader.getController();
            controller.username(userName);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("C195 Task1");
            stage.setScene(scene);
            stage.show();
        }
        else{
            pw.println("User: " + userName + " attempted login at: " + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS) + " Login Failed");
            pw.close();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("Login") + " " + rb.getString("Error"));
            alert.setContentText(rb.getString("Username") + " " + rb.getString("or") + " " + rb.getString("Password") + " " + rb.getString("incorrect") + ", "
                    + rb.getString("please") + " " + rb.getString("try") + " " + rb.getString("again"));
            alert.showAndWait();
        }
    }

    /**
     * This method initializes the login screen and changes the zoneID label based on the system default. It also translates the page based on the default.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneIDLabel.setText(ZoneId.systemDefault().getId());
        ResourceBundle rb = ResourceBundle.getBundle("NAT", Locale.getDefault());
        loginButton.setText(rb.getString("Login"));
        loginScreenLabel.setText(rb.getString("Login") + " " + rb.getString("Screen"));
        username.setText(rb.getString("Username"));
        password.setText(rb.getString("Password"));
    }

    /**
     * This method is called when the user logs in. It checks if there is any appointments for the logged in user within 15 minutes and provides a prompt
     * with the appointmentID and start time if there is.
     * If there is no appointment a prompt appears stating such.
     */
    public static void checkAppointments(String userName){
        int userID = DBUsers.getUserID(userName);
        ObservableList<Appointments> alist = DBAppointments.getAppointments(userID);
        boolean myAptmt = true;
        int count = 0;
        int size = alist.size();
        for(Appointments a : alist){
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime startTime = a.getStart().toLocalDateTime();
            long timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime);
            count = count +1;
            if(timeDifference < 15 && timeDifference >= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Imminent");
                alert.setContentText("Appointment: " + a.getAppointmentID() + " starts at: " + a.getStart());
                alert.showAndWait();
                myAptmt = true;
                return;
            }

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Appointment Imminent");
        alert.setContentText("No Appointments within 15 Minutes.");
        alert.showAndWait();

    }
}

