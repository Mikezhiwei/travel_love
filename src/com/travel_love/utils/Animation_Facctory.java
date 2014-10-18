package com.travel_love.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

public class Animation_Facctory {
	
	
	     public static Animation getRotateAnimation(float toDegree ,float  PiontX,float PiontY,final boolean isstay)
	     {
	    	    final  Animation rotateAnimation=new RotateAnimation(0,toDegree,Animation.RELATIVE_TO_SELF,PiontX,Animation.RELATIVE_TO_SELF,PiontY);
	    	    rotateAnimation.setDuration(1500);
	    	    rotateAnimation.setAnimationListener(new AnimationListener(){
					public void onAnimationEnd(Animation arg0) {
						rotateAnimation.setFillAfter(isstay);
					}

					public void onAnimationRepeat(Animation arg0) {
					}

					public void onAnimationStart(Animation arg0) {
					}
	    	    	   
	    	    });
	    	    return rotateAnimation;
	     }
	          
         public static Animation getAlphaAnimation(float toStart,float toEnd)
         {
        	   Animation  AlphaAnimation=new AlphaAnimation(toStart,toEnd);
        	   AlphaAnimation.setDuration(1000);
        	   AlphaAnimation.setFillAfter(true);
        	   return AlphaAnimation;
         }
         public static Animation  getTransAnimation(float toX,float toY)
         {
        	   Animation TransAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF,0,toY);
        	   //��һ�������Ƕ�����ʼ��X��ʼλ�ã��ڶ�������ʱ���Xλ��,�������Ƕ�����ʼ��Yλ�ã����ĸ��Ƕ�������ʱ��Yλ��
        	   TransAnimation.setFillAfter(true);
        	   TransAnimation.setDuration(1000);
        	  return TransAnimation;
         }
         
}
