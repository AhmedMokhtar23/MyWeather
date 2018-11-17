package me5atech.myweather.Controllers.Splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import me5atech.myweather.Models.Globals;
import me5atech.myweather.Views.Splash.Splash_Interface;

public class Splash_Controller {
    private Splash_Interface UI;
    private Activity activity;

    public Splash_Controller(Splash_Interface UI) {
        super();
        this.UI = UI;
        activity = (Activity) UI;
    }

    public void can_start() {
        ArrayList<String> permissions = new ArrayList<>();
        if (!storage_available()) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }
        Globals.City_Codes.put("cairo", "360630");
        Globals.City_Codes.put("ismailia", "361055");
        Globals.City_Codes.put("suez", "359796");
        Globals.City_Codes.put("luxor", "360502");
        Globals.City_Codes.put("port said", "358619");
        UI.on_success();
        if (!permissions.isEmpty()) {
            UI.request_permissions(permissions.toArray(new String[permissions.size()]));
        } else {
            UI.on_success();
        }
    }


    public float get_font_difference() {
        float width = Math.round((float) activity.getResources().getDisplayMetrics().widthPixels / activity.getResources().getDisplayMetrics().xdpi);
        float height = Math.round((float) activity.getResources().getDisplayMetrics().heightPixels / activity.getResources().getDisplayMetrics().ydpi);
        int screen_size = Math.round((float) Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));
        switch (screen_size) {
            case 5:
                return 3f;
            case 6:
                return 4f;
            case 7:
                return 5f;
            case 8:
                return 6f;
            case 9:
                return 7f;
            case 10:
                return 8f;
            default:
                return 0;
        }
    }

    private boolean internet_available() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_NETWORK_STATE)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET)
                        == PackageManager.PERMISSION_GRANTED;
    }

    private boolean storage_available() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
    }
}


//http://api.openweathermap.org/data/2.5/forecast?id=360630