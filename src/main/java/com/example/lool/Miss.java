package com.example.lool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Miss implements Initializable {
    @FXML
    private ImageView first_img;
    @FXML
    private ImageView second_img;
    @FXML
    private ImageView third_img;
    @FXML
    private ImageView fourth_img;
    @FXML
    private ImageView fifth_img;
    @FXML
    private ImageView sixth_img;
    @FXML
    private ImageView first_img1;
    @FXML
    private ImageView second_img1;
    @FXML
    private ImageView third_img1;
    @FXML
    private ImageView fourth_img1;
    @FXML
    private ImageView fifth_img1;
    @FXML
    private ImageView sixth_img1;
    @FXML
    private ImageView spell1_img;
    @FXML
    private ImageView spell2_img;
    @FXML
    private Button build;
    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Label third;
    @FXML
    private Label fourth;
    @FXML
    private Label fifth;
    @FXML
    private Label sixth;
    @FXML
    private Label first1;
    @FXML
    private Label second1;
    @FXML
    private Label third1;
    @FXML
    private Label fourth1;
    @FXML
    private Label fifth1;
    @FXML
    private Label sixth1;
    @FXML
    private Label spell1;
    @FXML
    private Label spell2;
    @FXML
    private Button lore;
    @FXML
    private Label Lore;
    @FXML
    private Button hide;
    @FXML
    private DatePicker date_ahri;
    @FXML
    private Button goback_ahri;
    @FXML
    private Button original_ahri;
    @FXML
    private Button dinasty_ahri;
    @FXML
    private Button midnight_ahri;

    @FXML
    private ImageView image_ahri;
    @FXML
    private HBox ahri_hbox;
    @FXML
    private Pane RunePanel;
    @FXML
    private Pane RunePanel1;
    @FXML
    private Label Lore1;
    @FXML
    private Label avg;

    private rating ratingController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Lore.setVisible(false);
        Lore1.setVisible(false);
        RunePanel.setVisible(false);
        RunePanel1.setVisible(false);

        goback_ahri.setOnAction(actionEvent -> DBUtils.changeScene(actionEvent, "Champions.fxml", null, null, 735, 620));

        build.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.getRunes(first, second, third, fourth, fifth, sixth, spell1, spell2, 6);
                DBUtils.getBuild(first1, second1, third1, fourth1, fifth1, sixth1, 6);
                DBUtils.createImageRunes(first_img,second_img,third_img,fourth_img,fifth_img,sixth_img,spell1_img,spell2_img,6);
                DBUtils.createImageItems(first_img1,second_img1,third_img1,fourth_img1,fifth_img1,sixth_img1,6);
                RunePanel.setVisible(true);
                RunePanel1.setVisible(true);
            }
        });

        ratingController = new rating(ahri_hbox);

        lore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Lore.setVisible(true);
                Lore1.setVisible(true);
            }
        });
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Lore.setVisible(false);
                Lore1.setVisible(false);
                RunePanel.setVisible(false);
                RunePanel1.setVisible(false);
            }
        });

        original_ahri.setOnAction(actionEvent -> {
            String url1 = "C:\\Users\\sebir\\LooL\\src\\main\\resources\\miss-fortune-broken-covenant-762x.jpg";
            DBUtils.SelectSkin("file:" + url1, image_ahri);


            // Initialize and set up the rating system
            date_ahri.setVisible(false);
            ratingController.clearStars();
            ratingController.initialize(ahri_hbox, 6, date_ahri,avg);
            date_ahri.setVisible(true);


        });
        dinasty_ahri.setOnAction(actionEvent -> {
            String url12 = "C:\\Users\\sebir\\LooL\\src\\main\\resources\\miss-fortune-broken-covenant-prestige-762x.jpg";
            DBUtils.SelectSkin("file:" + url12, image_ahri);

            // Initialize and set up the rating system
            ratingController.clearStars();
            ratingController.initialize(ahri_hbox, 6, date_ahri,avg);


        });

        midnight_ahri.setOnAction(actionEvent -> {
            String url13 = "C:\\Users\\sebir\\LooL\\src\\main\\resources\\miss-fortune-battle-bunny-762x.jpg";
            DBUtils.SelectSkin("file:" + url13, image_ahri);
            // Initialize and set up the rating system
            ratingController.clearStars();
            ratingController.initialize(ahri_hbox, 6, date_ahri,avg);

        });


    }




}

