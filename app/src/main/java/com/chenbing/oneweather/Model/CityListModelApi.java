package com.chenbing.oneweather.Model;

import com.chenbing.oneweather.Data.City;

import java.util.List;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public interface CityListModelApi {

  void loadCityList();

  void matchCity(String content);

  void setOnMatchedListener(CityListModel.OnMatchedListener onMatchedListener);
}
