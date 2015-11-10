package com.example.myappweixin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.HttpCallBacak;
import util.HttpUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

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
	private PullToRefreshListView PullToRefresh;
	private List<DialogBean> dialogData ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_weixin);
		queryFromServer();
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
		//获取listview
		PullToRefresh = (PullToRefreshListView) view1.findViewById(R.id.pullToRefresh);
		//dialogData = getDate();
		
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
	
	public List<DialogBean> getDate()
	{
		
		for(int i=0;i<30;i++)
		{
			DialogBean dialogBean =new DialogBean();
			dialogBean.setImageId(R.drawable.xiaohei);
			dialogBean.setUsername("小黑");
			dialogBean.setLasttext("再见:"+i);
			dialogBean.setTime("昨天晚上");
			dialogData.add(dialogBean);
		}
		return dialogData;
	}
	
	public void init()
	{
		ILoadingLayout startLabels = PullToRefresh    
                .getLoadingLayoutProxy(true, false);    
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示    
        startLabels.setRefreshingLabel("正在载入...");// 刷新时    
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示    
    
        ILoadingLayout endLabels = PullToRefresh.getLoadingLayoutProxy(    
                false, true);    
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示    
        endLabels.setRefreshingLabel("正在载入...");// 刷新时    
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示    
          
	}
	
	private class FinishRefresh extends AsyncTask<Void, Void, Void>{    
        @Override    
        protected Void doInBackground(Void... params) {    
             try {    
                 Thread.sleep(1000);    
             } catch (InterruptedException e) {    
             }    
            return null;    
        }    
     
        @Override    
        protected void onPostExecute(Void result){    
//          adapter.notifyDataSetChanged();  
        	PullToRefresh.onRefreshComplete();
        }    
    }   
	
	public void queryFromServer()
	{
		HttpUtils.sendRequest("http://192.168.1.113:8080/cloudserver/login/loginweixin", new HttpCallBacak() {
			
			@Override
			public void onerror(Exception e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void finish(final String response) {
			
				show(response);	
			}
		});
	}
	
	public void show(String response)
	{
		try {
			dialogData = new ArrayList<DialogBean>();
			JSONArray array = new JSONArray(response);
			for(int i=0;i<array.length();i++)
			{
				JSONObject object = new JSONObject();
				object = (JSONObject) array.get(i);
				DialogBean dialogBean =new DialogBean();
				dialogBean.setImageId(R.drawable.xiaohei);
				dialogBean.setUsername(object.get("name").toString());
				dialogBean.setLasttext("再见:"+i);
				dialogBean.setTime("昨天晚上");
				dialogData.add(dialogBean);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final MyAdapter adaper = new MyAdapter(MainWeinxin.this,dialogData);
		PullToRefresh.setAdapter(adaper);
		PullToRefresh.setMode(Mode.BOTH);
		init();
		PullToRefresh.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				// TODO Auto-generated method stub
				DialogBean db = new DialogBean();
				db.setImageId(R.drawable.xiaohei);
				db.setTime("昨天晚上");
				db.setUsername("小小向下");
				db.setLasttext("再见");
				dialogData.add(db);
				new FinishRefresh().execute();
				adaper.notifyDataSetChanged();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				DialogBean db = new DialogBean();
				db.setImageId(R.drawable.xiaohei);
				db.setTime("昨天晚上");
				db.setUsername("小小向上");
				db.setLasttext("再见");
				dialogData.add(db);
				new FinishRefresh().execute();
				adaper.notifyDataSetChanged();
				
			}
			
		});
		PullToRefresh.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				DialogBean bean =  dialogData.get(position);
				Toast.makeText(MainWeinxin.this, bean.getUsername(), Toast.LENGTH_SHORT).show();
			}
			
		});
	}
}
