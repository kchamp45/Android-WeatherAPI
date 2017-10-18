package com.epicodus.weatherapi.models;

import java.util.ArrayList;

/**
 * Created by Guest on 10/17/17.
 */

public class Weather {

    private int temperature;
    private int humidity;
    private int wind;



    public Weather(int temperature, int humidity, int wind) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.wind = wind;

        }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWind() {
        return wind;
    }
}
