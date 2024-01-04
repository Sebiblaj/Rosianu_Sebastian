package com.example.lool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 622, 340);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
       /* DBConnection dbConnection=new DBConnection();
        Connection connection=dbConnection.connect_to_db("postgres","postgres","Sebilica09");
        dbConnection.insert_row(connection,"\"LoL-schema\".users","sebbbi","sebi.rosianu9@yahoo.com","1234","male");

        dbConnection.read_data(connection,"\"LoL-schema\".users");

        */
    }
}