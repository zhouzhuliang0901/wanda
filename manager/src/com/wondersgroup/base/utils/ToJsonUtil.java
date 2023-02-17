package com.wondersgroup.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class ToJsonUtil {

	public JSONObject reqToJson(HttpServletRequest req){
		JSONObject jsonObject = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
			StringBuilder builder = new StringBuilder();
			String inputStr = "";
			while((inputStr = reader.readLine())!=null){
				builder.append(inputStr);
			}
			jsonObject = JSONObject.fromObject(builder.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
