package com.chenbing.oneweather.Model;

import com.chenbing.oneweather.Data.WeatherData;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/30
 * Notes:
 */

public interface WeatherDataModelApi {
  void requestWeatherData();

  void setRequestWeatherDataListener(RequestWeatherDataListener requestWeatherDataListener);

  // 因为每个Model可能需要请求多个接口，所以每个回调可能不同，就把它写到内部了
  interface RequestWeatherDataListener {
    void onRequestWeatherDataSuccess(WeatherData data);

    void onRequestWeatherDataFailure(String message);
  }
}
