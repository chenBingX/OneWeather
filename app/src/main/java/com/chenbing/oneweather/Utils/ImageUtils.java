package com.chenbing.oneweather.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Project Name:IceWeather
 * Author:IceChen
 * Date:16/8/30
 * Notes:
 */
public class ImageUtils {

  public static Bitmap changeBitmapColor(Bitmap bmp, int color) {
    Bitmap b = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
    Canvas canvas = new Canvas(b);
    Paint paint = new Paint();
    paint.setColor(color);
    paint.setAntiAlias(true);
    canvas.drawBitmap(bmp, bmp.getWidth(), bmp.getHeight(), paint);
    return b;
  }
}
