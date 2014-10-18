package com.example.travel_love;

import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;

import com.travel_love.utils.Animation_Facctory;
import com.travel_love.utils.Load_Data_Thread;
import android.content.Intent;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.PopupWindow;
import android.widget.ImageView;
public class MessageActivity extends BaseActivity {
	
	
    	 private Button  startdays_btn;
    	 private Button  enddays_btn;
    	 private TextView  show_calendar;
    	 
    	 private ImageView  desnetion_input;
    	 private ImageView startring_input;
    	 private TextView desnetion;
    	 private TextView startring;
    	 private EditText  requestments;
    	 
    	 private  Button  decalrtionbtn;
    	 private  ImageView  cancelbtn;
    	 
    	 public static  Map<String,String> res_map;
    	 private Map<String,String> params;
    	 
         private DesnetionPopupWindow desnetion_choice_win;
         
    	 private String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_insert_message.php";
    	
    	 
	protected void onCreate(Bundle savedInstanceState) 
	{
              super.onCreate(savedInstanceState);
	          this.setContentView(R.layout.message_send_activity);
	          this.initial();
	}
	private void initial()
	{
		   this.startdays_btn=(Button)this.findViewById(R.id.startday);
		   this.enddays_btn=(Button)this.findViewById(R.id.endday);
		   this.cancelbtn=(ImageView)this.findViewById(R.id.cancel_msg_btn);
		   this.decalrtionbtn=(Button)this.findViewById(R.id.decaltionbtn);
		   this.desnetion_input=(ImageView)this.findViewById(R.id.destination_input);
		   this.startring_input=(ImageView)this.findViewById(R.id.starting_input);
		   
   	       this.desnetion=(TextView)this.findViewById(R.id.desnetion);
   	       this.startring=(TextView)this.findViewById(R.id.startring);
		   
   	       this.requestments=(EditText)this.findViewById(R.id.requestment);
		   this.params=new HashMap<String,String>();
		   this.show_calendar=(TextView)this.findViewById(R.id.show_calendar_text);
		   this.startdays_btn.setOnClickListener(new OnClickListener(){
			     public void onClick(View v)
			     {
			    	       new mPopupWindow(MessageActivity.this,show_calendar,startdays_btn); 
			     }
		   });
		   this.enddays_btn.setOnClickListener(new OnClickListener(){
			     public void onClick(View v)
			     {
			    	       new mPopupWindow(MessageActivity.this,show_calendar,enddays_btn);
			     }
		   });
		   this.cancelbtn.setOnClickListener(new OnClickListener(){
			    public void onClick(View v)
			    {
			    	startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                    finish();
			    }
		   });
		   this.desnetion_input.setOnClickListener(new OnClickListener(){
			       public void onClick(View v)
			       {
			    	     desnetion_input.startAnimation(Animation_Facctory.getRotateAnimation(90.0f, 0.5f, 0.5f,false));
			    	     desnetion_choice_win=new DesnetionPopupWindow(MessageActivity.this,desnetion_input,desnetion);
			       }
		   });
		   this.startring_input.setOnClickListener(new OnClickListener(){
			       public void onClick(View v)
			       {
			    	      startring_input.startAnimation(Animation_Facctory.getRotateAnimation(90.0f, 0.5f, 0.5f,false));
			    	      desnetion_choice_win=new DesnetionPopupWindow(MessageActivity.this,startring_input,startring);
			       }
		   });
		   this.decalrtionbtn.setOnClickListener(new OnClickListener(){
                public void onClick(View arg0) 
                {
                	 if(desnetion.getText().toString().equals(""))
                	 {
                		  Toast.makeText(getApplicationContext(), "目的地未填写", Toast.LENGTH_SHORT).show(); 
                	 }else if(startring.getText().toString().equals(""))
                	 {
                		Toast.makeText(getApplicationContext(), "出发地未填写", Toast.LENGTH_SHORT).show();
                	 }else if(startdays_btn.getText().toString().equals("起始时间")) 
                	 {
                	    Toast.makeText(getApplicationContext(), "起始时间未填写", Toast.LENGTH_SHORT).show(); 
                	 }else  if(enddays_btn.getText().toString().equals("结束时间"))
                	 {
                		 Toast.makeText(getApplicationContext(),"结束时间未填写" , Toast.LENGTH_SHORT).show();
                     }else 
                     {
                    	String startday="20"+startdays_btn.getText().toString();
                    	String endday="20"+enddays_btn.getText().toString();
                    	String start_elements[]=startday.split("-");
                    	String end_elements[]=endday.split("-");
                    	Calendar cal1=Calendar.getInstance();
                        cal1.set(Calendar.YEAR, Integer.valueOf(start_elements[0]));
                        cal1.set(Calendar.MONTH,Integer.valueOf(start_elements[1]));
                        cal1.set(Calendar.DAY_OF_MONTH, Integer.valueOf(start_elements[2]));
                        Calendar cal2=Calendar.getInstance();
                        cal2.set(Calendar.YEAR, Integer.valueOf(end_elements[0]));
                        cal2.set(Calendar.MONTH, Integer.valueOf(end_elements[1]));
                        cal2.set(Calendar.DAY_OF_MONTH,Integer.valueOf(end_elements[2]));
                        Calendar current=Calendar.getInstance();
                        current.set(Calendar.MONTH, current.get(Calendar.MONTH)+1);
                        current.set(Calendar.DAY_OF_MONTH,current.get(Calendar.DAY_OF_MONTH)-1);
                        if(cal1.after(cal2))
                        {
                        	
                        	Toast.makeText(getApplicationContext(), "终止日期选择错误", Toast.LENGTH_SHORT).show();
                        }
                        else if(!(cal1.after(current)))
                        {
                        	Toast.makeText(getApplicationContext(), "起始日期选择错误", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {//一切检验无误后才能向服务提交数据
                        	params.put("user_id", mSprefer.getString("user_id",""));
                    	    params.put("desnetion", desnetion.getText().toString());
                    	    params.put("starting",startring.getText().toString());
                    	    params.put("start_time",startday);
                    	    params.put("end_time", endday);
                    	    if(requestments.getText().toString().equals(""))
                    	    {
                    	    	 params.put("req", "无");
                    	    }else{
                    	    	params.put("req", requestments.getText().toString());
                    	    }
                    	    params.put("location", "电子科技大学清水河校区");
                    	    params.put("state", String.valueOf(1));//代表状态正确
                    	    mThread=new Load_Data_Thread(handler,path,params,6);
                        	mThread.start();
                        	showDialog("发布中");
                        }
                	  }
			    }
			       
		   });
	}
   
    private  class mPopupWindow extends PopupWindow{
    	          private CalendarView mCalendar;
    	          private Button  finsh_btn;
    	          public mPopupWindow (Context mContext,View parent,final View showdate)
                  {  
    	        	    View contentView=View.inflate(mContext, R.layout.popupwindow, null);
    	        	    this.setWidth(LayoutParams.FILL_PARENT);
    	        	    this.setHeight(LayoutParams.FILL_PARENT);
    	        	    this.setTouchable(true);
    	        	    this.setFocusable(true);
    	        	    this.setContentView(contentView);
    	        	    this.setBackgroundDrawable(new BitmapDrawable());//必须设置Popupwindow的背景色
    	        	    // this.setBackgroundDrawable(MessageActivity.this.getResources().getDrawable(R.drawable.btn_add_accounts_normal));
    	        	    this.showAsDropDown(parent);
    	        	    this.update();
    	        	    this.mCalendar=(CalendarView)contentView.findViewById(R.id.traveldate_calendar);
    	        	    this.finsh_btn=(Button)contentView.findViewById(R.id.achieve_choose_btn);
    	        	    this.mCalendar.setOnDateChangeListener(new OnDateChangeListener(){
                              public void onSelectedDayChange(CalendarView arg0,
							      int year, int month, int day) {
							      month+=1;//java里面默认月份是从0开始的
							      year-=2000;
							      String date=Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
							      
							      if(showdate==MessageActivity.this.startdays_btn)
							      {
							    	  MessageActivity.this.startdays_btn.setText(date);
							      }else if(showdate==MessageActivity.this.enddays_btn)
							      {
							    	  MessageActivity.this.enddays_btn.setText(date);
							      }
                              }
    	        	   });
    	        	   this.finsh_btn.setOnClickListener(new OnClickListener(){
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							    dismiss();
						}
    	                });
    	          }
    };
    private  Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg)
        {
        	     int state=msg.getData().getInt("state");
        	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR){
        	    	     Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
        	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
        	     {
                       requestments.setText("");
                       startdays_btn.setText("起始时间");
                       enddays_btn.setText("结束时间");
                       dismiisDialog();
                       if(res_map.get("isInsert").equals("insert"))
                       {
                    	     Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
                       }
                       try {
						Thread.sleep(1000);
					   } catch (InterruptedException e) {
						e.printStackTrace();
					  }
                       startActivity(new Intent().setClass(getApplicationContext(), MainActivity.class));
                       finish();
            	 }
        }
     };
     
 	

}
