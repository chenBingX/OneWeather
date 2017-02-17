package com.chenbing.oneweather.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import android.support.annotation.NonNull;

import com.chenbing.oneweather.AppConstant;

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/26
 * Notes:
 */
public class ThreadUtil {

  private ExecutorService threadPool;

  private static ThreadUtil Instance;


  private ThreadUtil() {
    if (threadPool == null) {
      threadPool = Executors.newFixedThreadPool(AppConstant.CPU_NUM);
    }
  }

  public static final ThreadUtil getThreadPool() {
    if (Instance == null) {
      synchronized (ThreadUtil.class) {
        if (Instance == null) {
          Instance = new ThreadUtil();
        }
      }
    }
    return Instance;
  }

  public void run(@NonNull Runnable runnable) {
    threadPool.execute(runnable);
  }

  public <T> Future<T> submit(@NonNull Callable<T> call) {
    return threadPool.submit(call);
  }

  /**
   * 关闭线程池,这将导致改线程池立即停止接受新的线程请求,但已经存在的任务仍然会执行,直到完成。
   */
  public void shutDown() {
    if (Instance == null)
      return;
    synchronized (this) {
      if (Instance != null) {
        threadPool.shutdownNow();
      }
      Instance = null;
    }
  }

}
