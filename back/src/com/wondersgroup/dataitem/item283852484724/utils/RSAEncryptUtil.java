package com.wondersgroup.dataitem.item283852484724.utils;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;

import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * RSA加密解密
 * Created on 2019/7/8
 *
 * @author ：chenxin
 */
public class RSAEncryptUtil {

    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();

    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();

    /**
     * 生成秘钥对
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * 获取公钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 获取私钥(Base64编码)
     * @param keyPair
     * @return
     */
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * 将Base64编码后的公钥转换成PublicKey对象
     * @param pubStr
     * @return
     * @throws Exception
     */
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 将Base64编码后的私钥转换成PrivateKey对象
     * @param priStr
     * @return
     * @throws Exception
     */
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 用公钥加密信息
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 用公钥解密信息
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] publicDecrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 用私钥加密信息
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateEncrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 用私钥解密信息
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 字节数组转Base64编码
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes){
    	return BASE64_ENCODER.encodeToString(bytes);
    }

    /**
     * Base64编码转字节数组
     * @param base64Key
     * @return
     * @throws IOException 
     */
    public static byte[] base642Byte(String base64Key){
        return BASE64_DECODER.decode(base64Key);
    }

    /**
     * 用公钥加密信息 解决RSA加密 长度限制 117 bytes
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] publicEncrypt1(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLength = content.length;
        // 最大加密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 117;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(content, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    /**
     * 用私钥解密信息 解决RSA解密 长度限制 128 bytes
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] privateDecrypt1(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLength = content.length;
        // 最大加密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(content, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(content, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    public static void main(String[] args) {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            //生成RSA公钥和私钥，并Base64编码
            KeyPair keyPair = RSAEncryptUtil.getKeyPair();
            String publicKeyStr = RSAEncryptUtil.getPublicKey(keyPair);
            String privateKeyStr = RSAEncryptUtil.getPrivateKey(keyPair);
            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

//            //=================客户端=================
//            //内容信息加密
//            String message = "摩西摩西，我是老司机，你是哪位？";
//            //将Base64编码后的公钥转换成PublicKey对象
//            PublicKey publicKey = RSAEncryptUtil.string2PublicKey(publicKeyStr);
//            //用公钥加密
//            byte[] publicEncrypt = RSAEncryptUtil.publicEncrypt(message.getBytes(), publicKey);
//            //加密后的内容Base64编码
//            String byte2Base64 = RSAEncryptUtil.byte2Base64(publicEncrypt);
//            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);
//
//
//            //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################
//
//
//
//            //===================服务端================
//            //将Base64编码后的私钥转换成PrivateKey对象
//            PrivateKey privateKey = RSAEncryptUtil.string2PrivateKey(privateKeyStr);
//            //加密后的内容Base64解码
//            byte[] base642Byte = RSAEncryptUtil.base642Byte(byte2Base64);
//            //用私钥解密
//            byte[] privateDecrypt = RSAEncryptUtil.privateDecrypt(base642Byte, privateKey);
//            //解密后的明文
//            System.out.println("解密后的明文: " + new String(privateDecrypt));



            //================================
            // MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZV5BJiImpLSGbU7qJdbeJqwJBcqKIyTpO7lJyTyFYGkdIjErwrwGdRWRfZRNwNOrCDXOVg7Pa/fx3pTHxojrAlfYPu54O2RnTVhZoUlNYZqslK9W3hm64B3uraMwxFBaFdeMp8eJBdQBpA5CqRtxrv/34wVsEv35B9V82RjXYEwIDAQAB
            JSONObject jsonObject = new JSONObject(true);
            jsonObject.put("windowType", 1);
            jsonObject.put("district", "黄浦区");
            jsonObject.put("streetCode", "");
            jsonObject.put("serviceHall","黄浦区行政服务中心");
            jsonObject.put("serviceHallAddr", "黄浦区巨鹿路139号");
            jsonObject.put("manageDeptCode", "");
            jsonObject.put("windowNo", "2号楼1号综合窗口");
            jsonObject.put("jobNo", "1032号");
            jsonObject.put("staffName", "徐子琳");
            jsonObject.put("userName", "张三丰");
            jsonObject.put("mobile", "17602553003");
            jsonObject.put("userType", 1);
            jsonObject.put("projectTarget", "张三丰");
            jsonObject.put("targetLicenseNo", "310101234567890123");
            jsonObject.put("itemCode", "310150712000");
            jsonObject.put("itemName", "食品生产许可证核发（特殊食品除外）");
            jsonObject.put("serialNo", "2019082800107");
            jsonObject.put("projectNo", "");
            jsonObject.put("deptName", "黄浦区市场监督管理局");
            jsonObject.put("shDeptCode", "SHGSSH");
            jsonObject.put("bizType", 1);
            jsonObject.put("appraiseType", "A003");
            jsonObject.put("totalValue", 5.0);
            jsonObject.put("totalScoreCodes", "501,502,503");

            System.out.println(jsonObject);
            //=================客户端=================
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = RSAEncryptUtil.string2PublicKey(publicKeyStr);
            //用公钥加密
            byte[] publicEncrypt = RSAEncryptUtil.publicEncrypt1(jsonObject.toString().getBytes("UTF-8"), publicKey);
            //加密后的内容Base64编码
            String byte2Base64 = RSAEncryptUtil.byte2Base64(publicEncrypt);
            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

            //将Base64编码后的私钥转换成PrivateKey对象
            PrivateKey privateKey = RSAEncryptUtil.string2PrivateKey(privateKeyStr);
            //加密后的内容Base64解码
            byte[] base642Byte = RSAEncryptUtil.base642Byte(byte2Base64);
            //用私钥解密
            byte[] privateDecrypt = RSAEncryptUtil.privateDecrypt1(base642Byte, privateKey);
            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
