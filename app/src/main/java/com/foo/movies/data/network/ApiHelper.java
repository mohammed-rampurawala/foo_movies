package com.foo.movies.data.network;


import com.foo.movies.data.model.ConfigurationResponse;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public interface ApiHelper {
    Observable<ConfigurationResponse> getApiConfiguration();

}
