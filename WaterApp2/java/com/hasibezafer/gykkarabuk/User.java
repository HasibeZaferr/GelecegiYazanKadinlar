package com.hasibezafer.gykkarabuk;

/**
 * Created by hasibezafer on 11.06.2017.
 */

public class User {

    private String userName;
    private boolean userGender;
    private String waterAmount;


    public User(String userName, boolean userGender, String waterAmount) {
        this.userName = userName;
        this.userGender = userGender;
        this.waterAmount = waterAmount;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUserGender() {
        return userGender;
    }

    public void setUserGender(boolean userGender) {
        this.userGender = userGender;
    }

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }





}
