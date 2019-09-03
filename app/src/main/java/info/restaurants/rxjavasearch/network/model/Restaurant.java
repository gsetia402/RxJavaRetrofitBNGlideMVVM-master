package info.restaurants.rxjavasearch.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created on : August 30, 2019
 * Author     : Setia
 */

public class Restaurant {


    @Expose
    @SerializedName("restaurant")
    public RestaurantModel restaurant;

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public static class RestaurantModel{

        @SerializedName("name")
        String name;

        @SerializedName("average_cost_for_two")
        String average_cost_for_two;

        @SerializedName("3yrreturns")
        String threeyrreturns;

        @SerializedName("thumb")
        String thumb;

        @SerializedName("events_url")
        String events_url;

        @SerializedName("location")
        String location;

        @SerializedName("user_rating")
        String user_rating;


        public String getName() {
            return name;
        }

        public String getAverage_cost_for_two() {
            return average_cost_for_two;
        }

        public String getThreeyrreturns() {
            return threeyrreturns;
        }

        public String getThumb() {
            return thumb;
        }

        public String getEvents_url() {
            return events_url;
        }
    }



}
