package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;
import java.time.LocalDate;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productName;
    private int quantity;
    private LocalDate dateAddedToCart;

    public CartItem() {
    }

    public CartItem(String productName, int quantity, LocalDate dateAddedToCart) {
        this.productName = productName;
        this.quantity = quantity;
        this.dateAddedToCart = dateAddedToCart;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateAddedToCart() {
        return dateAddedToCart;
    }

    public void setDateAddedToCart(LocalDate dateAddedToCart) {
        this.dateAddedToCart = dateAddedToCart;
    }
}
