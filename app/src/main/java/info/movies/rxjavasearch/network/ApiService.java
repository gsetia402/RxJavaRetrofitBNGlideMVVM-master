package info.movies.rxjavasearch.network;

import info.movies.rxjavasearch.network.model.MovieWrapper;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created on : August 16, 2019
 * Author     : Setia
 */

public interface ApiService {

    @GET("/api/movies")
    Single<MovieWrapper> getMovie();
}
