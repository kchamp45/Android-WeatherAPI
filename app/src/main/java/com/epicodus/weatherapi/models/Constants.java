package com.epicodus.weatherapi.models;

import com.epicodus.weatherapi.BuildConfig;

/**
 * Created by Guest on 10/17/17.
 */

public class Constants {
    public static final String API_KEY = BuildConfig.API_KEY;
    public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
    public static final String WEATHER_LOCATION_QUERY_PARAMETER = "zip"; //Example: "location"
    public static final String WEATHER_KEY_QUERY_PARAMETER = "APPID";
}
