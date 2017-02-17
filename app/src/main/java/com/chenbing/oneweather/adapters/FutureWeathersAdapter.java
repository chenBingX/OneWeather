package com.chenbing.oneweather.adapters;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.CustomViews.ItemView.FutureWeatherItem;
import com.chenbing.oneweather.CustomViews.ItemView.TodayDetailInfoItem;
import com.chenbing.oneweather.Data.Weather;
import com.chenbing.oneweather.Data.WeatherData;
import com.chenbing.oneweather.View.BaseView.BaseItemViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/5
 * Notes:
 */

public class FutureWeathersAdapter extends RecyclerView.Adapter {
  public static final int WEATHER_ITEM = 1;
  public static final int TODAY_INFO_ITEM = 3;


  private Context mContext;
  private List<Weather> weathers = new ArrayList<>();
  private WeatherData.Data data;

  public FutureWeathersAdapter(Context mContext, WeatherData.Data data) {
    this.mContext = mContext;
    this.data = data;
    this.weathers.addAll(data.getWeather());
  }


  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == WEATHER_ITEM) {
      return new BaseItemViewHolder(new FutureWeatherItem(mContext));
    } else if(viewType == TODAY_INFO_ITEM){
      return new BaseItemViewHolder(new TodayDetailInfoItem(mContext));
    } else {
      return null;
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == WEATHER_ITEM){
      ((FutureWeatherItem)holder.itemView).setData(weathers.get(position), position);
    } else if (getItemViewType(position) == TODAY_INFO_ITEM){
      ((TodayDetailInfoItem)holder.itemView).setData(data, position);
    }

  }

  @Override
  public int getItemViewType(int position) {
    if (position < weathers.size()){
      return WEATHER_ITEM;
    } else if (position == weathers.size()){
      return TODAY_INFO_ITEM;
    } else {
      return 0;
    }

  }

  @Override
  public int getItemCount() {
    return weathers.size() + 1;
  }

  public void updateData(WeatherData.Data data){
    this.data = data;
    this.weathers.clear();
    this.weathers.addAll(data.getWeather());
    notifyDataSetChanged();
  }
}
