package com.wondersgroup.self.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import wfc.service.config.Config;
import wfc.service.log.Log;


/**
 * 读取xml内容
 * @author biany
 *
 */
public class ReadXMLContent {
	
	static File file = new File(Config.get("xmlUrl"));//Persons.xml文件绝对路径
	JSONObject obj = new JSONObject();
	
	
	public String readXMLContent() throws Exception{
		//创建dom4j解析器
		SAXReader reader = new SAXReader();
		//加载document对象
		Document document = reader.read(file);
		//获取根节点
		Element root =  document.getRootElement();
		//遍历获取子节点
		int size = root.nodeCount();
		for(int i = 0; i < size; i++){
			Node node = root.node(i);
			if(node instanceof Element){
				//节点名
				String nodeName = node.getName();
				//节点内容
				String nodeValue = node.getText();
				//System.out.println(nodeName+":"+nodeValue);
				obj.put(nodeName, nodeValue);
				
			}	
		}
		String jsonStr = JSON.toJSONString(obj);
		String postStr = jsonpost(Config.get("url")+"/inforpub/deviceinfo/XMLPreloading.do", "&stDeviceMac="+Config.get("client.mac"), "&clContent="+jsonStr+"&version="+Config.get("version"));
		Log.info("读取设备xml接口"+postStr);
		return postStr;
		
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

	
	public static void main(String[] args) {
		ReadXMLContent re = new ReadXMLContent();
		try {
			System.out.println(re.readXMLContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
