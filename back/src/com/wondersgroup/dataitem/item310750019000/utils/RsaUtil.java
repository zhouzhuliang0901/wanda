package com.wondersgroup.dataitem.item310750019000.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * RSA签名算法工具
 *
 * @author WuBin
 *
 */
public class RsaUtil {

    /**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK256 = 256 - 11;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK256 = 256;

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 转换私钥
	 *
	 * @param key
	 *            私钥字符
	 * @return 私钥
	 */
	public static PrivateKey parsePrivateKey(String key) {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
					Base64.decodeBase64(key));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("转换私钥失败", e);
		}
	}

	/**
	 * 转换私钥
	 *
	 * @param is
	 *            私钥文件流
	 * @return 私钥
	 */
	public static PrivateKey parsePrivateKey(InputStream is) {
		try {
			byte[] encodedKey = new byte[is.available()];
			is.read(encodedKey);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("转换私钥失败", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				;
			}
		}
	}

	/**
	 * 签名Sha1WithRSA
	 *
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待签名数据
	 * @return 签名数据
	 */
	public static byte[] sign(PrivateKey privateKey, byte[]... data) {
		return sign(privateKey, "Sha1WithRSA", data);
	}

	/**
	 * 签名Sha256WithRSA
	 *
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待签名数据
	 * @return 签名数据
	 */
	public static byte[] sign256(PrivateKey privateKey, byte[]... data) {
		return sign(privateKey, "Sha256WithRSA", data);
	}

	/**
	 * 签名
	 *
	 * @param privateKey
	 *            私钥
	 * @param algorithm
	 *            签名算法
	 * @param data
	 *            待签名数据
	 * @return 签名数据
	 */
	public static byte[] sign(PrivateKey privateKey, String algorithm,
			byte[]... data) {
		try {
			Signature signObject = Signature.getInstance(algorithm);
			signObject.initSign(privateKey);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (byte[] sub : data) {
				if (sub != null) {
					baos.write(sub);
				}
			}
			signObject.update(baos.toByteArray());
			return signObject.sign();
		} catch (Exception e) {
			throw new RuntimeException("签名失败", e);
		}
	}

	/**
	 * 解密
	 *
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待解密数据
	 * @return 解密数据
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[]... data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (byte[] sub : data) {
				if (sub != null) {
					baos.write(sub);
				}
			}
			byte[] encryptedData = baos.toByteArray();
			int inputLen = baos.toByteArray().length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet,
							MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen
							- offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			return out.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("解密失败", e);
		}
	}

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param data       待解密数据
     * @return 解密数据
     */
    public static byte[] decrypt256(PrivateKey privateKey, byte[]... data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (byte[] sub : data) {
                if (sub != null) {
                    baos.write(sub);
                }
            }
            byte[] encryptedData = baos.toByteArray();
            int inputLen = baos.toByteArray().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK256) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK256);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK256;
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

	/**
	 * 转换公钥
	 *
	 * @param key
	 *            公钥字符
	 * @return 公钥
	 */
	public static PublicKey parsePublicKey(String key) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
					Base64.decodeBase64(key));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePublic(keySpec);
		} catch (Exception e) {
			throw new RuntimeException("转换公钥失败", e);
		}
	}

	/**
	 * 验签Sha1WithRSA
	 *
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            签名
	 * @param data
	 *            待验签数据
	 * @return 是否通过
	 */
	public static boolean verify(PublicKey publicKey, byte[] sign,
			byte[]... data) {
		return verify(publicKey, "Sha1WithRSA", sign, data);
	}

	/**
	 * 验签Sha256WithRSA
	 *
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            签名
	 * @param data
	 *            待验签数据
	 * @return 是否通过
	 */
	public static boolean verify256(PublicKey publicKey, byte[] sign,
			byte[]... data) {
		return verify(publicKey, "Sha256WithRSA", sign, data);
	}

	/**
	 * 验签
	 *
	 * @param publicKey
	 *            公钥
	 * @param algorithm
	 *            签名算法
	 * @param sign
	 *            签名
	 * @param data
	 *            待验签数据
	 * @return 是否通过
	 */
	public static boolean verify(PublicKey publicKey, String algorithm,
			byte[] sign, byte[]... data) {
		try {
			Signature signObject = Signature.getInstance(algorithm);
			signObject.initVerify(publicKey);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (byte[] sub : data) {
				if (sub != null) {
					baos.write(sub);
				}
			}
			signObject.update(baos.toByteArray());
			return signObject.verify(sign);
		} catch (Exception e) {
			throw new RuntimeException("验签失败", e);
		}
	}

	/**
	 * 加密
	 *
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            待加密数据
	 * @return 加密数据
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[]... data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (byte[] sub : data) {
				if (sub != null) {
					baos.write(sub);
				}
			}
			byte[] originalData = baos.toByteArray();
			int inputLen = baos.toByteArray().length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(originalData, offSet,
							MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(originalData, offSet, inputLen
							- offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			return out.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("加密失败", e);
		}
    }

    /**
	 * 加密
	 *
	 * @param publicKey
	 *            公钥
	 * @param data
	 *            待加密数据
	 * @return 加密数据
	 */
	public static byte[] encrypt256(PublicKey publicKey, byte[]... data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (byte[] sub : data) {
				if (sub != null) {
					baos.write(sub);
				}
			}
			byte[] originalData = baos.toByteArray();
			int inputLen = baos.toByteArray().length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK256) {
					cache = cipher.doFinal(originalData, offSet,
							MAX_ENCRYPT_BLOCK256);
				} else {
					cache = cipher.doFinal(originalData, offSet, inputLen
							- offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK256;
			}
			return out.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException("加密失败", e);
		}
	}

}
