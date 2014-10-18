package com.travel_love.adapter;

import java.util.List;

import com.example.travel_love.R;
import com.travel_love.data_msg.RadomMessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RandomMessageAdapter extends MyBaseAdapter{

	private RadomMessage one_list_random_meeage;
	
	public RandomMessageAdapter(List m_load_data_list,Context mContext) 
	{
		super(m_load_data_list,mContext);
	}
	public View getView(int position, View contentView, ViewGroup parent) {//留给子类
		
		this.one_list_random_meeage=(RadomMessage)this.m_load_data_list.get(position);
		ViewHolder mHolder=null;
		if(contentView==null)
		{
			  contentView=LayoutInflater.from(mContext).inflate(R.layout.message_random_list3, null);
			  mHolder=new ViewHolder();
			  
			  mHolder.user_head=(ImageView)contentView.findViewById(R.id.user_head);
			  mHolder.destination=(TextView)contentView.findViewById(R.id.destination);
			  mHolder.starting=(TextView)contentView.findViewById(R.id.starting);
			  mHolder.startdays_msg=(TextView)contentView.findViewById(R.id.startdays_msg);
			  mHolder.enddays_msg=(TextView)contentView.findViewById(R.id.enddays_msg);
			  mHolder.msg_id=(TextView)contentView.findViewById(R.id.msg_id);
			  mHolder.user_id_msg=(TextView)contentView.findViewById(R.id.user_id_msg);
			  mHolder.during=(TextView)contentView.findViewById(R.id.duringdays);
			  mHolder.message_article=(TextView)contentView.findViewById(R.id.message_article);
			  
			  mHolder.user_head.setImageResource(R.drawable.p2);
			  mHolder.destination.setText(this.one_list_random_meeage.getDesnetion());
			  mHolder.starting.setText(this.one_list_random_meeage.getStarting());
			  mHolder.during.setText(this.one_list_random_meeage.getDuring());
			  mHolder.enddays_msg.setText(this.one_list_random_meeage.getEnd_time());
			  mHolder.startdays_msg.setText(this.one_list_random_meeage.getStart_time());
			  mHolder.msg_id.setText(this.one_list_random_meeage.getMsg_id());
			  mHolder.user_id_msg.setText(this.one_list_random_meeage.getUser_id());
		      mHolder.message_article.setText(this.one_list_random_meeage.getReqment());
		      contentView.setTag(mHolder);
		}else{
			
			  mHolder=(ViewHolder)contentView.getTag();
			  mHolder.user_head.setImageResource(R.drawable.p2);
			  mHolder.destination.setText(this.one_list_random_meeage.getDesnetion());
			  mHolder.starting.setText(this.one_list_random_meeage.getStarting());
			  mHolder.during.setText(this.one_list_random_meeage.getDuring());
			  mHolder.enddays_msg.setText(this.one_list_random_meeage.getEnd_time());
			  mHolder.startdays_msg.setText(this.one_list_random_meeage.getStart_time());
			  mHolder.msg_id.setText(this.one_list_random_meeage.getMsg_id());
			  mHolder.user_id_msg.setText(this.one_list_random_meeage.getUser_id());
			  mHolder.message_article.setText(this.one_list_random_meeage.getReqment());
		
		}
		return contentView;
	}
	private class ViewHolder{
    	
        ImageView user_head;
        TextView destination;
    	TextView starting;
    	TextView startdays_msg;
    	TextView enddays_msg;
    	TextView message_article;
    	
    	TextView msg_id;//隐藏加载
    	TextView user_id_msg;//发布人主键
    	TextView during;//持续时间
   }
	
}
