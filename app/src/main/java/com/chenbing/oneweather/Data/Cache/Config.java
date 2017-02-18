package com.chenbing.oneweather.Data.Cache;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.chenbing.oneweather.BuildConfig;
import com.chenbing.oneweather.Utils.AppUtils;
import com.chenbing.oneweather.Utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/12/5
 * Notes:
 */

public class Config {

  public static final boolean DEBUG = BuildConfig.DEBUG;

  public static final String GENERIC_CONFIG_PREFERENCE_NAME = "CoorChice_config";
  private static String START_APP_TIME_KEY = "last_start_app_time";
  private static String MY_CITY_LIST_KEY = "my_city_list_key";

  private static long LAST_START_APP_TIME = 0;

  private static SharedPreferences genericSharedPrefs;

  private Config(){

  }

  public static synchronized void init(Context context) {
    if (genericSharedPrefs == null) {
      genericSharedPrefs = context.getSharedPreferences(GENERIC_CONFIG_PREFERENCE_NAME,
        Context.MODE_PRIVATE);

      LAST_START_APP_TIME = getStartAppTime();
      saveStartAppTime(System.currentTimeMillis());
    }
  }

  private static void saveStartAppTime(long startAppTime) {
    SharedPreferences.Editor editor = genericSharedPrefs.edit();
    editor.putLong(START_APP_TIME_KEY, startAppTime);
    editor.apply();
  }

  public static synchronized long getStartAppTime() {
    return genericSharedPrefs.getLong(START_APP_TIME_KEY, 0);
  }

  public static synchronized long getLastStartAppTime() {
    return LAST_START_APP_TIME;
  }

  public static void saveMyCityList(List<String> myCityList) {
    SharedPreferences.Editor editor = genericSharedPrefs.edit();
    editor.putString(MY_CITY_LIST_KEY, GsonUtils.getSingleInstance().toJson(myCityList));
    editor.apply();
  }

  public static synchronized List<String> getMyCityList() {
    String stringJson = genericSharedPrefs.getString(MY_CITY_LIST_KEY, "");
    Type type = new TypeToken<List<String>>() {}.getType();
    return GsonUtils.getSingleInstance().fromJson(stringJson, type);
  }

  public static final void saveObject(String key, @NonNull Object object)
    throws IOException {
    synchronized (genericSharedPrefs) {
      SharedPreferences.Editor editor = genericSharedPrefs.edit();
      String base64String = AppUtils.ObjectToBase64String(object);
      editor.putString(key, base64String);
      editor.apply();
    }
  }

  public static final <T> T restoreObject(String key, Class<T> clazz)
    throws IOException, ClassNotFoundException {
    synchronized (genericSharedPrefs) {
      T t = null;
      String base64String = genericSharedPrefs.getString(key, "");
      if (!base64String.equals("")) {
        t = AppUtils.Base64StringToObject(base64String, clazz);
      }
      return t;
    }
  }

}
