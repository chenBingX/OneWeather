package com.chenbing.oneweather.View.BaseView;
import android.support.v7.app.AppCompatActivity;

import com.chenbing.oneweather.Presenter.BasePresenter;

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:2016/10/8
 * Notes:
 */
public abstract class BaseActivity extends AppCompatActivity {
  abstract protected void initData();

  abstract protected void initView();

  abstract protected void addListener();

  /**
   * 创建Presenter后必须重写这个方法，将其作为返回值
   */
  abstract protected BasePresenter getPresenter();

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (getPresenter() != null){
      getPresenter().destroy(); //销毁Presenter，避免Activity对象因被Presenter持有而不能被销毁
    }
  }
}
