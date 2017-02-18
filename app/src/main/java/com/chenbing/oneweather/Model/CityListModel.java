package com.chenbing.oneweather.Model;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.ChiceApplication;
import com.chenbing.oneweather.Data.City;
import com.chenbing.oneweather.Data.Cache.DataCache;
import com.chenbing.oneweather.Utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public class CityListModel implements CityListModelApi {

  private OnCityListLoadedListener onCityListLoadedListener;
  private OnMatchedListener onMatchedListener;

  @Override
  public void loadCityList() {
    Observable.create(new Observable.OnSubscribe<List<City>>() {
      @Override
      public void call(Subscriber<? super List<City>> subscriber) {
        List<City> cityList =
            DataCache.getInstance().get(DataCache.Key.CITY_LIST, ArrayList.class);
        if (cityList != null) {
          subscriber.onNext(cityList);
          subscriber.onCompleted();
        } else {
          InputStream inputStream = null;
          try {
            inputStream = ChiceApplication.getAppContext().getAssets().open("CityArray.JSON");
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            inputStream.close();
            String string = new String(bytes);
            Type type = new TypeToken<List<City>>() {}.getType();
            cityList = GsonUtils.getSingleInstance().fromJson(string, type);
            subscriber.onNext(cityList);
            subscriber.onCompleted();
          } catch (IOException e) {
            e.printStackTrace();
            if (inputStream != null){
              try {
                inputStream.close();
              } catch (IOException e1) {
                e1.printStackTrace();
                subscriber.onCompleted();
              }
            }
            subscriber.onCompleted();
          }
        }
      }
    })
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<City>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
          public void onNext(List<City> cityList) {
            DataCache.getInstance().add(DataCache.Key.CITY_LIST, cityList);
            if (onCityListLoadedListener != null) {
              onCityListLoadedListener.onCityListLoaded(cityList);
            }
        }
      });
  }

  @Override
  public void matchCity(String content) {
    setOnCityListLoadedListener(cityList -> {
      Observable.create(new Observable.OnSubscribe<List<String>>() {
        @Override
        public void call(Subscriber<? super List<String>> subscriber) {
          List<String> result = new ArrayList<>();
          for (City city : cityList) {
            String cityName = city.getAreaname();
            if (cityName.contains(content)) {
              result.add(cityName);
            }
          }
          subscriber.onNext(result);
          subscriber.onCompleted();
        }
      })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<String>>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(List<String> result) {
            if (onMatchedListener != null) {
              onMatchedListener.onMatched(result);
            }
          }
        });

    });
    loadCityList();
  }

  public void setOnCityListLoadedListener(OnCityListLoadedListener onCityListLoadedListener) {
    this.onCityListLoadedListener = onCityListLoadedListener;
  }

  @Override
  public void setOnMatchedListener(OnMatchedListener onMatchedListener) {
    this.onMatchedListener = onMatchedListener;
  }

  public static interface OnCityListLoadedListener {
    void onCityListLoaded(List<City> cityList);
  }

  public static interface OnMatchedListener {
    void onMatched(List<String> cities);
  }
}
