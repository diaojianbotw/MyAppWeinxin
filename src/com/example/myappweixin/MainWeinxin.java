package com.example.myappweixin;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainWeinxin extends Activity{

	private MainWeinxin instance =null;
	private ViewPager mViewPager;
	private ImageView mTabImg;
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int one;
	private int two;
	private int three;
	private int currIndex = 0;
	private LayoutInflater  layoutInflater;
	private View view;
	private PopupWindow popupWindow;
	private boolean menuflag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_weixin);
		//启东活动的时候不弹出软键盘
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		instance = this;
		mViewPager = (ViewPager) findViewById(R.id.tabpager);
		mViewPager.setOnPageChangeListener(new MyPageChangeListener());
		
		mTab1 = (ImageView) findViewById(R.id.img_weixin);
		mTab2 = (ImageView) findViewById(R.id.img_address);
		mTab3 = (ImageView) findViewById(R.id.img_friends);
		mTab4 = (ImageView) findViewById(R.id.img_settings);
		
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);
		
		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		mTab4.setOnClickListener(new MyOnClickListener(3));
		
		//获取屏幕分辨率
		Display currDisplay = getWindowManager().getDefaultDisplay();
		int displayWidth = currDisplay.getWidth();
		int displayHeight = currDisplay.getHeight();
		one = displayWidth/4;
		two = one *2;
		three = one *3;
		
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
		View view2 = mLi.inflate(R.layout.main_tab_address, null);
		View view3 = mLi.inflate(R.layout.main_tab_friends, null);
		View view4 = mLi.inflate(R.layout.main_tab_settings, null);
		
		final List<View> list1 = new ArrayList<View>();
		list1.add(view1);
		list1.add(view2);
		list1.add(view3);
		list1.add(view4);
		
		PagerAdapter pagerAdapter = new PagerAdapter()
		{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list1.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(View container, int position,
					Object object) {
				// TODO Auto-generated method stub
				((ViewPager)container).removeView(list1.get(position));
				
			}
			
			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager)container).addView(list1.get(position));
				return list1.get(position);
			}
			
			
		};
		mViewPager.setAdapter(pagerAdapter);
	}
 
	//底部图表点击事件
	public class MyOnClickListener implements View.OnClickListener
	{
		private int index = 0;
		
		public MyOnClickListener(int i)
		{
			index = i;
		}
		@Override
		public void onClick(View v) {
			mViewPager.setCurrentItem(index);
		}
		
	}
	
	public class MyPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Animation animation = null;
			switch(arg0)
			{
				case 0:
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
					if(currIndex==1)
					{
						animation = new TranslateAnimation(one,0,0,0);
						mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
					} else if(currIndex==2)
					{
						animation = new TranslateAnimation(two,0,0,0);
						mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
					} else if(currIndex==3)
					{
						animation = new TranslateAnimation(three,0,0,0);
						mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
					}
					break;
				case 1:
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
					if(currIndex==0)
					{
						animation = new TranslateAnimation(0,one,0,0);
						mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
					} else if(currIndex==2)
					{
						animation = new TranslateAnimation(two,one,0,0);
						mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
					} else if(currIndex==3)
					{
						animation = new TranslateAnimation(three,one,0,0);
						mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
					}
					break;
				case 2:
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
					if(currIndex==0)
					{
						animation = new TranslateAnimation(0,two,0,0);
						mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
					}else if(currIndex ==1)
					{
						animation = new TranslateAnimation(one,two,0,0);
						mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
					} else if(currIndex == 3)
					{
						animation = new TranslateAnimation(three,two,0,0);
						mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
					}
					break;
				case 3:
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
					if(currIndex==0)
					{
						animation = new TranslateAnimation(0,three,0,0);
						mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_normal));
					} else if(currIndex==1)
					{
						animation = new TranslateAnimation(one,three,0,0);
						mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
					} else if(currIndex == 2)
					{
						animation = new TranslateAnimation(two,three,0,0);
						mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
					}
					break;
			}
			
			currIndex = arg0;
			animation.setDuration(150);
			animation.setFillAfter(true);// True:图片停在动画结束位置
			mTabImg.startAnimation(animation);
		}
		
	} 
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_MENU)
		{
			if(!menuflag)
			{
				layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
				view = layoutInflater.inflate(R.layout.main_menu, null);
				popupWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
				popupWindow.showAtLocation(findViewById(R.id.main_wexin), Gravity.BOTTOM, 0, 0);
				LinearLayout  linearLayout = (LinearLayout) view.findViewById(R.id.menu_close);
				LinearLayout  mlinearLayout = (LinearLayout) view.findViewById(R.id.menu_close_btn);
				mlinearLayout.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						AlertDialog.Builder dialog = new AlertDialog.Builder(MainWeinxin.this);
						dialog.setTitle("警告");
						dialog.setMessage("确认退出?");
						dialog.setCancelable(false);
						dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {	
							@Override
							public void onClick(DialogInterface dialog, int which) {
								finish();
								instance.finish();
							}
						});
						dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								popupWindow.dismiss();
							}				
						});
						dialog.show();
					}
					
				});
				menuflag = true;
			}
			
		}
		return false;
	}



	public void btnmainright(View v)
	{
		//Intent intent = new Intent(MainWeinxin.this,MainTopRightDialog.class);
		//startActivity(intent);
	}
	
}
