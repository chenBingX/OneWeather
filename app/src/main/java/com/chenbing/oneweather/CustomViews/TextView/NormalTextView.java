package com.chenbing.oneweather.CustomViews.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/28
 * Notes:
 */

public class NormalTextView extends TextView {
  public NormalTextView(Context context) {
    super(context);
  }

  public NormalTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NormalTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    this.setTypeface(Typeface.DEFAULT);
  }
}
