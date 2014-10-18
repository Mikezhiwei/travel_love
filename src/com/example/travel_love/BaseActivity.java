package com.example.travel_love;

import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends Activity{
	
	  
	  protected  ProgressDialog dialog;
	  protected  SharedPreferences mSprefer;
	  protected Load_Data_Thread mThread=null;
	  
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  this.dialog=new ProgressDialog(this);
		  this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
	  }
	  
	  protected void showDialog(String title)
	  {
		     this.dialog.setTitle(title);
		     this.dialog.setMessage("连接服务器中..");
	    	 this.dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    	 this.dialog.show();
	  }
	  protected void dismiisDialog()
	  {
		    this.dialog.dismiss();
	  }
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
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
	protected Intent prepareIntent(int  which_page)
    {
    	   Intent back_to_firstpage=new Intent().setClass(getApplicationContext(), MainActivity.class);
  	       Bundle bd=new Bundle();
  	       bd.putInt("which_page",which_page);
  	       back_to_firstpage.putExtras(bd);
  	       return back_to_firstpage;
	 }
	

}
