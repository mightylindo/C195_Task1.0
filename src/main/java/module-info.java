module com.example.c195_task1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.c195_task1 to javafx.fxml;
    exports com.example.c195_task1;
    exports Model;
    opens Model to javafx.fxml;
}