package com.chenbing.oneweather.CustomViews.ItemView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Data.RxEvent.CityNameEvent;
import com.chenbing.oneweather.Presenter.View.WeatherListItemFooterPresenter;
import com.chenbing.oneweather.Presenter.View.WeatherListItemFooterPresenterApi;
import com.chenbing.oneweather.Utils.DisplayUtils;
import com.chenbing.oneweather.Utils.RxBus;
import com.chenbing.oneweather.View.BaseView.BaseRelativeLayout;
import com.chenbing.oneweather.adapters.CityListAdapter;
import com.chenbing.oneweather.icing.SimpleTextWatcher;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
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
  @BindView(R.id.not_found)
  TextView tvNotFound;

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
    init();
  }

  @Override
  protected int getLayout() {
    return R.layout.item_weather_list_footer;
  }

  private void init() {
    presenter = new WeatherListItemFooterPresenter(this);
    initRvCityList();
    addListener();
  }

  private void initRvCityList() {
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    rvCityList.setLayoutManager(layoutManager);
    rvCityList.setItemAnimator(new DefaultItemAnimator());
    cityListAdapter = new CityListAdapter(getContext(), datas);
    rvCityList.setAdapter(cityListAdapter);
  }

  private void addListener() {
    tvCancel.setOnClickListener(v->{
      clearSearch();
    });

    cityListAdapter.setOnItemClickListener((v, cityName) -> {
      if (!TextUtils.isEmpty(cityName)){
        CityNameEvent cityNameEvent = new CityNameEvent();
        cityNameEvent.cityName = cityName;
        RxBus.get().post(cityNameEvent);
        clearSearch();
      }
    });

    listenEtSearchTextChange();
  }

  private void clearSearch() {
    etSearch.setText("");
    etSearch.clearFocus();
    tvCancel.setFocusableInTouchMode(true);
    tvCancel.requestFocus();
    rvCityList.setVisibility(GONE);
    tvNotFound.setVisibility(GONE);
  }

  private void listenEtSearchTextChange() {
    etSearch.addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s.toString())) {
          tryToMatchCity(s.toString());
        } else {
          cityListAdapter.updateDatas(Collections.emptyList());
          rvCityList.setVisibility(GONE);
          tvNotFound.setVisibility(GONE);
        }
      }
    });
  }

  private void tryToMatchCity(String s) {
    presenter.matchCity(s, cities -> {
      if (!cities.isEmpty()) {
        cityListAdapter.setMatchContent(s);
        cityListAdapter.updateDatas(cities);
        rvCityList.setVisibility(VISIBLE);
        tvNotFound.setVisibility(GONE);
      } else {
        rvCityList.setVisibility(GONE);
        tvNotFound.setVisibility(VISIBLE);
      }
    });
  }

  public void setPosition(int position) {
    int padding = vpRoot.getPaddingLeft();
    if (position == 0) {
      vpRoot.setPadding(padding, DisplayUtils.dipToPx(30), padding, padding);
    } else {
      vpRoot.setPadding(padding, padding, padding, padding);
    }
  }
}
