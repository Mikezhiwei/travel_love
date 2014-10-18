package com.travel_love.adapter;

import java.util.ArrayList;
import java.util.List;

import com.travel_love.data_msg.Message;
import com.travel_love.data_msg.RadomMessage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class  MyBaseAdapter  extends BaseAdapter{

	
	 protected List m_load_data_list=null; 
	 protected Context mContext;
	 public MyBaseAdapter(List m_load_data_list,Context mContext)
	 {
		 this.m_load_data_list=m_load_data_list;   
		 this.mContext=mContext;
	 }
	 public int getCount() {//返回列表的数目
		return this.m_load_data_list.size();
	 }

	public Object getItem(int position) {
		return this.m_load_data_list.get(position);
	}

	public long getItemId(int pos) {
		return pos;
	}

	
	public View getView(int arg0, View arg1, ViewGroup arg2) {//留给子类
		return null;
	}
	
	

}
