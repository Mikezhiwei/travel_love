package com.example.travel_love;

import java.util.List;

import com.travel_love.adapter.CommentAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LoadLinearLayout extends LinearLayout {

	 private Context mContext;
	 
	  private LinearLayout no_comment_layout;
	  private ListView comments_list_ofnocomentlayout;
	  public  Button   leave_word_btn;
	  private ImageView smile_nocomments;
	
	  
	    private LinearLayout comoment_layout;
	    private ListView  comments_list_ofcommentlayout;
	    public Button  reply_word_btn;
	    
	    private LinearLayout.LayoutParams l_params;
	  public LoadLinearLayout(Context context) {
		super(context);
		this.initial(context);
	}
	public LoadLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initial(context);
	}

	private void initial(Context mContext)
	{
	         	 this.mContext=mContext;
	}
	public void load_layout_nocomment()
	{
		  no_comment_layout=(LinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.nocomments_layout, null);
    	  l_params=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	  no_comment_layout.setLayoutParams(l_params);
    	  this.addView(no_comment_layout);
    	  comments_list_ofcommentlayout=(ListView)no_comment_layout.findViewById(R.id.comments_list_ofnocomments);
    	  leave_word_btn=(Button)no_comment_layout.findViewById(R.id.leave_word_btn);
    	  smile_nocomments=(ImageView)no_comment_layout.findViewById(R.id.smile_comment);
	}
	public void load_layout_comment(ListAdapter listadpater)
	{
		  comoment_layout=(LinearLayout)LayoutInflater.from(this.mContext).inflate(R.layout.comment_layout, null);
    	  l_params=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
    	  comoment_layout.setLayoutParams(l_params);
    	  this.addView(comoment_layout);
    	  comments_list_ofcommentlayout=(ListView) comoment_layout.findViewById(R.id.comments_list_ofcommentlayout);
    	  reply_word_btn=(Button)comoment_layout.findViewById(R.id.reply_comments_btn);
    	  comments_list_ofcommentlayout.setAdapter(listadpater);
    	  
    	  
	}
}
