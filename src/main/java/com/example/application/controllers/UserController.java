package com.example.application.controllers;

public class UserController {
 
    private static Integer userId;
    private String username;
    private String password;

    public UserController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Integer getId() {
        return userId;
    }

    public static void setUserId(Integer id) {
        userId = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
