package com.wondersgroup.selfapi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class WeiXinUtils {

	/**
	 * 
	 * 
	 * @param paramMaps
	 * @param secretKey
	 * @return
	 */
	public static String signatureGenerator(Map<String, String> paramMaps,
			String secretKey) {
		final String[] paramsArray = new String[paramMaps.size()];
		int index = 0;
		for (final Entry<String, String> entry : paramMaps.entrySet()) {

			final String key = entry.getKey();
			final String value = paramMaps.get(key);
			paramsArray[index] = key + "=" + value;

			index++;
		}

		Arrays.sort(paramsArray);
		String string1 = StringUtils.EMPTY;
		String signature = StringUtils.EMPTY;

		for (final String element : paramsArray) {
			string1 += element + "&";
		}

		string1 = string1.substring(0, string1.length() - 1);
		if (StringUtils.isNotEmpty(secretKey)) {
			string1 += secretKey; // 
		}

		try {
			final MessageDigest crypt = MessageDigest.getInstance("SHA-1");//
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return signature;
	}

	/**
	 * 得到sha1签名
	 * 
	 * @param string1
	 * @return
	 */
	public static String getSha1Sign(String string1) {

		String signature = StringUtils.EMPTY;

		try {
			final MessageDigest crypt = MessageDigest.getInstance("SHA-1");// 
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return signature;

	}

	public static String byteToHex(final byte[] hash) {
		final Formatter formatter = new Formatter();
		for (final byte b : hash) {
			formatter.format("%02x", b);
		}
		final String result = formatter.toString();
		formatter.close();
		return result;
	}

	@SuppressWarnings("unused")
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	@SuppressWarnings("unused")
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
