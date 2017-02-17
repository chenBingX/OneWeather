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

public class NormalBoldTextView extends TextView {
  public NormalBoldTextView(Context context) {
    super(context);
  }

  public NormalBoldTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public NormalBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    this.setTypeface(Typeface.DEFAULT_BOLD);
  }

}
