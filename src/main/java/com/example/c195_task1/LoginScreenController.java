package com.example.c195_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;

public class LoginScreenController {

    /**
     * @param actionEvent
     * @throws IOException
     */

    //Need to add functionality that will check Username and Password against database

    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        System.out.println("It worked");
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
