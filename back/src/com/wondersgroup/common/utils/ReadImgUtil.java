package com.wondersgroup.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import sun.misc.BASE64Encoder;

public class ReadImgUtil {
	 private static final String appKey = "30831d179d0c3d4de7b60677dee3349a";
	 private static final String appSecret = "db1c7ef92d693add71d2fa8710666fe7";
	 
	 public static void main(String[] args) {
		 readImg("");
	}
	 
	 public static String readImg(String imgData){
	      String url = "https://ocr-api.ccint.com/ocr_service?app_key=%s";
	      url = String.format(url, appKey);
	        OutputStreamWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            //imgData = imageToBase64("/E:/apache-tomcat-7.0.81-ac_product/webapps/ac-product/WEB-INF/classes/com/wondersgroup/aci/workplatform/web//template/img.png");
	        	String param="{\"app_secret\":\"%s\",\"image_data\":\"%s\"}";
	            param=String.format(param,appSecret,imgData);
	            System.out.println("请求地址："+url);
	            System.out.println("请求参数："+param);
	            URL realUrl = new URL(url);
	            
	            // 解决JAVA6下HTTPS连接关于SSL安全连接的报错
	            HostnameVerifier hv = new HostnameVerifier() {
	            	public boolean verify(String urlHostName, SSLSession session) {
	            		return urlHostName.equals(session.getPeerHost());
	            	}
	            };
	            HttpsURLConnection.setDefaultHostnameVerifier(hv);
	            TrustManager[] tm = { new SSLTrust() };

	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	            sslContext.init(null, tm, new java.security.SecureRandom());
	            SSLSocketFactory ssf = sslContext.getSocketFactory();
	            
	            // 创建HTTPS连接
	            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
	            conn.setSSLSocketFactory(ssf);
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的
	            
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.connect();
	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            out.append(param);
	            out.flush();
	            out.close();
	            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        finally {
	            try {
	                if (out != null) {
	                    out.close();
	                }
	                if (in != null) {
	                    in.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        return result;
	 }
	 
	    public static String imageToBase64(String path) {
	        String imgFile = path;
	        InputStream in = null;
	        byte[] data = null;
	        try
	        {
	            in = new FileInputStream(imgFile);
	            data = new byte[in.available()];
	            in.read(data);
	            in.close();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        BASE64Encoder encoder = new BASE64Encoder();
	        return encoder.encode(data);
	    }

}
