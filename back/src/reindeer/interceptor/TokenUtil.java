package reindeer.interceptor;


import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.http.HttpHeaders;

import reindeer.oauth2.service.OAuth2Service;
import tw.ecosystem.reindeer.web.ReindeerWebContext;
import tw.tool.helper.LogHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenUtil {
	// 资源服务器的名称
	public static String RESOURCE_SERVER_NAME = "ac-self-api-oauth2";
	// 验证失败提示
	public static final String INVALID_CLIENT_DESCRIPTION = "failed, maybe the client_id or client_secret invalid";

	private OAuth2Service auth2Service;

	/**
	 * 获取请求的token(从session中获取）
	 * 
	 * @throws OAuthProblemException
	 * @throws Exception
	 */
	public String getRequestToken(HttpServletRequest request)
			throws OAuthSystemException, OAuthProblemException {
		String token = (String) request.getSession().getAttribute(
				"access_token");
		// 如果session中不存在token，从header中获取token
		if (StringUtils.isBlank(token)) {
			// token = request.getParameter("access_token");
			// token = request.getHeader("token");
			try {
				// 构建OAuth资源请求(远程的可以使用自定义接口)
				OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(
						request, ParameterStyle.QUERY);
				// 获取Access Token
				String accessToken = oauthRequest.getAccessToken();
				// 验证Access Token
				LogHelper.info("Z:资源服务器验证令牌");
				System.out.println(accessToken);
				if (auth2Service == null)
					auth2Service = (OAuth2Service) ReindeerWebContext
							.getSingleBean(OAuth2Service.class);
				if (!auth2Service.checkAccessToken(accessToken)) {
					// 如果不存在/过期了，返回未验证错误，需重新验证
					OAuthResponse oauthResponse = OAuthRSResponse
							.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
							.setRealm(RESOURCE_SERVER_NAME)
							.setError(OAuthError.ResourceResponse.INVALID_TOKEN)
							.buildHeaderMessage();

					HttpHeaders headers = new HttpHeaders();
					headers.add(
							OAuth.HeaderType.WWW_AUTHENTICATE,
							oauthResponse
									.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
					return null;
				}
				return accessToken;
			} catch (OAuthProblemException e) {
				// 检查是否设置了错误码
				String errorCode = e.getError();
				if (OAuthUtils.isEmpty(errorCode)) {
					OAuthResponse oauthResponse = OAuthRSResponse
							.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
							.setRealm(RESOURCE_SERVER_NAME)
							.buildHeaderMessage();
					HttpHeaders headers = new HttpHeaders();
					headers.add(
							OAuth.HeaderType.WWW_AUTHENTICATE,
							oauthResponse
									.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
					return null;
				}

				OAuthResponse oauthResponse = OAuthRSResponse
						.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setRealm(RESOURCE_SERVER_NAME).setError(e.getError())
						.setErrorDescription(e.getDescription())
						.setErrorUri(e.getUri()).buildHeaderMessage();

				HttpHeaders headers = new HttpHeaders();
				headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse
						.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
				return null;
			}
		}
		return token;
	}

}
