package com.wondersgroup.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

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
		String str = "92db6ca47c48dee5637ba755f458b7696822c760cb8c7f3c559d8236378899d42569326815818dfcb520260f6f88f4de2e7e5354fb9f8e24ae0ea5f93c9e6f90e4494a0566681e70b19211621e242f4eae699eecb4316748de44a118f14fc123";
		String s = new String(AESUtil.decrypt(str, "8NONwyJtHesysWpD"));
		System.out.println(s);
//		JSONObject json = new JSONObject();
//		json.put("licenseNo", "430426199804106174");
//		json.put("itemCode", "113100000024204128231010025000001");
//		json.put("swrsfz", "310110202001011121");
//		String param = parseByte2HexStr(AESUtil.encrypt(json.toString(), "8NONwyJtHesysWpD"));
//		System.out.println(param);
	}
}
