package com.example.myappweixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WhatsnewDoor extends Activity{

	private ImageView mleft;
	private ImageView mright;
	private TextView mtext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.whats_door);
		mleft = (ImageView) findViewById(R.id.imageLeft);
		mright = (ImageView) findViewById(R.id.imageRight);
		mtext = (TextView) findViewById(R.id.anim_text);
		
		//left
		AnimationSet mAnimationSet = new AnimationSet(true);
		//�ƶ�����
		TranslateAnimation mTranslateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
		//��������ʱ��
		mTranslateAnimation.setDuration(2000);
		//����ִ��ǰ�ĵȴ�ʱ��
		mAnimationSet.setStartOffset(800);
		mAnimationSet.addAnimation(mTranslateAnimation);
		//Ϊtrue����ִ�к󣬿ؼ���ͣ����ִ�н�����״̬
		mAnimationSet.setFillAfter(true);
		mleft.startAnimation(mAnimationSet);
		
		//right
		AnimationSet mAnimationSet1 = new AnimationSet(true);
		//�ƶ�����
		TranslateAnimation mTranslateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
		//��������ʱ��
		mTranslateAnimation1.setDuration(2000);
		//����ִ��ǰ�ĵȴ�ʱ��
		mAnimationSet1.setStartOffset(800);
		mAnimationSet1.addAnimation(mTranslateAnimation1);
		//Ϊtrue����ִ�к󣬿ؼ���ͣ����ִ�н�����״̬
		mAnimationSet1.setFillAfter(true);
		mright.startAnimation(mAnimationSet1);
		
		//����,͸��
		AnimationSet mAnimationSet2 = new AnimationSet(true);
		//����
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		//��������ʱ��
		mScaleAnimation.setDuration(2000);
		//͸��
		AlphaAnimation mAlphaAnimation = new AlphaAnimation(1,0.0001f);
		//��������ʱ��
		mAlphaAnimation.setDuration(2000);
		mAnimationSet2.addAnimation(mAlphaAnimation);
		mAnimationSet2.addAnimation(mScaleAnimation);
		mAnimationSet2.setFillAfter(true);
		mtext.startAnimation(mAnimationSet2);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(WhatsnewDoor.this,MainWeinxin.class);
				startActivity(intent);				
			}
		}, 2300);
	}

	
}
