package com.example.travel_love;

import java.util.Timer;
import java.util.TimerTask;

import com.travel_love.utils.Animation_Facctory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	  
	  
	  
	  private  Intent welcomeIntent;
	  
	  private TextView welcome_text;
	  private ImageView welcome_logo;
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		  super.onCreate(savedInstanceState);

		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  this.setContentView(R.layout.welcome_actvitiy);
		  
          this.intiail();
	  }
	  private void intiail()
	  {
		   this.welcome_logo=(ImageView)this.findViewById(R.id.welcome_logo);
		   this.welcome_text=(TextView)this.findViewById(R.id.wel_text);
		   Timer timer=new Timer();
		   TimerTask task=new TimerTask(){
			         public void run()
			         {
			        	    welcomeIntent=new Intent();
			        	    welcomeIntent.setClass(getApplicationContext(),mPreferenceActivity.class);//激活本地缓存的组件
			        	    WelcomeActivity.this.startActivity(welcomeIntent);
			        	    WelcomeActivity.this.finish();
			         }
		   };
		   timer.schedule(task, 3*1000);
		   this.welcome_text.startAnimation(Animation_Facctory.getAlphaAnimation(0.1f, 1.0f));
		  this.welcome_logo.startAnimation(Animation_Facctory.getTransAnimation(0.0f, -40.0f));
	  }
	  

}
