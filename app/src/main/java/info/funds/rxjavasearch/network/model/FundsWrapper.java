package info.funds.rxjavasearch.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created on : August 30, 2019
 * Author     : Setia
 */

public class FundsWrapper {

    @SerializedName("data")
    private List<Funds> mData;

    public List<Funds> getFunds() {
        return mData;
    }

    public void setFunds(List<Funds> data) {
        mData = data;
    }
}
