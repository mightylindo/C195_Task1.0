package com.example.c195_task1;
import javafx.event.ActionEvent;
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

import java.io.IOException;

public class ReportScreenController {
    public Button report1Button;
    public Button report2Button;
    public Button report3Button;
    public Button saveAndExitButton;

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
    @FXML
    public void report1(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Report1.fxml"));
        Parent root = loader.load();
        Report1Controller controller = loader.getController();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }

    public void report2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Report2.fxml"));
        Parent root = loader.load();
        Report2Controller controller = loader.getController();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }

    public void report3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Report3.fxml"));
        Parent root = loader.load();
        Report3Controller controller = loader.getController();
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("C195 Task1");
        stage.setScene(scene);
        stage.show();
    }
}
