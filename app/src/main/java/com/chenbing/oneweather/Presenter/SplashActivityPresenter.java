package com.chenbing.oneweather.Presenter;

import com.chenbing.oneweather.Data.DataCache;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Model.WeatherDataModel;
import com.chenbing.oneweather.Model.WeatherDataModelApi;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.View.activitys.SplashActivityView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/30
 * Notes:
 */

public class SplashActivityPresenter
    implements
      SplashActivityPresenterApi,
      WeatherDataModelApi.RequestWeatherDataListener {

  private WeatherDataModelApi model;
  private SplashActivityView view;

  public SplashActivityPresenter(SplashActivityView view) {
    this.model = new WeatherDataModel();
    this.model.setRequestWeatherDataListener(this);
    this.view = view;
  }


  @Override
  public void requestWeatherData() {
    model.requestWeatherData();
  }

  @Override
  public void onRequestWeatherDataSuccess(WeatherData data) {
    DataCache.getInstance().setWeatherData(data); //缓存数据
  }

  @Override
  public void onRequestWeatherDataFailure(String message) {
    LogUtils.e("请求出错：" + message);
  }


  @Override
  public void destroy() {
    view = null;
    model = null;
  }


}
