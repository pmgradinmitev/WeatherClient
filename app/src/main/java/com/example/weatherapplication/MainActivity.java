/**
 * This specifies the package name for the application.
 * It's used for organizing your project and avoiding class name conflicts.
 */
package com.example.weatherapplication;

/**
 * These imports bring in necessary Android components (Bundle, TextView, EditText, Button, etc.)
 * and Retrofit classes for making network requests.
 */

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
    private TextView weatherText; //weatherText: Displays weather details.
    private EditText cityInput; //cityInput: User enters the city name here.
    private Button fetchButton; //fetchButton: User clicks this button to fetch weather data.
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "API KEY"; // Replace with your OpenWeatherMap API Key

    /**
     * Called when the activity starts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Sets the UI layout.

        /**
         * Links UI elements (TextView, EditText, Button) to their
         * corresponding XML components in activity_main.xml using findViewById().
         */
        weatherText = findViewById(R.id.weatherText);
        cityInput = findViewById(R.id.cityInput);
        fetchButton = findViewById(R.id.fetchButton);

        /**
         *  Set Button Click Listener
         *  When fetchButton is clicked, getWeather() is called with the city name entered by the user.
         */
        fetchButton.setOnClickListener(v -> getWeather(cityInput.getText().toString()));
    }

    /**
     * @param city
     * This method fetches weather data for the given city.
     */
    private void getWeather(String city) {
        /**
         * Creates a Retrofit instance:
         * baseUrl(BASE_URL): Sets the API base URL.
         * addConverterFactory(GsonConverterFactory.create()): Uses Gson to convert JSON responses to Java objects.
         * build(): Builds the Retrofit object.
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * Uses Retrofit to create an instance of WeatherService (an interface defining API calls).
         */
        WeatherService service = retrofit.create(WeatherService.class);
        /**
         * Calls the getWeather method from WeatherService, passing:
         * city: User-entered city name.
         * API_KEY: OpenWeatherMap API key.
         * "metric": Temperature unit (Celsius).
         */
        Call<WeatherResponse> call = service.getWeather(city, API_KEY, "metric");

        /**
         * enqueue(): Makes the API request asynchronously.
         */
        call.enqueue(new Callback<WeatherResponse>() {
            /**
             *
             * @param call
             * @param response
             * If API response is successful and not null:
             * Extracts city name, temperature, and weather description.
             * Updates weatherText with fetched weather details.
             */
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    weatherText.setText("City: " + response.body().cityName + "\nTemp: " + response.body().main.temperature + "°C\nDescription: " + response.body().weather[0].description);
                }
            }

            /**
             *
             * @param call
             * @param t
             * If the request fails (e.g., no internet),
             * it sets weatherText to "Error fetching weather".
             */
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherText.setText("Error fetching weather");
            }
        });
    }
}
