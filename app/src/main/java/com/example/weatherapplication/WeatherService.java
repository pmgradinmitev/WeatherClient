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
     *
     * @GET("weather"): Specifies that this method will make a GET request to the "weather" endpoint.
     * Return Type (Call<WeatherResponse>): The response will be wrapped in a Call object containing a WeatherResponse object.
     * Method Name (getWeather): This is the function name used when calling the API.
     * Query Parameters (@Query):
     * @Query("q") String city: Specifies the city name for which weather data is requested.
     * @Query("appid") String apiKey: The API key required for authentication.
     * @Query("units") String units: Specifies the measurement units (e.g., "metric" for Celsius, "imperial" for Fahrenheit).
     * Example API Call URL:
     * If you call getWeather("London", "your_api_key", "metric"), Retrofit will construct a URL like:
     *
     * https://api.openweathermap.org/data/2.5/weather?q=London&appid=your_api_key&units=metric
     */
    @GET("weather")
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
