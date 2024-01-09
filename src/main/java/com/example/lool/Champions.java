package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.ResourceBundle;

public class Champions implements Initializable {
     @FXML
     private Button mainpage;
     @FXML
     private Button ahri;
    @FXML
    private Button blitz;
    @FXML
    private Button ashe;
    @FXML
    private Button sion;
    @FXML
    private Button warwick;
    @FXML
    private Button master;
    @FXML
    private Button darius;
    @FXML
    private Button miss;
    @FXML
    private Button teemo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
             mainpage.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"LogIn.fxml",null,null,735,620));
             ahri.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"ahri.fxml",null,null,735,620));
             blitz.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"blitz.fxml",null,null,735,620));
             teemo.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"teemo.fxml",null,null,735,620));
             master.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"master.fxml",null,null,735,620));
             sion.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"sion.fxml",null,null,735,620));
             miss.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"miss.fxml",null,null,735,620));
             darius.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"darius.fxml",null,null,735,620));
             warwick.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"warwick.fxml",null,null,735,620));
             ashe.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent,"ashe.fxml",null,null,735,620));
    }

}
