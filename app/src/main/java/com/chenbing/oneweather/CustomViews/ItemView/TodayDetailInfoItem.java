package com.chenbing.oneweather.CustomViews.ItemView;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.View.BaseView.BaseRelativeLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/5
 * Notes:
 */

public class TodayDetailInfoItem extends BaseRelativeLayout {
  @BindView(R.id.chuanyi)
  TextView chuanyi;
  @BindView(R.id.update_time_data)
  TextView updateTime;
  @BindView(R.id.moon_data)
  TextView moon;
  @BindView(R.id.wind_direct_data)
  TextView windDirect;
  @BindView(R.id.wind_power_data)
  TextView windPower;
  @BindView(R.id.air_quality_index_data)
  TextView airQualityIndex;
  @BindView(R.id.air_quality_data)
  TextView airQuality;

  public TodayDetailInfoItem(Context context) {
    this(context, null);
  }

  public TodayDetailInfoItem(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TodayDetailInfoItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected int getLayout() {
    return R.layout.item_today_detail_info;
  }


  public void setData(WeatherData.Data data, int position) {
    String chuanyi = data.getLife().getInfo().getChuanyi().get(1);
    this.chuanyi.setText(chuanyi);

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    String updateTime;
    if ((long)(data.getRealtime().getDataUptime()) < 1000000000000L){
      updateTime = dateFormat.format(data.getRealtime().getDataUptime() * 1000);
    } else {
      updateTime = dateFormat.format(data.getRealtime().getDataUptime());
    }
    this.updateTime.setText(updateTime);

    String moon = data.getRealtime().getMoon();
    this.moon.setText(moon);

    String windDirect = data.getRealtime().getWind().getDirect();
    this.windDirect.setText(windDirect);

    String windPower = data.getRealtime().getWind().getPower();
    this.windPower.setText(windPower);

    String airQualityIndex = getResources().getString(R.string.no_data);
    if (data.getPm25() != null && data.getPm25().getPm25() != null) {
      airQualityIndex = data.getPm25().getPm25().getCurPm();
    }
    this.airQualityIndex.setText(airQualityIndex);


    String airQuality = getResources().getString(R.string.no_data);
    if (data.getPm25() != null && data.getPm25().getPm25() != null) {
      airQuality = data.getPm25().getPm25().getQuality();
    }
    this.airQuality.setText(airQuality);
  }
}
