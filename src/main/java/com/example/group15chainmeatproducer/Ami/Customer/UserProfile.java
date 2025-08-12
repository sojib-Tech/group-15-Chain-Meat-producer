package com.example.group15chainmeatproducer.Ami.Customer;

import java.time.LocalDate;

public class UserProfile {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String country;
    private String gender;
    private LocalDate dateOfBirth;

    public UserProfile() {
    }

    public UserProfile(String fullName, String phoneNumber, String address, String country, String gender, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
