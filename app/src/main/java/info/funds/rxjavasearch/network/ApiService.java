package info.funds.rxjavasearch.network;

import info.funds.rxjavasearch.network.model.FundsWrapper;
import info.funds.rxjavasearch.network.model.MovieWrapper;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created on : August 30, 2019
 * Author     : Setia
 */

public interface ApiService {

    @GET("/api/v1/funds")
    Single<FundsWrapper> getFunds();
}
