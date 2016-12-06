package com.chenbing.oneweather.CustomViews.ItemView;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Data.Weather;
import com.chenbing.oneweather.View.BaseView.BaseRelativeLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/5
 * Notes:
 */

public class FutureWeatherItem extends BaseRelativeLayout {
  @BindView(R.id.week)
  TextView week;
  @BindView(R.id.weather_pic)
  ImageView weatherPic;
  @BindView(R.id.high_temperature)
  TextView highTemperature;
  @BindView(R.id.low_temperature)
  TextView lowTemperature;

  public FutureWeatherItem(Context context) {
    this(context, null);
  }

  public FutureWeatherItem(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FutureWeatherItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected int getLayout() {
    return R.layout.item_future_weather_info;
  }


  public void setData(Weather data, int position) {
    String weekFormat = getContext().getString(R.string.week);
    String week = String.format(weekFormat, data.getWeek());
    if (position == 0) {
      week = getContext().getString(R.string.today);
    }
    this.week.setText(week);

    this.weatherPic.setImageResource(R.drawable.sun);

    String highTemperature = data.getInfo().getDay().get(2);
    this.highTemperature.setText(highTemperature);

    String lowTemperature = data.getInfo().getNight().get(2);
    this.lowTemperature.setText(lowTemperature);
  }
}
