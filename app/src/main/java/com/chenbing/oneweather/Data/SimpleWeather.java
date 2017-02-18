package com.chenbing.oneweather.Data;

import java.io.Serializable;

/**
 * Project Name:OneWeather
 * Author:Chice
 * Date:2016/12/22
 * Notes:
 */

public class SimpleWeather implements Serializable {
  private static final long serialVersionUID = -1203703563648129626L;

  private String city;
  private String time;
  private String temperature;

  public SimpleWeather(){
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }
}
