package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Tournaments implements Initializable {
    @FXML
    private Button goback;
    @FXML
    private Button goback2;
    @FXML
    private Button goback3;
    @FXML
    private Button goback4;
    @FXML
    private Button goback5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goback.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
        goback2.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
        goback3.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
        goback4.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
        goback5.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
    }
}
