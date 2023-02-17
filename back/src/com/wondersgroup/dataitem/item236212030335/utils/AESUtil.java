package com.wondersgroup.dataitem.item236212030335.utils;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
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
//	      KeyGenerator kgen = KeyGenerator.getInstance("AES");
//	      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//	      secureRandom.setSeed(password.getBytes());
//	      kgen.init(128, secureRandom);
//	      SecretKey secretKey = kgen.generateKey();
//	      byte[] enCodeFormat = secretKey.getEncoded();
	      SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
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
//	      KeyGenerator kgen = KeyGenerator.getInstance("AES");
//	      SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//	      secureRandom.setSeed(password.getBytes());
//	      kgen.init(128, secureRandom);
//	      SecretKey secretKey = kgen.generateKey();
//	      byte[] enCodeFormat = secretKey.getEncoded();
	      
//	      SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
//	      Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
//	      cipher.init(Cipher.ENCRYPT_MODE, key);
//	      byt = cipher.doFinal(decryptFrom);
	      SecretKeySpec skeySpec = new SecretKeySpec(password.getBytes(), "AES");
          Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
          cipher.init(Cipher.DECRYPT_MODE, skeySpec);
          original = cipher.doFinal(Hex.decodeHex(content.toCharArray()));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		return original;
	}
	
	public static void main(String[] args) throws IOException, DecoderException {
		String str = "6F9AB52F9389E11CFA2C0448A51B3D90E86D0316EF23AD83ECEFCC3C398578EC249FCCA77ADE73746EA4ABD03A79EE5C1CE12343A9CC04351BDA210224CA1AFDF03E901E944412D3876DC58E4F272E3B02D86E475FEB345D8B49EC6A2BE6F741E84426B5157124D526FB1A6C9F283BEA5E8785DCE92DCB383301360174A86667FF8F1882E726D8AE60F29848FD0D3DE87345938FC4F24E94A3452213206F73320B4BD86B7C4F51982DB4E83C2FED5ECF45A02058D7A2EF1227C482A2F34987DBBE5AB5E158EDEA17C7E7552E329A7E66C062A75D05DD5442A7695B4CFC1C3AACF92DBAD046EE81E7F82C903738DBA815198BED1BF71FB1970C52D37EDF606A875D089305C7E7B35B37663B5AB509E735C8CF42AC63E66E60DB800864457638FC";
//		byte[] byts = new BASE64Decoder().decodeBuffer(str);
		System.out.println(new String(decrypt(str, "8NONwyJtHesysWpD")));
	}
}
