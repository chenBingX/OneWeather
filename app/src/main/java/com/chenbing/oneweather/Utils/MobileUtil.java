package com.chenbing.oneweather.Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.text.TextUtils;

public class MobileUtil {
  private final static int MOBILENUM_LENGTH = 11;

  private final static Set<Integer> MOBILENUM_SEGMENT = new HashSet<>();

  private final static Set<String> NUMBER_IGNORE = new HashSet<>();

  static {
    // 加移动号段
    MOBILENUM_SEGMENT.addAll(Arrays.asList(139, 138, 137, 136, 135, 134, 147, 150, 151, 152,
            157, 158, 159, 182, 183, 184, 187, 188));
    // 联通号段
    MOBILENUM_SEGMENT.addAll(Arrays.asList(130, 131, 132, 155, 156, 185, 186, 145));
    // 电信号段
    MOBILENUM_SEGMENT.addAll(Arrays.asList(133, 153, 180, 181, 189));
    // 未知号段
    MOBILENUM_SEGMENT.addAll(Arrays.asList(140, 141, 142, 143, 144, 146, 148, 149, 154));
    // 听说是虚拟运营商的
    MOBILENUM_SEGMENT.addAll(Arrays.asList(170, 171, 172, 173, 174, 175, 176, 177, 178, 179));
  }

  public enum CheckMobileState {
    MARRY, NOT, CHECK, NULL
  }

  public static CheckMobileState checkMobile(String mobile) {
    mobile = TextUtils.isEmpty(mobile) ? null : mobile.trim();
    if (!TextUtils.isEmpty(mobile)) {
      if (mobile.length() >= MOBILENUM_LENGTH) {
        mobile = normalize(mobile);
        if (TextUtils.isEmpty(mobile)) {
          return CheckMobileState.NOT;
        } else {
          return CheckMobileState.MARRY;
        }
      } else {
        int checkToPosition;
        if (mobile.length() > 3) {
          checkToPosition = 3;
        } else {
          checkToPosition = mobile.length();
        }
        Iterator<Integer> integerIterator = MOBILENUM_SEGMENT.iterator();
        while (integerIterator.hasNext()) {
          Integer value = integerIterator.next();
          if (mobile.startsWith(String.valueOf(value).substring(0, checkToPosition))) {
            return CheckMobileState.CHECK;
          }
        }
        return CheckMobileState.NOT;
      }
    } else {
      return CheckMobileState.NULL;
    }
  }

  /**
   * 格式化大陆手机号码
   *
   * @param mobile
   * @return 返回大陆手机号格式，11位数字，格式不正确返回null
   */
  private static String normalize(String mobile) {
    mobile = TextUtils.isEmpty(mobile) ? null : mobile.trim();

    if (!TextUtils.isEmpty(mobile)) {
      mobile = toDBC(mobile);
      char[] charArray = mobile.toCharArray();
      String numeric = "";
      for (char c : charArray) {
        if (Character.isDigit(c)) {
          numeric += c;
        }
      }
      if (numeric.length() >= MOBILENUM_LENGTH) {
        numeric = TextUtils.substring(numeric, numeric.length() - MOBILENUM_LENGTH,
                numeric.length());
        // 判断号段
        String segment = TextUtils.substring(numeric, 0, 3);
        if (MOBILENUM_SEGMENT.contains(Integer.valueOf(segment))) {
          if (NUMBER_IGNORE.contains(numeric)) {
            return null;
          }

          return numeric;
        }
      }
    }
    return null;
  }

  /**
   * 全角转半角
   *
   * @param input String.
   * @return 半角字符串
   */
  private static String toDBC(String input) {

    char c[] = input.toCharArray();
    for (int i = 0; i < c.length; i++) {
      if (c[i] == '\u3000') {
        c[i] = ' ';
      } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
        c[i] = (char) (c[i] - 65248);

      }
    }
    String returnString = new String(c);

    return returnString;
  }

  private static String phoneFormatter(String mobile) {
    if (mobile.trim().length() >= 11) {
      return normalize(
              mobile.trim().substring(mobile.trim().length() - 11, mobile.trim().length()));
    } else {
      return null;
    }
  }
}
