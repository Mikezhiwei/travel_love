package com.example.travel_love;

import android.content.Context;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DesnetionPopupWindow extends PopupWindow implements OnClickListener{
	
	         
	           private TextView des_chengdu;
	           private TextView des_jiuzhaigou;
	           private TextView des_guosetianxiang;
	           private TextView des_huanglegu;
	           private TextView des_emeishan;
	           private TextView des_shunanzhuhai;
	           private TextView des_xilingxueshan;
	           private TextView des_qingchenshan;
	           private TextView des_huanglongguzhen;
	           
	           private TextView m_get_pos;
	           
	           public DesnetionPopupWindow(Context mContext, View parent,TextView get_pos)
	           {
	        	       View contentView=LayoutInflater.from(mContext).inflate(R.layout.desnetion_choose_popwin_layout, null);
	        	       this.setWidth(LayoutParams.FILL_PARENT);
	        	       this.setHeight(LayoutParams.WRAP_CONTENT);
	        	       this.setTouchable(true);
	        	       this.setFocusable(true);
	        	       this.setContentView(contentView);
	        	       this.setBackgroundDrawable(new BitmapDrawable());
	        	       this.showAsDropDown(parent);
	        	       this.update();
	        	       
	        	       this.des_chengdu=(TextView)contentView.findViewById(R.id.des_chengdu);
	        	       this.des_emeishan=(TextView)contentView.findViewById(R.id.des_emeishan);
	        	       this.des_guosetianxiang=(TextView)contentView.findViewById(R.id.des_guosetianxiang);
	        	       this.des_huanglegu=(TextView)contentView.findViewById(R.id.des_huanglegu);
	        	       this.des_huanglongguzhen=(TextView)contentView.findViewById(R.id.des_huanglong);
	        	       this.des_qingchenshan=(TextView)contentView.findViewById(R.id.des_qingchenshan);
	        	       this.des_jiuzhaigou=(TextView)contentView.findViewById(R.id.des_jiuzhaigou);
	        	       this.des_shunanzhuhai=(TextView)contentView.findViewById(R.id.des_shunanzhuhai);
	        	       this.des_xilingxueshan=(TextView)contentView.findViewById(R.id.des_xilingxueshan);
	                   this.des_qingchenshan=(TextView)contentView.findViewById(R.id.des_qingchenshan);
	                    
	                   this.des_chengdu.setOnClickListener(this);
	                   this.des_emeishan.setOnClickListener(this);
	                   this.des_guosetianxiang.setOnClickListener(this);
	                   this.des_huanglegu.setOnClickListener(this);
	                   this.des_huanglongguzhen.setOnClickListener(this);
	                   this.des_jiuzhaigou.setOnClickListener(this);
	                   this.des_qingchenshan.setOnClickListener(this);
	                   this.des_xilingxueshan.setOnClickListener(this);
	                   this.des_shunanzhuhai.setOnClickListener(this);
	                    
	                   this.m_get_pos=get_pos;
	           }
	          
			public void onClick(View choose_textview) {
				  
				  switch(choose_textview.getId())
				  {
				     case R.id.des_chengdu:
				        this.set_After_action("成都");
				    	break;
				     case R.id.des_emeishan:
				    	this.set_After_action("峨眉山");
				    	break;
				     case R.id.des_guosetianxiang:
				    	 this.set_After_action("国色天香");
				    	 break;
				     case R.id.des_huanglegu:
				    	 this.set_After_action("欢乐谷");
				    	 break;
				     case R.id.des_huanglong:
				    	 this.set_After_action("黄龙古镇");
				    	 break;
				     case R.id.des_jiuzhaigou:
				    	 this.set_After_action("九寨沟");
				    	 break;
				     case R.id.des_qingchenshan:
				    	 this.set_After_action("清城山");
				    	 break;
				     case R.id.des_shunanzhuhai:
				    	 this.set_After_action("蜀南竹海");
				    	 break;
				     case R.id.des_xilingxueshan:
				    	 this.set_After_action("西岭雪山");
				  }
			}
			private void set_After_action(String desnetion)
			{
				   this.m_get_pos.setText(desnetion);
				   this.m_get_pos.setVisibility(View.VISIBLE);
				   this.dismiss();
			}
	

}
