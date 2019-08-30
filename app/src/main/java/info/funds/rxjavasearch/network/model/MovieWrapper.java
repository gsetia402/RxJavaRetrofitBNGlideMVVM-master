package info.funds.rxjavasearch.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created on : August 30, 2019
 * Author     : Setia
 */

public class MovieWrapper {

    @SerializedName("data")
    private List<Movie> mData;

    public List<Movie> getMovie() {
        return mData;
    }

    public void setMovie(List<Movie> data) {
        mData = data;
    }
}
