package com.example.c195_task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Report2Controller {
    public Button returnButton;
    public TableView scheduleTableView;
    public TableColumn appointmentIDColumn;
    public TableColumn titleColumn;
    public TableColumn typeColumn;
    public TableColumn descriptionColumn;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn customerIDColumn;
    public RadioButton contact1Button;
    public ToggleGroup contact;
    public RadioButton contact2Button;
    public RadioButton contact3Button;

    @FXML
    public void returnR2(ActionEvent actionEvent) throws IOException {
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
