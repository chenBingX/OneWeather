package com.chenbing.oneweather.Data.Network;

import android.support.annotation.Nullable;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public interface OnFailureCallback<T> {
  void onFailure(@Nullable String message);
}
