package com.example.lool;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


import static com.example.lool.DBUtils.saveRatingToDatabase;

public class rating {
    DataSingleton dataSingleton=DataSingleton.getInstance();
    String username= dataSingleton.getUsername();


    @FXML
    private HBox starRatingBox;
    private static final String STAR_EMPTY_PATH = "file:C:\\Users\\sebir\\LooL\\src\\main\\resources\\png-clipart-star-star-angle-triangle.png";
    private static final String STAR_FILLED_PATH = "file:C:\\Users\\sebir\\LooL\\src\\main\\resources\\png-transparent-gold-star-star-star-angle-orange-symmetry-thumbnail.png";

    private int maxRating = 5; // Change this based on your requirement
    private int userRating = 0;

    @FXML
    public void initialize(HBox starRatingBox, int championId,DatePicker datePicker,Label label) {
        // Check if the user has already rated the champion
        int previousRating = DBUtils.getPreviousRating(championId,username);

        // Populate the star rating box with stars based on the previous rating
        for (int i = 0; i < maxRating; i++) {
            ImageView star = createStar(i < previousRating ? STAR_FILLED_PATH : STAR_EMPTY_PATH);
            int rating = i + 1; // Ratings start from 1
            star.setOnMouseClicked(event -> handleStarClick(rating, starRatingBox, championId,datePicker));
            starRatingBox.getChildren().add(star);
        }
        DBUtils.getAverageReview(championId,label);

    }





private ImageView createStar(String imagePath) {
        ImageView star = new ImageView(new Image(imagePath));
        star.setFitWidth(30);
        star.setFitHeight(30);
        return star;
    }

    private void handleStarClick(int rating, HBox starRatingBox, int championId, DatePicker datePicker) {
        // Update the userRating and change the star images accordingly
        userRating = rating;

        for (int i = 0; i < maxRating; i++) {
            ImageView star = (ImageView) starRatingBox.getChildren().get(i);
            star.setImage(i < rating ? new Image(STAR_FILLED_PATH) : new Image(STAR_EMPTY_PATH));
        }

        // Save the rating in the database
        saveRatingToDatabase(championId,rating,username,datePicker);

        // You can perform any additional actions based on the user's rating here
        System.out.println("User rating: " + userRating);
    }

    public rating(HBox starRatingBox) {
        this.starRatingBox = starRatingBox;

    }
    public void clearStars() {
        starRatingBox.getChildren().clear();
    }
}

