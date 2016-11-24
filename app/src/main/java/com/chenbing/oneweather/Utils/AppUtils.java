package com.chenbing.oneweather.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import android.util.Base64;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;

import com.chenbing.oneweather.ChiceApplication;

/**
 * Project Name:AnimDveDemo
 * Author:IceChen
 * Date:16/8/24
 * Notes:
 */
public class AppUtils {

  public static final String ObjectToBase64String(Object object) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(object);
    String base64String = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    if (oos != null){
      oos.close();
    }
    if (baos != null){
      baos.close();
    }
    return base64String;
  }

  public static final <T> T Base64StringToObject(String base64String, Class<T> clazz)
      throws IOException, ClassNotFoundException {
    byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bais);
    T t = (T) ois.readObject();
    if (ois != null) {
      ois.close();
    }
    if (bais != null) {
      bais.close();
    }
    return t;
  }

  public static boolean PackageIsExist(String packageName) {
    PackageManager packageManager = ChiceApplication.getAppContext().getPackageManager();
    try {
      if (packageManager.getPackageInfo(packageName, 0) == null) {
        return false;
      } else
        return true;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void showInputMethod(View view) {
    try {
      InputMethodManager imm = (InputMethodManager) view.getContext()
          .getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void hideInputMethod(View view) {
    if (view != null && view.getContext() != null && view.getWindowToken() != null) {
      try {
        ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
            .hideSoftInputFromWindow(view.getWindowToken(), 0);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 判断当前屏幕是否锁屏.
   *
   * @param context
   * @return boolean
   */
  public static boolean inKeyguardRestrictedInputMode(Context context) {
    KeyguardManager mKeyguardManager =
      (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
    return mKeyguardManager.inKeyguardRestrictedInputMode();
  }

  /**
   * 屏幕是否是亮着的.
   *
   * @param context
   * @return boolean
   */
  public static boolean isScreenOn(Context context) {
    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    return pm.isScreenOn();
  }

  /**
   * 获取虚拟按键栏的高度
   *
   * @param context
   * @return
   */
  public static int navigationBarHeight = 0;

  public static int getNavigationBarHeight(Context context) {
    if (navigationBarHeight == 0) {
      if (hasNavBar(context)) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
          navigationBarHeight = res.getDimensionPixelSize(resourceId);
        }
      } else if (isMeizu()) {
        navigationBarHeight = getSmartBarHeight(context);
      }
    }
    return navigationBarHeight;
  }

  /**
   * 检查是否存在虚拟按键栏
   *
   * @param context
   * @return
   */
  @SuppressLint("NewApi")
  private static boolean hasNavBar(Context context) {
    Resources res = context.getResources();
    int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
    if (resourceId != 0) {
      boolean hasNav = res.getBoolean(resourceId);
      // check override flag
      String sNavBarOverride = getNavBarOverride();
      if ("1".equals(sNavBarOverride)) {
        hasNav = false;
      } else if ("0".equals(sNavBarOverride)) {
        hasNav = true;
      }
      return hasNav;
    } else { // fallback
      return !ViewConfiguration.get(context).hasPermanentMenuKey();
    }
  }

  /**
   * 判断是否meizu手机
   *
   * @return
   */
  public static boolean isMeizu() {
    return Build.BRAND.equals("Meizu");
  }

  /**
   * 获取魅族手机底部虚拟键盘高度
   *
   * @param context
   * @return
   */
  public static int getSmartBarHeight(Context context) {
    try {
      Class c = Class.forName("com.android.internal.R$dimen");
      Object obj = c.newInstance();
      Field field = c.getField("mz_action_button_min_height");
      int height = Integer.parseInt(field.get(obj).toString());
      return context.getResources().getDimensionPixelSize(height);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * 判断虚拟按键栏是否重写
   *
   * @return
   */
  private static String getNavBarOverride() {
    String sNavBarOverride = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      try {
        Class c = Class.forName("android.os.SystemProperties");
        Method m = c.getDeclaredMethod("get", String.class);
        m.setAccessible(true);
        sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
      } catch (Throwable e) {}
    }
    return sNavBarOverride;
  }

  /**获得手机Ip*/
  public static String getPhoneIp() {
    try {
      for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en
        .hasMoreElements();) {
        NetworkInterface intf = en.nextElement();
        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
          .hasMoreElements();) {
          InetAddress inetAddress = enumIpAddr.nextElement();
          if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress().toString();
          }
        }
      }
    } catch (Exception e) {}
    return "";
  }


}
