module com.example.lool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.lool to javafx.fxml;
    exports com.example.lool;
}