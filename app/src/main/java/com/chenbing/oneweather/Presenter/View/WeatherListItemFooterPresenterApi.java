package com.chenbing.oneweather.Presenter.View;

import com.chenbing.oneweather.Model.CityListModel;
import com.chenbing.oneweather.Presenter.BasePresenter;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public interface WeatherListItemFooterPresenterApi extends BasePresenter {

  void matchCity(String content, CityListModel.OnMatchedListener listener);
}
