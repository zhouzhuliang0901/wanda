package com.wondersgroup.dataitem.item204492999222.utils;

//import org.apache.commons.codec.binary.Hex;
//import org.bouncycastle.crypto.digests.SM3Digest;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import javax.crypto.Cipher;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.SecureRandom;
//import java.security.Security;
//import java.util.Random;
//
//public class GmUtil {
//	static {
//		Security.addProvider(new BouncyCastleProvider());
//	}
//
//	/**
//	 * sm3 摘要
//	 */
//	public static byte[] sm3Digest(byte[] input) {
//		SM3Digest sm3Digest = new SM3Digest();
//		sm3Digest.update(input, 0, input.length);
//		byte[] ret = new byte[sm3Digest.getDigestSize()];
//		sm3Digest.doFinal(ret, 0);
//		return ret;
//	}
//
//	/**
//	 * sm3 摘要
//	 */
//	public static String sm3Digest(String input) {
//		byte[] bytes = input.getBytes();
//		SM3Digest sm3Digest = new SM3Digest();
//		sm3Digest.update(bytes, 0, bytes.length);
//
//		byte[] ret = new byte[sm3Digest.getDigestSize()];
//		sm3Digest.doFinal(ret, 0);
//		return Hex.encodeHexString(ret);
//	}
//
//	/**
//	 * SM4 加密 SM4/CBC/PKCS5Padding 模式
//	 * 
//	 * @param input
//	 *            明文数据
//	 * @param key
//	 *            密钥
//	 * @param iv
//	 *            初始向量(ECB 模式下传 NULL)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] sm4Encrypt(byte[] input, byte[] key, byte[] iv)
//			throws Exception {
//		return sm4(input, key, iv, Cipher.ENCRYPT_MODE);
//	}
//
//	public static String sm4Encrypt(String input, String key, String iv) {
//		try {
//			return Hex.encodeHexString(sm4Encrypt(input.getBytes("utf-8"),
//					Hex.decodeHex(key), iv == null ? null : iv.getBytes("utf-8")));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * SM4 解密
//	 * 
//	 * @param input
//	 *            密文数据
//	 * @param key
//	 *            密钥
//	 * @param iv
//	 *            初始向量(ECB 模式下传 NULL)
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] sm4Decrypt(byte[] input, byte[] key, byte[] iv)
//			throws Exception {
//		return sm4(input, key, iv, Cipher.DECRYPT_MODE);
//	}
//
//	/**
//	 * SM4 解密
//	 * 
//	 * @param input
//	 *            密文数据
//	 * @param key
//	 *            密钥
//	 * @param iv
//	 *            初始向量(ECB 模式下传 NULL)
//	 * @return
//	 * @throws Exception
//	 */
//	public static String sm4Decrypt(String input, String key, String iv)
//			throws Exception {
//		return new String(sm4Decrypt(Hex.decodeHex(input), Hex.decodeHex(key),
//				iv == null ? null : iv.getBytes("utf-8")), "utf-8");
//	}
//
//	public static Cipher sm4CbcPkcs7PaddingCipher;
//	public static Cipher sm4EcbPkcs7PaddingCipher;
//	public static String appKey = "2bec0f287c1e4ff19358954b6621d174";
//	public static String secretKey = "9e3a5aff229a4e239eabb795b2fa7eab";
//	static {
//		try {
//			sm4CbcPkcs7PaddingCipher = Cipher.getInstance(
//					SM4ModeAndPaddingEnum.SM4_CBC_PKCS7Padding.getName(),
//					BouncyCastleProvider.PROVIDER_NAME);
//			sm4EcbPkcs7PaddingCipher = Cipher.getInstance(
//					SM4ModeAndPaddingEnum.SM4_ECB_PKCS7Padding.getName(),
//					BouncyCastleProvider.PROVIDER_NAME);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchProviderException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static byte[] sm4(byte[] input, byte[] key, byte[] iv, int mode)
//			throws Exception {
//		IvParameterSpec ivParameterSpec = null;
//		if (iv != null) {
//			ivParameterSpec = new IvParameterSpec(iv);
//		}
//		SecretKeySpec sm4Key = new SecretKeySpec(key, "SM4");
//		if (ivParameterSpec == null) {
//			sm4EcbPkcs7PaddingCipher.init(mode, sm4Key);
//			return sm4EcbPkcs7PaddingCipher.doFinal(input);
//		} else {
//			sm4CbcPkcs7PaddingCipher.init(mode, sm4Key, ivParameterSpec);
//			return sm4CbcPkcs7PaddingCipher.doFinal(input);
//		}
//	}
//
//	public enum SM4ModeAndPaddingEnum {
//		SM4_ECB_NoPadding("SM4/ECB/NoPadding"), SM4_ECB_PKCS5Padding(
//				"SM4/ECB/PKCS5Padding"), SM4_ECB_PKCS7Padding(
//				"SM4/ECB/PKCS7Padding"), SM4_CBC_NoPadding("SM4/CBC/NoPadding"), SM4_CBC_PKCS5Padding(
//				"SM4/CBC/PKCS5Padding"), SM4_CBC_PKCS7Padding(
//				"SM4/CBC/PKCS7Padding");
//		private String name;
//
//		SM4ModeAndPaddingEnum(String name) {
//			this.name = name;
//		}
//
//		public String getName() {
//			return name;
//		}
//	}
//
//	/**
//	 * 生成 16 位不重复的随机数，含数字+大小写
//	 * 
//	 * @return
//	 */
//
//	public static String getGUID() {
//		StringBuilder uid = new StringBuilder();
//		// 产生 16 位的强随机数
//		Random rd = new SecureRandom();
//		for (int i = 0; i < 16; i++) {
//			// 产生 0-2 的 3 位随机数
//			int type = rd.nextInt(3);
//			switch (type) {
//			case 0:
//				// 0-9 的随机数
//				uid.append(rd.nextInt(10));
//				break;
//			case 1:
//				// ASCII 在 65-90 之间为大写,获取大写随机
//				uid.append((char) (rd.nextInt(25) + 65));
//				break;
//			case 2:
//				// ASCII 在 97-122 之间为小写，获取小写随机
//				uid.append((char) (rd.nextInt(25) + 97));
//				break;
//			default:
//				break;
//			}
//		}
//		return uid.toString();
//	}
//	
//	public static void main(String[] args) throws Exception {
//		String decryptResult = GmUtil.sm4Decrypt("1eab11b87c9191171674bd5a4bea321a3af2d91b2bc1c2d94fb017e701adf3911a26481d04a2d369f3cb0990cc27c91a96f907e2d491d1d515fa233a08dae12d61b3d40b20256158ec032a9f8aa07fa5", 
//				GmUtil.secretKey, "G9I48px881uE2537");
//		System.out.println(decryptResult);	
////		String appKey = "2bec0f287c1e4ff19358954b6621d174";
////		String secretKey = "9e3a5aff229a4e239eabb795b2fa7eab";
////		Long timestamp = System.currentTimeMillis();
////		System.out.println("timestamp：" + timestamp);
////		String iv = getGUID();
////		System.out.println("iv：" + iv);
////		// 查询参数加密
////		HashMap<String, String> params = new HashMap<String, String>();
////		params.put("ztmc", "上海有限公司");
////		String dataStr = JSONObject.toJSONString(params); // 将 map 转为 json 格式字符串
////		String data = GmUtil.sm4Encrypt(dataStr, secretKey, iv); // 主参数加密
////		System.out.println("data：" + data);
////		String signParams = "appKey=" + appKey + "&secretKey=" + secretKey
////				+ "&iv=" + iv + "&timestamp=" + timestamp + "&nonce=" + "1245"
////				+ "&data=" + data;
////		String sign = GmUtil.sm3Digest(signParams);// 参数签名
////		System.out.println("sign：" + sign);
//	}
//}

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GmUtil {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sm3摘要
     */
    public static byte[] sm3Digest(byte[] input) {
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(input, 0, input.length);
        byte[] ret = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(ret, 0);
        return ret;
    }

    /**
     * sm3摘要
     */
    public static String sm3Digest(String input) {
        byte[] bytes = input.getBytes();
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(bytes, 0, bytes.length);
        byte[] ret = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(ret, 0);
        return Hex.encodeHexString(ret);
    }

    /**
     * SM4 加密 SM4/CBC/PKCS5Padding模式
     *
     * @param input 明文数据
     * @param key   密钥
     * @param iv    初始向量(ECB模式下传NULL)
     * @return
     * @throws Exception
     */
    public static byte[] sm4Encrypt(byte[] input, byte[] key, byte[] iv) throws Exception {
        return sm4(input, key, iv, Cipher.ENCRYPT_MODE);
    }

    public static String sm4Encrypt(String input, String key, String iv) {
        try {
            return Hex.encodeHexString(sm4Encrypt(input.getBytes("UTF-8"), Hex.decodeHex(key), iv == null ? null : iv.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sm4Encrypt(String input, String key, String iv, String charsetName) {
        try {
            return Hex.encodeHexString(sm4Encrypt(input.getBytes(charsetName), Hex.decodeHex(key), iv == null ? null : iv.getBytes(charsetName)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * SM4 解密
     *
     * @param input 密文数据
     * @param key   密钥
     * @param iv    初始向量(ECB模式下传NULL)
     * @return
     * @throws Exception
     */
    public static byte[] sm4Decrypt(byte[] input, byte[] key, byte[] iv) throws Exception {
        return sm4(input, key, iv, Cipher.DECRYPT_MODE);
    }

    /**
     * SM4 解密
     *
     * @param input 密文数据
     * @param key   密钥
     * @param iv    初始向量(ECB模式下传NULL)
     * @return
     * @throws Exception
     */
    public static String sm4Decrypt(String input, String key, String iv) throws Exception {
        return new String(sm4Decrypt(Hex.decodeHex(input), Hex.decodeHex(key), iv == null ? null : iv.getBytes("UTF-8")));
    }

    public static String sm4Decrypt(String input, String key, String iv, String charsetName) throws Exception {
        return new String(sm4Decrypt(Hex.decodeHex(input), Hex.decodeHex(key), iv == null ? null : iv.getBytes(charsetName)));
    }


    public static volatile List<Cipher> sm4CbcPkcs7PaddingCiphers = null;
    public static volatile List<Cipher> sm4EcbPkcs7PaddingCiphers = null;
    
	public static String appKey = "2bec0f287c1e4ff19358954b6621d174";
	public static String secretKey = "9e3a5aff229a4e239eabb795b2fa7eab";

    public static volatile int cipherIndex = 0;


    public static int cipherNumBound = 500;

    //暂时不使用
    public static ConcurrentHashMap<String, String> indexMap = new ConcurrentHashMap<String, String>();


    private static byte[] sm4(byte[] input, byte[] key, byte[] iv, int mode) throws Exception {
        byte[] data = new byte[]{};
        IvParameterSpec ivParameterSpec = null;
        if (iv != null) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        SecretKeySpec sm4Key = new SecretKeySpec(key, "SM4");

//        System.out.println("111"+Thread.currentThread().getName()+":"+(System.currentTimeMillis()-startTime));
        if (sm4EcbPkcs7PaddingCiphers == null || sm4CbcPkcs7PaddingCiphers == null) {
            try {
                synchronized (GmUtil.class) {
                    if (sm4EcbPkcs7PaddingCiphers == null || sm4CbcPkcs7PaddingCiphers == null) {
                        sm4EcbPkcs7PaddingCiphers = new ArrayList<Cipher>(cipherNumBound);
                        sm4CbcPkcs7PaddingCiphers = new ArrayList<Cipher>(cipherNumBound);
                        for (int i = 0; i < cipherNumBound; i++) {
                            Cipher sm4EcbPkcs7PaddingCipher = Cipher.getInstance(SM4ModeAndPaddingEnum.SM4_ECB_PKCS7Padding.getName(), BouncyCastleProvider.PROVIDER_NAME);
                            sm4EcbPkcs7PaddingCiphers.add(sm4EcbPkcs7PaddingCipher);

                            Cipher sm4CbcPkcs7PaddingCipher = Cipher.getInstance(SM4ModeAndPaddingEnum.SM4_CBC_PKCS7Padding.getName(), BouncyCastleProvider.PROVIDER_NAME);
                            sm4CbcPkcs7PaddingCiphers.add(sm4CbcPkcs7PaddingCipher);
                        }
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }

        if (ivParameterSpec == null) {
            int index = 0;
            synchronized (GmUtil.class) {
                ++cipherIndex;
                if (cipherIndex >= cipherNumBound) {
                    cipherIndex = 0;
                }
                index = cipherIndex;
            }
            Cipher sm4EcbPkcs7PaddingCipher = sm4EcbPkcs7PaddingCiphers.get(index);
            synchronized (sm4EcbPkcs7PaddingCipher) {
                sm4EcbPkcs7PaddingCipher.init(mode, sm4Key);
                data = sm4EcbPkcs7PaddingCipher.doFinal(input);
            }
        } else {
            int index = 0;
            data = new byte[]{};
//            System.out.println("222"+Thread.currentThread().getName()+":"+(System.currentTimeMillis()-startTime));
            synchronized (GmUtil.class) {
                ++cipherIndex;
                if (cipherIndex >= cipherNumBound) {
                    cipherIndex = 0;
                }
                index = cipherIndex;
            }
            Cipher sm4CbcPkcs7PaddingCipher = sm4CbcPkcs7PaddingCiphers.get(index);
            synchronized (sm4CbcPkcs7PaddingCipher) {
                sm4CbcPkcs7PaddingCipher.init(mode, sm4Key, ivParameterSpec);
                data = sm4CbcPkcs7PaddingCipher.doFinal(input);
            }

        }
        return data;
    }

    public enum SM4ModeAndPaddingEnum {
        SM4_ECB_NoPadding("SM4/ECB/NoPadding"),
        SM4_ECB_PKCS5Padding("SM4/ECB/PKCS5Padding"),
        SM4_ECB_PKCS7Padding("SM4/ECB/PKCS7Padding"),
        SM4_CBC_NoPadding("SM4/CBC/NoPadding"),
        SM4_CBC_PKCS5Padding("SM4/CBC/PKCS5Padding"),
        SM4_CBC_PKCS7Padding("SM4/CBC/PKCS7Padding");

        private String name;

        SM4ModeAndPaddingEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 生成16位不重复的随机数，含数字+大小写
     *
     * @return
     */
    public static String getGUID() {
        StringBuilder uid = new StringBuilder();
        //产生16位的强随机数
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            //产生0-2的3位随机数
            int type = rd.nextInt(3);
            switch (type) {
                case 0:
                    //0-9的随机数
                    uid.append(rd.nextInt(10));
                    break;
                case 1:
                    //ASCII在65-90之间为大写,获取大写随机
                    uid.append((char) (rd.nextInt(25) + 65));
                    break;
                case 2:
                    //ASCII在97-122之间为小写，获取小写随机
                    uid.append((char) (rd.nextInt(25) + 97));
                    break;
                default:
                    break;
            }
        }
        return uid.toString();
    }
    
    public static void main(String[] args) {
         Long timestamp = System.currentTimeMillis();
         System.out.println("timestamp："+timestamp);
         String iv =getGUID();
         System.out.println("iv："+iv);
         //查询参数加密
         HashMap<String,String> params = new HashMap<String,String>();
         params.put("ztmc","上海有限公司");
         String dataStr = JSONObject.toJSONString(params); //将map转为json格式字符串
         String data = GmUtil.sm4Encrypt(dataStr,secretKey,iv); //主参数加密
         System.out.println("data："+data);
         String signParams="appKey=" + appKey + "&secretKey=" + secretKey + "&iv=" + iv
                 +"&timestamp=" + timestamp + "&nonce=" + "1245"+"&data="+data;
         String sign=GmUtil.sm3Digest(signParams);//参数签名
         System.out.println("sign："+sign);
	}
}
