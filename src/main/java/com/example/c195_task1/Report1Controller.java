package com.example.c195_task1;
import DBAccess.DBCustomers;
import DBAccess.DBReports;
import Model.Customers;
import Model.Reports;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class Report1Controller implements Initializable {
    public Button returnButton;
    public TableView report1TableView;
    public TableColumn janColumn;
    public TableColumn febColumn;
    public TableColumn marColumn;
    public TableColumn aprilColumn;
    public TableColumn mayColumn;
    public TableColumn juneColumn;
    public TableColumn julyColumn;
    public TableColumn augColumn;
    public TableColumn sepColumn;
    public TableColumn octColumn;
    public TableColumn novColumn;
    public TableColumn decColumn;
    public ComboBox typeComboBox;
    int jan = 0;
    int feb = 0;
    int mar = 0;
    int apr = 0;
    int may = 0;
    int june = 0;
    int july = 0;
    int aug = 0;
    int sep = 0;
    int oct = 0;
    int nov = 0;
    int dec = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Intitalized!");
        ObservableList<String> tlist = FXCollections.observableArrayList();
        tlist.add("Meeting");
        tlist.add("Interview");
        tlist.add("Disciplinary");
        typeComboBox.setItems(tlist);
    }

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

    public void selectType(ActionEvent actionEvent) {
        String type = typeComboBox.getSelectionModel().getSelectedItem().toString();
        if(type == "Meeting"){
            ObservableList<Reports> rlist = DBReports.getReport1(type);
            int count = rlist.size();
            System.out.println(count);
            if(count == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No " + type + " Appointments");
                alert.setContentText("There are no appointments of type: " + type + ". Please select another type.");
                alert.showAndWait();
            }
            else if(count >=1 ) {
                report1TableView.setItems(rlist);
                System.out.println("We made it in.");
                for (Reports r : rlist){

                }
            }
            else{
                System.out.println("Something went wrong.");
            }
        }
        else if(type == "Interview"){
            ObservableList<Reports> rlist = DBReports.getReport1(type);
            int count = rlist.size();
            if(count == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No " + type + " Appointments");
                alert.setContentText("There are no appointments of type: " + type + ". Please select another type.");
                alert.showAndWait();
            }
            else if(count >=1 ) {
                report1TableView.setItems(rlist);
                for (Reports r : rlist) {
                    LocalDateTime start = r.getStart();
                    if(start.getMonth() == Month.JANUARY){
                       jan = jan + 1;
                    }else if(start.getMonth() == Month.FEBRUARY){
                        feb = feb + 1;
                    }else if(start.getMonth() == Month.MARCH){
                        mar = mar + 1;
                    }else if(start.getMonth() == Month.APRIL){
                        apr = apr + 1;
                    }else if(start.getMonth() == Month.MAY){
                        may = may + 1;
                    }else if(start.getMonth() == Month.JUNE){
                        june = june + 1;
                    }else if(start.getMonth() == Month.JULY){
                        july = july + 1;
                    }else if(start.getMonth() == Month.AUGUST){
                        aug = aug + 1;
                    }else if(start.getMonth() == Month.SEPTEMBER){
                        sep = sep + 1;
                    }else if(start.getMonth() == Month.OCTOBER){
                        oct = oct + 1;
                    }else if(start.getMonth() == Month.NOVEMBER){
                        nov = nov + 1;
                    }else if(start.getMonth() == Month.DECEMBER){
                        dec = dec + 1;
                    }
                }
                janColumn.cellFactoryProperty().setValue(jan);
                febColumn.cellFactoryProperty().setValue(feb);
                marColumn.cellFactoryProperty().setValue(mar);
                aprilColumn.cellFactoryProperty().setValue(apr);
            }
            else{
                System.out.println("Something went wrong 2.");
            }
        }
        else if(type == "Disciplinary"){
            ObservableList<Reports> rlist = DBReports.getReport1(type);
            int count = rlist.size();
            if(count == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No " + type + " Appointments");
                alert.setContentText("There are no appointments of type: " + type + ". Please select another type.");
                alert.showAndWait();
            }
            else if(count >=1 ) {
                report1TableView.setItems(rlist);
                for (Reports r : rlist) {
                    //need to implement the rest of the code
                }
            }
            else{
                System.out.println("Something went wrong 3.");
            }
        }
        else{
           System.out.println("whoops");
        }
    }
}
