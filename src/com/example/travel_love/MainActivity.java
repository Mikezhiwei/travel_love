package com.example.travel_love;

import com.travel_love.utils.Animation_Facctory;

import android.os.Bundle;
import android.preference.PreferenceManager;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;


public class MainActivity extends TabActivity implements OnClickListener{

	
	private TabHost tabhost;
	private TabHost.TabSpec space;
	
	private ImageView firstpage;
	private ImageView tipspage;
	private ImageView messagepage;
	private ImageView strategypage;
	private ImageView userinfopaage;
	
	
	private Intent  chooseintent;
	private SharedPreferences mSprefer;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		
	      super.onCreate(savedInstanceState);
	      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	      this.setContentView(R.layout.index_main_activity);
	      this.initail();
	   
	}
	private void initail()
	{
		      this.tabhost=this.getTabHost();
		      this.mSprefer=PreferenceManager.getDefaultSharedPreferences(this);
		      this.firstpage=(ImageView)this.findViewById(R.id.firstpage);
		      this.tipspage=(ImageView)this.findViewById(R.id.tipspage);
		      this.messagepage=(ImageView)this.findViewById(R.id.messagepage);
		      this.userinfopaage=(ImageView)this.findViewById(R.id.userinfopage);
		      this.strategypage=(ImageView)this.findViewById(R.id.strategypage);
		      
		      this.chooseintent=new Intent().setClass(getApplicationContext(),FirstPageActivity.class);
		      this.space=this.tabhost.newTabSpec("firstpage").setIndicator("firstpage").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载首页
		      this.chooseintent=new Intent().setClass(getApplicationContext(),TipsActivity.class);
		      this.space=this.tabhost.newTabSpec("tipspage").setIndicator("tipspage").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载贴士界面
		      this.chooseintent=new Intent().setClass(getApplicationContext(),MessageActivity.class);
		      this.space=this.tabhost.newTabSpec("messagepage").setIndicator("messagepage").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载发送消息界面
		      this.chooseintent=new Intent().setClass(getApplicationContext(),StrategyActivity.class);
		      this.space=this.tabhost.newTabSpec("strategypage").setIndicator("strategypage").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载攻略界面
		      this.chooseintent=new Intent().setClass(getApplicationContext(),UserInfoActivity.class);
		      this.space=this.tabhost.newTabSpec("userinfopage").setIndicator("userinfopage").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载我的信息的界面
		      this.chooseintent=new Intent().setClass(getApplicationContext(), LoginActiviy.class);
		      this.space=this.tabhost.newTabSpec("login").setIndicator("login").setContent(this.chooseintent);
		      this.tabhost.addTab(space);
		      //加载我的登陆的界面
		    
		     
		      this.firstpage.setOnClickListener(this);
		      this.tipspage.setOnClickListener(this);
		      this.messagepage.setOnClickListener(this);
		      this.strategypage.setOnClickListener(this);
		      this.userinfopaage.setOnClickListener(this);
		      
		       
	}
	

	public void onClick(View which_page) {
		
		   int clickview_ID=which_page.getId();
		   switch(clickview_ID)
		   {
		   case R.id.firstpage:
			   this.firstpage.setImageResource(R.drawable.home_page_pressed);
			   this.tipspage.setImageResource(R.drawable.tips_unpressed);
			   this.strategypage.setImageResource(R.drawable.strategy_unpressed);
			   this.userinfopaage.setImageResource(R.drawable.myinfo_unpressed);
			   tabhost.setCurrentTabByTag("firstpage");
			   break;
		   case R.id.tipspage:
			   this.tipspage.setImageResource(R.drawable.tips_pressed);
			   this.firstpage.setImageResource(R.drawable.home_page_unpressed);
			   this.strategypage.setImageResource(R.drawable.strategy_unpressed);
			   this.userinfopaage.setImageResource(R.drawable.myinfo_unpressed);
			   tabhost.setCurrentTabByTag("tipspage");
			   break;
		   case R.id.messagepage:
			   this.messagepage.startAnimation(Animation_Facctory.getRotateAnimation(360.0f, 0.5f, 0.5f,true));
			   this.tipspage.setImageResource(R.drawable.tips_unpressed);
			   this.firstpage.setImageResource(R.drawable.home_page_unpressed);
			   this.strategypage.setImageResource(R.drawable.strategy_unpressed);
			   this.userinfopaage.setImageResource(R.drawable.myinfo_unpressed);
			   if(mSprefer.getString("islogin", "").equals("unlogin"))
			    	{
			    		tabhost.setCurrentTabByTag("login");
			    		Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();

			    	}else{
			    		tabhost.setCurrentTabByTag("messagepage");
			    	}
			   break;
		   case R.id.strategypage:
			   this.strategypage.setImageResource(R.drawable.strategy_pressed);
			   this.tipspage.setImageResource(R.drawable.tips_unpressed);
			   this.firstpage.setImageResource(R.drawable.home_page_unpressed);
			   this.userinfopaage.setImageResource(R.drawable.myinfo_unpressed);
			   tabhost.setCurrentTabByTag("strategypage");
			   break;
		   case R.id.userinfopage:
			   this.strategypage.setImageResource(R.drawable.strategy_unpressed);
			   this.tipspage.setImageResource(R.drawable.tips_unpressed);
			   this.firstpage.setImageResource(R.drawable.home_page_unpressed);
			   this.userinfopaage.setImageResource(R.drawable.myinfo_unpressed);
			   if(mSprefer.getString("islogin", "").equals("unlogin"))
			    	{
			    		tabhost.setCurrentTabByTag("login");
			    		Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
			    	}else{
			    	    tabhost.setCurrentTabByTag("userinfopage");
			    	}
			   break;
		   default:
				 break;
		   }
              		
	}

	
	

}
