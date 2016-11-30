package com.chenbing.oneweather.View.activitys;

import com.chenbing.oneweather.Presenter.BasePresenter;
import com.chenbing.oneweather.R;
import com.chenbing.oneweather.Presenter.SplashActivityPresenter;
import com.chenbing.oneweather.Presenter.SplashActivityPresenterApi;
import com.chenbing.oneweather.Utils.DisplayUtils;
import com.chenbing.oneweather.View.BaseView.BaseActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashActivityView {

  @BindView(R.id.sun)
  ImageView sun;
  @BindView(R.id.cloud_1)
  ImageView cloud_1;
  @BindView(R.id.cloud_2)
  ImageView cloud_2;
  @BindView(R.id.cloud_3)
  ImageView cloud_3;
  @BindView(R.id.join_now)
  TextView joinNow;

  private SplashActivityPresenterApi presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    presenter = new SplashActivityPresenter(this);
    ButterKnife.bind(this);
    initData();
    initView();
    addListener();
  }

  @Override
  protected void initData() {
    presenter.requestWeatherData();
  }

  @Override
  protected void initView() {}

  @Override
  protected void addListener() {
    playAnimOnLayoutFinish();
    registerJoinNowListener();
  }

  @Override
  protected BasePresenter getPresenter() {
    return presenter;
  }

  private void playAnimOnLayoutFinish() {
    // 需要在布局填充完成后才能获取到View的尺寸
    getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(
        new ViewTreeObserver.OnGlobalLayoutListener() {
          @Override
          public void onGlobalLayout() {
            playAnim();
            // 需要移除监听，否则会重复触发
            getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
          }
        });
  }

  private void registerJoinNowListener() {
    joinNow.setOnClickListener(v -> jumpToMainActivity());
  }

  private void playAnim() {
    playSunAnim();
    playCloud_1Anim();
    playCloud_2Anim();
    playCloud_3Anim();
    playJoinNowAnim();
  }

  private void playSunAnim() {
    ObjectAnimator anim = ObjectAnimator.ofFloat(sun, "rotation", 0f, 360f);
    anim.setRepeatMode(ObjectAnimator.RESTART);
    anim.setRepeatCount(ObjectAnimator.INFINITE);
    anim.setInterpolator(new LinearInterpolator());
    anim.setDuration(30 * 1000);
    anim.start();
  }

  private void playCloud_1Anim() {
    int left = cloud_1.getLeft();
    int right = DisplayUtils.getScreenWidth() - cloud_1.getRight();
    int width = cloud_1.getMeasuredWidth();
    ObjectAnimator anim =
        ObjectAnimator.ofFloat(cloud_1, "translationX", right + width, -(left + width));
    anim.setRepeatMode(ObjectAnimator.RESTART);
    anim.setRepeatCount(ObjectAnimator.INFINITE);
    anim.setInterpolator(new LinearInterpolator());
    anim.setDuration(31 * 1000);
    anim.start();
  }

  private void playCloud_2Anim() {
    int left = cloud_2.getLeft();
    int right = DisplayUtils.getScreenWidth() - cloud_2.getRight();
    int width = cloud_2.getMeasuredWidth();
    int cloud_1Width = cloud_1.getMeasuredWidth();
    int leftMargin =
        Math.abs(((ViewGroup.MarginLayoutParams) cloud_2.getLayoutParams()).leftMargin);
    ObjectAnimator anim =
        ObjectAnimator.ofFloat(cloud_2, "translationX",
            right + width + leftMargin + ((float) cloud_1Width * 0.8f),
            -(left + width + leftMargin));
    anim.setRepeatMode(ObjectAnimator.RESTART);
    anim.setRepeatCount(ObjectAnimator.INFINITE);
    anim.setInterpolator(new LinearInterpolator());
    anim.setDuration(32 * 1000);
    anim.start();
  }

  private void playCloud_3Anim() {
    int left = cloud_3.getLeft();
    int right = DisplayUtils.getScreenWidth() - cloud_3.getRight();
    int width = cloud_3.getMeasuredWidth();
    int cloud_1Width = cloud_1.getMeasuredWidth();
    int leftMargin =
        Math.abs(((ViewGroup.MarginLayoutParams) cloud_3.getLayoutParams()).leftMargin);
    ObjectAnimator anim =
        ObjectAnimator.ofFloat(cloud_3, "translationX",
            right + width + leftMargin + ((float) cloud_1Width * 2.5f),
            -(left + width + leftMargin));
    anim.setRepeatMode(ObjectAnimator.RESTART);
    anim.setRepeatCount(ObjectAnimator.INFINITE);
    anim.setInterpolator(new LinearInterpolator());
    anim.setDuration(35 * 1000);
    anim.start();
  }

  private void playJoinNowAnim() {
    ObjectAnimator anim = ObjectAnimator.ofFloat(joinNow, "alpha", 0.1f, 1f);
    anim.setDuration(2000);
    anim.setRepeatCount(ObjectAnimator.INFINITE);
    anim.setRepeatMode(ObjectAnimator.REVERSE);
    anim.start();
  }

  private void jumpToMainActivity() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }

}
