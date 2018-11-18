package me5atech.myweather.Views.Splash;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me5atech.myweather.Controllers.Splash.Splash_Controller;
import me5atech.myweather.Models.Globals;
import me5atech.myweather.R;
import me5atech.myweather.Views.Main.Main_Activity;
import me5atech.myweather.Views.MyActivity;

public class Splash_Activity extends MyActivity implements Splash_Interface {
    Splash_Controller controller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller = new Splash_Controller(this);
        if(Globals.City_Codes.isEmpty()) {new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                controller.can_start();
            }
        },300);} else{
            Globals.City_Codes.clear();
            onBackPressed();
        }
    }

    @Override
    public void on_success() {
        Globals.font_difference = controller.get_font_difference();
        startActivity(new Intent(this,Main_Activity.class));
    }

    @Override
    public void request_permissions(String[] permissions) {
        ActivityCompat.requestPermissions(this,permissions,0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        on_success();
    }
}
