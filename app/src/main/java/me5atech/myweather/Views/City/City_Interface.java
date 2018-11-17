package me5atech.myweather.Views.City;

import me5atech.myweather.Models.Weather_City;
import me5atech.myweather.Views.MyInterface;

public interface City_Interface extends MyInterface {
    void on_loaded(Weather_City city);
    void on_going_offline();
}
