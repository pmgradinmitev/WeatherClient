package com.example.weatherapplication;
import com.google.gson.annotations.SerializedName;

/**
 * This WeatherResponse class is a model used to map the JSON response from the OpenWeatherMap API
 * into a Java object using Retrofit and Gson.
 */
public class WeatherResponse {
    @SerializedName("name")
    public String cityName;

    @SerializedName("main")
    public Main main;

    @SerializedName("weather")
    public Weather[] weather;

    public class Main {
        @SerializedName("temp")
        public float temperature;
    }

    public class Weather {
        @SerializedName("description")
        public String description;
    }
}
