package com.wondersgroup.self.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import wfc.service.config.Config;

import com.alibaba.fastjson.JSONObject;

//192.168.1.125
public class ModifyXml {
	static File file = new File(Config.get("xmlUrl"));//Persons.xml文件绝对路径
    public void UploadXMl() throws Exception{
			String resultName = jsonpost(Config.get("url")+"/infopub/deviceinfo/modifyXML.do", "&stDeviceMac="+Config.get("client.mac"));
			System.out.println(resultName);
			JSONObject jsobject = JSONObject.parseObject(resultName);
			
			 for (Map.Entry<String, Object> entry : jsobject.entrySet()) {
		        /*System.out.println("key值="+entry.getKey());
		        System.out.println("对应key值的value="+entry.getValue());*/
		        String value = (String) entry.getValue();
		            
			//①获得解析器DocumentBuilder的工厂实例DocumentBuilderFactory  然后拿到DocumentBuilder对象
			DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//②获取一个与磁盘文件关联的非空Document对象
			Document doc = newDocumentBuilder.parse(file);
			//③通过文档对象获得该文档对象的根节点
			Element root = doc.getDocumentElement();
			//查找指定节点
			//通过根节点获得子节点
			NodeList personList = root.getElementsByTagName(entry.getKey());
			//判断标签是否存在，如果存在修改，不存在增加
			if(personList.getLength()>0){
				
				//修改
				personList.item(0).setTextContent(value);
			}else{
				  Element typeElement=doc.createElement(entry.getKey());
		            typeElement.setTextContent(value);
		            root.appendChild(typeElement);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Source source = new DOMSource(doc);
			Result result = new StreamResult(file);
			transformer.transform(source, result);//将 XML==>Source 转换为 Result
		}
		}
    		
    public static String jsonpost(String args, String args1) {
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