package com.example.lool;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    @FXML
    private Button goback;
    @FXML
    private Button gohub;
    @FXML
    private Button update;
    @FXML
    private Button create;
    @FXML
    private Button delete;
    @FXML
    private TableView<user> table;  // Specify the type of TableView as user
    @FXML
    private TableColumn<user, Integer> userid;
    @FXML
    private TableColumn<user, String> username;
    @FXML
    private TableColumn<user, String> password;
    @FXML
    private TableColumn<user, String> email;
    @FXML
    private TableColumn<user, String> gender;
    private List<user> modifiedUsers = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goback.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent, "hello-view.fxml", null, null, 622, 340));
        gohub.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent, "LogIn.fxml", null, null, 735, 620));
        create.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent, "SignUpAdmin.fxml", null, null, 622, 340));
        delete.setOnAction(actionEvent -> {
            user selectedUser = table.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                DBUtils.deleteUser(selectedUser);
                // Refresh the table or perform other necessary updates
                table.setItems(DBUtils.table());
            } else {
                // Show an alert or message indicating that no user is selected
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please select a user to delete.");
                alert.show();
            }
        });

        userid.setCellValueFactory(new PropertyValueFactory<>("userid"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        table.setEditable(true);

        username.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        gender.setCellFactory(TextFieldTableCell.forTableColumn());

        username.setOnEditCommit(event -> handleEditCommit(event, "username"));
        password.setOnEditCommit(event -> handleEditCommit(event, "password"));
        email.setOnEditCommit(event -> handleEditCommit(event, "email"));
        gender.setOnEditCommit(event -> handleEditCommit(event, "gender"));

        update.setOnAction(event -> handleUpdateButtonClick());
        table.setItems(DBUtils.table());

    }
    private void handleEditCommit(TableColumn.CellEditEvent<user, String> event, String column) {
        user editedUser = event.getRowValue();
        String newValue = event.getNewValue();

        System.out.println("Editing " + column + " for user: " + editedUser.getUsername() + " to new value: " + newValue);

        switch (column) {
            case "username":
                editedUser.setUsername(newValue);
                break;
            case "password":
                editedUser.setPassword(newValue);
                break;
            case "email":
                editedUser.setEmail(newValue);
                break;
            case "gender":
                editedUser.setGender(newValue);
                break;
            // Add more cases for other columns if needed
        }

        // Store the modified user in the collection

        modifiedUsers.add(editedUser);
        System.out.println("Modified users size: " + modifiedUsers.size());
    }

    private void handleUpdateButtonClick() {
        System.out.println("Updating " + modifiedUsers.size() + " users...");

        for (user modifiedUser : modifiedUsers) {
            DBUtils.updateUser(modifiedUser);
        }

        // Clear the selection and refresh the table
        table.getSelectionModel().clearSelection();
        table.refresh();

        System.out.println("Update complete.");

        modifiedUsers.clear();
    }

}
