package com.example.travel_love;

import com.travel_love.adapter.CommentAdapter;
import com.travel_love.adapter.MyBaseAdapter;
import com.travel_love.data_msg.CommentMessage;
import com.travel_love.utils.Load_Data_Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.R.layout;
public class DetaiedMessageActivity extends BaseActivity {
	
	
	private  TextView  desnetion;
	private  TextView  starting;
	private  TextView  during;
	private  TextView  req;
    private  TextView  user_id;//消息发布者的数据库的主键
    private  TextView  start_time;
    private  TextView  end_time;
    private  TextView msg_id;
    
    private Intent res_intent;
    
   
    //以上组件是一条消息所含有的信息,包括数据库主键，外键
    
    private LoadLinearLayout load_layout;

    private  Map<String,String> params;
    
    public static  List<CommentMessage> res_list=null;
    public static String m_nickname="";
    
    private String path=com.travel_love.paramters.CommonParams.URL;
    
    private Load_Data_Thread mThread;
 
    private MyBaseAdapter load_comment_adapter;
    
    private CommentPopWindow mCommentPopWindow;
    
    private TextView  comment_increase_new;
    
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);	
	    this.initail();
	}
	private void initail()
	{
		
 	    this.setContentView(R.layout.comment_detail_msg);
 	    
 	    this.desnetion=(TextView)this.findViewById(R.id.destination_other);
 	    this.starting=(TextView)this.findViewById(R.id.starting_other);
 	    this.during=(TextView)this.findViewById(R.id.duringdays_other);
 	    this.start_time=(TextView)this.findViewById(R.id.startdays_msg_other);
 	    this.end_time=(TextView)this.findViewById(R.id.enddays_msg_other);
 	    this.msg_id=(TextView)this.findViewById(R.id.msg_id_other);
 	    this.user_id=(TextView)this.findViewById(R.id.user_id_msg_other);
 	    this.req=(TextView)this.findViewById(R.id.message_article_other);
        this.load_layout=(LoadLinearLayout)this.findViewById(R.id.load_layout);
 	    this.comment_increase_new=(TextView)this.findViewById(R.id.commment_article_newincrease);
		this.getFromFirstPage_One_Message();
		
    }
	private void getFromFirstPage_One_Message()
	{
		 this.res_intent=this.getIntent();
		 
		 Bundle bd=this.res_intent.getExtras();
		 
		 this.desnetion.setText(bd.getString("desnetion"));
		 this.starting.setText(bd.getString("starting"));
		 this.during.setText(bd.getString("during"));
		 this.req.setText(bd.getString("req"));
		 this.user_id.setText(bd.getString("user_id"));
		 this.start_time.setText(bd.getString("start_time"));
		 this.end_time.setText(bd.getString("end_time"));
		 this.msg_id.setText(bd.getString("msg_id"));
		
		 this.m_nickname=this.mSprefer.getString("nick_name", "");
		
		 if(this.mSprefer.getString("user_id", "").equals(this.user_id.getText()))//自己查看自己的消息
		 {
			// 自己查看自己的消息就找出这条评论的所有就好
			  this.encodeForm_get(10);
			     
		 }else{
			 // 如果是自己查看别人的消息，就查看自己和那个发消息的人的评论就好    
	         this.encodeForm_get(9);
		 }
	}

	
	
     public boolean onKeyDown(int keyCode, KeyEvent event) {
	      if(keyCode==KeyEvent.KEYCODE_BACK)//选择事件
	      {
	          finish();	  
	      }
         return false;
     }
     private Handler handler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg)
            {
            	 int state=msg.getData().getInt("state");
            	 if(state==com.travel_love.paramters.CommonParams.STATE_ERROR)
            	 {
            		   Toast.makeText(getApplicationContext(), "加载失败", 1000).show();
            	 }else if(state==com.travel_love.paramters.CommonParams.STATE_RIGHT)
            	 {
            		      if(res_list==null)
            		      {
            		    	  load_layout.load_layout_nocomment();
            		    	  load_layout.leave_word_btn.setOnClickListener(new OnClickListener(){
								 public void onClick(View arg0) {
									 mCommentPopWindow=new CommentPopWindow(getApplication(),load_layout.leave_word_btn,comment_increase_new);
								}
            		    		     
            		    	  });
            		    	  Toast.makeText(getApplicationContext(), "目前还没有评论哦", 1000).show();
            		      }else{
            		           load_comment_adapter=new CommentAdapter(res_list,getApplicationContext());
            		    	  load_layout.load_layout_comment(load_comment_adapter);
            		    	  load_layout.reply_word_btn.setOnClickListener(new OnClickListener(){
								public void onClick(View arg0) {
									mCommentPopWindow=new CommentPopWindow(getApplication(),load_layout.reply_word_btn,comment_increase_new);
								    
								}
            		    		       
            		    	  });
            		      }
            	 }
           }
     };
     private void encodeForm_get(int way)
     {
    	 if(way==9)
    	 {
    		  this.path=this.path+"/Res_to_client/Deal_get_comments.php";
    	      this.params=new HashMap<String,String>();
    	      this.params.put("msg_id", this.msg_id.getText()+"");
    	      this.params.put("com_user_id", this.mSprefer.getString("user_id", "")+"");
    	      //发消息的人在数据库中的主键
    	      this.params.put("com_reply_id", this.user_id.getText()+"");
    	      this.mThread=new Load_Data_Thread(handler,this.path,this.params,way);
    	      this.mThread.start();
    	 }else if(way==10)
    	 {
    		  this.path=this.path+"/Res_to_client/Deal_onemessage_allcomments.php";
    		  this.params=new HashMap<String,String>();
    		  this.params.put("msg_id", this.msg_id.getText()+"");
    		  this.mThread=new Load_Data_Thread(handler,this.path,this.params,way);
    		  this.mThread.start();
    	 }
     }
}
