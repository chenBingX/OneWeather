package com.chenbing.oneweather.Model;

import com.baidu.location.BDLocation;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Data.Cache.DataCache;
import com.chenbing.oneweather.Data.Network.ApiClient;
import com.chenbing.oneweather.Utils.GsonUtils;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.Utils.Helpers.LocationHelper;

import android.text.TextUtils;

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
      DataCache.getInstance().add(DataCache.Key.BD_LOCATION, location); // 缓存定位信息
    });
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
    return cityName == null || TextUtils.isEmpty(cityName)
        || DataCache.getInstance().get(DataCache.Key.BD_LOCATION, BDLocation.class).getCity()
            .contains(cityName);
  }

  private void getCacheData() {
    WeatherData data;
    data = DataCache.getInstance().get(DataCache.Key.WEATHER_DATA, WeatherData.class);
    if (requestWeatherDataListener != null) {
      requestWeatherDataListener.onRequestWeatherDataSuccess(data); // 请求成功
    }
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
