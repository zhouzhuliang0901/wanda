package reindeer.oauth2.service;

import reindeer.oauth2.view.UserInfo;

/**
 * 安全认证业务逻辑层
 * 
 * @author Administrator
 * 
 */
public interface OAuth2Service {

	// 添加 auth code
	public void addAuthCode(String authCode, UserInfo userInfo);

	// 添加 access token
	public void addAccessToken(String accessToken, UserInfo userInfo);

	// 验证auth code是否有效
	boolean checkAuthCode(String authCode);

	// 验证access token是否有效
	boolean checkAccessToken(String accessToken);

	// 根据验证获取用户
	UserInfo getUserInfoByAuthCode(String authCode);

	// 根据令牌获取用户
	UserInfo getUserInfoByAccessToken(String accessToken);

	// auth code / access token 过期时间（单位是秒）
	long getExpireIn();

	// 验证客户端id是否有效
	public boolean checkClientId(String clientId);

	// 验证客户端key是否有效
	public boolean checkClientSecret(String clientSecret);
}
