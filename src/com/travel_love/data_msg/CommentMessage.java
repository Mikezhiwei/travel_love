package com.travel_love.data_msg;

public class CommentMessage extends Message {
	
	
	           private String comment_name;
	           private String comment_artcile;
	           private String comment_time;
	           private int comments_id;
	           public void setComment_name(String comment_name)
	           {
	        	     this.comment_name=comment_name;
	           }
	           
	           public String getComment_name()
	           {
	        	   return (this.comment_name);
	           }
	           public void setComment_article(String comment_article)
	           {
	        	    this.comment_artcile=comment_article;
	           }
	           public String getComment_aricle()
	           {
	        	   return (this.comment_artcile);
	           }
	           public void setComment_time(String comment_time)
	           {
	        	     this.comment_time=comment_time;
	           }
	           public String getComment_time()
	           {
	        	   return (this.comment_time);
	           }
	           public void setComment_id(int comment_id)
	           {
	        	    this.comments_id=comment_id;
	           }
	           public int getComment_id()
	           {
	        	    return( this.comments_id);
	           }
	           

}
