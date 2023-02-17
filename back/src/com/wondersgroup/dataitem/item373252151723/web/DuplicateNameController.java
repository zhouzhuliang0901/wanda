package com.wondersgroup.dataitem.item373252151723.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class DuplicateNameController {
	
	/**
	 * 新生儿重名查询
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/duplicateName/queryDuplicateName.do")
	public void queryDuplicateName(HttpServletRequest req,
    		HttpServletResponse res)throws IOException{
		String xm = req.getParameter("name");
		if(StringUtils.isNotEmpty(xm)){
			xm = URLDecoder.decode(xm, "utf-8");
		}
		
		String appName = "07c07a71-a695-4580-b0a9-3ea3d3420c2f";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		head.put("sid", "s_12310000MB2F042367_3224");
		
		JSONObject obj = new JSONObject();
		obj.put("xm",xm);
		
		String contentType = "application/json;charset=utf-8";
		String result = HttpUtil.doPost(head,obj.toString(),contentType).toLowerCase();
		System.out.println("新生儿重名查询结果："+result);
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}
	
	public static void main(String[] args) {
		String s = "{\"Msg\":\"调用接口成功\",\"total\":1,\"Data\":[{\"xm\":\"郑栋杰\",\"syrklbdm\":1}],\"Code\":0,\"Success\":true,\"Name\":\"重名（新）\"}";
		System.out.println(s.toLowerCase());
	}
}
