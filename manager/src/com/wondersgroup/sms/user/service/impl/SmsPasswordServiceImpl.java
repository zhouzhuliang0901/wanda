package com.wondersgroup.sms.user.service.impl;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.apache.shiro.authz.ShiroSmsRetryLimitHashedCredentialsMatcher;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm.SaltStyle;
import org.springframework.stereotype.Service;

import tw.tool.helper.LogHelper;

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.service.SmsPasswordService;

@Service
public class SmsPasswordServiceImpl implements SmsPasswordService {

	@Resource
	private ShiroSmsRetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher;

	@Resource
	private JdbcRealm jdbcRealm;

	@Override
	public void encryptPassword(SmsUser user) {
		Class<?> clz = jdbcRealm.getClass();
		SaltStyle saltStyle = SaltStyle.NO_SALT;
		try {
			Field[] fs = clz.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				if ("saltStyle".equals(fs[i].getName())) {
					fs[i].setAccessible(true);
					saltStyle = (SaltStyle) fs[i].get(jdbcRealm);
					break;
				}
			}
		} catch (SecurityException e) {
			LogHelper.error(e);
		} catch (IllegalArgumentException e) {
			LogHelper.error(e);
		} catch (IllegalAccessException e) {
			LogHelper.error(e);
		}
		String salt = "";
		switch (saltStyle) {
		case NO_SALT:
			break;
		case CRYPT:
			// TODO: separate password and hash from getPasswordForUser[0]
			throw new ConfigurationException("Not implemented yet");
			// break;
		case COLUMN:
			salt = new SecureRandomNumberGenerator().nextBytes().toHex();
			break;
		case EXTERNAL:
			salt = user.getStLoginName();
		}

		SimpleHash hash = new SimpleHash(
				retryLimitHashedCredentialsMatcher.getHashAlgorithmName(),
				user.getStPassword(), salt,
				retryLimitHashedCredentialsMatcher.getHashIterations());
		user.setStSalt(salt);
		user.setStPassword(hash.toHex());
	}

	/**
	 * 赋能注册用户加密
	 */
	@Override
	public void encryptPassword(Oauth2Client newOauth2Client) {
		Class<?> clz = jdbcRealm.getClass();
		SaltStyle saltStyle = SaltStyle.NO_SALT;
		try {
			Field[] fs = clz.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				if ("saltStyle".equals(fs[i].getName())) {
					fs[i].setAccessible(true);
					saltStyle = (SaltStyle) fs[i].get(jdbcRealm);
					break;
				}
			}
		} catch (SecurityException e) {
			LogHelper.error(e);
		} catch (IllegalArgumentException e) {
			LogHelper.error(e);
		} catch (IllegalAccessException e) {
			LogHelper.error(e);
		}
		String salt = "";
		switch (saltStyle) {
		case NO_SALT:
			break;
		case CRYPT:
			// TODO: separate password and hash from getPasswordForUser[0]
			throw new ConfigurationException("Not implemented yet");
			// break;
		case COLUMN:
			salt = new SecureRandomNumberGenerator().nextBytes().toHex();
			break;
		case EXTERNAL:
			salt = newOauth2Client.getStInterfaceUser();
		}
		SimpleHash hash = new SimpleHash(
				retryLimitHashedCredentialsMatcher.getHashAlgorithmName(),
				newOauth2Client.getStInterfacePwd(), salt,
				retryLimitHashedCredentialsMatcher.getHashIterations());
		System.out.println(hash.toHex());
		newOauth2Client.setStInterfacePwd(hash.toHex());

	}
}
