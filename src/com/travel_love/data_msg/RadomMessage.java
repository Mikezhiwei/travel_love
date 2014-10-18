package com.travel_love.data_msg;

public class RadomMessage extends Message {
	
	          
	            private String user_id;//发布人主键
	            private String msg_id;//消息主键
	            private String desnetion;//目的地
	            private String startring;//出发地
                private String reqments;//组队要求
                private String loacation ;//所在地
                private String start_time;//出发时间
                private String end_time;//结束时间
                private String during;//预期天数
               
                
                public void setUser_id(String user_id)
                {
                	    this.user_id=user_id;
                }
                public String getUser_id()
                {
                	   return(this.user_id);
                }
                public void setMsg_id(String msg_id)
                {
                	  this.msg_id=msg_id;
                }
                public String getMsg_id()
                {
                	 return (this.msg_id);
                }
                public void  setReqments(String reqments)
                {
                	 this.reqments=reqments;
                }
                public String getReqment()
                {
                      return(this.reqments);
                }
                public void setStarting(String starting)
                {
                	this.startring=starting;
                }
                public String getStarting()
                {
                	 return this.startring;
                }
                public void setDesnetion(String desnetion)
                {
                	this.desnetion=desnetion;
                }
                public String getDesnetion()
                {
                	 return this.desnetion;
                }
                public void setStart_time(String start_time)
                {
                	  this.start_time=start_time;
                }
                public String getStart_time()
                {
                	   return this.start_time;
                }
                public void   setEnd_time(String end_time)
                {
                	 this.end_time=end_time;
                }
                public String getEnd_time()
                {
                	 return this.end_time;
                }
                public void setLoaction(String location)
                {
                	  this.loacation=location;
                }
                public String  getLocation()
                {
                	 return this.loacation;
                }
                public  void setDuring(String during)
                {
                	this.during=during;
                }
                public String getDuring()
                {
                	 return this.during;
                }
                
                
                
                
	            

}
