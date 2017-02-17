package com.chenbing.oneweather.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IntDef;
import android.widget.Toast;

import com.chenbing.oneweather.ChiceApplication;

/**
 * Created by yanbingwu on 16/4/6.
 */
public class ToastUtil extends Toast {
  /** @hide */
  @IntDef({LENGTH_SHORT, LENGTH_LONG})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Duration {}

  public ToastUtil(Context context) {
    super(context);
  }

  private static Toast toast;

  public static void showToast(Context context, CharSequence text, @Duration int duration) {
    ToastUtil.makeText(context, text, duration).show();
  }

  public static void showLongToast(CharSequence text) {
    if (toast == null) {
      toast = ToastUtil.makeText(ChiceApplication.getAppContext(), text, Toast.LENGTH_LONG);
    } else {
      toast.setText(text);
    }
    toast.show();
  }

  public static void showLongToast(int textId) {
    if (toast == null) {
      toast = ToastUtil.makeText(ChiceApplication.getAppContext(), textId, Toast.LENGTH_LONG);
    } else {
      toast.setText(textId);
    }
    toast.show();
  }

  public static void showShortToast(CharSequence text) {
    if (toast == null) {
      toast = ToastUtil.makeText(ChiceApplication.getAppContext(), text, Toast.LENGTH_SHORT);
    } else {
      toast.setText(text);
    }
    toast.show();
  }

  public static void showShortToast(int textId) {
    if (toast == null) {
      toast = ToastUtil.makeText(ChiceApplication.getAppContext(), textId, Toast.LENGTH_SHORT);
    } else {
      toast.setText(textId);
    }
    toast.show();
  }

  public static void showToast(Context context, int textResId,
      @Duration int duration)
      throws Resources.NotFoundException {
    showToast(context, context.getResources().getText(textResId), duration);
  }
}

