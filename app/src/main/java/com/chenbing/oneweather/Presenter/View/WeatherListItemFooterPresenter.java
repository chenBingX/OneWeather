package com.chenbing.oneweather.Presenter.View;

import java.util.List;

import com.chenbing.oneweather.CustomViews.ItemView.WeatherListItemFooter;
import com.chenbing.oneweather.CustomViews.ItemView.WeatherListItemFooterView;
import com.chenbing.oneweather.Model.CityListModel;
import com.chenbing.oneweather.Model.CityListModelApi;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public class WeatherListItemFooterPresenter
    implements
      WeatherListItemFooterPresenterApi,
      CityListModel.OnMatchedListener {
  private WeatherListItemFooterView view;
  private CityListModelApi model;

  public WeatherListItemFooterPresenter(WeatherListItemFooter view) {
    this.view = view;
    this.model = new CityListModel();
    model.setOnMatchedListener(this);
  }

  @Override
  public void matchCity(String content) {
    model.matchCity(content);
  }

  @Override
  public void onMatched(List<String> cities) {
    view.onMatched(cities);
  }

  @Override
  public void destroy() {
    model = null;
    view = null;
  }
}
