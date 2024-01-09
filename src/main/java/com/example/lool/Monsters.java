package com.example.lool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Monsters implements Initializable {
    @FXML
    private Button goback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goback.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));

    }
}
