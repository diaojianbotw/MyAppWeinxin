package com.example.myappweixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Loading extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(Loading.this,Whatnew.class);
				startActivity(intent);
				Toast.makeText(Loading.this, "��½�ɹ�", Toast.LENGTH_SHORT).show();
				
			}
		}, 1000);
	}
	
	
	
}
