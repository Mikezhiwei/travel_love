package com.example.travel_love;

import java.util.HashMap;
import java.util.Map;

import com.example.travel_love.R.id;
import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EnrollActivity extends BaseActivity{
	
	   private EditText enrollusername;
	   private EditText enrollpassword1;
	   private EditText enrollpassword2;
	   private Button   enrollbtn;
	   private ImageButton logintext;
	   
	   private Map<String,String> params;
	   public  static Map<String,String> res_map;
	   
	 
	   private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_enroll.php";
	   
  	   protected void onCreate(Bundle savedInstanceState) 
	   {
		    super.onCreate(savedInstanceState);
	        this.setContentView(R.layout.enroll_activity2);
	        this.initial();
	   }
  	   private  OnClickListener logintext_listener=new OnClickListener()
  	   {
  		        public void onClick(View arg0)
  		        {
  		        	   //startActivity(prepareIntent(2));
  		        	   finish();
  		        }
  	   };
  	   private void initial()
  	   {    
  		       this.enrollpassword1=(EditText)this.findViewById(R.id.enrollpassword1);
  		       this.enrollpassword2=(EditText)this.findViewById(R.id.enrollpassword2);
  		       this.enrollusername=(EditText)this.findViewById(R.id.enrollusername);
  		       this.enrollbtn=(Button)this.findViewById(R.id.enrollbtn);
  		       this.logintext=(ImageButton)this.findViewById(R.id.logintext);
  		       this.logintext.setOnClickListener(logintext_listener);
  		       this.enrollbtn.setOnClickListener(new OnClickListener(){
  		    	         public void onClick(View v)
  		    	         {
  		    	        	if(!(enrollpassword1.getText().toString().equals(enrollpassword2.getText().toString())))//两次输入密码不同
  		    	        	{
  		    	        		  Toast.makeText(getApplicationContext(), "两次密码不同", Toast.LENGTH_SHORT).show();
  		    	        		  enrollpassword1.setText("");
  		    	        		  enrollpassword2.setText("");
  		    	            }
  		    	        	else
  		    	            {
  		    	            	if((enrollusername.getText().toString().indexOf("@")<=0))//用户输入错误邮件格式
  		    	            	{
    		    	        		  Toast.makeText(getApplicationContext(), "用户名格式不正确", Toast.LENGTH_SHORT).show();
    		    	                  enrollusername.setText("");
  		    	            	}else if(enrollusername.getText().toString().indexOf("@")==(enrollusername.getText().toString().length()-1))
    		    	            {
  		    	        		      Toast.makeText(getApplicationContext(), "用户名格式不正确", Toast.LENGTH_SHORT).show();
  		    	        		      enrollusername.setText("");
    		    	            }
    		    	             else{//检验格式都正确后进行注册
    		    	                showDialog("正在注册");
    	  				    	    params=new HashMap<String,String>();
    	  				    	    params.put("usermail", enrollusername.getText().toString());
    	  				    	    params.put("userpassword", enrollpassword1.getText().toString());
    	  				    	    mThread=new Load_Data_Thread(handler,path,params,2);
    	  				    	    mThread.start();
    		    	            }
  		    	             }
  				    	  }
  		       });
  	    }
  		private  Handler handler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg)
            {
            	     int state=msg.getData().getInt("state");
            	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
            	     {
            	    	    dismiisDialog();
            	            Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
            	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
            	     {
            	    	    dismiisDialog();
            	    	    if(res_map.get("isEnroll").equals("duplicateusername"))
            	    	    {
                	            Toast.makeText(getApplicationContext(), "已经有用户注册过了，尝试其他用户名注册", Toast.LENGTH_SHORT).show();
                	            enrollusername.setText("");
                	        }else{
                	            Toast.makeText(getApplicationContext(), "注册成功,请尝试登录", Toast.LENGTH_SHORT).show();
                                enrollusername.setText("");
                                enrollpassword1.setText("");
                                enrollpassword2.setText("");
                                startActivity(prepareIntent(2));
                                finish();
            	    	    }
            	     } 
             }
          };
          public boolean onKeyDown(int keyCode, KeyEvent event) 
          {
     	      if(keyCode==KeyEvent.KEYCODE_BACK)
     	      {
     	    	    startActivity(prepareIntent(4));
     	    	    return false;
     	      }
     	      return true;
          }


  		
}
