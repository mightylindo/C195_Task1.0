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
import javafx.scene.control.cell.PropertyValueFactory;
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

    /**
     * This method initializes the report 1 screen
     * @param url
     * @param resourceBundle
     */
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
                    LocalDateTime start = r.getStart();
                    if(start.getMonth() == Month.JANUARY){
                        int i = r.getJan();
                        r.setJan(i + 1);
                    }else if(start.getMonth() == Month.FEBRUARY){
                        int i = r.getFeb();
                        r.setFeb(i + 1);
                    }else if(start.getMonth() == Month.MARCH){
                        int i = r.getMar();
                        r.setMar(i + 1);
                    }else if(start.getMonth() == Month.APRIL){
                        int i = r.getApr();
                        r.setApr(i + 1);
                    }else if(start.getMonth() == Month.MAY){
                        int i = r.getMay();
                        r.setMay(i + 1);
                    }else if(start.getMonth() == Month.JUNE){
                        int i = r.getJune();
                        r.setJune(i + 1);
                    }else if(start.getMonth() == Month.JULY){
                        int i = r.getJuly();
                        r.setJuly(i + 1);
                    }else if(start.getMonth() == Month.AUGUST){
                        int i = r.getAug();
                        r.setAug(i + 1);
                    }else if(start.getMonth() == Month.SEPTEMBER){
                        int i = r.getSep();
                        r.setSep(i + 1);
                    }else if(start.getMonth() == Month.OCTOBER){
                        int i = r.getOct();
                        r.setOct(i + 1);
                    }else if(start.getMonth() == Month.NOVEMBER){
                        int i = r.getNov();
                        r.setNov(i + 1);
                    }else if(start.getMonth() == Month.DECEMBER){
                        int i = r.getDec();
                        r.setDec(i + 1);
                    }
                }
                janColumn.setCellValueFactory(new PropertyValueFactory<>("Jan"));
                febColumn.setCellValueFactory(new PropertyValueFactory<>("Feb"));
                marColumn.setCellValueFactory(new PropertyValueFactory<>("Mar"));
                aprilColumn.setCellValueFactory(new PropertyValueFactory<>("Apr"));
                mayColumn.setCellValueFactory(new PropertyValueFactory<>("May"));
                juneColumn.setCellValueFactory(new PropertyValueFactory<>("June"));
                julyColumn.setCellValueFactory(new PropertyValueFactory<>("July"));
                augColumn.setCellValueFactory(new PropertyValueFactory<>("Aug"));
                sepColumn.setCellValueFactory(new PropertyValueFactory<>("Sep"));
                octColumn.setCellValueFactory(new PropertyValueFactory<>("Oct"));
                novColumn.setCellValueFactory(new PropertyValueFactory<>("Nov"));
                decColumn.setCellValueFactory(new PropertyValueFactory<>("Dec"));
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
                        int i = r.getJan();
                        r.setJan(i + 1);
                    }else if(start.getMonth() == Month.FEBRUARY){
                        int i = r.getFeb();
                        r.setFeb(i + 1);
                    }else if(start.getMonth() == Month.MARCH){
                        int i = r.getMar();
                        r.setMar(i + 1);
                    }else if(start.getMonth() == Month.APRIL){
                        int i = r.getApr();
                        r.setApr(i + 1);
                    }else if(start.getMonth() == Month.MAY){
                        int i = r.getMay();
                        r.setMay(i + 1);
                    }else if(start.getMonth() == Month.JUNE){
                        int i = r.getJune();
                        r.setJune(i + 1);
                    }else if(start.getMonth() == Month.JULY){
                        int i = r.getJuly();
                        r.setJuly(i + 1);
                    }else if(start.getMonth() == Month.AUGUST){
                        int i = r.getAug();
                        r.setAug(i + 1);
                    }else if(start.getMonth() == Month.SEPTEMBER){
                        int i = r.getSep();
                        r.setSep(i + 1);
                    }else if(start.getMonth() == Month.OCTOBER){
                        int i = r.getOct();
                        r.setOct(i + 1);
                    }else if(start.getMonth() == Month.NOVEMBER){
                        int i = r.getNov();
                        r.setNov(i + 1);
                    }else if(start.getMonth() == Month.DECEMBER){
                        int i = r.getDec();
                        r.setDec(i + 1);
                    }
                }
                janColumn.setCellValueFactory(new PropertyValueFactory<>("Jan"));
                febColumn.setCellValueFactory(new PropertyValueFactory<>("Feb"));
                marColumn.setCellValueFactory(new PropertyValueFactory<>("Mar"));
                aprilColumn.setCellValueFactory(new PropertyValueFactory<>("Apr"));
                mayColumn.setCellValueFactory(new PropertyValueFactory<>("May"));
                juneColumn.setCellValueFactory(new PropertyValueFactory<>("June"));
                julyColumn.setCellValueFactory(new PropertyValueFactory<>("July"));
                augColumn.setCellValueFactory(new PropertyValueFactory<>("Aug"));
                sepColumn.setCellValueFactory(new PropertyValueFactory<>("Sep"));
                octColumn.setCellValueFactory(new PropertyValueFactory<>("Oct"));
                novColumn.setCellValueFactory(new PropertyValueFactory<>("Nov"));
                decColumn.setCellValueFactory(new PropertyValueFactory<>("Dec"));

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
                    LocalDateTime start = r.getStart();
                    if(start.getMonth() == Month.JANUARY){
                        int i = r.getJan();
                        r.setJan(i + 1);
                    }else if(start.getMonth() == Month.FEBRUARY){
                        int i = r.getFeb();
                        r.setFeb(i + 1);
                    }else if(start.getMonth() == Month.MARCH){
                        int i = r.getMar();
                        r.setMar(i + 1);
                    }else if(start.getMonth() == Month.APRIL){
                        int i = r.getApr();
                        r.setApr(i + 1);
                    }else if(start.getMonth() == Month.MAY){
                        int i = r.getMay();
                        r.setMay(i + 1);
                    }else if(start.getMonth() == Month.JUNE){
                        int i = r.getJune();
                        r.setJune(i + 1);
                    }else if(start.getMonth() == Month.JULY){
                        int i = r.getJuly();
                        r.setJuly(i + 1);
                    }else if(start.getMonth() == Month.AUGUST){
                        int i = r.getAug();
                        r.setAug(i + 1);
                    }else if(start.getMonth() == Month.SEPTEMBER){
                        int i = r.getSep();
                        r.setSep(i + 1);
                    }else if(start.getMonth() == Month.OCTOBER){
                        int i = r.getOct();
                        r.setOct(i + 1);
                    }else if(start.getMonth() == Month.NOVEMBER){
                        int i = r.getNov();
                        r.setNov(i + 1);
                    }else if(start.getMonth() == Month.DECEMBER){
                        int i = r.getDec();
                        r.setDec(i + 1);
                    }
                }
                janColumn.setCellValueFactory(new PropertyValueFactory<>("Jan"));
                febColumn.setCellValueFactory(new PropertyValueFactory<>("Feb"));
                marColumn.setCellValueFactory(new PropertyValueFactory<>("Mar"));
                aprilColumn.setCellValueFactory(new PropertyValueFactory<>("Apr"));
                mayColumn.setCellValueFactory(new PropertyValueFactory<>("May"));
                juneColumn.setCellValueFactory(new PropertyValueFactory<>("June"));
                julyColumn.setCellValueFactory(new PropertyValueFactory<>("July"));
                augColumn.setCellValueFactory(new PropertyValueFactory<>("Aug"));
                sepColumn.setCellValueFactory(new PropertyValueFactory<>("Sep"));
                octColumn.setCellValueFactory(new PropertyValueFactory<>("Oct"));
                novColumn.setCellValueFactory(new PropertyValueFactory<>("Nov"));
                decColumn.setCellValueFactory(new PropertyValueFactory<>("Dec"));
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
