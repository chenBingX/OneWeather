package com.chenbing.oneweather.View.BaseView;
import android.support.v7.app.AppCompatActivity;

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

}
