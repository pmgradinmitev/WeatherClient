package com.example.weatherapplication;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView weatherText;
    private EditText cityInput;
    private Button fetchButton;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "API KEY"; // Replace with your OpenWeatherMap API Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherText = findViewById(R.id.weatherText);
        cityInput = findViewById(R.id.cityInput);
        fetchButton = findViewById(R.id.fetchButton);

        fetchButton.setOnClickListener(v -> getWeather(cityInput.getText().toString()));
    }

    private void getWeather(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getWeather(city, API_KEY, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    weatherText.setText("City: " + response.body().cityName + "\nTemp: " + response.body().main.temperature + "°C\nDescription: " + response.body().weather[0].description);
                }
            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherText.setText("Error fetching weather");
            }
        });
    }
}
