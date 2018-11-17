package me5atech.myweather.Views.City;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

public class Spinner_Adapter<T> extends ArrayAdapter<T> {

    public Spinner_Adapter(@NonNull Context context, T[] s) {
        super(context, R.layout.spinner_layout,s);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.spinner_layout,parent,false);
        ((TextView)v.findViewById(R.id.spinner_txt)).setText(getItem(position).toString());
        ((MyActivity)getContext()).set_font((ViewGroup) v);
        return v;
    }
}
