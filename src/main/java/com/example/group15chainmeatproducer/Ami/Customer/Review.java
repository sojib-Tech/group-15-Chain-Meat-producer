package com.example.group15chainmeatproducer.Ami.Customer;

import java.io.Serializable;

public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reviewer;
    private int rating;
    private String comment;

    public Review(String reviewer, int rating, String comment) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comment = comment;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
