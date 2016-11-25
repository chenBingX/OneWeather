package com.chenbing.oneweather.View.activitys;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Presenter.MainActivityPresenter;
import com.chenbing.oneweather.Presenter.MainActivityPresenterApi;
import com.chenbing.oneweather.View.BaseView.BaseActivity;

import android.os.Bundle;

public class MainActivity extends BaseActivity implements MainActivityView {
  private MainActivityPresenterApi presenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    presenter = new MainActivityPresenter(this);
    initData();
    initView();
    addListener();
  }

  @Override
  protected void initData() {

  }

  @Override
  protected void initView() {

  }

  @Override
  protected void addListener() {

  }
}
