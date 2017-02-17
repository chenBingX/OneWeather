package com.chenbing.oneweather.Utils;

import com.google.gson.Gson;

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/24
 * Notes:
 */
public class GsonUtils {
  private static Gson gson;

  private GsonUtils() {

  }

  public static Gson getSingleInstance() {
    if (gson == null) {
      synchronized (GsonUtils.class) {
        if (gson == null) {
          gson = new Gson();
        }
      }
    }
    return gson;
  }

  public static Gson newInstance(){
    return new Gson();
  }
}
