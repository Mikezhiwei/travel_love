package com.example.travel_love;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CommentPopWindow extends PopupWindow {
	
	   private EditText  comment_input;
	   private Button   send_btn;
	   
	  public CommentPopWindow(Context mContext, View parent,final TextView comment_article)
	  {
		       View contentView=LayoutInflater.from(mContext).inflate(R.layout.comments_popwin_layout, null);
		       this.comment_input=(EditText)contentView.findViewById(R.id.send_comments_article);
		       this.send_btn=(Button)contentView.findViewById(R.id.send_comment_btn);
		       this.setWidth(LayoutParams.FILL_PARENT);
    	       this.setHeight(LayoutParams.WRAP_CONTENT);
    	       this.setTouchable(true);
    	       this.setFocusable(true);
    	       this.setContentView(contentView);
    	       this.setBackgroundDrawable(new BitmapDrawable());
    	       this.showAsDropDown(parent);
    	       this.send_btn.setOnClickListener(new OnClickListener(){
				    public void onClick(View arg0) {
				    	comment_article.setText(comment_input.getText());
					   dismiss();
				}
    	    	       
    	       });
    	       
	  }

}
