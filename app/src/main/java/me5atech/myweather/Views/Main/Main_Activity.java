package me5atech.myweather.Views.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me5atech.myweather.R;
import me5atech.myweather.Views.City.City_Activity;
import me5atech.myweather.Views.MyActivity;

public class Main_Activity extends MyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        set_font();
        set_animation();
    }

    public void contact_us(View v){

    }

    public void select_city(View v){
        startActivity(new Intent(this,City_Activity.class));
    }

    @Override
    public void on_corrupted_data() {

    }
}
