package com.chenbing.oneweather.Data;

import java.util.List;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/29
 * Notes:
 */

public class Life {
  private String date;
  private Info info;

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }


  /**
   * date : 2016-11-29
   * info :
   * {"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["不宜","不宜洗车，未来24小时内有雪，如果在此期间洗车，雪水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降雪，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}
   */
  public static class Info {
    private List<String> chuanyi;
    private List<String> ganmao;
    private List<String> kongtiao;
    private List<String> xiche;
    private List<String> yundong;
    private List<String> ziwaixian;

    public List<String> getChuanyi() {
      return chuanyi;
    }

    public void setChuanyi(List<String> chuanyi) {
      this.chuanyi = chuanyi;
    }

    public List<String> getGanmao() {
      return ganmao;
    }

    public void setGanmao(List<String> ganmao) {
      this.ganmao = ganmao;
    }

    public List<String> getKongtiao() {
      return kongtiao;
    }

    public void setKongtiao(List<String> kongtiao) {
      this.kongtiao = kongtiao;
    }

    public List<String> getXiche() {
      return xiche;
    }

    public void setXiche(List<String> xiche) {
      this.xiche = xiche;
    }

    public List<String> getYundong() {
      return yundong;
    }

    public void setYundong(List<String> yundong) {
      this.yundong = yundong;
    }

    public List<String> getZiwaixian() {
      return ziwaixian;
    }

    public void setZiwaixian(List<String> ziwaixian) {
      this.ziwaixian = ziwaixian;
    }
  }
}

