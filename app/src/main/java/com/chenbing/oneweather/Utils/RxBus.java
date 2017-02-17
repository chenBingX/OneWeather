package com.chenbing.oneweather.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 项目名称：
 * 类描述：【略】
 * 创建人：CoorChice
 * 备注：用于处理事件传递
 *
 * @version 邮箱：icechen_@outlook.com
 */
public class RxBus {
  private ConcurrentHashMap<Object, ConcurrentHashMap<String, Subject>> subjectManager =
      new ConcurrentHashMap<>(); // 这是事件队列的管理器

  public static RxBus get() {
    return RxBusHolder.instance;
  }

  private static class RxBusHolder {
    private static RxBus instance = new RxBus();

  }

  private RxBus() {}

  /**
   * 使用之后一定要在所在对象销毁时调用{@code RxBus.unregister}
   */
  public <T> Observable<T> register(@NonNull Object context, @NonNull Class<T> clazz) {
    ConcurrentHashMap<String, Subject> subjectMap = subjectManager.get(context);
    if (subjectMap == null) {
      subjectMap = new ConcurrentHashMap<>();
      subjectManager.put(context, subjectMap);
    }
    if (subjectMap.get(clazz.getName()) != null) {
      throw new IllegalArgumentException(
          "Don't repeat in the same class to register the same event.");
    }
    Subject<T, T> subject = PublishSubject.create();
    subjectMap.put(clazz.getName(), subject);
    LogUtils.e("注册Subject：" + clazz.getName());
    return subject;
  }

  /**
   * 该方法用于解注册一个环境下所有已注册的Observable
   * 一定要在接收的类里解除注册，否则会造成连锁的内存泄漏。
   * 
   * @param context
   */
  public void unregister(@NonNull Object context) {
    ConcurrentHashMap<String, Subject> subjectMap = subjectManager.get(context);
    if (subjectMap != null) {
      subjectMap.entrySet().stream().forEach(e -> {
        e.getValue().onCompleted();
        subjectMap.remove(e.getKey());
      });
      subjectManager.remove(context);
      LogUtils.e("移除群组");
    }
  }

  /**
   * 发送事件
   * 
   * @param content 事件内容，需要有相应的订阅者
   */
  @SuppressWarnings("unchecked")
  public <T> void post(@NonNull T content) {
    for (Map.Entry<Object, ConcurrentHashMap<String, Subject>> entry : subjectManager.entrySet()) {
      ConcurrentHashMap<String, Subject> subjectMap = entry.getValue();
      Subject subject = subjectMap.get(content.getClass().getName());
      if (subject != null) {
        subject.onNext(content);
      }
    }
  }
}
