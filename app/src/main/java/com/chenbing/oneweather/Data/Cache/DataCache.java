package com.chenbing.oneweather.Data.Cache;

import com.baidu.location.BDLocation;
import com.chenbing.oneweather.Data.WeatherData;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/30
 * Notes:
 */

public class DataCache {

  private WeatherData weatherData;
  private BDLocation currentLocation;

  private DataCache(){

  }

  public static DataCache getInstance(){
    return DataCacheHolder.instance;
  }

  private static class DataCacheHolder{

    private static final DataCache instance = new DataCache();
  }
  public WeatherData getWeatherData() {
    return weatherData;
  }

  public void setWeatherData(WeatherData weatherData) {
    this.weatherData = weatherData;
  }

  public BDLocation getCurrentLocation() {
    return currentLocation;
  }

  public void setCurrentLocation(BDLocation currentLocation) {
    this.currentLocation = currentLocation;
  }
}
