package com.chenbing.oneweather.Utils;

import java.util.Locale;


import android.util.Log;

import com.chenbing.oneweather.BuildConfig;

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/23
 * Notes:
 */
public class LogUtils {

  public static boolean DEBUG = BuildConfig.DEBUG;
  /**
   * 该参数需要根据实际情况来设置才能准确获取期望的调用信息，比如：
   * 在Java中，该参数应该为3
   * 在一般Android中，该参数为4
   * 你需要自己打印的看看，调用showAllElementsInfo()就可以了。
   */
  private static final int INDEX = 4;

  private static String getPrefix() {
    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[INDEX];
    String className = stackTraceElement.getClassName();
    int classNameStartIndex = className.lastIndexOf(".") + 1;
    className = className.substring(classNameStartIndex);
    String methodName = stackTraceElement.getMethodName();
    int methodLine = stackTraceElement.getLineNumber();
    String format = "%s-%s(L:%d)";
    return String.format(Locale.CHINESE, format, className, methodName, methodLine);
  }

  public static void v(String content) {
    if (DEBUG) Log.v(getPrefix(), content);
  }

  public static void v(String content, Throwable tr) {
    if (DEBUG) Log.v(getPrefix(), content, tr);
  }

  public static void d(String content) {
    if (DEBUG) Log.d(getPrefix(), content);
  }

  public static void d(String content, Throwable tr) {
    if (DEBUG) Log.d(getPrefix(), content, tr);
  }

  public static void i(String content) {
    if (DEBUG) Log.i(getPrefix(), content);
  }

  public static void i(String content, Throwable tr) {
    if (DEBUG) Log.i(getPrefix(), content, tr);
  }

  public static void w(String content) {
    if (DEBUG) Log.e(getPrefix(), content);
  }

  public static void w(String content, Throwable tr) {
    if (DEBUG) Log.w(getPrefix(), content, tr);
  }

  public static void e(String content) {
    if (DEBUG) Log.e(getPrefix(), content);
  }

  public static void e(String content, Throwable tr) {
    if (DEBUG) Log.e(getPrefix(), content, tr);
  }

  public static String showAllElementsInfo() {
    String print = "";
    int count = 0;
    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    for (StackTraceElement stackTraceElement : stackTraceElements) {
      count++;
      print += String.format("ClassName:%s " +
          "\nMethodName:%s " +
          "\nMethodLine:%d " +
          "\n当前是第%d个 " +
          "\n---------------------------- " +
          "\n ",
          stackTraceElement.getClassName(),
          stackTraceElement.getMethodName(),
          stackTraceElement.getLineNumber(),
          count);
    }
    return print;
  }
}
