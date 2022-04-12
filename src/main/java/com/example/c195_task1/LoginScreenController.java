package com.example.c195_task1;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
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

    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        userName = username.getText();
        passWord = password.getText();
        uvalid = DBUsers.testUsername(userName, passWord);
        ResourceBundle rb = ResourceBundle.getBundle("NAT", Locale.getDefault());
        if(uvalid == true){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
            Parent root = loader.load();
            MainScreenController controller = loader.getController();
            controller.username(userName);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("C195 Task1");
            stage.setScene(scene);
            stage.show();
            DBAppointments.checkAppointments();
        }
        else if(uvalid == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("Login") + " " + rb.getString("Error"));
            alert.setContentText(rb.getString("Username") + " " + rb.getString("or") + " " + rb.getString("Password") + " " + rb.getString("incorrect") + ", "
                    + rb.getString("please") + " " + rb.getString("try") + " " + rb.getString("again"));
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zoneIDLabel.setText(ZoneId.systemDefault().getId());
        ResourceBundle rb = ResourceBundle.getBundle("NAT", Locale.getDefault());
        loginButton.setText(rb.getString("Login"));
        loginScreenLabel.setText(rb.getString("Login") + " " + rb.getString("Screen"));
    }
}

