package com.example.c195_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Report3Controller {
    public Button returnButton;
    @FXML
    public void returnR3(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportsScreen.fxml"));
            Parent root = loader.load();
            ReportScreenController controller = loader.getController();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("C195 Task1");
            stage.setScene(scene);
            stage.show();
    }
}
