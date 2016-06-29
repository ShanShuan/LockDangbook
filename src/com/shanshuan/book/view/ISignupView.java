package com.shanshuan.book.view;

import android.graphics.Bitmap;

/**
 *Created by Zifeng Wang 2016-6-27обнГ3:22:09
 */
public interface ISignupView {
 public void showCode(Bitmap bitmap);
 public void signupSeccess();
 public void signupFail(String error);
}
