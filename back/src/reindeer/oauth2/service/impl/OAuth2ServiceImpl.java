package reindeer.oauth2.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import reindeer.oauth2.service.ClientService;
import reindeer.oauth2.service.OAuth2Service;
import reindeer.oauth2.view.UserInfo;

@Service
public class OAuth2ServiceImpl implements OAuth2Service {

	private Cache cache;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private ClientService clientService;

	@PostConstruct
	public void init() {
		this.cache = cacheManager.getCache("code-cache");
	}

	@Override
	public void addAuthCode(String authCode, UserInfo userInfo) {
		cache.put(authCode, userInfo);
	}

	@Override
	public void addAccessToken(String accessToken, UserInfo userInfo) {
		cache.put(accessToken, userInfo);
	}

	@Override
	public UserInfo getUserInfoByAuthCode(String authCode) {
		return (UserInfo) cache.get(authCode).get();
	}

	@Override
	public UserInfo getUserInfoByAccessToken(String accessToken) {
		return (UserInfo) cache.get(accessToken).get();
	}

	@Override
	public boolean checkAuthCode(String authCode) {
		return cache.get(authCode) != null;
	}

	@Override
	public boolean checkAccessToken(String accessToken) {
		return cache.get(accessToken) != null;
	}

	@Override
	public boolean checkClientId(String clientId) {
		return clientService.findByClientId(clientId) != null;
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		return clientService.findByClientSecret(clientSecret) != null;
	}

	@Override
	public long getExpireIn() {
		return 3600L;
	}
}
