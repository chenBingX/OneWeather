package com.chenbing.oneweather;

import android.app.Application;
import android.content.Context;

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
    //初始化异常处理类
//    CrashHandler.getInstance().init(context);
  }

  public static Context getAppContext(){
    return context;
  }
}
