package me5atech.myweather.Models;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather_Period {
    private String period;
    private int temp_average, temp_max, temp_min, wind_speed,wind_degree,humidity,pressur;

    public Weather_Period(JSONObject json) throws JSONException {
        period = json.getString("dt_txt");
        period = period.substring(period.indexOf(" ") + 1);
        JSONObject jsonobject = json.getJSONObject("main");
        temp_average = new Double(jsonobject.getDouble("temp") - 273).intValue();
        temp_min = new Double(jsonobject.getDouble("temp_min") - 273).intValue();
        temp_max = new Double(jsonobject.getDouble("temp_max") - 273).intValue();
        humidity = new Double(jsonobject.getDouble("humidity")).intValue();
        pressur = new Double(jsonobject.getDouble("pressure")).intValue();
        jsonobject = json.getJSONObject("wind");
        wind_speed = new Double(jsonobject.getDouble("speed")).intValue();
        wind_degree = new Double(jsonobject.getDouble("deg")).intValue();
    }

    public String getPeriod() {
        return period;
    }

    public int getTemp_average() {
        return temp_average;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressur() {
        return pressur;
    }

    @NonNull
    @Override
    public String toString() {
        return period;
    }
}
