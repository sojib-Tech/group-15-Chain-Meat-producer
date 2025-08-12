package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;
import javafx.scene.image.Image;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String imagePath; // optional path to image resource
    private String name;
    private double price;
    private String shortDescription;

    public Product(Image image, String name, double price, String shortDescription) {
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
    }

    public Product(String id, Image image, String name, double price, String shortDescription) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
    }

    public Product(String id, String imagePath, String name, double price, String shortDescription) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // For existing code expecting Image, return null (image loading can be added later)
    public Image getImage() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
