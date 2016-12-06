package com.chenbing.oneweather.Presenter.fragment;

import com.chenbing.oneweather.Presenter.BasePresenter;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/3
 * Notes:
 */

public interface WeatherDetailFragmentPresenterApi extends BasePresenter {

  void getWeatherData(String cityName);

}
