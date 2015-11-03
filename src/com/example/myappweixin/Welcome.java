package com.example.myappweixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	}

	public void clickLogin(View v)
	{
		Intent intent = new Intent(Welcome.this,login.class);
		startActivity(intent);
	}
	
	public void regist(View v)
	{
		
	}
}
