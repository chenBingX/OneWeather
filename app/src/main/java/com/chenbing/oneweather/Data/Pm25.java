package com.chenbing.oneweather.Data;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/29
 * Notes:
 */

import java.io.Serializable;

/**
 * key : Beijing
 * show_desc : 0
 * pm25 : {"curPm":"103","pm25":"72","pm10":"116","level":3,"quality":"轻度污染","des":"轻微污染
 * 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"}
 * dateTime : 2016年11月29日10时
 * cityName : 北京
 */
public class Pm25 implements Serializable{
  private static final long serialVersionUID = 1831873779905345493L;
  private String key;
  private int show_desc;
  private Pm25Info pm25;
  private String dateTime;
  private String cityName;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public int getShow_desc() {
    return show_desc;
  }

  public void setShow_desc(int show_desc) {
    this.show_desc = show_desc;
  }

  public Pm25Info getPm25() {
    return pm25;
  }

  public void setPm25(Pm25Info pm25) {
    this.pm25 = pm25;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }
  /**
   * curPm : 103
   * pm25 : 72
   * pm10 : 116
   * level : 3
   * quality : 轻度污染
   * des : 轻微污染 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。
   */
  public static class Pm25Info implements Serializable{
    private static final long serialVersionUID = 2324707852474004330L;
    private String curPm;
    private String pm25;
    private String pm10;
    private int level;
    private String quality;
    private String des;

    public String getCurPm() {
      return curPm;
    }

    public void setCurPm(String curPm) {
      this.curPm = curPm;
    }

    public String getPm25() {
      return pm25;
    }

    public void setPm25(String pm25) {
      this.pm25 = pm25;
    }

    public String getPm10() {
      return pm10;
    }

    public void setPm10(String pm10) {
      this.pm10 = pm10;
    }

    public int getLevel() {
      return level;
    }

    public void setLevel(int level) {
      this.level = level;
    }

    public String getQuality() {
      return quality;
    }

    public void setQuality(String quality) {
      this.quality = quality;
    }

    public String getDes() {
      return des;
    }

    public void setDes(String des) {
      this.des = des;
    }
  }
}

