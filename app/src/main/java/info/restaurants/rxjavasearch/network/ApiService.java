package info.restaurants.rxjavasearch.network;

import info.restaurants.rxjavasearch.network.model.RestaurantWrapper;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created on : Sep 3, 2019
 * Author     : Setia
 */

public interface ApiService {

    @GET("/geocode?lat=28.4595&lon=77.0266")
    Single<RestaurantWrapper> getRestaurant();
//    Single<RestaurantWrapper> getRestaurant(@Path("lat") String lat, @Path("lon") String lon);
}
