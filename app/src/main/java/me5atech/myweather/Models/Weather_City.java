package me5atech.myweather.Models;

import org.json.JSONArray;
import org.json.JSONObject;
import org.threeten.bp.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Weather_City {
    private String name;
    private HashMap<DayOfWeek,HashMap<String,Weather_Period>> Days;

    public Weather_City(JSONObject json) throws Exception {
        name = json.getJSONObject("city").getString("name");
        setDays(json.getJSONArray("list"));
    }

    void setDays(JSONArray jsonArray) throws Exception {
        Days = new HashMap<>();
        for(int i=0;i<jsonArray.length();){
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            int j = i;
            while (j < 40 && jsonArray.getJSONObject(i).getString("dt_txt").substring(0,jsonArray.getJSONObject(i).getString("dt_txt").indexOf(" ")).equals(
                    jsonArray.getJSONObject(j).getString("dt_txt").substring(0,jsonArray.getJSONObject(i).getString("dt_txt").indexOf(" "))
            )){
                jsonObjects.add(jsonArray.getJSONObject(j));
                j++;
            }
            i = j;
            String periodString = jsonObjects.get(0).getString("dt_txt");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(periodString.substring(0,periodString.indexOf(" "))));
            int dayIndex = (calendar.get(Calendar.DAY_OF_WEEK) - 2);
            if(dayIndex < 0)dayIndex = 6 + dayIndex;
            DayOfWeek day = DayOfWeek.values()[dayIndex];
            HashMap<String,Weather_Period> periods = new HashMap<>();
            for(int k=0;k<jsonObjects.size();k++){
                Weather_Period period = new Weather_Period(jsonObjects.get(k));
                periods.put(period.getPeriod(),period);
            }
            Days.put(day,periods);
        }
    }

    public HashMap<DayOfWeek, HashMap<String, Weather_Period>> getDays() {
        return Days;
    }
}
