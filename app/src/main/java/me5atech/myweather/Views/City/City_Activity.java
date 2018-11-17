package me5atech.myweather.Views.City;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Spinner;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashMap;

import me5atech.myweather.Models.Weather_City;
import me5atech.myweather.Models.Weather_Period;
import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

public class City_Activity extends MyActivity implements City_Interface {

    Weather_City weather_city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);
        on_connecting();
    }

    @Override
    protected void onStart() {
        super.onStart();
        set_font();
        set_animation();
    }

    private void set_spinners(){
        Spinner spinner = findViewById(R.id.city_sp_day);
        HashMap<DayOfWeek,HashMap<String,Weather_Period>> weather_days = weather_city.getDays();
        String[] days = (String[]) weather_days.keySet().toArray(new String[weather_days.keySet().size()]);
        spinner.setAdapter(new Spinner_Adapter<>(this,weather_days.keySet().toArray(new DayOfWeek[5])));
        spinner.setSelection(0);
        String[] periodsString = new String[4];
        Weather_Period[] periods = weather_days.get(days[0]).values().toArray(new Weather_Period[4]);
        for(int i=0;i<4;i++){
            periodsString[i] = periods[i].getPeriod();
        }
        spinner = findViewById(R.id.city_sp_periods);
        spinner.setAdapter(new Spinner_Adapter<>(this,periodsString));
    }

    @Override
    public void on_loaded(Weather_City city) {
        abort_connection(null);
        weather_city = city;
        set_spinners();
    }
}
