package com.pietertolsma.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pietertolsma on 11/24/15.
 */
public class MovieItem implements Parcelable{

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

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(movieName);
        out.writeString(imageUrl);
        out.writeString(releaseDate);
        out.writeString(movieId);
        out.writeInt(moviePosition);
        out.writeString(rating);
        out.writeString(description);
    }

    @Override
    public int describeContents() {
    // ignore for now
        return 0;
    }
    public static final Parcelable.Creator<MovieItem> CREATOR
            = new Parcelable.Creator<MovieItem>() {
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    private MovieItem(Parcel in) {
        movieName = in.readString();
        imageUrl = in.readString();
        releaseDate = in.readString();
        movieId = in.readString();
        moviePosition = in.readInt();
        rating = in.readString();
        description = in.readString();
    }
}