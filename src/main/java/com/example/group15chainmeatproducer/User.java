package com.example.group15chainmeatproducer;

public class User {
    private int id;
    private String fullName;
    private String idNumber;
    private String userType;
    private String password;

    public User() {
    }

    public User(String fullName, String idNumber, String userType, String password) {
        this.fullName = fullName;
        this.idNumber = idNumber;
        this.userType = userType;
        this.password = password;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}