package com.example.fidel116.firebaseintegration.model;

public class MovieData {
    private String movie_id;
    private String user_id;
    private String name;
    private String review;
    private float rating;

    public MovieData() {
    }

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }
}