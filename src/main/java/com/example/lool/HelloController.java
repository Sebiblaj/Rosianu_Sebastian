package com.example.lool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {




    @FXML
    private Button login;

    @FXML
    private PasswordField password;


    @FXML
    private Button signup;

    @FXML
    private TextField username;
    @FXML
    private Label invalidLogin;
    @FXML
    private CheckBox remember;
    @FXML
    private PasswordField pin;

    DataSingleton dataSingleton= DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invalidLogin.setVisible(false);
        pin.setVisible(false);
        remember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pin.setVisible(remember.isSelected());
            }
        });
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dataSingleton.setUsername(username.getText());
                String enteredPassword = password.getText();
                if (pin.isVisible() && Objects.equals(pin.getText(), "5030")) {
                    // Correct PIN entered, navigate to the next page
                    DBUtils.changeScene(actionEvent, "Admin.fxml", null, null, 735, 620);
                } else {
                    // No PIN or incorrect PIN entered, proceed with login
                    DBUtils.logInUser(actionEvent, username.getText(), enteredPassword, invalidLogin);
                }
            }
        });


        signup.setOnAction(event -> DBUtils.changeScene(event,"SignUp.fxml",null,null,622,340));
    }

}