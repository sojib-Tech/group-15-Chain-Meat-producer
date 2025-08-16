package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String category;
    private LocalDate dateAdded;
    private String description;

    public Product() {
    }

    public Product(String name, String category, LocalDate dateAdded, String description) {
        this.name = name;
        this.category = category;
        this.dateAdded = dateAdded;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
