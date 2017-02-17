package com.chenbing.oneweather.CustomViews.ItemView;

import java.util.ArrayList;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Presenter.View.WeatherListItemFooterPresenter;
import com.chenbing.oneweather.Presenter.View.WeatherListItemFooterPresenterApi;
import com.chenbing.oneweather.View.BaseView.BaseRelativeLayout;
import com.chenbing.oneweather.adapters.CityListAdapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;

public class WeatherListItemFooter extends BaseRelativeLayout {
  @BindView(R.id.root)
  ViewGroup vpRoot;
  @BindView(R.id.search)
  EditText etSearch;
  @BindView(R.id.cancel)
  TextView tvCancel;

  @BindView(R.id.city_list)
  RecyclerView rvCityList;

  private List<String> datas = new ArrayList<>();
  private CityListAdapter cityListAdapter;
  private WeatherListItemFooterPresenterApi presenter;


  public WeatherListItemFooter(Context context) {
    this(context, null);
  }

  public WeatherListItemFooter(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WeatherListItemFooter(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView();
    addListener();
  }

  @Override
  protected int getLayout() {
    return R.layout.item_weather_list_footer;
  }

  private void initView() {
    presenter = new WeatherListItemFooterPresenter(this);

    rvCityList.setLayoutManager(new LinearLayoutManager(getContext()));
    rvCityList.setItemAnimator(new DefaultItemAnimator());
    cityListAdapter = new CityListAdapter(getContext(), datas);
    rvCityList.setAdapter(cityListAdapter);

  }

  private void addListener() {
    tvCancel.setOnClickListener(v->{
      if (rvCityList.getVisibility() == VISIBLE) {
        rvCityList.setVisibility(GONE);
      } else {
        rvCityList.setVisibility(VISIBLE);
      }
    });

    etSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {


      }

      @Override
      public void afterTextChanged(Editable s) {
        presenter.matchCity(s.toString(), cities -> {
          if (!cities.isEmpty()) {
            cityListAdapter.updateDatas(cities);
          } else {

          }
        });
      }
    });
  }

}
