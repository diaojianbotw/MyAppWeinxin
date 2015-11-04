package com.example.myappweixin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Whatnew extends Activity{

	private ViewPager mViewPager;
	private ImageView mpage0;
	private ImageView mpage1;
	private ImageView mpage2;
	private ImageView mpage3;
	private ImageView mpage4;
	private ImageView mpage5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whatnews_viewpager);
		mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
		
		mpage0 = (ImageView) findViewById(R.id.page0);
		mpage1 = (ImageView) findViewById(R.id.page1);
		mpage2 = (ImageView) findViewById(R.id.page2);
		mpage3 = (ImageView) findViewById(R.id.page3);
		mpage4 = (ImageView) findViewById(R.id.page4);
		mpage5 = (ImageView) findViewById(R.id.page5);
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View view1 = layoutInflater.inflate(R.layout.whats1, null);
		View view2 = layoutInflater.inflate(R.layout.whats2, null);
		View view3 = layoutInflater.inflate(R.layout.whats3, null);
		View view4 = layoutInflater.inflate(R.layout.whats4, null);
		View view5 = layoutInflater.inflate(R.layout.whats5, null);
		View view6 = layoutInflater.inflate(R.layout.whats6, null);
		
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		views.add(view5);
		views.add(view6);
		
		PagerAdapter mPagerAdapter = new PagerAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			
			@Override
			//³õÊ¼»¯
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}

			@Override
			//Ïú»Ù
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				((ViewPager)container).removeView(views.get(position));
			}
			
		};
		mViewPager.setAdapter(mPagerAdapter);
	}

	public class MyOnchangePageListener implements OnPageChangeListener
	{

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
			switch(arg0)
			{
				case 0:
					mpage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mpage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 1:
					mpage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mpage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mpage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 3:
					mpage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mpage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mpage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
					break;
				case 4:
					mpage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mpage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					mpage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
				case 5:
					mpage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
					mpage5.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
					break;
			}
		}
		
	}
}
