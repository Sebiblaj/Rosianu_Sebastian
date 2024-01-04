package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Button forgotPassword;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox remember;

    @FXML
    private Button signup;

    @FXML
    private TextField username;
    @FXML
    private Label invalidLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invalidLogin.setVisible(false);
        login.setOnAction(event -> DBUtils.logInUser(event,username.getText(),password.getText(),invalidLogin));

        signup.setOnAction(event -> DBUtils.changeScene(event,"SignUp.fxml",null,null));
    }
}