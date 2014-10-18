package com.travel_love.utils;

import java.io.BufferedReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.example.travel_love.TipsActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpUtils {

	          public static String sendHttpCLientPost(String path,Map<String,String> params,String encode)
	          {
	        	      List<NameValuePair> list=new ArrayList<NameValuePair>();
	        	      if(params!=null && !params.isEmpty())
	        	      {
	        	    	      for(Map.Entry<String,String> entry:params.entrySet())//foreach语句，将params的键值全部对应与Entry变量中
	        	    	      {
	        	    	    	    //每次迭代中list都添加一个匿名类BasicNameValuePair，作为封装体
	        	    	    	    list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	        	    	      }
	        	    	      try {
	        	    	    	  //实现一个类似于WEB的表单请求
	        	    	    	//ANDROID 平台的编码格式是UTF-8,但是服务器的数据库编码格式是中文编码,因此,如果使用UTF-8的编码格式来编码，会导致服务器出现乱码问题
								UrlEncodedFormEntity post_entity=new UrlEncodedFormEntity(list,"gb2312");//第二个参数是表单的编码格式
								//使用HTTP_POST方法来请求,path=URL(需要请求的网页)
								HttpPost httpPost=new HttpPost(path);
								//封装实体于表单中
								httpPost.setEntity(post_entity);
								//使用到HTTPCLIENT来作为中间方来发送请求
								try {
									//到此，完成了POST的提交
									 HttpClient customsHttpClient=new DefaultHttpClient();
									//HttpResponse response=com.travel_love.utils.CustomsHttpClient.getHttpClient().execute(httpPost);
								    HttpResponse response= customsHttpClient.execute(httpPost);
										if(response.getStatusLine().getStatusCode()==200)//判定返回的请求的网页状态编码
		                                {
		                                	 HttpEntity back_entity=response.getEntity();//获得返回对象
		                                	  //调用下列方法将输入流转化为字符串，由于服务器是gbk编码的字符串 ,此处获取从服务器的字符串都要用GBK的编码格式
		                                	 return(getStrem_to_string(back_entity.getContent(),encode));
		                                }
								  
								} catch (ClientProtocolException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	        	     }
	        	      return("");    
	          }
	          //此方法将输入流转化为一个字符串
	          public static String getStrem_to_string (InputStream m_inputstream,String encode)
	          {
	        	  try {
	        		   String one_line_string=new String();
	        		   //由于不是读取文件，所以直接使用Reader字符输入,并且InputStreamReader转化字节流
	        	       BufferedReader m_bufferedreader=new BufferedReader(new InputStreamReader(m_inputstream,encode));
	        	       StringBuffer m_stringbuffer=new StringBuffer();
	        	       while((one_line_string=m_bufferedreader.readLine())!=null)
					   {
						     m_stringbuffer.append(one_line_string);//缓存String
					   }
	        	       return(m_stringbuffer.toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				
					}
	        	     return("");    
	          }
	         /* public static Bitmap getHttpBitmap(String data)  
	          {  
	              Bitmap bitmap = null;  
	              try  
	              {  
	                  //初始化一个URL对象  
	                  URL url = new URL(data);  
	                  //获得HTTPConnection网络连接对象  
	                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	                  connection.setConnectTimeout(5*1000);  
	                  connection.setDoInput(true);  
	                  connection.connect();  
	                  //得到输入流  
	                  InputStream is = connection.getInputStream();  
	                  bitmap = BitmapFactory.decodeStream(is);  
	                  //关闭输入流  
	                  is.close();  
	                  //关闭连接  
	                  connection.disconnect();  
	              } catch (Exception e)  
	              {  
	                  // TODO Auto-generated catch block  
	                  e.printStackTrace();  
	              }  
	                
	              return bitmap;  
	          }  */
	          public static Bitmap getImageInputStream(String url_path_image)
	   	   {
	        	  Bitmap bitmap=null;//位图的表示
	        	  InputStream inputstream=null;//输出流
	   		      try {
	   				URL url=new URL(url_path_image);
	   				if(url!=null)
	   				{
	   					
	   						HttpURLConnection connection=(HttpURLConnection)url.openConnection();//得到HTTPconnection
	   					    connection.setDoInput(true);//设置可以输出
	   					    connection.setReadTimeout(1000*5);//设置最大延时
	   					    connection.setRequestMethod("GET");//设置获取方式
	   					    connection.connect();
	   					    if(connection.getResponseCode()==200)
	   					    {
	   					       inputstream=connection.getInputStream();//得到图片资源的输入流
	   					    }
	   					    //从网络的输入流得到位图
	   					    bitmap=BitmapFactory.decodeStream(inputstream);
	   					    inputstream.close();
	   					    connection.disconnect();
	   			    } 
	   		      }catch (MalformedURLException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			  }catch(IOException e){
	   				  e.printStackTrace();
	   			  }
	   		      return bitmap;
	   	   }
	       public static Bitmap getImageInputStream_Byte(String image_path)//效率更低一点
	       {
	    	     Bitmap bitmap=null;
	    	     InputStream inputstream=null;
	    	   try {
			      URL url_image=new URL(image_path);
				  if(url_image!=null)
				  {
					  HttpURLConnection connection=(HttpURLConnection)url_image.openConnection();
					  connection.setReadTimeout(5*1000);
					  connection.setRequestMethod("GET");
					  connection.setDoInput(true);
					  connection.connect();//一定要在所有设置完成后在写入
				   if(connection.getResponseCode()==200)
				   {
					  ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
					  inputstream=connection.getInputStream();
			    	  byte data[]=new byte[1024];
			    	  int len;
			    	  while((len=inputstream.read(data))!=-1)
			    	  {
			    		      outputstream.write(data, 0, len);
			    	  }
			    	  outputstream.flush();
			    	  outputstream.close();
			    	  inputstream.close();
			    	  connection.disconnect();
			    	  byte img[]=outputstream.toByteArray();//获得缓冲的数组
			    	  bitmap=BitmapFactory.decodeByteArray(img, 0,img.length);
			    	  //写入从ByteArrayOutputStream缓存
			    	  return bitmap;
				   }
				   else 
				   {
					   return null;
				   }
				 }
		    	     
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
	    	    return bitmap;
	       }
	
}
