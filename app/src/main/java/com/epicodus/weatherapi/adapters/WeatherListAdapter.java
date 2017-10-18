package com.epicodus.weatherapi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.weatherapi.R;
import com.epicodus.weatherapi.models.Weather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {

        private ArrayList<Weather> mForecasts = new ArrayList<>();
        private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> forecasts) {
            mContext = context;
            mForecasts = forecasts;
        }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mForecasts.get(position));

    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.temperatureTextView) TextView mTempTextView;
        @Bind(R.id.humidityTextView) TextView mHumidityTextView;
        @Bind(R.id.windTextView)
        TextView mWindTextView;

        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindWeather(Weather forecast) {
            mTempTextView.setText(forecast.getTemperature());
            mHumidityTextView.setText(forecast.getHumidity());
            mWindTextView.setText(forecast.getWind());
        }
    }
}
