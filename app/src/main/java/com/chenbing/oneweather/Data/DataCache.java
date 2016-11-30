package com.chenbing.oneweather.Data;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/30
 * Notes:
 */

public class DataCache {

  private WeatherData weatherData;

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
}
