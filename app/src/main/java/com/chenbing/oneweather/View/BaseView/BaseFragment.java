package com.chenbing.oneweather.View.BaseView;

import com.chenbing.oneweather.Presenter.BasePresenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:2016/11/19
 * Notes:
 */

public abstract class BaseFragment extends Fragment {

  abstract protected void initData();

  abstract protected void initView();

  abstract protected void addListener();

  /**
   * 创建Presenter后必须重写这个方法，将其作为返回值
   */
  abstract protected BasePresenter getPresenter();

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.setClickable(true);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (getPresenter() != null){
      getPresenter().destroy(); //销毁Presenter，避免Activity对象因被Presenter持有而不能被销毁
    }
  }
}
