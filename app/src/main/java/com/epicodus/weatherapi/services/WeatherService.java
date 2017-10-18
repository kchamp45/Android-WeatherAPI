package com.epicodus.weatherapi.services;

import com.epicodus.weatherapi.models.Constants;
import com.epicodus.weatherapi.models.Weather;

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
                 int humidity = eachDayJSON.getJSONObject("main").getInt("humidity");
                 int wind = eachDayJSON.getJSONObject("wind").getInt("speed");

                 Weather forecast = new Weather(temperature, humidity, wind);
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
