package com.chenbing.oneweather.CustomViews;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.chenbing.oneweather.R;


/**
 * Project Name:
 * Author:CoorChice
 * Date:2016/12/27
 * Notes:
 */

public class RoundCornerTextView extends TextView {
  private static final float DEFAULT_CORNER = 0f;
  private static final int DEFAULT_SOLID = Color.WHITE;
  private static final float DEFAULT_STROKE_WIDTH = 0f;
  private static final int DEFAULT_STROKE_COLOR = Color.BLACK;
  private static final int DEFAULT_STATE_DRAWABLE_MODE = 4;
  private static final int DEFAULT_TEXT_STROKE_COLOR = Color.BLACK;
  private static final int DEFAULT_TEXT_FILL_COLOR = Color.BLACK;
  private static final float DEFAULT_TEXT_STROKE_WIDTH = 0f;



  private float corner;
  private int solid;
  private float strokeWidth;
  private int strokeColor;
  private int stateDrawableMode;
  private boolean isShowState;

  private Paint paint;
  private int width;
  private int height;
  private Drawable drawable;
  private float density;
  private boolean autoAdjust;
  private TextAdjuster textAdjuster;
  private boolean textStroke;
  private int textStrokeColor;
  private int textFillColor;
  private float textStrokeWidth;

  public RoundCornerTextView(Context context) {
    super(context);
    init(null);
  }

  public RoundCornerTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(attrs);
  }

  public RoundCornerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public RoundCornerTextView(Context context, AttributeSet attrs, int defStyleAttr,
                             int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(attrs);
  }

  private void init(AttributeSet attrs) {
    density = getContext().getResources().getDisplayMetrics().density;
    initAttrs(attrs);
    paint = new Paint();
    initPaint();
  }

  private void initAttrs(AttributeSet attrs) {
    if (attrs != null) {
      TypedArray typedArray =
          getContext().obtainStyledAttributes(attrs, R.styleable.RoundCornerTextView);
      corner = typedArray.getDimension(R.styleable.RoundCornerTextView_corner, DEFAULT_CORNER);
      solid = typedArray.getColor(R.styleable.RoundCornerTextView_solid, DEFAULT_SOLID);
      strokeWidth = typedArray.getDimension(R.styleable.RoundCornerTextView_stroke_width,
          DEFAULT_STROKE_WIDTH);
      strokeColor =
          typedArray.getColor(R.styleable.RoundCornerTextView_stroke_color, DEFAULT_STROKE_COLOR);
      drawable = typedArray.getDrawable(R.styleable.RoundCornerTextView_state_drawable);
      isShowState = typedArray.getBoolean(R.styleable.RoundCornerTextView_isShowState, false);
      stateDrawableMode = typedArray.getInteger(R.styleable.RoundCornerTextView_state_drawable_mode,
          DEFAULT_STATE_DRAWABLE_MODE);
      textStroke = typedArray.getBoolean(R.styleable.RoundCornerTextView_text_stroke, false);
      textStrokeColor = typedArray.getColor(R.styleable.RoundCornerTextView_text_stroke_color,
          DEFAULT_TEXT_STROKE_COLOR);
      textFillColor = typedArray.getColor(R.styleable.RoundCornerTextView_text_fill_color,
          DEFAULT_TEXT_FILL_COLOR);
      textStrokeWidth = typedArray.getDimension(R.styleable.RoundCornerTextView_text_stroke_width,
          DEFAULT_TEXT_STROKE_WIDTH);
      autoAdjust = typedArray.getBoolean(R.styleable.RoundCornerTextView_autoAdjust, false);
      typedArray.recycle();
    }
  }

  private void initPaint() {
    paint.reset();
    paint.setAntiAlias(true);
    paint.setDither(true);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    width = getWidth();
    height = getHeight();
    drawStrokeLine(canvas);
    drawSolid(canvas);
    drawStateDrawable(canvas);
    adjustText();

    if (textStroke){
      getPaint().setStyle(Paint.Style.STROKE);
      setTextColor(textStrokeColor);
      getPaint().setFakeBoldText(true);
      getPaint().setStrokeWidth(textStrokeWidth);
      super.onDraw(canvas);

      getPaint().setStyle(Paint.Style.FILL);
      getPaint().setFakeBoldText(false);
      setTextColor(textFillColor);
    }

    super.onDraw(canvas);
  }

  private void drawStrokeLine(Canvas canvas) {
    if (strokeWidth > 0) {
      initPaint();
      RectF rectF =
          new RectF(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2,
              height - strokeWidth / 2);
      paint.setStyle(Paint.Style.STROKE);
      paint.setColor(strokeColor);
      paint.setStrokeWidth(strokeWidth);
      if (corner != 0) {
        paint.setStrokeJoin(Paint.Join.ROUND);
      }
      canvas.drawRoundRect(rectF, corner, corner, paint);
    }
  }

  private void drawSolid(Canvas canvas) {
    initPaint();
    RectF rectF = new RectF(strokeWidth / 1, strokeWidth / 1, width - strokeWidth / 1,
        height - strokeWidth / 1);
    if (strokeWidth > 1 * density){
      paint.setStyle(Paint.Style.FILL_AND_STROKE);
      paint.setStrokeWidth(3);
    } else {
      paint.setStyle(Paint.Style.FILL);
    }
    paint.setColor(solid);
    float cornerCoefficient = corner / (width - strokeWidth);
    float fitCorner = rectF.width() * cornerCoefficient;
    canvas.drawRoundRect(rectF, fitCorner, fitCorner, paint);
  }

  private void drawStateDrawable(Canvas canvas) {
    if (drawable != null && isShowState) {
      float[] drawableBounds = getDrawableBounds();
      drawable.setBounds((int) drawableBounds[0], (int) drawableBounds[1], (int) drawableBounds[2],
          (int) drawableBounds[3]);
      drawable.draw(canvas);
    }
  }

  private float[] getDrawableBounds() {
    float[] drawableBounds = new float[4];
    switch (stateDrawableMode) {
      case 0: // left
        drawableBounds[0] = 0;
        drawableBounds[1] = height * (1f / 4f);
        drawableBounds[2] = width * (1f / 2f);
        drawableBounds[3] = height * (3f / 4f);
        break;
      case 1: // top
        drawableBounds[0] = width * (1f / 4f);
        drawableBounds[1] = 0;
        drawableBounds[2] = width * (3f / 4f);
        drawableBounds[3] = height * (1f / 2f);
        break;
      case 2: // right
        drawableBounds[0] = width * (1f / 2f);
        drawableBounds[1] = height * (1f / 4f);
        drawableBounds[2] = width;
        drawableBounds[3] = height * (3f / 4f);
        break;
      case 3: // bottom
        drawableBounds[0] = width * (1f / 4f);
        drawableBounds[1] = height * (1f / 2f);
        drawableBounds[2] = width * (3f / 4f);
        drawableBounds[3] = height;
        break;
      case 4: // center
        drawableBounds[0] = width * (1f / 4f);
        drawableBounds[1] = height * (1f / 4f);
        drawableBounds[2] = width * (3f / 4f);
        drawableBounds[3] = height * (3f / 4f);
        break;
      case 5: // fill
        drawableBounds[0] = 0;
        drawableBounds[1] = 0;
        drawableBounds[2] = width;
        drawableBounds[3] = height;
        break;
      case 6: // leftTop
        drawableBounds[0] = 0;
        drawableBounds[1] = 0;
        drawableBounds[2] = width * (1f / 2f);
        drawableBounds[3] = height * (1f / 2f);
        break;
      case 7: // rightTop
        drawableBounds[0] = width * (1f / 2f);
        drawableBounds[1] = 0;
        drawableBounds[2] = width;
        drawableBounds[3] = height * (1f / 2f);
        break;
      case 8: // leftBottom
        drawableBounds[0] = 0;
        drawableBounds[1] = height * (1f / 2f);
        drawableBounds[2] = width * (1f / 2f);
        drawableBounds[3] = height;
        break;
      case 9: // rightBottom
        drawableBounds[0] = width * (1f / 2f);
        drawableBounds[1] = height * (1f / 2f);
        drawableBounds[2] = width;
        drawableBounds[3] = height;
        break;
    }

    return drawableBounds;
  }

  private void adjustText() {
    if (autoAdjust) {
      if (textAdjuster == null) {
        textAdjuster = new DefaultTextAdjuster();
      }
      textAdjuster.adjust(this);
    }
  }

  public float getCorner() {
    return corner;
  }

  public void setCorner(float corner) {
    this.corner = corner;
    postInvalidate();

  }

  public int getSolid() {
    return solid;
  }

  public void setSolid(int solid) {
    this.solid = solid;
    postInvalidate();
  }

  public float getStrokeWidth() {
    return strokeWidth;
  }

  public void setStrokeWidth(float strokeWidth) {
    this.strokeWidth = strokeWidth;

    postInvalidate();
  }

  public int getStrokeColor() {
    return strokeColor;
  }

  public void setStrokeColor(int strokeColor) {
    this.strokeColor = strokeColor;
    postInvalidate();
  }

  public Drawable getDrawable() {
    return drawable;
  }

  public void setDrawable(Drawable drawable) {
    this.drawable = drawable;
    postInvalidate();
  }

  public boolean isShowState() {
    return isShowState;
  }

  public void setShowState(boolean showState) {
    isShowState = showState;
    postInvalidate();
  }

  public int getStateDrawableMode() {
    return stateDrawableMode;
  }

  public void setStateDrawableMode(int stateDrawableMode) {
    this.stateDrawableMode = stateDrawableMode;
    postInvalidate();
  }

  public void setTextAdjuster(TextAdjuster textAdjuster) {
    this.textAdjuster = textAdjuster;
    postInvalidate();
  }

  public TextAdjuster getTextAdjuster() {
    return textAdjuster;
  }

  public boolean isTextStroke() {
    return textStroke;
  }

  public void setTextStroke(boolean textStroke) {
    this.textStroke = textStroke;
    postInvalidate();

  }

  public int getTextStrokeColor() {
    return textStrokeColor;
  }

  public void setTextStrokeColor(int textStrokeColor) {
    this.textStrokeColor = textStrokeColor;
    postInvalidate();

  }

  public int getTextFillColor() {
    return textFillColor;
  }

  public void setTextFillColor(int textFillColor) {
    this.textFillColor = textFillColor;
    postInvalidate();

  }

  public float getTextStrokeWidth() {
    return textStrokeWidth;
  }

  public void setTextStrokeWidth(float textStrokeWidth) {
    this.textStrokeWidth = textStrokeWidth;
    postInvalidate();

  }

  public static interface TextAdjuster {
    void adjust(TextView v);
  }

  public static class DefaultTextAdjuster implements TextAdjuster{

    @Override
    public void adjust(TextView v) {
      int length = v.length();
      float scale = v.getWidth() / (116.28f * v.getResources().getDisplayMetrics().density);
      float[] textSizes = {
        37.21f, 37.21f, 24.81f, 27.9f, 24.81f,
        22.36f, 18.6f,
        18.6f
      };
      switch (length) {
        case 1:
          v.setTextSize(textSizes[0] * scale);
          break;
        case 2:
          v.setTextSize(textSizes[1] * scale);
          break;
        case 3:
          v.setTextSize(textSizes[2] * scale);
          break;
        case 4:
          v.setTextSize(textSizes[3] * scale);
          break;
        case 5:
        case 6:
          v.setTextSize(textSizes[4] * scale);
          break;
        case 7:
        case 8:
        case 9:
          v.setTextSize(textSizes[5] * scale);
          break;
        case 10:
        case 11:
        case 12:
          v.setTextSize(textSizes[6] * scale);
          break;
        case 13:
        case 14:
        case 15:
        case 16:
          v.setTextSize(textSizes[7] * scale);
          break;
      }
    }

  }
}
