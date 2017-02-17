package com.chenbing.oneweather.Utils;

import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/23
 * Notes:
 */
public class ReflectUtils {

  public static Method getMethod(@NonNull Class<?> clazz, @NonNull String methodName) throws NoSuchMethodException {
    Method method = clazz.getDeclaredMethod(methodName);
    method.setAccessible(true);
    return method;
  }

  public static Method getMethod(@NonNull Class<?> clazz, @NonNull String methodName,
                                 Class... parameterTypes) throws NoSuchMethodException {
    Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
    method.setAccessible(true);
    return method;
  }

  public static Method getApiMethod(@NonNull Class<?> clazz, @NonNull String methodName)
      throws NoSuchMethodException {
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.getName().contains(methodName)) {
        method.setAccessible(true);
        return method;
      }
    }
    return null;
  }



  public static void invokeMethod(@NonNull Class<?> clazz, @NonNull String methodName) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
    getMethod(clazz, methodName).invoke(clazz.newInstance());
  }

  public static <T> void invokeMethod(T t, @NonNull String methodName)
      throws IllegalAccessException, InstantiationException, InvocationTargetException,
      NoSuchMethodException {
    getMethod(t.getClass(), methodName).invoke(t);
  }

  public static <T, V> void invokeApiMethod(T t, @NonNull String methodName, V v,
                                            Class... parameterTypes) throws IllegalAccessException, InstantiationException,
      InvocationTargetException, NoSuchMethodException {
    getApiMethod(t.getClass(), methodName).invoke(t, v);
  }

  public static Field getVariable(@NonNull Class<?> clazz, @NonNull String variableName) throws NoSuchFieldException {
    Field variable = clazz.getDeclaredField(variableName);
    variable.setAccessible(true);
    return variable;
  }
}
