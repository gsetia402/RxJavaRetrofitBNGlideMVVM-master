package info.movies.rxjavasearch.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created on : August 16, 2019
 * Author     : Setia
 */

public class Movie {

    @SerializedName("poster")
    String poster;

    @SerializedName("title")
    String title;

    @SerializedName("genre")
    String genre;

    @SerializedName("year")
    String year;

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

}
