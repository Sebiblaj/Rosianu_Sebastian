package com.example.lool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static org.postgresql.PGProperty.forName;


public class DBUtils {
    public static void changeScene(ActionEvent event,String fxmlFile,String username,String password){
        Parent root=null;
        if(username!=null && password!=null){
            try{
                FXMLLoader loader=new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root=loader.load();
                LogIn loggedInController=loader.getController();
                loggedInController.setUserInformation(username);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 615, 340));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void signUpUser(ActionEvent event,String username,String password,String email,String gender){
        Connection connection=null;
        PreparedStatement preparedStatement1=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{

            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"postgres","postgres","Sebilica09");
            if (connection == null) throw new AssertionError();
            preparedStatement1 = connection.prepareStatement("SELECT * FROM \"LoL-schema\".users WHERE username = ?");
            preparedStatement1.setString(1,username);
            resultSet=preparedStatement1.executeQuery();
            if(resultSet.isBeforeFirst()){
                System.out.println("user already exists");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            }
            else{
                preparedStatement= connection.prepareStatement("INSERT INTO \"LoL-schema\".users (username, password, email ,gender) VALUES (?,?,?,?)");
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);
                preparedStatement.setString(3,email);
                preparedStatement.setString(4,gender);

               // changeScene(event,"LogIn.fxml",username,password);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                    changeScene(event, "LogIn.fxml", username, password);
                } else {
                    System.out.println("Failed to insert data.");
                }
            }
        }catch(SQLException  | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            if(resultSet!=null)
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if(preparedStatement1!=null)
                try{
                    preparedStatement1.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if(preparedStatement!=null)
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if(connection!=null)
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password, Label invalidLogin){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"postgres","postgres","Sebilica09");
            if (connection == null) throw new AssertionError();
            preparedStatement=connection.prepareStatement("SELECT password FROM \"LoL-schema\".users where username = ?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                invalidLogin.setText("Contul acesta nu exista!");
                invalidLogin.setVisible(true);
            }else{
                while(resultSet.next()){
                    String retrievedPassword=resultSet.getString("password");
                    if(retrievedPassword.equals(password)){
                        changeScene(event,"LogIn.fxml",username,password);
                    }
                    else{
                        invalidLogin.setText("Parola nu se potriveste!");
                        invalidLogin.setVisible(true);
                    }
                }

            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}
