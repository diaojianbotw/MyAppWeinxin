package com.example.myappweixin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainTopRightDialog extends Activity{

	private LinearLayout linearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_top_right_dialog);
		linearLayout = (LinearLayout) findViewById(R.id.main_dialog_layout);
		linearLayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainTopRightDialog.this, "µã»÷ÖÜÎ§ÍË³ö", Toast.LENGTH_SHORT).show();
			}
		});
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	
}
