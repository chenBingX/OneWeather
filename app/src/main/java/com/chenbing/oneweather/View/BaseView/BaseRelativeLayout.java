package com.chenbing.oneweather.View.BaseView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:2016/10/9
 * Notes:
 */
public abstract class BaseRelativeLayout extends RelativeLayout {
  public BaseRelativeLayout(Context context) {
    this(context, null);
  }

  public BaseRelativeLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    bindView();
  }

  private void bindView() {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    inflater.inflate(getLayout(), this, true);
    ButterKnife.bind(this);
  };

  abstract protected int getLayout();
}
