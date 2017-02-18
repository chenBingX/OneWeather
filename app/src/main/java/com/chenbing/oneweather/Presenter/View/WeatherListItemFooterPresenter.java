package com.chenbing.oneweather.Presenter.View;

import com.chenbing.oneweather.CustomViews.ItemView.WeatherListItemFooter;
import com.chenbing.oneweather.Model.CityListModel;
import com.chenbing.oneweather.Model.CityListModelApi;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public class WeatherListItemFooterPresenter implements WeatherListItemFooterPresenterApi {
  private WeatherListItemFooter view;
  private CityListModelApi model;

  public WeatherListItemFooterPresenter(WeatherListItemFooter view) {
    this.view = view;
    this.model = new CityListModel();
  }



  @Override
  public void matchCity(String content, CityListModel.OnMatchedListener listener) {
    model.setOnMatchedListener(listener);
    model.matchCity(content);
  }

  @Override
  public void destroy() {
    model = null;
    view = null;
  }
}
