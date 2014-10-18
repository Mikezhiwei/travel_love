package com.travel_love.adapter;

import java.util.List;

import com.example.travel_love.R;
import com.travel_love.data_msg.CommentMessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommentAdapter extends MyBaseAdapter {

	 private CommentMessage one_comment_message;
	 
	public CommentAdapter(List m_load_data_list, Context mContext) 
	{
		super(m_load_data_list, mContext);
	}

	 public View getView(int position, View contentView, ViewGroup parent)
	 {
		  this.one_comment_message=(CommentMessage)this.m_load_data_list.get(position);
		  ViewHolder viewHolder=null;
		  if(contentView==null)
		  {
			     contentView=LayoutInflater.from(mContext).inflate(R.layout.comments_item, null);
			     
			     viewHolder=new ViewHolder();
			     viewHolder.comment_article=(TextView)contentView.findViewById(R.id.leave_word_article);
			     viewHolder.comment_name=(TextView)contentView.findViewById(R.id.leave_word_user_name);
			     viewHolder.comment_time=(TextView)contentView.findViewById(R.id.leave_word_time);
			     
			     viewHolder.comment_article.setText(this.one_comment_message.getComment_aricle());
			     viewHolder.comment_name.setText(this.one_comment_message.getComment_name());
			     viewHolder.comment_time.setText(this.one_comment_message.getComment_time());
			     contentView.setTag(viewHolder);
		  }else{// use graphics buf
			     viewHolder=(ViewHolder)contentView.getTag();
			     viewHolder.comment_article.setText(this.one_comment_message.getComment_aricle());
			     viewHolder.comment_name.setText(this.one_comment_message.getComment_name());
			     viewHolder.comment_time.setText(this.one_comment_message.getComment_time());
		  }
		  return contentView;
		  
	 }
	 private class ViewHolder{
		   TextView comment_name;
		   TextView comment_article;
		   TextView comment_time;
	 }
}
