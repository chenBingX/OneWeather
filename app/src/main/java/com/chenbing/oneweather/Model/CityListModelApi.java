package com.chenbing.oneweather.Model;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public interface CityListModelApi {

  void matchCity(String content);

  void setOnMatchedListener(CityListModel.OnMatchedListener onMatchedListener);
}
