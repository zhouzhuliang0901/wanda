package com.wondersgroup.dataitem.item276652591922.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * <p>
 * MD5加密工具类
 * </p>
 */
public class MD5 {

	/**
	 * @Description 字符串加密为MD5 中文加密一致通用,必须转码处理： plainText.getBytes("UTF-8")
	 * @param plainText 需要加密的字符串
	 * @return
	 */
	public static String toMD5(String plainText) {
		StringBuffer rlt = new StringBuffer();
		try {
			rlt.append(md5String(plainText.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
		}
		return rlt.toString();
	}



	public static String md5String(byte[] data) {
		String md5Str = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buf = md5.digest(data);
			for (byte element : buf) {
				md5Str += Byte2Hex.byte2Hex(element);
			}
		} catch (Exception e) {
			md5Str = null;
		}
		return md5Str;
	}

}
