package com.example.weatherapplication;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    /**
     *
     * @param city
     * @param apiKey
     * @param units
     * @return
     *
     * Call<T>: Represents an HTTP request that can be executed asynchronously or synchronously.
     * @GET: Annotation used to define a GET request.
     * @Query: Used to append query parameters to the request URL.
     */
    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
