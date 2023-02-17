package com.wondersgroup.dataitem.item199812116020.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import wfc.service.log.Log;

public class ReadJSONUtil {
	
	 public static String getMachineInfo(File jsonFile,String machineId) {
	        String jsonStr = "";
	        Log.debug("————开始读取" + jsonFile.getPath() + "文件————");
	        try {
	            //File jsonFile = new File(fileName);
	            FileReader fileReader = new FileReader(jsonFile);
	            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
	            int ch = 0;
	            StringBuffer sb = new StringBuffer();
	            while ((ch = reader.read()) != -1) {
	                sb.append((char) ch);
	            }
	            fileReader.close();
	            reader.close();
	            jsonStr = sb.toString();
	            Log.debug("————读取" + jsonFile.getPath() + "文件结束!————");
	            
	            net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(jsonStr);
	            net.sf.json.JSONObject jsonMachine = new net.sf.json.JSONObject();
	            for(int iLoop = 0;iLoop<arr.size();iLoop++){
	            	jsonMachine = arr.optJSONObject(iLoop);
	            	String id = jsonMachine.optString("machineMAC");
	            	if(id.equals(machineId)){
	            		break;
	            	}
	            }
	            return jsonMachine.toString();
	        } catch (Exception e) {
	        	Log.debug("————读取" + jsonFile.getPath() + "文件出现异常，读取失败!————");
	            e.printStackTrace();
	            return null;
	        }
	    }
}
