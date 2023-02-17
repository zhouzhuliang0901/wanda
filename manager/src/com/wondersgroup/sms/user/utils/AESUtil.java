package com.wondersgroup.sms.user.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import tw.ecosystem.reindeer.config.RdConfig;

public class AESUtil {
	
	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < buf.length; i++) {
	      String hex = Integer.toHexString(buf[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      sb.append(hex.toUpperCase());
	    }
	    return sb.toString();
    }
	
	public static byte[] parseHexStr2Byte(String hexStr) {
	    if (hexStr.length() < 1) {
	    	return null;
	    }
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++)
	    {
	      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
	      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
	      result[i] = ((byte)(high * 16 + low));
	    }
	    return result;
	}
	
	/**
	 * 加密
	 * @param content
	 * @param outkey
	 * @return
	 */
	public static byte[] encrypt(String content, String password) {
	    try {
	      SecretKeySpec key = new SecretKeySpec(password.getBytes("utf-8"), "AES");
	      Cipher cipher = Cipher.getInstance("AES");
	      byte[] byteContent = content.getBytes("utf-8");
	      cipher.init(Cipher.ENCRYPT_MODE, key);
	      byte[] result = cipher.doFinal(byteContent);
	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	}
	
	
	
	/**
	 * 解密
	 * @param content
	 * @param outKey
	 * @return
	 */
	public static byte[] decrypt(String content, String password) {
		byte[] original = null;
	    try {
	      SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes("utf-8"), "AES");
          Cipher cipher = Cipher.getInstance("AES");
          cipher.init(Cipher.DECRYPT_MODE, skeySpec);
          original = cipher.doFinal(Hex.decodeHex(content.toCharArray()));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		return original;
	}
	
	public static void main(String[] args) {
		//String str = "867D4632408F91D2BD5D7F996E40C23CE205A03F96A536DDD522C785C801E936FE3D8DA7232E14FBBDF8CEDE01C6C815";
		//System.out.println(new String(AESUtil.decrypt(str, "8NONwyJtHesysWpD")));
		 byte[] email = AESUtil.encrypt("2241112222@qq.com", "8NONwyJtHesysWpD");
		 String str = HexUtil.bytes2Hex(email);
		
		 System.out.println(new String(str));
		
	}
}
