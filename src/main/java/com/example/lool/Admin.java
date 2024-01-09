package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    @FXML
    private Button goback;
    @FXML
    private Button gohub;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goback.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"hello-view.fxml",null,null,622,340));
        gohub.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
    }
}
