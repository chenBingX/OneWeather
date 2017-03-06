package com.chenbing.oneweather.Presenter.fragment;

import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Model.WeatherDataModel;
import com.chenbing.oneweather.Model.WeatherDataModelApi;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.Utils.RxBus;
import com.chenbing.oneweather.View.fragments.WeatherDetailFragmentView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/3
 * Notes:
 */

public class WeatherDetailFragmentPresenter implements WeatherDetailFragmentPresenterApi, WeatherDataModelApi.RequestWeatherDataListener {
  private WeatherDetailFragmentView view;
  private WeatherDataModelApi model;
  private String cityName;

  public WeatherDetailFragmentPresenter(WeatherDetailFragmentView view) {
    this.view = view;
    model = new WeatherDataModel();
    model.setRequestWeatherDataListener(this);
  }

  public void getWeatherData(String cityName){
    this.cityName = cityName;
    model.requestWeatherData(cityName);
  }

  @Override
  public void onRequestWeatherDataSuccess(WeatherData data) {
    if (view != null){
      view.onWeatherDataUpdate(data);
    } else {
      SimpleWeather simpleWeather = new SimpleWeather();
      if (data != null) {
        simpleWeather.setCity(data.getData().getRealtime().getCity_name());
        simpleWeather.setTemperature(data.getData().getRealtime().getWeather().getTemperature());
      } else {
        simpleWeather.setCity(cityName);
      }
      RxBus.get().post(simpleWeather);
    }
  }

  @Override
  public void onRequestWeatherDataFailure(String message) {
    LogUtils.e(": " + message);
  }

  @Override
  public void destroy() {
    model.setRequestWeatherDataListener(null);
    model = null;
    view = null;
  }
}
