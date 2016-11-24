package com.chenbing.oneweather.Utils;

import android.content.res.Resources;

/**
 * Project Name:AnimDveDemo
 * Author:Ice
 * Date:16/8/9
 * Notes:
 */
public class DisplayUtils {

  public static int dipToPx(int dip) {
    return (int) (dip * getDensity() + 0.5f);
  }

  public static float dipToPx(float dip) {
    return (dip * getDensity() + 0.5f);
  }

  public static int pxToDip(int px) {
    return (int) ((px - 0.5f) / getDensity());
  }

  public static float getDensity() {
    return Resources.getSystem().getDisplayMetrics().density;
  }

  public static int spToPx(int sp){
    return (int) (sp * getScaledDensity() + 0.5f);
  }

  public static int pxToSp(int px) {
    return (int) ((px - 0.5f) / getScaledDensity());
  }

  public static float getScaledDensity() {
    return Resources.getSystem().getDisplayMetrics().scaledDensity;
  }

  public static int getScreenWidth(){
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public static int getScreenHeight(){
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }



}
