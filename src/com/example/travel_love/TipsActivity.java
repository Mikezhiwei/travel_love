package com.example.travel_love;

import java.io.IOException;
import java.io.InputStream;

import com.travel_love.utils.HttpUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class TipsActivity extends Activity {
	
	
	
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
          this.setContentView(R.layout.tips_activity);
      }
	 
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	 		// TODO Auto-generated method stub
	    	 //实现按下两次退出
	    	    if(keyCode==KeyEvent.KEYCODE_BACK)//选择事件
	    	    {
	    	    	   long currentTime=System.currentTimeMillis();
	    	    	   if((currentTime-com.travel_love.paramters.CommonParams.touchTime)>=3000)
	    	    	   {
	    	    		      Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
	    	    	          com.travel_love.paramters.CommonParams.touchTime=currentTime;
	    	    	          return(true);
	    	    	   }else{
	    	    		     return(false);//注：return为true表示系统拦截这个按下的事件,false表示不拦截事件,执行按键
	    	    	   }
	    	    }
	 	        return(super.onKeyDown(keyCode,event));
	 		
	 	}
	    
	  
	  
}
