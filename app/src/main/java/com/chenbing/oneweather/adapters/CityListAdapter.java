package com.chenbing.oneweather.adapters;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.View.BaseView.BaseItemViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Project Name:OneWeather
 * Author:CoorChice
 * Date:2017/2/17
 * Notes:
 */

public class CityListAdapter extends RecyclerView.Adapter {
  private Context context;
  private List<String> datas = new ArrayList<>();
  private OnItemClickListener onItemClickListener;

  public CityListAdapter(Context context, List<String> datas) {
    this.context = context;
    this.datas = datas;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    TextView view =
        (TextView) LayoutInflater.from(context).inflate(R.layout.item_text_view, parent, false);
    return new BaseItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((TextView) (holder.itemView)).setText(datas.get(position));
    holder.itemView.setOnClickListener(v -> {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(v, position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public static interface OnItemClickListener {
    void onItemClick(View v, int position);
  }

  public void updateDatas(List<String> datas){
    this.datas = datas;
    notifyDataSetChanged();
  }
}
