package com.chenbing.oneweather.Data;

import java.util.List;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/29
 * Notes:
 */

/**
 * date : 2016-11-29
 * info : {"day":["6","雨夹雪","4","","微风","07:14"],"night":["6","雨夹雪","-3","","微风","16:50"]}
 * week : 二
 * nongli : 十一月初一
 */
public class Weather {
  private String date;
  private Info info;
  private String week;
  private String nongli;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }

  public String getWeek() {
    return week;
  }

  public void setWeek(String week) {
    this.week = week;
  }

  public String getNongli() {
    return nongli;
  }

  public void setNongli(String nongli) {
    this.nongli = nongli;
  }

  public static class Info {
    private List<String> day;
    private List<String> night;

    public List<String> getDay() {
      return day;
    }

    public void setDay(List<String> day) {
      this.day = day;
    }

    public List<String> getNight() {
      return night;
    }

    public void setNight(List<String> night) {
      this.night = night;
    }
  }
}

