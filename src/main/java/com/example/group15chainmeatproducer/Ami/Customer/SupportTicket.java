package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;

public class SupportTicket implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ticketId;
    private String date;
    private String status;

    public SupportTicket(String ticketId, String date, String status) {
        this.ticketId = ticketId;
        this.date = date;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
