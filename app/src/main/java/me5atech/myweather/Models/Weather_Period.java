package me5atech.myweather.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather_Period {
    private String period;
    private double temp_average, temp_max, temp_min, wind_speed,wind_degree,humidity,pressur;

    public Weather_Period(JSONObject json) throws JSONException {
        period = json.getString("dt_txt");
        period = period.substring(period.indexOf(" ") + 1);
        JSONObject jsonobject = json.getJSONObject("jsonobject");
        temp_average = jsonobject.getDouble("temp");
        temp_min = jsonobject.getDouble("temp_min");
        temp_max = jsonobject.getDouble("temp_max");
        humidity = jsonobject.getDouble("humidity");
        pressur = jsonobject.getDouble("pressure");
        jsonobject = json.getJSONObject("wind");
        wind_speed = jsonobject.getDouble("speed");
        wind_degree = jsonobject.getDouble("deg");
    }

    public String getPeriod() {
        return period;
    }

    public double getTemp_average() {
        return temp_average;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getWind_degree() {
        return wind_degree;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressur() {
        return pressur;
    }
}
