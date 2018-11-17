package me5atech.myweather.Views.City;

import me5atech.myweather.Models.Weather_City;

public interface City_Interface {
    void on_loadedFromAPI(Weather_City city);
    void onloadedFromStorage(Weather_City city);
    void onFailed();
}
