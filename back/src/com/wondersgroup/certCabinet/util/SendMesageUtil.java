package com.wondersgroup.certCabinet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.wondersgroup.common.utils.HttpUtil;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.config.Config;
import wfc.service.log.Log;

public class SendMesageUtil {
	private static ExecutorService executorService = Executors.newFixedThreadPool(50);
	public static String sendMesage(final String msgNumber, final String msgContent){
		final Object[] objs = new Object[1];
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String HTTP_BACK_MESSAGE = HTTP_POST(Config.get("reindeer.server.SmsService.master.address"), 
							"id=wonders&pwd=Wonders300168&to="+msgNumber+"&content=" + URLEncoder.encode(msgContent, "gb2312") + "&time=");
					System.out.println("短信发送结果："+HTTP_BACK_MESSAGE);
					objs[0] = HTTP_BACK_MESSAGE;
					countDownLatch.countDown();
				} catch (Exception e) {
					Log.debug("发送失败："+e.getMessage());
				}
			}
		});
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (String)objs[0];
	}
	
	/**
	 * 短信转发
	 * 大数据中心应用服务器访问到nginx服务器，ngnix转发到公司服务器
	 * @param msgNumber
	 * @param msgContent
	 */
	public static void forwardSendMesage(final String msgNumber, final String msgContent){
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String HTTP_BACK_MESSAGE = postSendMesage(RdConfig.get("reindeer.forward.url"), msgNumber, msgContent);
					System.out.println("短信发送结果："+HTTP_BACK_MESSAGE);
				} catch (Exception e) {
					Log.debug("发送失败："+e.getMessage());
				}
			}
		});
	}
	
	private static String postSendMesage(String url, String msgNumber,
			String msgContent) throws UnsupportedEncodingException {
		CloseableHttpClient closeableHttpClient;
		if (url.startsWith("https://")) {
			closeableHttpClient = HttpUtil.getHttpsClient();
		} else {
			closeableHttpClient = HttpClients.createDefault();
		}
		String body = "";
		HttpPost httpPost = new HttpPost(url);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.setCharset(CharsetUtils.get("UTF-8"));
		builder.addPart(
				"msgNumber",
				new StringBody(msgNumber, ContentType.create(
						ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
		builder.addPart(
				"msgContent",
				new StringBody(msgContent, ContentType.create(
						ContentType.TEXT_PLAIN.getMimeType(), "UTF-8")));
		HttpEntity reqEntity = builder.build();
		// 设置参数到请求对象中
		httpPost.setEntity(reqEntity);

		CloseableHttpResponse response = null;
		try {
			if (closeableHttpClient == null) {

			}
			response = closeableHttpClient.execute(httpPost);
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			Log.debug("访问失败：" + e);
		} finally {
			try {
				if (response != null) {
					// 释放链接
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
		
	private static String HTTP_POST(String URL, String Data) throws Exception{
        BufferedReader In = null;
        PrintWriter Out = null;   
        HttpURLConnection HttpConn = null;
        try {
	        URL url=new URL(URL);
	        HttpConn=(HttpURLConnection)url.openConnection();
	        HttpConn.setRequestMethod("POST");
	        HttpConn.setDoInput(true);
	        HttpConn.setDoOutput(true);
	     
	        Out=new PrintWriter(HttpConn.getOutputStream());
	        Out.println(Data);
	        Out.flush();
	        
	        if(HttpConn.getResponseCode() == HttpURLConnection.HTTP_OK){
		        StringBuffer content = new StringBuffer();
		        String tempStr = "";
		        In = new BufferedReader(new InputStreamReader(HttpConn.getInputStream()));
		        while((tempStr = In.readLine()) != null){
		        	content.append(tempStr);
		        }
		        In.close();
		        return content.toString();
	        }
	        else
	        {
				throw new Exception("HTTP_POST_ERROR_RETURN_STATUS：" + HttpConn.getResponseCode());
	        }
        } catch (IOException e) {
        	e.printStackTrace();
        }finally{
	        Out.close();
	        HttpConn.disconnect();
        }
        return null;
	}
	
	public static void main(String[] args) {
		forwardSendMesage("13545161135", "This is a demo!");
	}
}
