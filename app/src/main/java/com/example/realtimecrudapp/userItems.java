package com.example.realtimecrudapp;

public class userItems {
    String userID;
    String userName;
    String userEmail;
    String userCountry;

    public userItems(String userID, String userName, String userEmail, String userCountry) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCountry = userCountry;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserCountry() {
        return userCountry;
    }

}
