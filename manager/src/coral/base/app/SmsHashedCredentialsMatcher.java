package coral.base.app;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authz.ShiroSmsRetryLimitHashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class SmsHashedCredentialsMatcher extends
		ShiroSmsRetryLimitHashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;

	public SmsHashedCredentialsMatcher(CacheManager cacheManager) {
		super(cacheManager);
		this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String tokenCredentials = new String((char[]) token.getCredentials());
		Object accountCredentials = getCredentials(info);
		if (tokenCredentials != null && accountCredentials != null
				&& tokenCredentials.equals(accountCredentials.toString()))
			return true;
		String username = (String) token.getPrincipal();
		AtomicInteger retryCount = (AtomicInteger) this.passwordRetryCache
				.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			this.passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5)
			throw new ExcessiveAttemptsException();
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches)
			this.passwordRetryCache.remove(username);
		return matches;
	}
}
