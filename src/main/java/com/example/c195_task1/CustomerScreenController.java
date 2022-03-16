package com.example.c195_task1;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class CustomerScreenController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initialized");
        /**testing user class
        Users Bob = new Users(1, "Bob", "1234", "Jill", "Frank");
        Bob.setUserID(2);
        Bob.setUserName("Frank");
        Bob.setPassword("4321");
        Bob.setCreateBy("Bob");
        Bob.setLastUpdatedBy("Jill");
        System.out.println(Bob.getUserID());
        System.out.println(Bob.getUserName());
        System.out.println(Bob.getPassword());
        System.out.println(Bob.getCreateBy());
        System.out.println(Bob.getLastUpdatedBy());
         */
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
}
