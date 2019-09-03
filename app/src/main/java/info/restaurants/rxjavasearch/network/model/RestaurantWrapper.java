package info.restaurants.rxjavasearch.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RestaurantWrapper {

    @SerializedName("nearby_restaurants")
    private List<Restaurant> mData;

    public List<Restaurant> getRestaurant() {
        return mData;
    }

    public void setRestaurant(List<Restaurant> data) {
        mData = data;
    }
}
