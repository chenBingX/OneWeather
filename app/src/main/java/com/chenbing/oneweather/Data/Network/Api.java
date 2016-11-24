package com.chenbing.oneweather.Data.Network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public interface Api {

  @GET("")
  Call<BaseResponse> getWeatherData();
}
