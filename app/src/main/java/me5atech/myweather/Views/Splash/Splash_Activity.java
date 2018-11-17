package me5atech.myweather.Views.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

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
        controller = new Splash_Controller(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                controller.can_start();
            }
        },300);
    }

    @Override
    public void on_success() {
        Globals.font_difference = controller.get_font_difference();
        startActivity(new Intent(this,Main_Activity.class));
    }

    @Override
    public void on_corrupted_data() {

    }
}
