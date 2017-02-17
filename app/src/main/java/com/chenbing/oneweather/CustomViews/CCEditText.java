package com.chenbing.oneweather.CustomViews;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.chenbing.oneweather.R;

public class CCEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

//  //caolei
//  private DrawableLeftListener mLeftListener ;
//  private DrawableRightListener mRightListener ;

  private CCEditTextChangeListener CCEditTextChangeListener;

  final int DRAWABLE_LEFT = 0;
  final int DRAWABLE_TOP = 1;
  final int DRAWABLE_RIGHT = 2;
  final int DRAWABLE_BOTTOM = 3;

  private Drawable mClearDrawable;
  private boolean haveFocus;


  public CCEditText(Context context) {
    this(context, null);
  }

  public CCEditText(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.editTextStyle);
  }

  public CCEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private final void init() {
    mClearDrawable = getCompoundDrawables()[DRAWABLE_RIGHT];
    if(mClearDrawable == null) {
      mClearDrawable = getResources().getDrawable(R.drawable.edit_text_clear_text);
      mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
    }


    addTextChangedListener(this);
    setOnFocusChangeListener(this);
    setClearDrawableVisible(false);

    setPadding(getPaddingLeft(), getPaddingTop(), 8, getPaddingBottom());
  }

  private final void setClearDrawableVisible(boolean visible) {
    Drawable clearDrawable = visible ? mClearDrawable : null;
    Drawable[] drawables = getCompoundDrawables();
    setCompoundDrawables(drawables[DRAWABLE_LEFT], drawables[DRAWABLE_TOP], clearDrawable, drawables[DRAWABLE_BOTTOM]);
    setCompoundDrawablePadding(6);
  }

  public final void setCCEditTextChangeListener(CCEditTextChangeListener listener) {
    this.CCEditTextChangeListener = listener;
  }

//  public void setDrawableLeftListener(DrawableLeftListener listener) {
//    this.mLeftListener = listener;
//  }
//
//  public void setDrawableRightListener(DrawableRightListener listener) {
//    this.mRightListener = listener;
//  }

//  public interface DrawableLeftListener {
//    public void onDrawableLeftClick(View view) ;
//  }
//
//  public interface DrawableRightListener {
//    public void onDrawableRightClick(View view) ;
//  }

  public interface CCEditTextChangeListener {
    public void onTextChanged(Editable editable);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
//    switch (event.getAction()) {
//      case MotionEvent.ACTION_UP:
//        if (mRightListener != null) {
//          Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT] ;
//          if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())) {
//            mRightListener.onDrawableRightClick(this) ;
//            return false ; //返回false避免点击时Edtittext会获取焦点的问题
//          }
//        }
//
//        if (mLeftListener != null) {
//          Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT] ;
//          if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())) {
//            mLeftListener.onDrawableLeftClick(this) ;
//            return false ;
//          }
//        }
//        break;
//    }
    if(event.getAction() == MotionEvent.ACTION_UP) {
      float tx = event.getX();
      Drawable clearDrawable = getCompoundDrawables()[2];
      if(clearDrawable != null) {
        Rect rect = clearDrawable.getBounds();
        int clearDrawableX = getWidth() - rect.right - getTotalPaddingRight();
        if(tx > clearDrawableX) {
          setText("");
        }
//        Log.i("caolei", "touch >> " + tx + "*" + ty + " && " + clearDrawableX);
      }
    }

    return super.onTouchEvent(event);
  }

  @Override
  public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter);
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void afterTextChanged(Editable editable) {
    if(haveFocus && editable != null) {
      setClearDrawableVisible(editable.length() > 0);
    }
    if(CCEditTextChangeListener != null) {
      CCEditTextChangeListener.onTextChanged(editable);
    }
  }

  @Override
  public void onFocusChange(View view, boolean focused) {
    haveFocus = focused;
    if(focused) {
      CharSequence content = getText();
      if(content != null) {
        setClearDrawableVisible(content.length() > 0);
      }
    } else {
      setClearDrawableVisible(false);
    }
  }
}
