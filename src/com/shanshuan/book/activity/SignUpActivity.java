package com.shanshuan.book.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.shanshuan.book.R;
import com.shanshuan.book.R.layout;
import com.shanshuan.book.R.menu;
import com.shanshuan.book.entity.User;
import com.shanshuan.book.persenter.ISignupPresenter;
import com.shanshuan.book.persenter.imp.SignupPresenterImp;
import com.shanshuan.book.view.ISignupView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends Activity implements ISignupView {
	@ViewInject(R.id.iv_signup_code)
	private ImageView ivCode;
	@ViewInject(R.id.et_signup_email)
	private EditText etUserEmail;
	@ViewInject(R.id.et_sginup_password)
	private EditText etUserPassword;
	@ViewInject(R.id.et_signup_realName)
	private EditText etUserNickName;
	@ViewInject(R.id.et_singup_code)
	private EditText etCode;
	@ViewInject(R.id.btn_signup)
	private Button btnSignup;
	private SignupPresenterImp persenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		x.view().inject(this);
		persenter = new SignupPresenterImp(this);
		persenter.getImageCode();

	}

	public void doClick(View v) {
		User user=new User();
		user.setEmail(etUserEmail.getText().toString());
		user.setPassword(etUserPassword.getText().toString());
		user.setNickname(etUserNickName.getText().toString());
		String code =etCode.getText().toString();
		persenter.signup(user,code);
	}

	@Override
	public void showCode(Bitmap bitmap) {
		ivCode.setImageBitmap(bitmap);
	}

	@Override
	public void signupSeccess() {
		Toast.makeText(this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void signupFail(String error) {
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
	}

}
