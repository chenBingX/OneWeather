package com.chenbing.oneweather.adapters;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.View.BaseView.BaseItemViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
  private String matchContent = "";

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
    setItemViewData(holder, position);
    listenItemClick(holder, position);
  }

  private void listenItemClick(RecyclerView.ViewHolder holder, int position) {
    holder.itemView.setOnClickListener(v -> {
      if (onItemClickListener != null) {
        onItemClickListener.onItemClick(v, datas.get(position));
      }
    });
  }

  private void setItemViewData(RecyclerView.ViewHolder holder, int position) {
    if (!TextUtils.isEmpty(matchContent)){
      SpannableString ss = new SpannableString(datas.get(position));
      int index = datas.get(position).indexOf(matchContent);
      int length = matchContent.length();
      ss.setSpan(new ForegroundColorSpan(Color.WHITE), index, index + length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      ((TextView) (holder.itemView)).setText(ss);
    } else {
      ((TextView) (holder.itemView)).setText(datas.get(position));
    }
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  public static interface OnItemClickListener {
    void onItemClick(View v, String cityName);
  }

  public void updateDatas(List<String> datas){
    this.datas = datas;
    notifyDataSetChanged();
  }

  public String getMatchContent() {
    return matchContent;
  }

  public void setMatchContent(String matchContent) {
    this.matchContent = matchContent;
  }
}
