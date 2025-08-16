package com.example.group15chainmeatproducer.Saiful.User4.models;

import java.io.Serializable;
import java.time.LocalDate;

public class DispatchStock implements Serializable {
    private String itemId;
    private String quantity;
    private String team;
    private String status;
    private LocalDate dispatchDate;

    public DispatchStock() {
    }

    public DispatchStock(String itemId, String quantity, String team, String status, LocalDate dispatchDate) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.team = team;
        this.status = status;
        this.dispatchDate = dispatchDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }
}
