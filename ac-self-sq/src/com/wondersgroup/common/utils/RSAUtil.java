package com.wondersgroup.common.utils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import sun.misc.BASE64Decoder;
import tw.ecosystem.reindeer.config.RdConfig;

public class RSAUtil {

	public static String KEY_PUBLIC = "public";
	public static String KEY_PRIVATE = "private";

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 生成公钥和私钥
	 */
	public static Map<String, Object> generateKey() throws Exception {
		KeyPairGenerator keyPairGen = null;
		KeyPair keyPair = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			keyPairGen.initialize(1024);
			// 产生密钥对
			keyPair = keyPairGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		Map<String, Object> hsKey = new HashMap<String, Object>(2);
		if (keyPair != null) {
			// 保存公钥
			hsKey.put(KEY_PUBLIC, keyPair.getPublic());
			// 保存私钥
			hsKey.put(KEY_PRIVATE, keyPair.getPrivate());
		}
		return hsKey;
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		
		String rtnData = "";
		try {
			byte[] keyBytes = Base64Util.decode(privateKey);
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			rtnData = new String(decryptedData, "utf-8");
			out.close();
		}
		catch (Exception exp) {
			
		}
		return rtnData;
	}

	/**
	 * <P>
	 * 私钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, RSAPrivateKey privateKey) throws Exception {
		
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}
	
	/**
	 * <p>
	 * 公钥解密
	 * </p>
	 * 
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		byte[] keyBytes = Base64Util.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 公钥加密
	 * </p>
	 * 
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		byte[] keyBytes = Base64Util.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 私钥加密
	 * </p>
	 * 
	 * @param data 源数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Util.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}
	
	/** 
     * <p> 
     * 获取私钥 
     * </p> 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")
	public static String getPrivateKey() throws Exception {  
    	// 获取上下文
    	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        Map<String, Object> keyMap = (HashMap<String, Object>) servletContext.getAttribute(RSAUtil.KEY_ALGORITHM);
    	return getPrivateKey(keyMap);
    }
    
	/** 
     * <p> 
     * 获取私钥 
     * </p> 
     * 
     * @param keyMap 密钥对 
     * @return 
     * @throws Exception 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {  
        Key key = (Key) keyMap.get(KEY_PRIVATE);  
        return Base64Util.encode(key.getEncoded());  
    }  
  
    /** 
     * <p> 
     * 获取公钥 
     * </p> 
     * 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")
	public static String getPublicKey() throws Exception {  
    	// 获取上下文
    	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        Map<String, Object> keyMap = (HashMap<String, Object>) servletContext.getAttribute(RSAUtil.KEY_ALGORITHM);
        return getPublicKey(keyMap);  
    }  
    
    /** 
     * 私钥转换成C#格式 
     * @param encodedPrivkey 
     * @return 
     */  
    @SuppressWarnings("unused")
	private static String getRSAPrivateKeyAsNetFormat(byte[] encodedPrivateKey) {  
        try {  
            StringBuffer buff = new StringBuffer(1024);  
  
            PKCS8EncodedKeySpec pvkKeySpec = new PKCS8EncodedKeySpec(  
                    encodedPrivateKey);  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPrivateCrtKey pvkKey = (RSAPrivateCrtKey) keyFactory  
                    .generatePrivate(pvkKeySpec);  
  
            buff.append("<RSAKeyValue>");  
            buff.append("<Modulus>"  
                    + encodeBase64(removeMSZero(pvkKey.getModulus()  
                            .toByteArray())) + "</Modulus>");  
  
            buff.append("<Exponent>"  
                    + encodeBase64(removeMSZero(pvkKey.getPublicExponent()  
                            .toByteArray())) + "</Exponent>");  
  
            buff.append("<P>"  
                    + encodeBase64(removeMSZero(pvkKey.getPrimeP()  
                            .toByteArray())) + "</P>");  
  
            buff.append("<Q>"  
                    + encodeBase64(removeMSZero(pvkKey.getPrimeQ()  
                            .toByteArray())) + "</Q>");  
  
            buff.append("<DP>"  
                    + encodeBase64(removeMSZero(pvkKey.getPrimeExponentP()  
                            .toByteArray())) + "</DP>");  
  
            buff.append("<DQ>"  
                    + encodeBase64(removeMSZero(pvkKey.getPrimeExponentQ()  
                            .toByteArray())) + "</DQ>");  
  
            buff.append("<InverseQ>"  
                    + encodeBase64(removeMSZero(pvkKey.getCrtCoefficient()  
                            .toByteArray())) + "</InverseQ>");  
  
            buff.append("<D>"  
                    + encodeBase64(removeMSZero(pvkKey.getPrivateExponent()  
                            .toByteArray())) + "</D>");  
            buff.append("</RSAKeyValue>");  
  
            return buff.toString();  
        } catch (Exception e) {  
            System.err.println(e);  
            return null;  
        }  
    }  
    
    /** 
     * 公钥转成C#格式 
     * @param encodedPrivkey 
     * @return 
     */  
    @SuppressWarnings("unused")
	private static String getRSAPublicKeyAsNetFormat(byte[] encodedPublicKey) {  
        try {  
            StringBuffer buff = new StringBuffer(1024);  
              
            //Only RSAPublicKeySpec and X509EncodedKeySpec supported for RSA public keys   
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            RSAPublicKey pukKey = (RSAPublicKey) keyFactory  
                    .generatePublic(new X509EncodedKeySpec(encodedPublicKey));  
  
            buff.append("<RSAKeyValue>");  
            buff.append("<Modulus>"  
                    + encodeBase64(removeMSZero(pukKey.getModulus()  
                            .toByteArray())) + "</Modulus>");  
            buff.append("<Exponent>"  
                    + encodeBase64(removeMSZero(pukKey.getPublicExponent()  
                            .toByteArray())) + "</Exponent>");  
            buff.append("</RSAKeyValue>");  
            return buff.toString();  
        } catch (Exception e) {  
            System.err.println(e);  
            return null;  
        }  
    }  
    
    /** 
     * @param data 
     * @return 
     */  
    private static byte[] removeMSZero(byte[] data) {  
        byte[] data1;  
        int len = data.length;  
        if (data[0] == 0) {  
            data1 = new byte[data.length - 1];  
            System.arraycopy(data, 1, data1, 0, len - 1);  
        } else  
            data1 = data;  
  
        return data1;  
    }  
    
    /** 
     * base64编码 
     * @param input 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String encodeBase64(byte[] input) throws Exception {  
        Class clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod = clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
        Object retObj = mainMethod.invoke(null, new Object[] { input });  
        return (String) retObj;  
    }  
    
    /** 
     * <p> 
     * 获取公钥 
     * </p> 
     * 
     * @param keyMap 密钥对 
     * @return 
     * @throws Exception 
     */  
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {  
        Key key = (Key) keyMap.get(KEY_PUBLIC);  
        return Base64Util.encode(key.getEncoded());  
    }
    
    public static String bytesToHexString(byte[] src){   
        StringBuilder stringBuilder = new StringBuilder("");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < src.length; i++) {   
            int v = src[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
    }   
    
    public static byte[] hexStringToBytes(String hexString) {   
        if (hexString == null || hexString.equals("")) {   
            return null;   
        }   
        hexString = hexString.toUpperCase();   
        int length = hexString.length() / 2;   
        char[] hexChars = hexString.toCharArray();   
        byte[] d = new byte[length];   
        for (int i = 0; i < length; i++) {   
            int pos = i * 2;   
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
        }   
        return d;   
    }
    
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);   
    }  
    
    public static void main(String[] args) throws Exception {
    	JSONObject json = new JSONObject();
    	json.put("name", "肖邦");
    	json.put("identNo", "429004199312101138");
    	json.put("type", "year");
    	json.put("code", "GJ0001Q1");
    	
//    	Map<String, Object> map = RSAUtil.generateKey();
    	
//        System.err.println("公钥加密——私钥解密\r\n");  
//        String source = "这是一行要进行RSA加密的原始数据&100"; 
//        String source = "这是因为，RSA算法本身要求加密内容也就是明文长度m必须0<m<密钥长度n。如果小于这个长度就需要进行padding，因为如果没有padding，就无法确定解密后内容的真实长度，字符串之类的内容问题还不大，以0作为结束符，但对二进制数据就很难，因为不确定后面的0是内容还是内容结束符。而只要用到padding，那么就要占用实际的明文长度，于是实际明文长度需要减去padding字节长度。我们一般使用的padding标准有NoPPadding、OAEPPadding、PKCS1Padding等，其中PKCS#1建议的padding就占用了11个字节。";
////        System.out.println("\r加密前文字：\r\n" + source);  
//        byte[] data = source.getBytes("utf-8");
////        //公钥
////        System.out.println("公钥：\r\n" + RSAUtil.getPublicKey(map));
//        //公钥加密  RSAUtil.getPublicKey(map)
//        byte[] encodedData = RSAUtil.encryptByPublicKey(data, RdConfig.get("reindeer.servlet.rsa.publicKey"));
////        System.out.println("加密后文字：\r\n" + new String(encodedData));
////        String str = new BASE64Encoder().encode(encodedData);
//        String str = bytesToHexString(encodedData);
//        System.out.println("加密后文字：\r\n" + str);
        //私钥
//        System.out.println("私钥：\r\n" + RSAUtil.getPrivateKey(map));
        //私钥解密  //RSAUtil.getPrivateKey(map)
        String str = "LpSVLNw7d/eYDoCVq56vSfrba2oOFjtxoYnlIWB+KInu5TqfJuTBl5HmsOOROqy1yALiOR300FrfPMJi1/Lq/Jr7J+taUht5/IKJEYZ0QxOg4XIwnXrVjYbP24jNO9OGKDYebmL1HCsV0PRhVo+4iu2P4BYSiPzt57Dk7qxNOjgQY5fEJuLfH+S5fU4bwzIbso5w/YmoaFfOaJ7WGdlFTazSBHPZHlfJxD23JxkB6Y3auXwG+ontIfLXbuG5PjeA5+YYvaKdNBrd/IBNPr4QDC74+bMg0V1W5VeVoxoXuOmkNHxaaamRjC/27HVeoMxBfsfH/eB1K8cF6cLoam/F2DuSXWDcRNb9qMhXDbh3VEgtBS3AZgq5ywiVvqWozI2eXvOjxMyjDf3M92ahr5RzTD3V3/OBfsTSnqBL8Hi8uumSJEMS1RcjhtQ1qOKnc9LT3iHNZw4MzA6VWwTsaJRMrhcPnSgRt5/dK/EZFCI+F0gIb+IekuGA+9Vux7AI4BHR";
        String decodedData = RSAUtil.decryptByPrivateKey(new BASE64Decoder().decodeBuffer(str), RdConfig.get("reindeer.servlet.rsa.privateKey"));
        System.out.println("解密后文字: \r\n" + decodedData);
    }
}