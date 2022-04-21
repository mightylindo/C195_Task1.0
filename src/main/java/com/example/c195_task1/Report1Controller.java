package com.example.c195_task1;
import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import DBAccess.DBReports;
import Model.Appointments;
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
    public TableColumn typeColumn;


    MonthCounterInterface lambdaCounter = (type)->{
        ObservableList<Reports> rlist = DBReports.getReport1(type);
        int rjan = 0;
        int rfeb = 0;
        int rmar = 0;
        int rapr = 0;
        int rmay = 0;
        int rjune = 0;
        int rjuly = 0;
        int raug = 0;
        int rsep = 0;
        int roct = 0;
        int rnov = 0;
        int rdec = 0;
        for (Reports r : rlist) {
            LocalDateTime start = r.getStart();
            if (start.getMonth() == Month.JANUARY) {
                rjan = rjan + 1;
            } else if (start.getMonth() == Month.FEBRUARY) {
                rfeb= rfeb +1;
            } else if (start.getMonth() == Month.MARCH) {
                rmar= rmar+1;
            } else if (start.getMonth() == Month.APRIL) {
                rapr= rapr +1;
            } else if (start.getMonth() == Month.MAY) {
                rmay= rmay+1;
            } else if (start.getMonth() == Month.JUNE) {
                rjune=rjune +1;
            } else if (start.getMonth() == Month.JULY) {
                rjuly=rjuly+1;
            } else if (start.getMonth() == Month.AUGUST) {
                raug=raug+1;
            } else if (start.getMonth() == Month.SEPTEMBER) {
                rsep=rsep+1;
            } else if (start.getMonth() == Month.OCTOBER) {
                roct=roct+1;
            } else if (start.getMonth() == Month.NOVEMBER) {
                rnov=rnov+1;
            } else if (start.getMonth() == Month.DECEMBER) {
                rdec=rdec+1;
            }
        }
        Reports compiled = new Reports(type, rjan, rfeb, rmar, rapr, rmay, rjune, rjuly, raug, rsep, roct, rnov, rdec);
        return compiled;
    };
    /**
     * This method initializes the report 1 screen.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Intitalized!");
        selectType();
    }

    /**
     * This method is called when the user clicks on the return button and it loads the reports screen.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * This method is called by the initialize method. It goes through all the appointments and aggregates them into a single Report objects by type. The method then sets the tableview based
     * on an observable list of reports that contains the aggregated reports.
     */
    public void selectType() {
        ObservableList<Appointments> allApt = DBAppointments.getAppointments();
        ObservableList<Reports> comlist = FXCollections.observableArrayList();
        boolean flag = true;
        for(Appointments a : allApt) {
            flag = true;
            String type = a.getType();
            Reports check = lambdaCounter.getCounted(type);
            for(Reports r : comlist){
                String test = r.getType();
                if(type.equals(test)){
                    flag = false;
                }
            }
            if(flag == true){
                comlist.add(check);
            }
        }
        report1TableView.setItems(comlist);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
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
}
