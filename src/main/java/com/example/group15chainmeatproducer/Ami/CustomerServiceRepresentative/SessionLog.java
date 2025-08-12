package com.example.group15chainmeatproducer.Ami.CustomerServiceRepresentative;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SessionLog {
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>(this, "date");
    private final StringProperty activity = new SimpleStringProperty(this, "activity");
    private final StringProperty status = new SimpleStringProperty(this, "status");

    public SessionLog() {
    }

    public SessionLog(LocalDate date, String activity, String status) {
        setDate(date);
        setActivity(activity);
        setStatus(status);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate value) {
        date.set(value);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public String getActivity() {
        return activity.get();
    }

    public void setActivity(String value) {
        activity.set(value);
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
