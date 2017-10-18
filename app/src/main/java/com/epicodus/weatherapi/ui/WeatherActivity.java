package com.epicodus.weatherapi.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.weatherapi.R;
import com.epicodus.weatherapi.adapters.WeatherListAdapter;
import com.epicodus.weatherapi.models.Weather;
import com.epicodus.weatherapi.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = WeatherActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;

    public ArrayList<Weather> forecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intentPassedFromMainActivityOverCyberSpace = getIntent();
        String location = intentPassedFromMainActivityOverCyberSpace.getStringExtra("location");


        getWeather(location);
    }

    private void getWeather(String location) {
        final WeatherService weatherService = new WeatherService();
        weatherService.findWeather(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) {
                forecasts = weatherService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run(){
                        mAdapter = new WeatherListAdapter(getApplicationContext(), forecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(WeatherActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });

    }
}