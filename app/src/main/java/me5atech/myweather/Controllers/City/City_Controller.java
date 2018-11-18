package me5atech.myweather.Controllers.City;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

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

    public void get_city(final String city){
        StringRequest request = new StringRequest(Request.Method.GET,"http://api.openweathermap.org/data/2.5/forecast?id=" + Globals.City_Codes.get(city.toLowerCase()) + Globals.AppId,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Weather_City city = new Weather_City(new JSONObject(response));
                    save(response,city.getName());
                    UI.on_loadedFromAPI(city);
                }catch (Exception e){
                    Log.d(Globals.LOG_TAG,e.getMessage());
                    UI.onFailed();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                read_offline(city);
            }
        });
        request.setTag("");
        requestQueue.add(request);
    }

    private void save(String json,String city){
        File f = new File(((Context)UI).getFilesDir(),city);
        try{
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(json);
            fileWriter.close();
        }catch (Exception e){
            UI.onFailed();
        }

    }


    private void read_offline(String city){
        File f = new File(((Context)UI).getFilesDir(),city);
        if(f.exists()){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String s = "";
                String line = null;
                while ((line = reader.readLine()) != null){
                    s += line;
                }
                Weather_City w = new Weather_City(new JSONObject(s));
                UI.onloadedFromStorage(w);
            }catch (Exception e){
                UI.onFailed();
            }

        }else{
            UI.onFailed();
        }
    }

    public void Abort_Connection(){
        requestQueue.cancelAll("");
    }
}
