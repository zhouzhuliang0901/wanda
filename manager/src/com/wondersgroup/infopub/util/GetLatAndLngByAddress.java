package com.wondersgroup.infopub.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 根据地址值转化经纬度
 * @author biany
 *
 */

public class GetLatAndLngByAddress {
	
	public static void main(String[] args) {
		System.out.println(getLatAndLng("上海市黄浦区巨鹿路139号"));
	}
	
	public static Map<String,BigDecimal> getLatAndLng(String addr){
		if(addr!=null) {
			String address = "";
			try {
				address = java.net.URLEncoder.encode(addr, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("开始访问百度api");
			// 格式化字符串http://api.map.baidu.com/geocoding/v3/?address=北京市海淀区上地十街10号
			// &output=json&ak=您的ak&callback=showLocation
			String url = String.format("http://api.map.baidu.com/geocoding/v3/?"
					+ "ak=ModdTV7XApdhHGyVfSo3TTxLSmGqasVp&output=json&address=%s&region=全国", address);
			System.out.println(url);
			URL myURL = null;
			URLConnection httpsConn = null;
			// 进行转码
			try {
				myURL = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			StringBuffer sb = new StringBuffer();
			try {
				httpsConn = (URLConnection) myURL.openConnection();
				if (httpsConn != null) {
					InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
					BufferedReader br = new BufferedReader(insr);
					String data = null;
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					while ((data = br.readLine()) != null) {
						sb.append(data);
					}
					insr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
			JSONObject resultJson1 = JSON.parseObject(sb.toString());
			//System.out.println("resultJson1:"+resultJson1);
			JSONObject resultJson2 = (JSONObject) resultJson1.get("result");
			JSONObject resultJson3 = (JSONObject) resultJson2.get("location");
			// 纬度
			BigDecimal lat = (BigDecimal) resultJson3.get("lat");
			// 经度
			BigDecimal lng = (BigDecimal) resultJson3.get("lng");
			BigDecimal scaleLat = lat.setScale(6, BigDecimal.ROUND_HALF_UP);
			BigDecimal scaleLng = lng.setScale(6, BigDecimal.ROUND_HALF_UP);
			map.put("lat", scaleLat);
			map.put("lng", scaleLng);

			return map;
		}else {
			Map<String,BigDecimal> map=new HashMap<String,BigDecimal>();
			map.put("lat", null);
			map.put("lng", null);
			return null;
		}
	}
}
