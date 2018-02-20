package com.sahib.nomos.Rest;

import com.sahib.nomos.models.AssetsResponse;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sahib on 1/14/2018.
 */

public interface CryptoService {
    @GET("v1/assets/")
    Call<List<AssetsResponse>> getAssets(@Header("X-CoinAPI-Key") String apiKey);
    //Call<List<AssetsResponse>> getAssets(@Query("X-CoinAPI-Key") String apiKey);

/*    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);*/
}
