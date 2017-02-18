package com.chenbing.oneweather.adapters;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.CustomViews.ItemView.WeatherListItem;
import com.chenbing.oneweather.CustomViews.ItemView.WeatherListItemFooter;
import com.chenbing.oneweather.Data.SimpleWeather;
import com.chenbing.oneweather.View.BaseView.BaseItemViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project Name:OneWeather
 * Author:Chice
 * Date:2016/12/22
 * Notes:
 */

public class WeatherListAdapter extends RecyclerView.Adapter{
  public static final int ITEM_NORMAL = 1;
  public static final int ITEM_FOOT= 2;

  private Context mContext;
  private List<SimpleWeather> datas = new ArrayList<>();

  private OnItemClickListener onItemClickListener;

  public WeatherListAdapter(Context mContext, List<SimpleWeather> datas) {
    this.mContext = mContext;
    this.datas.clear();
    this.datas.addAll(datas);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == ITEM_NORMAL){
      return new BaseItemViewHolder(new WeatherListItem(mContext));
    } else if (viewType == ITEM_FOOT){
      return new BaseItemViewHolder(new WeatherListItemFooter(mContext));

    }
    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == ITEM_NORMAL){
      ((WeatherListItem)holder.itemView).setData(datas.get(position), position);
      holder.itemView.setOnClickListener(v -> {
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(v, position);
        }
      });
    } else if (getItemViewType(position) == ITEM_FOOT){
      
    }
  }

  @Override
  public int getItemCount() {
    return datas.size() + 1;
  }

  @Override
  public int getItemViewType(int position) {
    int type = ITEM_NORMAL;
    if (position < datas.size()){
      type = ITEM_NORMAL;
    } else if (position == datas.size()){
      type = ITEM_FOOT;
    }
    return type;
  }

  public void updateDatas(List<SimpleWeather> datas){
    if (datas != null && !datas.isEmpty()){
      this.datas.clear();
      this.datas.addAll(datas);
    }
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public static interface OnItemClickListener {
    void onItemClick(View v, int position);
  }
}
