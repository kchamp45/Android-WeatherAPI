package com.epicodus.weatherapi.services;

import android.util.Log;

import com.epicodus.weatherapi.Constants;
import com.epicodus.weatherapi.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {
    public static void findWeather(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY_PARAMETER, location)
                .addQueryParameter(Constants.WEATHER_KEY_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

     }

     public ArrayList<Weather> processResults(Response response){
         ArrayList<Weather> forecasts = new ArrayList<>();
         try {
             String jsonData = response.body().string();
             JSONObject forecastJSON = new JSONObject(jsonData);
             JSONArray listJSON = forecastJSON.getJSONArray("list");
             for (int i = 0; i < listJSON.length(); i++) {
                 JSONObject eachDayJSON = listJSON.getJSONObject(i);

                 int temperature = eachDayJSON.getJSONObject("main").getInt("temp");

                 ArrayList<String> climate = new ArrayList<>();
                 JSONArray weatherJSON = eachDayJSON.getJSONArray("weather");

                 for (int y = 0; y < weatherJSON.length(); y++) {
                     climate.add(weatherJSON.getJSONObject(y).getString("description"));
                 }
                 Weather forecast = new Weather(temperature, climate);
                 forecasts.add(forecast);
             }
         }
         catch (IOException e){
             e.printStackTrace();
         }
         catch (JSONException e){
             e.printStackTrace();
         }
         return forecasts;
     }
}
