package com.chenbing.oneweather.Data.Cache;

import java.util.concurrent.ConcurrentHashMap;

import android.support.annotation.NonNull;

/**
 * 将数据缓存到内存中。
 */
public class DataCache {
  private ConcurrentHashMap<String, Object> dataMap;

  private DataCache() {
    initDatasContainer();
  }

  private void initDatasContainer() {
    ConcurrentHashMap<String, Object> datas = new ConcurrentHashMap<>();
    dataMap = datas;
  }

  public static DataCache getInstance() {
    return DataCacheHolder.instance;
  }

  private static class DataCacheHolder {
    private static final DataCache instance = new DataCache();
  }

  public <T> void add(@NonNull String tag, @NonNull T t) {
    dataMap.put(tag, t);
  }

  /**
  * 尝试取出缓存的数据，如果存在。
  * @param tag key，如果定义在该类中Key，将方便统一管理，且易用
  * @param tClass 需要取出的数据类型, 这很重要。错误的类型传入后将返回null
  * @return 返回取出的数据，当没有取到或者类型错误时，将返回null，注意进行特殊处理
  *
  */
  public <T> T get(@NonNull String tag, @NonNull Class<T> tClass) {
    T t = null;
    Object obj = dataMap.get(tag);
    if (obj != null) {
      if (tClass.isInstance(obj)) {
        t = (T) obj;
      }
    }
    return t;
  }

  /**
   * 推荐！当确定一个数据不再需要时，请调用这个方法把它释放。
   */
  public void remove(@NonNull String tag){
    dataMap.remove(tag);
  }

  public void clear(){
    dataMap.clear();
  }

  public static class Key{
    public static final String CITY_LIST = "city_list";
    public static final String LOCALE_WEATHER_DATA = "locale_weather_data";
    public static final String BD_LOCATION = "bd_location";
    private Key(){
    }
  }
}
