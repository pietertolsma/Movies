package com.pietertolsma.movies;

/**
 * Created by pietertolsma on 11/24/15.
 */
public class MovieItem {

    String movieName;
    String imageUrl;
    String description;
    String movieId;
    String rating;
    String releaseDate;
    int moviePosition;

    public MovieItem(String title, String releaseDate, String rating, String imageUrl, String movieId, String description, int position){
        movieName = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.movieId = movieId;
        moviePosition = position;
    }
}