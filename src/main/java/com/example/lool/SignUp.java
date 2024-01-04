package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Button login;

    @FXML
    private RadioButton male;

    @FXML
    private PasswordField password;

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup=new ToggleGroup();
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);

        signup.setOnAction(event -> {
            String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
            if (!username.getText().trim().isEmpty() && !password.getText().trim().isEmpty()) {
                DBUtils.signUpUser(event, username.getText(), password.getText(), email.getText(), toggleName);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill up all the fields");
                alert.show();
            }
        });
        login.setOnAction(event -> DBUtils.changeScene(event,"hello-view.fxml",null,null));
    }

}
