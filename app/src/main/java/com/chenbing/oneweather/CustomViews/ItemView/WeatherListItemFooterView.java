package com.chenbing.oneweather.CustomViews.ItemView;

import com.chenbing.oneweather.View.BaseView.MvpView;

import java.util.List;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/3/7
 * Notes:
 */

public interface WeatherListItemFooterView extends MvpView{
  void onMatched(List<String> cities);
}
