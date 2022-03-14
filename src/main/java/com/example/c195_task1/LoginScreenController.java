package com.example.c195_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;


import java.io.IOException;

public class LoginScreenController {

    @FXML
    public void loginClicked(ActionEvent actionEvent) throws IOException {
        System.out.println("It worked");
        //nextScreen(Stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        MainScreenController controller = fxmlLoader.load();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }

    //public void nextScreen (Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainScreen.fxml"));
        //MainScreenController controller = fxmlLoader.load();
        //Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //stage.setTitle("C195 Task1");
        //stage.setScene(scene);
        //stage.show();
    //}
}
