package com.shanshuan.book.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shanshuan.book.R;
import com.shanshuan.book.entity.User;
import com.shanshuan.book.persenter.imp.LoginPersenterImp;
import com.shanshuan.book.view.ILoginView;

public class LoginActivity extends Activity implements ILoginView{
	@ViewInject(R.id.iv_login_back)
	private ImageView ivBack;
	@ViewInject(R.id.tv_login_sign_up)
	private TextView tvSignUp;
	@ViewInject(R.id.tv_login_modfy_password)
	private TextView tvModfyPassword;
	@ViewInject(R.id.btn_login)
	private Button btnLogin;
	@ViewInject(R.id.et_login_password)
	private EditText etPasseword;
	@ViewInject(R.id.et_login_email)
	private EditText etEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		x.view().inject(this);
		// ÉèÖÃ¼àÌýÆ÷
		setLitenners();
	}

	private void setLitenners() {
		//µÇÂ¼¼àÌýÊ±¼ä
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LoginPersenterImp login=new LoginPersenterImp(LoginActivity.this);
				User user=new User();
				user.setEmail(etEmail.getText().toString());
				user.setPassword(etPasseword.getText().toString());
				login.login(user);
			}
		});
		// ·µ»Ø°´Å¥¼àÌýÆ÷
		ivBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LoginActivity.this.finish();
			}
		});
		// ×¢²á¼àÌýÊÂ¼þ
		tvSignUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i=new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(i);

			}
		});
		// ÐÞ¸ÄÃÜÂë¼àÌý
		tvModfyPassword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void loginSeccess() {
		Toast.makeText(LoginActivity.this, "µÇÂ¼³É¹¦", Toast.LENGTH_SHORT).show();
		finish();
	}

	@Override
	public void loginFail(String error) {
		Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
	}

}
