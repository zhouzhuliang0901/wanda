package com.wondersgroup.self.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import wfc.service.config.Config;
import wfc.service.log.Log;

public class DiskUtil {
    
    public static String getHdSerialInfo() {

          String line = "";
          String HdSerial = "";//定义变量 硬盘序列号
          try {
           Process proces = Runtime.getRuntime().exec("cmd /c dir c:");//获取命令行参数
           BufferedReader buffreader = new BufferedReader(new InputStreamReader(proces.getInputStream(),"gbk"));

           while ((line = buffreader.readLine()) != null) {
            if (line.indexOf("卷的序列号是 ") != -1) {  //读取参数并获取硬盘序列号

             HdSerial = line.substring(line.indexOf("卷的序列号是 ") + "卷的序列号是 ".length(), line.length());
             break;
            }
           }

          } catch (IOException e) {
           e.printStackTrace();
          }

          return HdSerial;
         }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getHdSerialInfo());

    }
    
    
    public static void KeyEncryptionSave() {
    	String postStr = jsonpost(Config.get("url")+"/infopub/deviceinfo/KeyEncryptionSave.do", "&stDeviceMac="+Config.get("client.mac"), "&stDeviceKey="+getHdSerialInfo());
		Log.info("调取保存硬盘序列号保存状态"+postStr);
    }

    public static String jsonpost(String args, String args1,String args2) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(args);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求头属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行   否则会抛异常（java.net.ProtocolException: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)）
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流并开始发送参数
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //添加参数
            out.write(args1);
            out.write(args2);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
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
        return result.toString();
    }
    

}