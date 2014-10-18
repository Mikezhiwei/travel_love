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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUserInfoActivity extends BaseActivity {
	   
	  private  EditText user_nickname;
	  private  EditText user_job;
	  private  EditText user_age;
	  
	  private RadioGroup user_sex;
	  
	  private RadioButton male;
	  private RadioButton famale;
	 
	  private  Intent  m_intent;
	  
	 
	  private Map<String,String>  params;
	  public static  Map<String,String> res_map;
	
	 
	  private Button savebtn;
	  private Button cancel_updateinfo_btn;
	  private String path=com.travel_love.paramters.CommonParams.URL;//���ڲ�����ȷ�������������Ҳ��ȷ��
	  
	  private ImageButton cancelbtn;
	  
	  private int hascache;
	  
	  protected void onCreate(Bundle savedInstanceState) 
	  {
		     super.onCreate(savedInstanceState);
		     this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			 this.setContentView(R.layout.update_userinfo_activity);   
	         this.initail();
	  }
	  private void initail()
	  {
	    	 this.savebtn=(Button)this.findViewById(R.id.savainfobtn);
		     this.cancelbtn=(ImageButton)this.findViewById(R.id.cancelinfobtn);
		     this.user_age=(EditText)this.findViewById(R.id.age_update);
		     this.user_nickname=(EditText)this.findViewById(R.id.nickname_update);
		     this.male=(RadioButton)this.findViewById(R.id.male_sex);
		     this.famale=(RadioButton)this.findViewById(R.id.famale_sex);
		     this.cancel_updateinfo_btn=(Button)this.findViewById(R.id.cancel_updateinfo_btn);
		     this.user_job=(EditText)this.findViewById(R.id.job_update);
		     this.user_sex=(RadioGroup)this.findViewById(R.id.sex_choose);
		     
		     this.famale.setChecked(true);
		     this.m_intent=this.getIntent();
		     if(m_intent.getIntExtra("hascache", 1)==1)//û�б��ػ���
		     {
		    	      this.savebtn.setOnClickListener(insert_listener);//û�б��ػ�����ǲ������
		    	      this.hascache=1;
		    	   
		     }else{//�б��ػ���,ֱ�Ӷ�ȡ����
		    	this.hascache=2;//���ñ��ػ����־λ
		    	this.savebtn.setOnClickListener(update_listener);//�б��ػ�������޸Ĳ���
		    	this.user_age.setText(this.mSprefer.getString("age", "0"));
		    	this.user_job.setText(this.mSprefer.getString("job", "job"));
		        this.user_nickname.setText(this.mSprefer.getString("nick_name", "�ǳ�"));
		        if(this.mSprefer.getString("sex", "��").equals("��"))
		        {
		        	   this.male.setChecked(true);
		        }
		        else
		        {
		        	 this.famale.setChecked(true);
		        }
		     }
		     this.cancelbtn.setOnClickListener(new OnClickListener(){
		    	   public void onClick(View v)
		    	   {
          	  	       //startActivity(prepareIntent(4));
		    	       finish();
		    	   }
		     });
		     this.cancel_updateinfo_btn.setOnClickListener(new OnClickListener(){
		    	    public void onClick(View v)
		    	    {
		    	    	    if(hascache==1)
		    	    	    {
		    	    	    	  user_age.setText("");
		    	    	    	  user_job.setText("");
		    	    	    	  user_nickname.setText("");
		    	    	    	  famale.setChecked(true);
		    	    	    }else if(hascache==2)
		    	    	    {
		    	    	    	user_age.setText(mSprefer.getString("age", "0"));
		    			    	user_job.setText(mSprefer.getString("job", "job"));
		    			        user_nickname.setText(mSprefer.getString("nick_name", "�ǳ�"));
		    			        if(mSprefer.getString("sex", "��").equals("��"))
		    			        {
		    			        	   male.setChecked(true);
		    			        }
		    			        else
		    			        {
		    			        	   famale.setChecked(true);
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
          	            Toast.makeText(getApplicationContext(), "����ʧ��", Toast.LENGTH_SHORT).show();
          	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
          	     {
          	    	  
          	    	   user_age.setText("");
          	  	       user_job.setText("");
          	  	       user_nickname.setText("");
          	  	       famale.setChecked(false);
          	  	       male.setChecked(false);
          	  	       //startActivity(prepareIntent(4));
          	  	       finish();
          	     }
          }
     };
     private  OnClickListener insert_listener=new OnClickListener()
     {
    	        public void onClick(View v)
    	        {
    	        	  try{
    	        		 path+="/Res_to_client/Deal_insert_info.php";//�״����������Ϣ
    	        		 showDialog("�ύ������Ϣ��");
    	        		 if(user_age.getText().toString().equals("")||user_job.getText().toString().equals("")||user_job.getText().toString().equals(""))
    	        		 {
    	    	        		Toast.makeText(getApplicationContext(), "��δ�����", Toast.LENGTH_SHORT).show();  
    	    	        		dismiisDialog();
    	        		 }else if(Integer.valueOf(user_age.getText().toString())<0)
    	        		 {
 	    	        		 Toast.makeText(getApplicationContext(), "�����������", Toast.LENGTH_SHORT).show();  
 	    	        		dismiisDialog();
    	        		 }else
    	        		 {  
    	        			  encode_Form(path,4);
    	        		 } 
    	        	  }catch(Exception e){
    	        		Toast.makeText(getApplicationContext(), "�������벻��ȷ��ʽ", Toast.LENGTH_SHORT).show();  
    	        		dismiisDialog();
    	        	  }
    	        }
     };
     private  OnClickListener update_listener=new OnClickListener()
     {
    	        public void onClick(View v)
    	        {
    	        	 try{
    	        		 showDialog("�ύ������Ϣ��...");
    	        		 path+="/Res_to_client/Deal_update_info.php";//�޸ĸ�����Ϣ
    	        		 if(Integer.valueOf(user_age.getText().toString())<0)
    	        		 {
    	        			 Toast.makeText(getApplicationContext(), "�����������", Toast.LENGTH_SHORT).show();  
 	    	             }
    	        		 else
    	        		 {
    	        	         encode_Form(path,5);
    	        	     }
    	        	  }catch(Exception e){
    	        		Toast.makeText(getApplicationContext(), "�������벻��ȷ��ʽ", Toast.LENGTH_SHORT).show();  
    	        		dismiisDialog();
    	        	  }    	        
    	        }
     };
     private  void  encode_Form(String path,int way)//���ɱ�,���������
     {
	          	params=new HashMap<String,String>();
	          	SharedPreferences.Editor meditor=mSprefer.edit();
	          	params.put("user_age", user_age.getText().toString());
	          	meditor.putString("age", user_age.getText().toString());
	          	params.put("nick_name", user_nickname.getText().toString());
	          	meditor.putString("nick_name", user_nickname.getText().toString());
	          	params.put("user_job", user_job.getText().toString());
		  	    meditor.putString("job", user_job.getText().toString());
		  	    params.put("user_id",mSprefer.getString("user_id", ""));
		  	      if(famale.isChecked())
		  	     {
		  		     params.put("user_sex", "Ů");
		  		     meditor.putString("sex", "Ů");
		  	     }else{
		  		    params.put("user_sex", "��");
		  		    meditor.putString("sex", "��");
		  	     }
		  	     mThread=new Load_Data_Thread(handler,path,params,way);
		  	     mThread.start();
		  	     meditor.commit();
		  	    try {
					Thread.sleep(100);//��Ϣ�߳�0.2��,�����л�
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	    
		  	    //�µ����ݻ������޸ĺ�����ݶ����뱾�ػ�����
		  	    //ע:����������ת�Ļ�,commit()��ֵ����Ч��,����������ת�Ĳ���
	}
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
