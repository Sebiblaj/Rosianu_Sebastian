package com.example.lool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    @FXML
    private Button logout;

    @FXML
    private Label welcome;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml",null,null);
            }
        });
    }
    public void setUserInformation(String username){
        welcome.setText("Welcome "+username+" !");
    }
}
