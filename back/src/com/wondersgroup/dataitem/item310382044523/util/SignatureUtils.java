/** 
 * @(#)SignatureUtils.java 2017-8-17
 * 
 * Copyright (c) 1995-2016 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */
package com.wondersgroup.dataitem.item310382044523.util;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.wondersgroup.dataitem.item310382044523.exception.DepApiException;


/**
 * <pre>
 * 数字签名工具类
 * @author 
 * 2017-8-17
 * </pre>
 */
public class SignatureUtils {
	
	/**
     * 报文签名
     * 
     * @param content
     * @param privateKey
     * @param charset
     * @return
     * @throws AlipayApiException
     */
	public static String genSign(String content, String privateKey, String signType) throws DepApiException {
		if ("SHA256".equals(signType)) {
			return rsa256Sign(content, privateKey, DepAPIConstants.DEFAULT_CHARSET);
		} else {
			throw new DepApiException("无效的签名类型");
		}
	}
	
	/**
     * sha256WithRsa 加签
     * 
     * @param content
     * @param privateKey
     * @param charset
     * @return
     * @throws AlipayApiException
     */
    public static String rsa256Sign(String content, String privateKey,String charset) throws DepApiException {

        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(DepAPIConstants.SIGN_TYPE_RSA,privateKey);

            java.security.Signature signature = java.security.Signature.getInstance(DepAPIConstants.SIGN_SHA256RSA_ALGORITHMS);

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            return Base64Utils.byteArrayToBase64(signed);
        } catch (Exception e) {
            throw new DepApiException(e.getMessage(), e);
        }

    }
    
	/**
	 * <pre>
	 * 将Base64字符串密钥转换PrivateKey
	 * @param algorithm 算法标识
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 * @author 夏博斌 2017-5-12
	 * </pre>
	 */
	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, String privateKey) throws Exception {
		if (privateKey == null || StringUtils.isEmpty(algorithm)) {
			return null;
		}
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		byte[] encodedKey = Base64Utils.base64ToByteArray(privateKey);
		return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
	}
	
	
	/**
	 * <pre>
	 * 验证签名
	 * @param content 交易报文
	 * @param sign 数字签名
	 * @param publicKey 验签公钥
	 * @param signType　签名类型
	 * @return
	 * @throws DepApiException
	 * @author 夏博斌 2017-8-17
	 * </pre>
	 */
	public static boolean checkSign(String content, String sign, String publicKey, String signType) throws DepApiException {
		if ("SHA256".equals(signType)) {
			return sha256CheckContent(content, sign, publicKey, DepAPIConstants.DEFAULT_CHARSET);
		} else {
			throw new DepApiException("无效的签名类型："+signType);
		}
	}
	
	/**
	 * <pre>
	 * SHA256签证数字签名
	 * @param content　交易报文
	 * @param sign　数字签名
	 * @param publicKey　验签公钥
	 * @param charset 字符集　
	 * @return
	 * @throws DepApiException
	 * @author 夏博斌 2017-8-17
	 * </pre>
	 */
	public static boolean sha256CheckContent(String content, String sign, String publicKey, String charset) throws DepApiException {
		try {
			PublicKey pubKey = getPublicKeyFromX509(DepAPIConstants.SIGN_TYPE_RSA,publicKey);

			java.security.Signature signature = java.security.Signature.getInstance(DepAPIConstants.SIGN_SHA256RSA_ALGORITHMS);

			signature.initVerify(pubKey);

			if (StringUtils.isEmpty(charset)) {
				signature.update(content.getBytes());
			} else {
				signature.update(content.getBytes(charset));
			}
			byte[] signByteArray = Base64Utils.base64ToByteArray(sign);
			return signature.verify(signByteArray);
		} catch (Exception e) {
			throw new DepApiException(e.getMessage(), e);
		}
	}
	
	/**
	 * <pre>
	 * 公钥转换
	 * @param algorithm
	 * @param publicKey
	 * @return
	 * @throws Exception
	 * @author 夏博斌 2017-8-17
	 * </pre>
	 */
	public static PublicKey getPublicKeyFromX509(String algorithm, String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64Utils.base64ToByteArray(publicKey)));
		return pubKey;
	}
	
	
	/**
	 * <pre>
	 * 应答报文追加签名
	 * @param response 应答报文 
	 * @param privateKey 签名私钥
	 * @param charset 字符编码
	 * @param signType 签名方式
	 * @return
	 * @throws UPPlatformApiException
	 * @throws UnsupportedEncodingException
	 * @author 夏博斌 2017-5-17
	 * </pre>
	 */
	public static String respSign(String response,String privateKey,String signType) throws DepApiException{
		String sign = SignatureUtils.genSign(response, privateKey, signType);
		return sign;
	}
}
