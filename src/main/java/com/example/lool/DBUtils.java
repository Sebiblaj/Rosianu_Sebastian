package com.example.lool;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;



public class DBUtils {

    public static void SelectSkin(String url, ImageView imageView) {
        try {
            Image image = new Image(url);
            imageView.setImage(image);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static void changeScene(ActionEvent event, String fxmlFile, String username, String password, int v1, int v2) {
        Parent root = null;
        if (username != null && password != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LogIn loggedInController = loader.getController();
                loggedInController.setUserInformation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, v1, v2));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void signUpUser(ActionEvent event, String username, String password, String email, String gender) throws AssertionError {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "postgres", "postgres", "Sebilica09");
            if (connection == null) throw new AssertionError();
            preparedStatement1 = connection.prepareStatement("SELECT * FROM \"LoL-schema\".users WHERE username = ?");
            preparedStatement1.setString(1, username);
            resultSet = preparedStatement1.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("user already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                preparedStatement = connection.prepareStatement("INSERT INTO \"LoL-schema\".users (username, password, email ,gender) VALUES (?,?,?,?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, gender);

                // changeScene(event,"LogIn.fxml",username,password);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully.");
                    changeScene(event, "LogIn.fxml", username, password, 735, 620);
                } else {
                    System.out.println("Failed to insert data.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement1 != null)
                try {
                    preparedStatement1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password, Label invalidLogin) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "postgres", "postgres", "Sebilica09");
            if (connection == null) throw new AssertionError();
            preparedStatement = connection.prepareStatement("SELECT password FROM \"LoL-schema\".users where username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                invalidLogin.setText("Contul acesta nu exista!");
                invalidLogin.setVisible(true);
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "LogIn.fxml", username, password, 735, 620);

                    } else {
                        invalidLogin.setText("Parola nu se potriveste!");
                        invalidLogin.setVisible(true);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// ...

    public static void saveRatingToDatabase(int championId, int rating, String username, DatePicker datePicker) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "postgres", "postgres", "Sebilica09");

            // Get userid using the first query
            String selectQuery = "SELECT userid FROM \"LoL-schema\".users WHERE username = ?";
            preparedStatement1 = connection.prepareStatement(selectQuery);
            preparedStatement1.setString(1, username);
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("userid");

                // Insert rating and date into the database
                String insertQuery = "INSERT INTO \"LoL-schema\".reviews (userid, championid, rating, reviewdate) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, championId);
                preparedStatement.setInt(3, rating);

                // Get the selected date from the DatePicker
                java.sql.Date sqlDate = java.sql.Date.valueOf(datePicker.getValue());
                preparedStatement.setDate(4, sqlDate);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately (log it, throw it, etc.)
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    static int getUserIdByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "postgres", "postgres", "Sebilica09");

            String selectQuery = "SELECT userid FROM \"LoL-schema\".users WHERE username = ?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("userid");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try_catch(connection, preparedStatement, resultSet);
        }

        // Return -1 if no user ID is found (you can choose an appropriate default value)
        return -1;
    }

    static public int getPreviousRating(int championId, String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + "postgres", "postgres", "Sebilica09");

            // Fetch the rating with the highest reviewid
            String selectQuery = "SELECT rating FROM \"LoL-schema\".reviews WHERE userid = ? AND championid = ? ORDER BY reviewid DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, getUserIdByUsername(username));
            preparedStatement.setInt(2, championId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("rating");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try_catch(connection, preparedStatement, resultSet);
        }

        // Return 0 if no previous rating is found
        return 0;
    }

    public static void getRunes(Label first, Label second, Label third, Label fourth, Label fifth, Label sixth, Label spell1, Label spell2, int championid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sebilica09");

            // Fetch the rating with the highest reviewid
            String selectQuery = "SELECT first,second,third,fourth,fifth,sixth,spell1,spell2 FROM \"LoL-schema\".runes WHERE runeid=?";

            // Correct order: create preparedStatement first, then set parameters
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, championid);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String first1 = resultSet.getString("first");
                String second1 = resultSet.getString("second");
                String third1 = resultSet.getString("third");
                String fourth1 = resultSet.getString("fourth");
                String fifth1 = resultSet.getString("fifth");
                String sixth1 = resultSet.getString("sixth");
                String spell11 = resultSet.getString("spell1");
                String spell21 = resultSet.getString("spell2");

                // Now, set the retrieved values to your labels or do something else with them
                first.setText(first1);
                second.setText(second1);
                third.setText(third1);
                fourth.setText(fourth1);
                fifth.setText(fifth1);
                sixth.setText(sixth1);
                spell1.setText(spell11);
                spell2.setText(spell21);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try_catch(connection, preparedStatement, resultSet);
        }
    }
    public static void getBuild(Label first, Label second, Label third, Label fourth, Label fifth, Label sixth, int championid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sebilica09");

            // Fetch the rating with the highest reviewid
            String selectQuery = "SELECT first,second,third,fourth,fifth,sixth FROM \"LoL-schema\".items WHERE itemid=?";

            // Correct order: create preparedStatement first, then set parameters
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, championid);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String first1 = resultSet.getString("first");
                String second1 = resultSet.getString("second");
                String third1 = resultSet.getString("third");
                String fourth1 = resultSet.getString("fourth");
                String fifth1 = resultSet.getString("fifth");
                String sixth1 = resultSet.getString("sixth");

                // Now, set the retrieved values to your labels or do something else with them
                first.setText(first1);
                second.setText(second1);
                third.setText(third1);
                fourth.setText(fourth1);
                fifth.setText(fifth1);
                sixth.setText(sixth1);

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try_catch(connection, preparedStatement, resultSet);
        }
    }

    private static void try_catch(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public static void createImageRunes(ImageView first,ImageView second,ImageView third,ImageView fourth,ImageView fifth,ImageView sixth,ImageView spell1,ImageView spell2,int championid){
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;
       try {
           Class.forName("org.postgresql.Driver");
           connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sebilica09");

           // Fetch the rating with the highest reviewid
           String selectQuery = "SELECT first_img,second_img,third_img,fourth_img,fifth_img,sixth_img,spell1_img,spell2_img FROM \"LoL-schema\".runes_images WHERE runeid=?";

           // Correct order: create preparedStatement first, then set parameters
           preparedStatement = connection.prepareStatement(selectQuery);
           preparedStatement.setInt(1, championid);

           resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               String first1 = resultSet.getString("first_img");
               String second1 = resultSet.getString("second_img");
               String third1 = resultSet.getString("third_img");
               String fourth1 = resultSet.getString("fourth_img");
               String fifth1 = resultSet.getString("fifth_img");
               String sixth1 = resultSet.getString("sixth_img");
               String spell11 = resultSet.getString("spell1_img");
               String spell21 = resultSet.getString("spell2_img");

               // Now, set the retrieved values to your labels or do something else with them
               first.setImage(new Image(first1));
               second.setImage(new Image(second1));
               third.setImage(new Image(third1));
               fourth.setImage(new Image(fourth1));
               fifth.setImage(new Image(fifth1));
               sixth.setImage(new Image(sixth1));
               spell1.setImage(new Image(spell11));
               spell2.setImage(new Image(spell21));

           }
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       } finally {
           try_catch(connection, preparedStatement, resultSet);
       }
   }
    public static void createImageItems(ImageView first,ImageView second,ImageView third,ImageView fourth,ImageView fifth,ImageView sixth,int championid){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sebilica09");

            // Fetch the rating with the highest reviewid
            String selectQuery = "SELECT first_img,second_img,third_img,fourth_img,fifth_img,sixth_img FROM \"LoL-schema\".items_images WHERE itemid=?";

            // Correct order: create preparedStatement first, then set parameters
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, championid);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String first1 = resultSet.getString("first_img");
                String second1 = resultSet.getString("second_img");
                String third1 = resultSet.getString("third_img");
                String fourth1 = resultSet.getString("fourth_img");
                String fifth1 = resultSet.getString("fifth_img");
                String sixth1 = resultSet.getString("sixth_img");

                // Now, set the retrieved values to your labels or do something else with them
                first.setImage(new Image(first1));
                second.setImage(new Image(second1));
                third.setImage(new Image(third1));
                fourth.setImage(new Image(fourth1));
                fifth.setImage(new Image(fifth1));
                sixth.setImage(new Image(sixth1));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try_catch(connection, preparedStatement, resultSet);
        }
    }
    public static void getAverageReview( int championid,Label label){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sebilica09");

            // Fetch the rating with the highest reviewid
            String selectQuery = "SELECT round(AVG(rating),1) AS avg_rating FROM \"LoL-schema\".reviews WHERE championid = ?";

            // Correct order: create preparedStatement first, then set parameters
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1,championid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                double avgrating=resultSet.getDouble("avg_rating");
                label.setText(" "+avgrating);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try_catch(connection, preparedStatement, resultSet);
        }
    }
}

