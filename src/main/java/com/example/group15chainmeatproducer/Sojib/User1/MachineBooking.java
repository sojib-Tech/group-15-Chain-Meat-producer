package com.example.group15chainmeatproducer.Sojib.User1;

import java.io.Serializable;

public class MachineBooking implements Serializable {
    private String machineId;
    private String bookerName;
    private String animalId;
    private String bookingDate;

    public MachineBooking() {
    }

    public MachineBooking(String machineId, String bookerName, String animalId, String bookingDate) {
        this.machineId = machineId;
        this.bookerName = bookerName;
        this.animalId = animalId;
        this.bookingDate = bookingDate;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}