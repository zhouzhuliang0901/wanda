package com.wondersgroup.dataitem.forward.web;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reindeer.oauth2.service.ClientService;
import reindeer.oauth2.service.OAuth2Service;
import reindeer.oauth2.view.UserInfo;
import tw.tool.helper.LogHelper;
import wfc.service.config.Config;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.dataitem.forward.web.service.SelmItemService;

@SuppressWarnings("all")
@Controller
public class ItemForwardController {
	// 资源服务器的名称
		public static String RESOURCE_SERVER_NAME = "ac-self-api-oauth2";
		// 验证失败提示
		public static final String INVALID_CLIENT_DESCRIPTION = "failed, maybe the client_id or client_secret invalid";

		@Autowired
		private OAuth2Service auth2Service;
		
		@Autowired
		private SelmItemService selmItemService;
	/**
	 * 事项跳转接口（获取access_token，并判断有效性）
	 * @param request
	 * @param res
	 * @return
	 * @throws OAuthSystemException
	 */
	@RequestMapping("/dataitem/forward/skipItem")
	public HttpEntity forward(HttpServletRequest request,HttpServletResponse res)
			throws OAuthSystemException {
		try {
			// 构建OAuth资源请求(远程的可以使用自定义接口)
			OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(
					request, ParameterStyle.QUERY);
			// 获取Access Token
			String accessToken = oauthRequest.getAccessToken();
			// 验证Access Token
			LogHelper.info("Z:资源服务器验证令牌");
			if (!auth2Service.checkAccessToken(accessToken)) {
				// 如果不存在/过期了，返回未验证错误，需重新验证
				OAuthResponse oauthResponse = OAuthRSResponse
						.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setRealm(RESOURCE_SERVER_NAME)
						.setError(OAuthError.ResourceResponse.INVALID_TOKEN)
						.buildHeaderMessage();

				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse
						.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity("Access Token不正确或者已失效",headers, HttpStatus.UNAUTHORIZED);
			}
			request.getSession().setAttribute("access_token",
					accessToken);
			//验证Access Token通过跳转界面
			String itemCode = request.getParameter("itemCode");
			SelmItem obj = selmItemService.get(itemCode);
			 String str=obj.getStWorkUrl().substring(0,4);
			if (str.equals("http")) {
				try {
					res.sendRedirect(obj.getStWorkUrl());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					res.sendRedirect(Config.get("oauth2Url")+obj.getStWorkUrl());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (OAuthProblemException e) {
			// 检查是否设置了错误码
			String errorCode = e.getError();
			if (OAuthUtils.isEmpty(errorCode)) {
				OAuthResponse oauthResponse = OAuthRSResponse
						.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setRealm(RESOURCE_SERVER_NAME).buildHeaderMessage();

				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse
						.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
			}

			OAuthResponse oauthResponse = OAuthRSResponse
					.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
					.setRealm(RESOURCE_SERVER_NAME).setError(e.getError())
					.setErrorDescription(e.getDescription())
					.setErrorUri(e.getUri()).buildHeaderMessage();

			HttpHeaders headers = new HttpHeaders();
			headers.add(OAuth.HeaderType.WWW_AUTHENTICATE,
					oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));		
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
