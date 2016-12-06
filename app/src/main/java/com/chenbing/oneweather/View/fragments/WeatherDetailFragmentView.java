package com.chenbing.oneweather.View.fragments;

import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.View.BaseView.MvpView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/3
 * Notes:
 */

public interface WeatherDetailFragmentView extends MvpView {

  void onWeatherDataUpdate(WeatherData data);
}
