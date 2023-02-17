package com.wondersgroup.login.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;

@Controller
public class UserController {
	
	/**
	 * 根据accessToken获取用户信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/workPlatform/getUserInfoByAccessToken.do")
	public void getUserInfoByAccessToken(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String accessToken = req.getParameter("accessToken");
		String userInfo = HttpUtil.getUserInfoByAccesstoken(accessToken);
		JSONObject obj = new JSONObject();
		System.out.println("用户信息：" + userInfo);
		JSONObject userJson = new JSONObject();
		try {
			userJson = JSONObject.fromObject(userInfo);
//			mobile = userJson.optString("zwdtsw_link_phone");
//			userName = userJson.optString("zwdtsw_name");
//			certNo = userJson.optString("zwdtsw_cert_id");
//			userID = userJson.optString("zwdtsw_user_id");

			obj.put("zwdtsw_link_phone", userJson.optString("zwdtsw_link_phone"));
			obj.put("zwdtsw_name", userJson.optString("zwdtsw_name"));
			obj.put("zwdtsw_cert_id", userJson.optString("zwdtsw_cert_id"));
			obj.put("zwdtsw_user_id", userJson.optString("zwdtsw_user_id"));
			obj.put("encrypt_identity", userJson.optString("encrypt_identity"));
		} catch (Exception e) {
			Log.debug("获取用户信息失败" + e.getMessage());
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
}
