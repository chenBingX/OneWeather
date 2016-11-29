package com.chenbing.oneweather.Data.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public interface Api {

  // key = 3b8c8c784b4b439701fc34522213884f
  @GET("onebox/weather/query")
  Call<BaseWeatherResponse<Result>> getWeatherData(@Query("cityname") String cityName, @Query("key") String key);

}
