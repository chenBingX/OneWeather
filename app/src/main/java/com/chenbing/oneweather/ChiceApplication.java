package com.chenbing.oneweather;

import android.app.Application;
import android.content.Context;

import com.chenbing.oneweather.Data.Cache.Config;
import com.chenbing.oneweather.Utils.CrashHandler;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/23
 * Notes:
 */

public class ChiceApplication extends Application {

  private static Context context;
  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    CrashHandler.getInstance().init(context);
    Config.init(context);
  }

  public static Context getAppContext(){
    return context;
  }
}
