package com.chenbing.oneweather.Presenter.activity;

import com.chenbing.oneweather.View.activitys.MainActivityView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public class MainActivityPresenter implements MainActivityPresenterApi {

  private MainActivityView view;

  public MainActivityPresenter(MainActivityView view) {
    this.view = view;
  }

  @Override
  public void destroy() {
    view = null;
  }
}
