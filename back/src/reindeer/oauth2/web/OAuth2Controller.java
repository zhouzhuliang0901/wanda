package reindeer.oauth2.web;

import java.net.URISyntaxException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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

import reindeer.oauth2.bean.Oauth2Client;
import reindeer.oauth2.service.ClientService;
import reindeer.oauth2.service.OAuth2Service;
import reindeer.oauth2.view.UserInfo;
import tw.tool.helper.LogHelper;

@Controller
public class OAuth2Controller {

	// 资源服务器的名称
	public static String RESOURCE_SERVER_NAME = "ac-self-api-oauth2";
	// 验证失败提示
	public static final String INVALID_CLIENT_DESCRIPTION = "failed, maybe the client_id or client_secret invalid";

	@Autowired
	private OAuth2Service auth2Service;

	@Autowired
	private ClientService clientService;

	/**
	 * 获取用户令牌
	 * 
	 * @param request
	 * @return
	 * @throws URISyntaxException
	 * @throws OAuthSystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/oauth2/access_token.do")
	public HttpEntity access_token(HttpServletRequest request)
			throws URISyntaxException, OAuthSystemException {

		try {
			// 构建OAuth请求
			OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
			// 检查提交的客户端id是否正确
			if (!auth2Service.checkClientId(oauthRequest.getClientId())) {
				OAuthResponse response = OAuthASResponse
						.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_CLIENT)
						.setErrorDescription(INVALID_CLIENT_DESCRIPTION)
						.buildJSONMessage();
				return new ResponseEntity(response.getBody(),
						HttpStatus.valueOf(response.getResponseStatus()));
			}

			// 检查客户端安全KEY是否正确
			if (!auth2Service.checkClientSecret(oauthRequest.getClientSecret())) {
				OAuthResponse response = OAuthASResponse
						.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
						.setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
						.setErrorDescription(INVALID_CLIENT_DESCRIPTION)
						.buildJSONMessage();
				return new ResponseEntity(response.getBody(),
						HttpStatus.valueOf(response.getResponseStatus()));
			}

			String authCode = oauthRequest.getParam(OAuth.OAUTH_CODE);
			UserInfo userInfo = null;
			// 检查验证类型，此处只检查AUTHORIZATION_CODE类型，其他的还有PASSWORD或REFRESH_TOKEN
			if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(
					GrantType.AUTHORIZATION_CODE.toString())) {
				if (!auth2Service.checkAuthCode(authCode)) {
					OAuthResponse response = OAuthASResponse
							.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
							.setError(OAuthError.TokenResponse.INVALID_GRANT)
							.setErrorDescription("错误的授权码").buildJSONMessage();
					return new ResponseEntity(response.getBody(),
							HttpStatus.valueOf(response.getResponseStatus()));
				}
				userInfo = auth2Service.getUserInfoByAuthCode(authCode);
			} else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(
					GrantType.CLIENT_CREDENTIALS.toString())) {
				Oauth2Client oClient = clientService
						.findByClientId(oauthRequest.getClientId());
				userInfo = new UserInfo();
				userInfo.setStUserName(oClient.getStClientId());
				userInfo.setStName(oClient.getStClientName());
			} else {
				OAuthResponse response = OAuthASResponse
						.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
						.setError(OAuthError.TokenResponse.INVALID_GRANT)
						.setErrorDescription("grant_type不正确")
						.buildJSONMessage();
				return new ResponseEntity(response.getBody(),
						HttpStatus.valueOf(response.getResponseStatus()));
			}

			// 生成Access Token
			OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(
					new MD5Generator());
			final String accessToken = oauthIssuerImpl.accessToken();
			auth2Service.addAccessToken(accessToken, userInfo);

			// 生成OAuth响应
			OAuthResponse response = OAuthASResponse
					.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(accessToken)
					.setExpiresIn(String.valueOf(auth2Service.getExpireIn()))
					.buildJSONMessage();
			LogHelper.info("S:认证服务器生成令牌Access Token=" + accessToken);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			// 根据OAuthResponse生成ResponseEntity
			return new ResponseEntity(response.getBody(), headers,
					HttpStatus.valueOf(response.getResponseStatus()));

		} catch (OAuthProblemException e) {
			e.printStackTrace();
			// 构建错误响应
			OAuthResponse res = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
					.buildJSONMessage();
			return new ResponseEntity(res.getBody(), HttpStatus.valueOf(res
					.getResponseStatus()));
		}
	}

	/**
	 * 获取授权信息
	 * 
	 * @param request
	 * @return
	 * @throws OAuthSystemException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/oauth2/userinfo.do")
	public HttpEntity userinfo(HttpServletRequest request)
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
				return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
			}
			// 返回客户端资源
			UserInfo userInfo = auth2Service
					.getUserInfoByAccessToken(accessToken);
			LogHelper.info("Z:访问資源控制器，并返回授权限的资源");
			JSONObject object = JSONObject.fromObject(userInfo);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			MediaType mediaType = new MediaType("text", "html",
					Charset.forName("utf-8"));
			headers.setContentType(mediaType);
			return new ResponseEntity(object.toString(), headers, HttpStatus.OK);

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
	}
}
