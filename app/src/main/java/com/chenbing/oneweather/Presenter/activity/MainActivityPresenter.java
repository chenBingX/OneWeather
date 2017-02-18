package com.chenbing.oneweather.Presenter.activity;

import android.util.Log;

import com.chenbing.oneweather.Data.RxEvent.CityNameEvent;
import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.Utils.RxBus;
import com.chenbing.oneweather.View.activitys.MainActivityView;

import rx.Subscriber;

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
    registerRxBus();
  }

  private void registerRxBus(){
    simpleWeatherRxBus();
    addCityWeatherRxBus();

  }

  private void simpleWeatherRxBus() {
    RxBus.get().register(this, SimpleWeather.class).subscribe(new Subscriber<SimpleWeather>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onNext(SimpleWeather simpleWeather) {
        if (view != null){
          LogUtils.e("simpleWeather-onNext = " + simpleWeather.getCity());
          view.updateSimpleWeatherDatas(simpleWeather);
        }
      }
    });
  }

  private void addCityWeatherRxBus() {
    RxBus.get().register(this, CityNameEvent.class).subscribe(new Subscriber<CityNameEvent>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onNext(CityNameEvent cityNameEvent) {
        String cityName = cityNameEvent.cityName;
        if(view != null){
          LogUtils.e("cityName = " + cityName);
          view.addCityPage(cityName);
        }
      }
    });

  }

  @Override
  public void destroy() {
    view = null;
    RxBus.get().unregister(this);
  }
}
