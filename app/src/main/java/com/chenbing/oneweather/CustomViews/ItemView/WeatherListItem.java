package com.chenbing.oneweather.CustomViews.ItemView;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.Utils.DisplayUtils;
import com.chenbing.oneweather.View.BaseView.BaseRelativeLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/5
 * Notes:
 */

public class WeatherListItem extends BaseRelativeLayout {
  @BindView(R.id.time)
  TextView tvTime;
  @BindView(R.id.city_name)
  TextView tvCityName;
  @BindView(R.id.temperature)
  TextView tvTemperature;

  @BindView(R.id.container)
  ViewGroup vpContainer;

  public WeatherListItem(Context context) {
    this(context, null);
  }

  public WeatherListItem(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeatherListItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected int getLayout() {
    return R.layout.item_weather_list;
  }

  public void setData(SimpleWeather data, int position) {
    if (position == 0){
      MarginLayoutParams layoutParams = (MarginLayoutParams) vpContainer.getLayoutParams();
      layoutParams.topMargin = DisplayUtils.dipToPx(30);
      vpContainer.requestLayout();
    }
    tvCityName.setText(data.getCity());
    tvTemperature.setText(String.format(getContext().getResources().getString(R.string.temperature),
        data.getTemperature()));
  }


}
