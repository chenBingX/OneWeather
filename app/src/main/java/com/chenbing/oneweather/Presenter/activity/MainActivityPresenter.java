package com.chenbing.oneweather.Presenter.activity;

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
    RxBus.get().register(this, SimpleWeather.class).subscribe(new Subscriber<SimpleWeather>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(SimpleWeather simpleWeather) {
        if (view != null){
          view.updateSimpleWeatherDatas(simpleWeather);
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
