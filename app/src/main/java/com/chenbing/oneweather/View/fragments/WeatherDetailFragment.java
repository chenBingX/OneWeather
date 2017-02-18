package com.chenbing.oneweather.View.fragments;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Presenter.BasePresenter;
import com.chenbing.oneweather.Presenter.fragment.WeatherDetailFragmentPresenter;
import com.chenbing.oneweather.Presenter.fragment.WeatherDetailFragmentPresenterApi;
import com.chenbing.oneweather.Utils.RxBus;
import com.chenbing.oneweather.View.BaseView.BaseFragment;
import com.chenbing.oneweather.adapters.FutureWeathersAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/3
 * Notes:
 */

public class WeatherDetailFragment extends BaseFragment implements WeatherDetailFragmentView {

  public static final String CITY_NAME = "city_name";

  @BindView(R.id.header)
  ViewGroup header;
  @BindView(R.id.city_name)
  TextView cityName;
  @BindView(R.id.weather_info)
  TextView weatherInfo;
  @BindView(R.id.air_quality)
  TextView airQuality;
  @BindView(R.id.temperature)
  TextView temperature;
  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;

  @BindView(R.id.empty_container)
  ViewGroup emptyContainer;

  private WeatherDetailFragmentPresenterApi presenter;
  private WeatherData data;
  private FutureWeathersAdapter adapter;
  private int totalDy;
  private int alphaReferenceValue;


  public static BaseFragment newInstance(String cityName) {
    WeatherDetailFragment instance = new WeatherDetailFragment();
    new WeatherDetailFragmentPresenter(null).getWeatherData(cityName);
    Bundle args = new Bundle();
    args.putString(CITY_NAME, cityName);
    instance.setArguments(args);
    return instance;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
    ButterKnife.bind(this, rootView);
    presenter = new WeatherDetailFragmentPresenter(this);
    initData();
    initView();
    addListener();
    return rootView;
  }

  @Override
  protected void initData() {
    String cityName = getArguments().getString(CITY_NAME);
    presenter.getWeatherData(cityName);
  }

  @Override
  protected void initView() {
    recyclerView.setLayoutManager(
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
  }

  @Override
  protected void addListener() {
    listenRecyclerView();
    temperature.getViewTreeObserver()
        .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override
          public void onGlobalLayout() {
            computeAlphaReferenceValue();
            temperature.getViewTreeObserver().removeOnGlobalLayoutListener(this);
          }
        });
  }

  private void computeAlphaReferenceValue() {
    int temperatureY = temperature.getBottom();
    int temperatureHeight = temperature.getMeasuredHeight();
    int recyclerViewPaddingTop = recyclerView.getPaddingTop();
    alphaReferenceValue = recyclerViewPaddingTop - temperatureY + temperatureHeight / 8;
  }

  private void listenRecyclerView() {
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        totalDy -= dy;

        float translationY = (float) (totalDy * 0.3);
        header.setTranslationY(translationY);

        float alpha = 1 - (float) (Math.abs(totalDy * 0.3)) / alphaReferenceValue;
        temperature.setAlpha(alpha);
        super.onScrolled(recyclerView, dx, dy);
      }
    });
  }

  @Override
  protected BasePresenter getPresenter() {
    return presenter;
  }

  @Override
  public void onWeatherDataUpdate(WeatherData data) {
    if (data != null) {
      updateView(data.getData());
    } else {
      showEmptyView();
    }
  }

  private void updateView(WeatherData.Data data) {
    emptyContainer.setVisibility(View.GONE);
    setHeaderView(data);
    setRecyclerView(data);
  }

  private void setHeaderView(WeatherData.Data data) {
    String cityName = data.getRealtime().getCity_name();
    this.cityName.setText(cityName);

    String weatherInfo = data.getRealtime().getWeather().getInfo();
    this.weatherInfo.setText(weatherInfo);

    String airQualityStrFormat = getString(R.string.air_quality);
    String airQuality = "暂无数据";
    if (data.getPm25() != null && data.getPm25().getPm25() != null) {
      airQuality = String.format(airQualityStrFormat, data.getPm25().getPm25().getQuality());
    }
    this.airQuality.setText(airQuality);

    String temperatureFormat = getString(R.string.temperature);
    String temperature =
        String.format(temperatureFormat, data.getRealtime().getWeather().getTemperature());
    this.temperature.setText(temperature);
  }

  private void setRecyclerView(WeatherData.Data data) {
    if (data == null) {
      return;
    }
    if (adapter == null) {
      recyclerView.setAdapter(new FutureWeathersAdapter(getActivity(), data));
    } else {
      adapter.updateData(data);
    }
  }

  private void showEmptyView() {
    this.cityName.setText(getArguments().getString(CITY_NAME));
    emptyContainer.setVisibility(View.VISIBLE);
  }
}
