package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.io.Serializable;
import java.time.LocalDate;

public class LoginAttempt implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private LocalDate loginDate;
    private String status;

    public LoginAttempt() {
    }

    public LoginAttempt(String username, LocalDate loginDate, String status) {
        this.username = username;
        this.loginDate = loginDate;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}