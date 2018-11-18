package me5atech.myweather.Views.ContactUs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

public class ContactUs_Activity extends MyActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contactus_layout);
    }
}
