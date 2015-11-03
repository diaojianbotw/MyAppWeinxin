package com.example.myappweixin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class login extends Activity {

	private EditText username;
	private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		username = (EditText) findViewById(R.id.login_user_edit);
		password = (EditText) findViewById(R.id.login_passwd_edit);
	}

	public void clickLogin(View v)
	{
		if("weixin".equals(username) && "123".equals(username))
		{
			
		}
		else if("".equals(username) || "".equals(password) )
		{
			new AlertDialog.Builder(login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("登录失败")
			.setMessage("微信帐号或者密码不能为空，\n请输入后再登录！").create().show();
		} else
		{
			new AlertDialog.Builder(login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("登录失败")
			.setMessage("微信帐号或者密码不正确，\n请检查后重新输入！")
			.create().show();
		}
	}
	
	public void clickBack(View v)
	{
		this.finish();
	}
	
	public void forget(View v)
	{
		Uri uri = Uri.parse("http://www.baidu.com");
		Intent intent = new Intent(Intent.ACTION_VIEW,uri);
		startActivity(intent);
	}
}
