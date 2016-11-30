package com.chenbing.oneweather.Presenter;

import com.chenbing.oneweather.Model.MainActivityModel;
import com.chenbing.oneweather.Model.MainActivityModelApi;
import com.chenbing.oneweather.View.activitys.MainActivityView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public class MainActivityPresenter implements MainActivityPresenterApi {

  private MainActivityView view;
  private MainActivityModelApi model;

  public MainActivityPresenter(MainActivityView view) {
    this.view = view;
    model = new MainActivityModel();
  }

  @Override
  public void destroy() {

  }
}
