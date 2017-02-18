package com.chenbing.oneweather.View.activitys;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.CustomViews.ZoomOutPageTransformer;
import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.Data.Cache.Config;
import com.chenbing.oneweather.Presenter.BasePresenter;
import com.chenbing.oneweather.Presenter.activity.MainActivityPresenter;
import com.chenbing.oneweather.Presenter.activity.MainActivityPresenterApi;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.View.BaseView.BaseActivity;
import com.chenbing.oneweather.View.BaseView.BaseFragment;
import com.chenbing.oneweather.View.fragments.WeatherDetailFragment;
import com.chenbing.oneweather.adapters.WeatherListAdapter;
import com.chenbing.oneweather.adapters.AppFragmentPagerAdapter.FragmentPagerAdapter;

import android.app.Fragment;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityView {

  @BindView(R.id.pager_container)
  ViewPager pagerContainer;

  @BindView(R.id.bottom_opration)
  ViewGroup vgBottomOpration;
  @BindView(R.id.left_button)
  ImageView btnMore;
  @BindView(R.id.right_button)
  ImageView btnSet;

  @BindView(R.id.weather_list)
  RecyclerView rvWeatherList;

  private MainActivityPresenterApi presenter;
  private List<BaseFragment> fragments = new ArrayList<>();
  private WeatherListAdapter weatherListAdapter;
  private List<SimpleWeather> simpleWeathers = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    presenter = new MainActivityPresenter(this);
    ButterKnife.bind(this);
    initData();
    initView();
    addListener();
  }

  @Override
  protected void initData() {
    fragments.add(WeatherDetailFragment.newInstance(null));
    List<String> myCityList = Config.getMyCityList();
    if (myCityList != null && !myCityList.isEmpty()) {
      for (String cityName : myCityList) {
        fragments.add(WeatherDetailFragment.newInstance(cityName));
      }
    }
  }

  @Override
  protected void initView() {
    configWindow();
    initPagerContainer();
    initWeatherList();
  }

  private void configWindow() {
    // 透明状态栏
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    getWindow().setFormat(PixelFormat.TRANSPARENT);
  }

  private void initPagerContainer() {
    pagerContainer.setAdapter(createAdapter());
    pagerContainer.setPageTransformer(false, new ZoomOutPageTransformer());
  }

  @NonNull
  private FragmentPagerAdapter createAdapter() {
    return new FragmentPagerAdapter(getFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        return fragments.get(position);
      }

      @Override
      public int getCount() {
        return fragments.size();
      }
    };
  }

  private void initWeatherList() {
    rvWeatherList
        .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rvWeatherList.setItemAnimator(new DefaultItemAnimator());
    weatherListAdapter = new WeatherListAdapter(this, simpleWeathers);
    weatherListAdapter.setOnItemClickListener((v, position) -> {
      showWeatherList(false);
      pagerContainer.setCurrentItem(position);
    });
    rvWeatherList.setAdapter(weatherListAdapter);
  }

  private void showWeatherList(boolean isShow) {
    if (isShow){
      rvWeatherList.setVisibility(View.VISIBLE);
      pagerContainer.setVisibility(View.GONE);
      vgBottomOpration.setVisibility(View.GONE);
    } else {
      rvWeatherList.setVisibility(View.GONE);
      pagerContainer.setVisibility(View.VISIBLE);
      vgBottomOpration.setVisibility(View.VISIBLE);
    }
  }

  @Override
  protected void addListener() {
    btnSet.setOnClickListener(v -> onBtnSetClick());
    btnMore.setOnClickListener(v -> onBtnMoreClick());
  }

  private void onBtnSetClick() {

  }

  private void onBtnMoreClick() {
    showWeatherList(true);
  }

  @Override
  protected BasePresenter getPresenter() {
    return presenter;
  }

  @Override
  public void updateSimpleWeatherDatas(SimpleWeather simpleWeather) {
    simpleWeathers.add(simpleWeather);
    LogUtils.e("simpleWeather = " + simpleWeather.getCity());
    weatherListAdapter.updateDatas(simpleWeathers);
    weatherListAdapter.notifyItemRangeInserted(simpleWeathers.size() - 1, 1);
    updateMyCityListCache();
  }

  private void updateMyCityListCache() {
    List<String> myCityList = new ArrayList<>();
    for (int i = 1; i < simpleWeathers.size(); i++) {
      myCityList.add(simpleWeathers.get(i).getCity());
    }
    Config.saveMyCityList(myCityList);
  }

  @Override
  public void addCityPage(String cityName) {
    fragments.add(WeatherDetailFragment.newInstance(cityName));
    PagerAdapter adapter = pagerContainer.getAdapter();
    if (adapter != null) {
      adapter.notifyDataSetChanged();
    }
  }

  @Override
  public void subtractCityPage(int position) {

  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && rvWeatherList.getVisibility() == View.VISIBLE){
      showWeatherList(false);
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }


}
