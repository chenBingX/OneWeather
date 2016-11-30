package com.chenbing.oneweather.Model;

import com.chenbing.oneweather.Data.Network.ApiClient;
import com.chenbing.oneweather.Utils.GsonUtils;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.Utils.Helpers.LocationHelper;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/30
 * Notes:
 */

public class WeatherDataModel implements WeatherDataModelApi {
  private RequestWeatherDataListener requestWeatherDataListener;

  @Override
  public void requestWeatherData() {
    locationThenGetWeatherData();
  }

  private void locationThenGetWeatherData() {
    LocationHelper.getInstance().startLocation();
    LocationHelper.getInstance().setListener(location -> {
      String cityname = location.getCity();
      if (cityname != null) {
        getWeatherData(cityname);
      }
    });
  }

  private void getWeatherData(String cityname) {
    ApiClient.getWeatherData(cityname, data -> {
      LogUtils.i("requestWeatherData: " + GsonUtils.getSingleInstance().toJson(data));
      if (requestWeatherDataListener != null) {
        requestWeatherDataListener.onRequestWeatherDataSuccess(data); // 请求成功
      }
    }, message -> {
      if (requestWeatherDataListener != null) {
        requestWeatherDataListener.onRequestWeatherDataFailure(message);
      }
    });
  }

  @Override
  public void setRequestWeatherDataListener(RequestWeatherDataListener requestWeatherDataListener) {
    this.requestWeatherDataListener = requestWeatherDataListener;
  }
}
