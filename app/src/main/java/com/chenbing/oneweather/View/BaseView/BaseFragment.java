package com.chenbing.oneweather.View.BaseView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:2016/11/19
 * Notes:
 */

public class BaseFragment extends Fragment {

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    view.setClickable(true);
  }

}
