package com.example.lool;

import javafx.beans.property.*;

public class user {
    private final IntegerProperty userid;
    private StringProperty username;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty gender;

    public user(int userid, String username, String email, String password, String gender) {
        this.userid = new SimpleIntegerProperty(userid);
        this.username = new SimpleStringProperty(username);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.gender = new SimpleStringProperty(gender);
    }

    public int getUserid() {
        return userid.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty usernameProperty(){
        return username;
    }
    public void setUsername(String newUsername){
        username.set(newUsername);
    }
    public StringProperty emailProperty(){
        return email;
    }
    public void setEmail(String newUsername){
        email.set(newUsername);
    }
    public StringProperty passwordProperty(){
        return password;
    }
    public void setPassword(String newUsername){
        password.set(newUsername);
    }
    public StringProperty genderProperty(){
        return gender;
    }
    public void setGender(String newUsername){
        gender.set(newUsername);
    }
}
