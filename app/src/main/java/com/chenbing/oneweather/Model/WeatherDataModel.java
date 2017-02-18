package com.chenbing.oneweather.Model;

import com.baidu.location.BDLocation;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Data.Cache.DataCache;
import com.chenbing.oneweather.Data.Network.ApiClient;
import com.chenbing.oneweather.Utils.ToastUtil;
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
    BDLocation bdLocation =
      DataCache.getInstance().get(DataCache.Key.BD_LOCATION, BDLocation.class);
    if (bdLocation == null){
      LocationHelper.getInstance().startLocation();
      LocationHelper.getInstance().setListener(location -> {
        LocationHelper.getInstance().stopLocation();
        String cityname = location.getCity();
        if (!TextUtils.isEmpty(cityname)) {
          getWeatherData(cityname, false);
        }
        DataCache.getInstance().add(DataCache.Key.BD_LOCATION, location); // 缓存定位信息
      });
    } else {
      String cityname = bdLocation.getCity();
      if (!TextUtils.isEmpty(cityname)) {
        getWeatherData(cityname, false);
      }
    }
  }

  @Override
  public void requestWeatherData(String cityName) {
    if (isLocaleCity(cityName)) {
      requestLocaleWeatherData();
    } else {
      requestOtherWeatherData(cityName);
    }
  }

  private boolean isLocaleCity(String cityName) {
    BDLocation bdLocation =
        DataCache.getInstance().get(DataCache.Key.BD_LOCATION, BDLocation.class);
    return TextUtils.isEmpty(cityName)
        || (bdLocation != null && bdLocation.getCity().contains(cityName));
  }

  private void requestLocaleWeatherData() {
    WeatherData data = getCacheData(DataCache.Key.LOCALE_WEATHER_DATA);
    if (data == null) {
      requestWeatherData();
    } else {
      if (requestWeatherDataListener != null) {
        requestWeatherDataListener.onRequestWeatherDataSuccess(data); // 请求成功
      }
    }
  }

  private void requestOtherWeatherData(String cityName) {
    WeatherData data = getCacheData(DataCache.Key.WEATHER_DATA_SUFFIX + cityName);
    if (data == null) {
      getWeatherData(cityName, true);
    } else {
      if (requestWeatherDataListener != null) {
        requestWeatherDataListener.onRequestWeatherDataSuccess(data); // 请求成功
      }
    }
  }

  private WeatherData getCacheData(String key) {
    return DataCache.getInstance().get(key, WeatherData.class);
  }

  private void getWeatherData(String cityname, boolean isCache) {
    ApiClient.getWeatherData(cityname, data -> {
      if (data != null) {
        if (isCache) {
          DataCache.getInstance().add(DataCache.Key.WEATHER_DATA_SUFFIX + cityname, data);
        }
      }
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
