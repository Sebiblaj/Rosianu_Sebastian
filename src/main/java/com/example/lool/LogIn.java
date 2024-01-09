package com.example.lool;

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
    private Label LOLHUB;

    @FXML
    private Button champions;

    @FXML Button monsters;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout.setOnAction(event -> DBUtils.changeScene(event,"hello-view.fxml",null,null,622,340));
        champions.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"Champions.fxml",null,null,735,620));
        monsters.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"monsters.fxml",null,null,735,620));
    }
    public void setUserInformation(){
        LOLHUB.setText("Welcome to League of Legends Hub !");
    }
}
