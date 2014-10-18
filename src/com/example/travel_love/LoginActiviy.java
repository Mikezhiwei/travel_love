package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActiviy extends BaseActivity implements OnFocusChangeListener{
	
	
	  private ImageButton enrol_text;
	  private EditText usermail;
	  private EditText userpassword;
	  private Button   loginbtn;
	  private ImageView usermail_icon;
	  private ImageView  password_icon;
	  
	  public static  Map<String,String> res_map;
	  private  Map<String,String> params;
	  
	  private  String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_login.php";
	  
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		     super.onCreate(savedInstanceState);
		     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	         this.setContentView(R.layout.login_activity);
	         this.initial();
	  }
	  private  OnClickListener enroll_text_listener=new OnClickListener(){
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent enrollintent=new Intent();
					enrollintent.setClass(getApplicationContext(),EnrollActivity.class);
					startActivity(enrollintent);
				    //finish();
				}
	  };
	  private OnClickListener loginbtn_listener=new OnClickListener(){
		         public void onClick(View arg0)
		         {
		        	    showDialog("登陆中");
		        	    params=new HashMap<String,String>();
		        	    params.put("usermail",usermail.getText().toString());
		        	    params.put("userpassword", userpassword.getText().toString());
		        	    mThread=new Load_Data_Thread(handler,path,params,1);
		        	    mThread.start();
		         }
	  };
	  private void initial()
	  {
		         
                  this.loginbtn=(Button)this.findViewById(R.id.loginbtn);
		          this.usermail=(EditText)this.findViewById(R.id.usermail);
		          this.userpassword=(EditText)this.findViewById(R.id.userpassword);
		          this.enrol_text=(ImageButton)this.findViewById(R.id.enrolltext);
		          this.password_icon=(ImageView)this.findViewById(R.id.userpassword_icon);
		          this.usermail_icon=(ImageView)this.findViewById(R.id.useremail_icon);
		          this.enrol_text.setOnClickListener(enroll_text_listener);
		          this.loginbtn.setOnClickListener(loginbtn_listener);
		          //this.usermail.setOnFocusChangeListener(this);
		          //this.userpassword.setOnFocusChangeListener(this);//影响外观的动态布局，不添加
	  }
      private Handler handler=new Handler(Looper.getMainLooper()){
    	      
    	    	   public void handleMessage(Message msg)
		            {
		            	     int state=msg.getData().getInt("state");
		            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
		            	     {
		            	    	 dismiisDialog();
		            	    	 Toast.makeText(getApplicationContext(), "登录失败请求查看网络设置", Toast.LENGTH_SHORT).show();
		            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
		            	     {
		            	    	   if(res_map.get("islogin").equals("nousername"))
		            	    	   {       
		            	    			 dismiisDialog();
		            	    		     usermail.setText("");
		            	    		     userpassword.setText("");
				            	    	 Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_SHORT).show();
                                   }
		            	    	   else if(res_map.get("islogin").equals("wrongpassword"))
		            	    	   {
		            	    			 dismiisDialog();
		            	    		     userpassword.setText("");
				            	    	 Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
				            	   }
		            	    	   else if(res_map.get("islogin").equals("login"))
		            	    	   {
		            	    		   dismiisDialog();
		            	    		   Toast.makeText(getApplicationContext(), "登录成功",Toast.LENGTH_SHORT).show();
		            	    		   SharedPreferences.Editor meditor=mSprefer.edit();
		            	    		   usermail.setText("");
		            	    		   userpassword.setText("");
		            	    		   meditor.putString("islogin", "login");
		            	    		   meditor.putString("user_id", res_map.get("user_id"));
		            	    		   meditor.commit();
		            	    		   startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                    	    		   finish();
		            	    	   }
			            	 }
		            }
         };
        
		public void onFocusChange(View FC_view, boolean focusable) 
		{
		       if(FC_view.getId()==R.id.usermail)
		       {
		    	       if(focusable)
		    	       {
		    	    	   this.usermail_icon.setVisibility(View.GONE);
		    	    	   this.usermail.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT-20, LayoutParams.WRAP_CONTENT));
		    	       }else{
		    	    	    this.usermail_icon.setVisibility(View.VISIBLE);
		    	    	    this.usermail.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT-20, LayoutParams.WRAP_CONTENT));
		    	       }
		       }else if(FC_view.getId()==R.id.userpassword)
		       {
		    	      if(focusable)
		    	      {
		    	    	    this.password_icon.setVisibility(View.GONE);
		    	    	    this.userpassword.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT-20,LayoutParams.WRAP_CONTENT));
		    	      }else{
		    	    	    this.password_icon.setVisibility(View.VISIBLE);
		    	    	    this.userpassword.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT-20,LayoutParams.WRAP_CONTENT));

		    	      }
		       }
		}
}
