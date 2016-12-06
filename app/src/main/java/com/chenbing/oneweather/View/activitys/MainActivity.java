package com.chenbing.oneweather.View.activitys;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.CustomViews.ZoomOutPageTransformer;
import com.chenbing.oneweather.Presenter.BasePresenter;
import com.chenbing.oneweather.Presenter.activity.MainActivityPresenter;
import com.chenbing.oneweather.Presenter.activity.MainActivityPresenterApi;
import com.chenbing.oneweather.View.BaseView.BaseActivity;
import com.chenbing.oneweather.View.BaseView.BaseFragment;
import com.chenbing.oneweather.View.fragments.WeatherDetailFragment;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainActivityView {

  @BindView(R.id.pager_container)
  ViewPager pagerContainer;

  private MainActivityPresenterApi presenter;
  private List<BaseFragment> fragments = new ArrayList<>();

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
  }

  @Override
  protected void initView() {
    setWindowProperties();
    pagerContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override
      public Fragment getItem(int position) {
        return fragments.get(position);
      }

      @Override
      public int getCount() {
        return fragments.size();
      }
    });

    pagerContainer.setPageTransformer(false, new ZoomOutPageTransformer());
  }

  private void setWindowProperties() {
    // 透明状态栏
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    getWindow().setFormat(PixelFormat.TRANSPARENT);
  }

  @Override
  protected void addListener() {

  }

  @Override
  protected BasePresenter getPresenter() {
    return presenter;
  }
}
