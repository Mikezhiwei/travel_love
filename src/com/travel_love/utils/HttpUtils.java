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
	        	    	      for(Map.Entry<String,String> entry:params.entrySet())//foreach��䣬��params�ļ�ֵȫ����Ӧ��Entry������
	        	    	      {
	        	    	    	    //ÿ�ε�����list�����һ��������BasicNameValuePair����Ϊ��װ��
	        	    	    	    list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	        	    	      }
	        	    	      try {
	        	    	    	  //ʵ��һ��������WEB�ı�����
	        	    	    	//ANDROID ƽ̨�ı����ʽ��UTF-8,���Ƿ����������ݿ�����ʽ�����ı���,���,���ʹ��UTF-8�ı����ʽ�����룬�ᵼ�·�����������������
								UrlEncodedFormEntity post_entity=new UrlEncodedFormEntity(list,"gb2312");//�ڶ��������Ǳ��ı����ʽ
								//ʹ��HTTP_POST����������,path=URL(��Ҫ�������ҳ)
								HttpPost httpPost=new HttpPost(path);
								//��װʵ���ڱ���
								httpPost.setEntity(post_entity);
								//ʹ�õ�HTTPCLIENT����Ϊ�м䷽����������
								try {
									//���ˣ������POST���ύ
									 HttpClient customsHttpClient=new DefaultHttpClient();
									//HttpResponse response=com.travel_love.utils.CustomsHttpClient.getHttpClient().execute(httpPost);
								    HttpResponse response= customsHttpClient.execute(httpPost);
										if(response.getStatusLine().getStatusCode()==200)//�ж����ص��������ҳ״̬����
		                                {
		                                	 HttpEntity back_entity=response.getEntity();//��÷��ض���
		                                	  //�������з�����������ת��Ϊ�ַ��������ڷ�������gbk������ַ��� ,�˴���ȡ�ӷ��������ַ�����Ҫ��GBK�ı����ʽ
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
	          //�˷�����������ת��Ϊһ���ַ���
	          public static String getStrem_to_string (InputStream m_inputstream,String encode)
	          {
	        	  try {
	        		   String one_line_string=new String();
	        		   //���ڲ��Ƕ�ȡ�ļ�������ֱ��ʹ��Reader�ַ�����,����InputStreamReaderת���ֽ���
	        	       BufferedReader m_bufferedreader=new BufferedReader(new InputStreamReader(m_inputstream,encode));
	        	       StringBuffer m_stringbuffer=new StringBuffer();
	        	       while((one_line_string=m_bufferedreader.readLine())!=null)
					   {
						     m_stringbuffer.append(one_line_string);//����String
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
	                  //��ʼ��һ��URL����  
	                  URL url = new URL(data);  
	                  //���HTTPConnection�������Ӷ���  
	                  HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
	                  connection.setConnectTimeout(5*1000);  
	                  connection.setDoInput(true);  
	                  connection.connect();  
	                  //�õ�������  
	                  InputStream is = connection.getInputStream();  
	                  bitmap = BitmapFactory.decodeStream(is);  
	                  //�ر�������  
	                  is.close();  
	                  //�ر�����  
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
	        	  Bitmap bitmap=null;//λͼ�ı�ʾ
	        	  InputStream inputstream=null;//�����
	   		      try {
	   				URL url=new URL(url_path_image);
	   				if(url!=null)
	   				{
	   					
	   						HttpURLConnection connection=(HttpURLConnection)url.openConnection();//�õ�HTTPconnection
	   					    connection.setDoInput(true);//���ÿ������
	   					    connection.setReadTimeout(1000*5);//���������ʱ
	   					    connection.setRequestMethod("GET");//���û�ȡ��ʽ
	   					    connection.connect();
	   					    if(connection.getResponseCode()==200)
	   					    {
	   					       inputstream=connection.getInputStream();//�õ�ͼƬ��Դ��������
	   					    }
	   					    //��������������õ�λͼ
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
	       public static Bitmap getImageInputStream_Byte(String image_path)//Ч�ʸ���һ��
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
					  connection.connect();//һ��Ҫ������������ɺ���д��
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
			    	  byte img[]=outputstream.toByteArray();//��û��������
			    	  bitmap=BitmapFactory.decodeByteArray(img, 0,img.length);
			    	  //д���ByteArrayOutputStream����
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
