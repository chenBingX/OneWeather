package com.chenbing.oneweather.Data.Network;

import java.util.List;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/29
 * Notes:
 */

public class F3h {
  private List<Temperature> temperature;
  private List<Precipitation> precipitation;

  public List<Temperature> getTemperature() {
    return temperature;
  }

  public void setTemperature(List<Temperature> temperature) {
    this.temperature = temperature;
  }

  public List<Precipitation> getPrecipitation() {
    return precipitation;
  }

  public void setPrecipitation(List<Precipitation> precipitation) {
    this.precipitation = precipitation;
  }

  /**
   * jg : 20161129080000
   * jb : 2
   */
  public static class Temperature {
    private String jg;
    private String jb;

    public String getJg() {
      return jg;
    }

    public void setJg(String jg) {
      this.jg = jg;
    }

    public String getJb() {
      return jb;
    }

    public void setJb(String jb) {
      this.jb = jb;
    }
  }

  /**
   * jg : 20161129080000
   * jf : 0.4
   */
  public static class Precipitation {
    private String jg;
    private String jf;

    public String getJg() {
      return jg;
    }

    public void setJg(String jg) {
      this.jg = jg;
    }

    public String getJf() {
      return jf;
    }

    public void setJf(String jf) {
      this.jf = jf;
    }
  }
}
