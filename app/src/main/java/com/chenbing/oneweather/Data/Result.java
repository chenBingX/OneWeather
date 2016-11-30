package com.chenbing.oneweather.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name:OneWeather
 * Author:IceChen
 * Date:2016/11/24
 * Notes:
 */

public class Result implements Serializable {
  private static final long serialVersionUID = 5257079040142218177L;
  /**
   * pubdate : 2016-11-29
   * pubtime : 08:00:00
   * realtime :
   * {"city_code":"101010100","city_name":"北京","date":"2016-11-29","time":"10:00:00","week":2,"moon":"十一月初一","dataUptime":1480385938,"weather":{"temperature":"2","humidity":"57","info":"多云","img":"1"},"wind":{"direct":"东风","power":"2级","offset":null,"windspeed":null}}
   * life :
   * {"date":"2016-11-29","info":{"chuanyi":["冷","天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"],"ganmao":["极易发","将有一次强降温过程，天气寒冷，且空气湿度较大，极易发生感冒，请特别注意增加衣服保暖防寒。"],"kongtiao":["开启制暖空调","您将感到有些冷，可以适当开启制暖空调调节室内温度，以免着凉感冒。"],"xiche":["不宜","不宜洗车，未来24小时内有雪，如果在此期间洗车，雪水和路上的泥水可能会再次弄脏您的爱车。"],"yundong":["较不宜","有降雪，推荐您在室内进行低强度运动；若坚持户外运动，请选择合适运动并注意保暖。"],"ziwaixian":["最弱","属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"]}}
   * weather :
   * [{"date":"2016-11-29","info":{"day":["6","雨夹雪","4","","微风","07:14"],"night":["6","雨夹雪","-3","","微风","16:50"]},"week":"二","nongli":"十一月初一"},{"date":"2016-11-30","info":{"dawn":["6","雨夹雪","-3","无持续风向","微风","16:50"],"day":["33","霾","9","","微风","07:16"],"night":["0","晴","-1","","微风","16:50"]},"week":"三","nongli":"十一月初二"},{"date":"2016-12-01","info":{"dawn":["0","晴","-1","无持续风向","微风","16:50"],"day":["0","晴","7","","微风","07:17"],"night":["0","晴","-4","","微风","16:50"]},"week":"四","nongli":"十一月初三"},{"date":"2016-12-02","info":{"dawn":["0","晴","-4","无持续风向","微风","16:50"],"day":["0","晴","9","","微风","07:17"],"night":["0","晴","-3","","微风","16:49"]},"week":"五","nongli":"十一月初四"},{"date":"2016-12-03","info":{"dawn":["0","晴","-3","无持续风向","微风","16:49"],"day":["33","霾","7","","微风","07:18"],"night":["33","霾","-2","","微风","16:49"]},"week":"六","nongli":"十一月初五"}]
   * f3h :
   * {"temperature":[{"jg":"20161129080000","jb":"2"},{"jg":"20161129110000","jb":"3"},{"jg":"20161129140000","jb":"2"},{"jg":"20161129170000","jb":"0"},{"jg":"20161129200000","jb":"-1"},{"jg":"20161129230000","jb":"-3"},{"jg":"20161130020000","jb":"-3"},{"jg":"20161130050000","jb":"-3"},{"jg":"20161130080000","jb":"-3"}],"precipitation":[{"jg":"20161129080000","jf":"0.4"},{"jg":"20161129110000","jf":"0"},{"jg":"20161129140000","jf":"0"},{"jg":"20161129170000","jf":"0.4"},{"jg":"20161129200000","jf":"0.3"},{"jg":"20161129230000","jf":"2.3"},{"jg":"20161130020000","jf":"2.3"},{"jg":"20161130050000","jf":"2.3"},{"jg":"20161130080000","jf":"2.3"}]}
   * pm25 :
   * {"key":"Beijing","show_desc":0,"pm25":{"curPm":"103","pm25":"72","pm10":"116","level":3,"quality":"轻度污染","des":"轻微污染
   * 易感人群症状有轻度加剧，健康人群出现刺激症状 心脏病和呼吸系统疾病患者应减少体力消耗和户外活动。"},"dateTime":"2016年11月29日10时","cityName":"北京"}
   * jingqu :
   * jingqutq :
   * date :
   * isForeign : 0
   */
  private Data data;

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public static class Data {
    private String pubdate;
    private String pubtime;
    private Realtime realtime;
    private Life life;
    private List<Weather> weather;
    private F3h f3h;
    private Pm25 pm25;
    private String jingqu;
    private String jingqutq;
    private String date;
    private String isForeign;

    public String getPubdate() {
      return pubdate;
    }

    public void setPubdate(String pubdate) {
      this.pubdate = pubdate;
    }

    public String getPubtime() {
      return pubtime;
    }

    public void setPubtime(String pubtime) {
      this.pubtime = pubtime;
    }

    public Realtime getRealtime() {
      return realtime;
    }

    public void setRealtime(Realtime realtime) {
      this.realtime = realtime;
    }

    public Life getLife() {
      return life;
    }

    public void setLife(Life life) {
      this.life = life;
    }

    public List<Weather> getWeather() {
      return weather;
    }

    public void setWeather(List<Weather> weather) {
      this.weather = weather;
    }

    public F3h getF3h() {
      return f3h;
    }

    public void setF3h(F3h f3h) {
      this.f3h = f3h;
    }

    public Pm25 getPm25() {
      return pm25;
    }

    public void setPm25(Pm25 pm25) {
      this.pm25 = pm25;
    }

    public String getJingqu() {
      return jingqu;
    }

    public void setJingqu(String jingqu) {
      this.jingqu = jingqu;
    }

    public String getJingqutq() {
      return jingqutq;
    }

    public void setJingqutq(String jingqutq) {
      this.jingqutq = jingqutq;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getIsForeign() {
      return isForeign;
    }

    public void setIsForeign(String isForeign) {
      this.isForeign = isForeign;
    }
  }
}
