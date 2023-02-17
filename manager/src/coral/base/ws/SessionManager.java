package coral.base.ws;

import java.io.UnsupportedEncodingException;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wfc.service.util.spring.CacheProvider;
import coral.base.util.SecurityHelper;

@Component
public class SessionManager {

	@Autowired
	private CacheProvider cacheProvider;

	public User login(SecretKey secretKey, String account, String password) {
		SecurityHelper securityHelper = new SecurityHelper(secretKey);
		String password2;
		try {
			password2 = SecurityHelper.byteToHexStr(securityHelper
					.encode(account.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw new WsLoginException(e);
		}
		if (password2.equals(password)) {
			User user = new User(account);
			user.setSecretKey(secretKey.getEncoded());
			addSession(user);
			return user;
		} else {
			throw new WsLoginException("登录失败");
		}
	}

	public void logoff(String sessionId) {
		removeSession(sessionId);
	}

	private void addSession(User user) {
		cacheProvider.put("webservice-session-" + user.getSessionId(), user);
	}

	private void removeSession(String sessionId) {
		cacheProvider.remove("webservice-session-" + sessionId);
	}

	public User getUser(SecretKey secretKey, String sessionId) {
		User user = (User) cacheProvider.get("webservice-session-" + sessionId);
		if (user == null) {
			throw new WsException("用户未登录或登录超时");
		} else if (!bytesEquals(secretKey.getEncoded(), user.getSecretKey())) {
			throw new WsException("没有调用本接口的权限");
		} else {
			return user;
		}
	}

	private boolean bytesEquals(byte[] bytes1, byte[] bytes2) {
		for (int i = 0; i < bytes1.length; i++) {
			if (bytes1[i] != bytes2[i]) {
				return false;
			}
		}
		return true;
	}

}
