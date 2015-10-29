package com.samuelhavard.popularmovies;

/**
 * Created by samue_000 on 10/29/2015.
 */
public class MovieData {
    String title;
    String plot;
    String rating;
    String popularity;
    String date;
    String image;

    /*
    json title = "original_title"
    TODO figure out if its poster or backdrop that is needed.
    json image string = "backdrop_path" or maybe "overview"
    json plot = "overview"
    json popularity = "popularity"
    json rating = "vote_average"

     */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
