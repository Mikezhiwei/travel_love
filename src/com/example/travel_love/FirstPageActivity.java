package com.example.travel_love;

import java.util.ArrayList;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.travel_love.adapter.MyBaseAdapter;
import com.travel_love.adapter.RandomMessageAdapter;
import com.travel_love.data_msg.RadomMessage;
import com.travel_love.utils.Load_Data_Thread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FirstPageActivity extends BaseActivity {
    
	
	 private  ListView  message_random_list;
	 private  TextView  filter;
	 private  ImageView refresh;
	
	 private Map<String,String> params;
	 

	 
	 private  String path=com.travel_love.paramters.CommonParams.URL+"/Res_to_client/Deal_random_msg.php";
	 
	 
	 public static  List<RadomMessage> load_data_list;
	 private MyBaseAdapter  load_adpater;
	 
	 protected void onCreate(Bundle savedInstanceState) 
	 {
		 super.onCreate(savedInstanceState);
		 this.setContentView(R.layout.firstpage_activity);
		 this.initial();
	 }
	 private void initial()
	 {    
		  // this.showDialog("加载最新消息中");
		   this.message_random_list=(ListView)this.findViewById(R.id.messagelist_random);
		   this.filter=(TextView)this.findViewById(R.id.filter);
		   this.refresh=(ImageView)this.findViewById(R.id.refresh);
		   this.filter.setOnClickListener(filter_listener);
		   this.refresh.setOnClickListener(refresh_listener);
		   params=new HashMap<String,String>();
	       params.put("name", "lzw");
	       //应付型的请求参数
	       mThread=new Load_Data_Thread(handler,path,params,7);
	       mThread.start();
	 }
	 
	 private OnClickListener filter_listener=new OnClickListener(){
		 public void onClick(View arg0) 
		 {
			  Intent sortintent=new Intent().setClass(getApplicationContext(), SortActivtiy.class);
			  startActivity(sortintent);
         }
     };
	 private  OnClickListener refresh_listener=new OnClickListener(){
		    public void onClick(View v)
		    {
		    	   mThread=new Load_Data_Thread(handler,path,params,7);
			       mThread.start();
			      
		    }
	 };
	 private final Handler handler=new Handler(Looper.getMainLooper()){
         public void handleMessage(Message msg)
         {
         	     int state=msg.getData().getInt("state");
         	     if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
         	     {
         	    	  dialog.dismiss();
         	          Toast.makeText(getApplicationContext(), "加载失败", Toast.LENGTH_SHORT).show();
         	     }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
         	     {
         	    	 if(load_data_list!=null)
         	    	 {
	         	    	  load_adpater=new RandomMessageAdapter(load_data_list,FirstPageActivity.this);
	         	    	  message_random_list.setAdapter(load_adpater);
	         	    	  //dismiisDialog();
	         	    	  message_random_list.setOnItemClickListener(listview_listenr);
         	    	 }
	            }
         }
     };
     private   OnItemClickListener  listview_listenr=new OnItemClickListener(){
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
		           Intent detail_msg_intent=new Intent().setClass(getApplicationContext(), DetaiedMessageActivity.class);
		           Bundle bd=new Bundle();
		           bd.putString("desnetion", load_data_list.get(position).getDesnetion());
		           bd.putString("starting", load_data_list.get(position).getStarting());
		           bd.putString("end_time", load_data_list.get(position).getEnd_time());
		           bd.putString("user_id", load_data_list.get(position).getUser_id());
		           bd.putString("req", load_data_list.get(position).getReqment());
		           bd.putString("during", load_data_list.get(position).getDuring());
		           bd.putString("msg_id", load_data_list.get(position).getMsg_id());
		           bd.putString("start_time", load_data_list.get(position).getStart_time());
		           detail_msg_intent.putExtras(bd);
		           startActivity(detail_msg_intent);
		} 
    	           
     };
     


}
