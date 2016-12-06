package com.chenbing.oneweather.Model;

import android.text.TextUtils;

import com.chenbing.oneweather.Data.Cache.DataCache;
import com.chenbing.oneweather.Data.Network.ApiClient;
import com.chenbing.oneweather.Data.WeatherData;
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

  @Override
  public void requestWeatherData(String cityName) {
    WeatherData data;
    if (isGetCacheData(cityName)){
      getCacheData();
    } else {
      getWeatherData(cityName);
    }
  }

  private boolean isGetCacheData(String cityName) {
    return cityName == null || TextUtils.isEmpty(cityName);
  }

  private void getCacheData() {
    WeatherData data;
    data = DataCache.getInstance().getWeatherData();
    if (requestWeatherDataListener != null) {
      requestWeatherDataListener.onRequestWeatherDataSuccess(data); // 请求成功
    }
  }

  private void locationThenGetWeatherData() {
    LocationHelper.getInstance().startLocation();
    LocationHelper.getInstance().setListener(location -> {
      String cityname = location.getCity();
      if (cityname != null) {
        getWeatherData(cityname);
      }
      DataCache.getInstance().setCurrentLocation(location); //缓存定位信息
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
