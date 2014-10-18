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
	    	 //ʵ�ְ��������˳�
	    	    if(keyCode==KeyEvent.KEYCODE_BACK)//ѡ���¼�
	    	    {
	    	    	   long currentTime=System.currentTimeMillis();
	    	    	   if((currentTime-com.travel_love.paramters.CommonParams.touchTime)>=3000)
	    	    	   {
	    	    		      Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�", Toast.LENGTH_SHORT).show();
	    	    	          com.travel_love.paramters.CommonParams.touchTime=currentTime;
	    	    	          return(true);
	    	    	   }else{
	    	    		     return(false);//ע��returnΪtrue��ʾϵͳ����������µ��¼�,false��ʾ�������¼�,ִ�а���
	    	    	   }
	    	    }
	 	        return(super.onKeyDown(keyCode,event));
	 		
	 	}
	    
	  
	  
}
