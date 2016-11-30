package com.chenbing.oneweather.Data.Network;

import com.chenbing.oneweather.BuildConfig;
import com.chenbing.oneweather.ChiceApplication;
import com.chenbing.oneweather.Data.BaseWeatherResponse;
import com.chenbing.oneweather.Data.Result;
import com.chenbing.oneweather.Utils.AppUtils;
import com.chenbing.oneweather.Utils.GsonUtils;
import com.chenbing.oneweather.Utils.LogUtils;
import com.chenbing.oneweather.Utils.NetworkUtils;

import android.support.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public class ApiClient {
  public static final Api api;
  private static String BASE_WEATHER_URL = "http://op.juhe.cn/";

  static {
    api = getRetrofit().create(Api.class);
  }

  private static Retrofit getRetrofit() {
    OkHttpClient client = getClient();
    GsonConverterFactory gsonConverterFactory =
        GsonConverterFactory.create(GsonUtils.newInstance());
    return new Retrofit.Builder()
        .baseUrl(BASE_WEATHER_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(client)
        .build();
  }

  @NonNull
  private static OkHttpClient getClient() {
    Interceptor interceptor = chain -> {
      Request request = chain.request();
      return chain.proceed(addRequestHeader(request));
    };
    // 是一个拦截器，用于输出网络请求和结果的 Log，
    // 可以配置 level 为 BASIC / HEADERS / BODY，都很好理解，对应的是原来 retrofit 的 set log level 方法，现在
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    return new OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .build();
  }

  private synchronized static Request addRequestHeader(Request request) {
    Request.Builder rq = request.newBuilder();
    if (ChiceApplication.getAppContext() != null) {
      String imei = AppUtils.getImei(ChiceApplication.getAppContext());
      if (imei != null) {
        rq.addHeader("imei", imei);
        LogUtils.v("imei: " + imei);
      }

      String imsi = AppUtils.getImsi(ChiceApplication.getAppContext());
      if (imsi != null) {
        rq.addHeader("imsi", imsi);
        LogUtils.v("imsi: " + imsi);
      }

      String networkType = NetworkUtils.getCurrentNetworkType(ChiceApplication.getAppContext());
      if (networkType != null) {
        rq.addHeader("net", networkType);
        LogUtils.e("net: " + networkType);
      }

    }
    String sdk = AppUtils.getSdkVersion();
    rq.addHeader("cv", sdk);
    LogUtils.e("cv: " + sdk);

    String os = "Android";
    rq.addHeader("os", os);
    LogUtils.e("os: " + os);

    String tc = AppUtils.getChannelName();
    rq.addHeader("tc", tc);
    LogUtils.e("tc: " + tc);

    String dt = AppUtils.getModelVersion();
    rq.addHeader("dt", dt);
    LogUtils.e("dt: " + dt);
    return rq.build();
  }

  public static void getWeatherData(OnSuccessCallback<Result> onSuccessCallback,
      OnFailureCallback<Result> onFailureCallback) {
    requestData(api.getWeatherData("北京", "3b8c8c784b4b439701fc34522213884f"), onSuccessCallback,
        onFailureCallback);
  }

  private static <T> void requestData(Call<BaseWeatherResponse<T>> call,
      OnSuccessCallback<T> onSuccessCallback,
      OnFailureCallback<T> onFailureCallback) {
    call.enqueue(new Callback<BaseWeatherResponse<T>>() {
      @Override
      public void onResponse(Call<BaseWeatherResponse<T>> call, Response<BaseWeatherResponse<T>> response) {
        if (response.isSuccessful() && response.body() != null) {
          LogUtils.e("结果 = " + GsonUtils.getSingleInstance().toJson(response.body()));
          if (onSuccessCallback != null) {
            onSuccessCallback.onSuccess(response.body().getResult());
          }
        }
      }

      @Override
      public void onFailure(Call<BaseWeatherResponse<T>> call, Throwable t) {
      }
    });
  }
}
