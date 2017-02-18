package com.chenbing.oneweather.View.activitys;

import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.View.BaseView.MvpView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public interface MainActivityView extends MvpView {

  void updateSimpleWeatherDatas(SimpleWeather simpleWeather);
}
