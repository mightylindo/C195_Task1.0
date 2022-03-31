package com.example.c195_task1;

import DBAccess.DBUsers;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Objects;

public class LoginScreenController {
    public Button loginButton;
    public TextField username;
    public TextField password;
    private String userName = "";
    private String passWord = "";
    private boolean uvalid = false;
    private boolean pvalid = false;
    private boolean valid = false;
    /**
     * @param actionEvent
     * @throws IOException
     */

    //Need to add functionality that will check Username and Password against database

    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        userName = username.getText();
        passWord = password.getText();
        Boolean uvalid = DBUsers.testUsername(userName);
        System.out.println(uvalid);
        Boolean pvalid = DBUsers.testPassword(passWord);
        System.out.println(pvalid);
        if(uvalid == true && pvalid == true){
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
}

