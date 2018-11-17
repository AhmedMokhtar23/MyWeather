package me5atech.myweather.Controllers.City;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import me5atech.myweather.Models.Globals;
import me5atech.myweather.Models.Weather_City;
import me5atech.myweather.Views.City.City_Interface;

public class City_Controller {

    private City_Interface UI;
    private RequestQueue requestQueue;

    public City_Controller(City_Interface UI){
        this.UI = UI;
        requestQueue = Volley.newRequestQueue((Context) UI);
    }

    public void get_city(String city){
        StringRequest request = new StringRequest(Request.Method.GET,"http://api.openweathermap.org/data/2.5/forecast?id=360630&APPID=f56a2e82cdf2133ec347e9afc4933c3a",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Weather_City city = new Weather_City(new JSONObject(response));
                    UI.on_loaded(city);
                }catch (Exception e){
                    UI.on_corrupted_data();
                    Log.d(Globals.LOG_TAG,e.getMessage());
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                UI.on_connection_error();
                Log.d(Globals.LOG_TAG,error.getMessage());
            }
        });
        requestQueue.add(request);
    }
}
