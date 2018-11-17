package me5atech.myweather.Views.City;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


import org.threeten.bp.DayOfWeek;

import java.util.HashMap;

import me5atech.myweather.Controllers.City.City_Controller;
import me5atech.myweather.Models.Weather_City;
import me5atech.myweather.Models.Weather_Period;
import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

public class City_Activity extends MyActivity implements City_Interface {

    Weather_City weather_city;
    City_Controller controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);
        on_connecting();
        controller = new City_Controller(this);
        weather_city = null;
        controller.get_city("Cairo");
        ((Spinner)(findViewById(R.id.city_sp_day))).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(weather_city != null){
                    set_period_spinner();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((Spinner)(findViewById(R.id.city_sp_periods))).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(weather_city != null){
                    apply_model(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        set_font();
        set_animation();
    }

    private void set_day_spinner(){
        Spinner spinner = findViewById(R.id.city_sp_day);
        HashMap<DayOfWeek,HashMap<String,Weather_Period>> weather_days = weather_city.getDays();
        spinner.setAdapter(new Spinner_Adapter<>(this,weather_days.keySet().toArray(new DayOfWeek[weather_days.keySet().size()])));
        set_period_spinner();
    }
    private void set_period_spinner(){
        Spinner spinner = findViewById(R.id.city_sp_periods);
        HashMap<String,Weather_Period> periods = weather_city.getDays().get(((Spinner)findViewById(R.id.city_sp_day)).getSelectedItem());
        spinner.setAdapter(new Spinner_Adapter<>(this,periods.values().toArray(new Weather_Period[periods.values().size()])));
        apply_model(spinner.getSelectedItem().toString());
    }

    private void apply_model(String periodstring){
        Weather_Period period = weather_city.getDays().get(((Spinner)findViewById(R.id.city_sp_day)).getSelectedItem()).
                get(periodstring);
        ((TextView)findViewById(R.id.city_txtv_tmp)).setText(period.getTemp_average() + "");
        ((TextView)findViewById(R.id.city_txtv_tmp_min)).setText(period.getTemp_min() + "");
        ((TextView)findViewById(R.id.city_txtv_tmp_max)).setText(period.getTemp_max() + "");
        ((TextView)findViewById(R.id.city_txtv_windspeed)).setText(period.getWind_speed() + "");
        ((TextView)findViewById(R.id.city_txtv_winddegree)).setText(period.getWind_degree() + "");
        ((TextView)findViewById(R.id.city_txtv_humidity)).setText(period.getHumidity() + "");
        ((TextView)findViewById(R.id.city_txtv_pressure)).setText(period.getPressur() + "");
    }

    @Override
    public void on_loaded(Weather_City city) {
        abort_connection(null);
        weather_city = city;
        set_day_spinner();
    }

    @Override
    public void on_going_offline() {

    }

    @Override
    public void on_corrupted_data() {

    }
}
