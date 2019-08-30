package info.funds.rxjavasearch.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created on : August 30, 2019
 * Author     : Setia
 */

public class Funds {

    @SerializedName("fundCode")
    String fundCode;

    @SerializedName("name")
    String name;

    @SerializedName("3yrreturns")
    String threeyrreturns;

    @SerializedName("nav")
    String nav;

    @SerializedName("fundCategory")
    String fundCategory;

    @SerializedName("icon")
    String icon;

    @SerializedName("rating")
    int rating;

    public String getFundCode() {
        return fundCode;
    }

    public String getName() {
        return name;
    }

    public String getThreeyrreturns() {
        return threeyrreturns;
    }

    public String getNav() {
        return nav;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public String getIcon() {
        return icon;
    }

    public int getRating() {
        return rating;
    }
}
