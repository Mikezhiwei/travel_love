package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

public class StrategyActivity extends Activity {
	
       
	   
	 
	 private  Map<String,String> map;//参数
	 public static Map<String,String> res_map;//返回结果,作为静态
	 
	 private  ProgressDialog dialog;
	 
	 private  String path=com.travel_love.paramters.CommonParams.URL+"/JSONtest/testjson.php";
	 private  Load_Data_Thread mThread;
	protected void onCreate(Bundle savedInstanceState) 
	{
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.strategy_activity);
		 
		
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
