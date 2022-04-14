package com.example.c195_task1;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.io.IOException;

public class Report1Controller {
    public Button returnButton;
    public TreeTableView report1TableView;
    public TreeTableColumn janColumn;
    public TreeTableColumn febColumn;
    public TreeTableColumn marColumn;
    public TreeTableColumn aprilColumn;
    public TreeTableColumn mayColumn;
    public TreeTableColumn juneColumn;
    public TreeTableColumn julyColumn;
    public TreeTableColumn augColumn;
    public TreeTableColumn sepColumn;
    public TreeTableColumn octColumn;
    public TreeTableColumn novColumn;
    public TreeTableColumn decColumn;
    public ComboBox customerComboBox;
    public ComboBox typeComboBox;

    @FXML
    public void returnR1(ActionEvent actionEvent) throws IOException {
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
