package me5atech.myweather.Views;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.TextView;

import me5atech.myweather.Models.Weather_Period;
import me5atech.myweather.R;
import me5atech.myweather.Views.MyActivity;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Weather_Period period,String city) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        views.setTextViewText(R.id.widget_txtv_city,city);
        views.setTextViewText(R.id.widget_txtv_period,period.getPeriod());

        views.setTextViewText(R.id.city_txtv_tmp, period.getTemp_average() + "");
        views.setTextViewText(R.id.city_txtv_tmp_min ,period.getTemp_min() + "");
        views.setTextViewText(R.id.city_txtv_tmp_max,period.getTemp_max() + "");
        views.setTextViewText(R.id.city_txtv_windspeed,period.getWind_speed() + "");
        views.setTextViewText(R.id.city_txtv_winddegree,period.getWind_degree() + "");
        views.setTextViewText(R.id.city_txtv_humidity,period.getHumidity() + "");
        views.setTextViewText(R.id.city_txtv_pressure,period.getPressur() + "");

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int[] ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        for(int i : ids){
            updateAppWidget(context,AppWidgetManager.getInstance(context),i,(Weather_Period)intent.getSerializableExtra("period"),intent.getStringExtra("city"));
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

