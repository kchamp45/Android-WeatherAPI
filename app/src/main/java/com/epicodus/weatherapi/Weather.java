package com.epicodus.weatherapi;

import java.util.ArrayList;

/**
 * Created by Guest on 10/17/17.
 */

public class Weather {

    private int temperature;
    private ArrayList<String> weather = new ArrayList<>();


    public Weather(int temperature, ArrayList weather) {
            this.temperature = temperature;
            this.weather = weather;

        }

    public int getTemperature() {
        return temperature;
    }

    public ArrayList<String> getWeather() {
        return weather;
    }
}
