package me5atech.myweather.Views.City;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

public class Spinner_Adapter<T> extends ArrayAdapter<T> {

    public Spinner_Adapter(@NonNull Context context, T[] s) {
        super(context, android.R.layout.simple_spinner_item,s);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView.setBackgroundResource(R.color.spiner_background);
        ((MyActivity)getContext()).set_font(convertView);
        return super.getDropDownView(position, convertView, parent);
    }
}
