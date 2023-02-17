package com.wondersgroup.selfapi.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import wfc.service.config.Config;

public class RSAUtils {

    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";

    public static Map<String, String> createKeys(int keySize){
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try{
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        }catch(NoSuchAlgorithmException e){
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 得到公钥
     * @param publicKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 得到私钥
     * @param privateKey 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String publicEncrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥解密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateDecrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     */

    public static String privateEncrypt(String data, RSAPrivateKey privateKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        }catch(Exception e){
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密
     * @param data
     * @param publicKey
     * @return
     */

    public static String publicDecrypt(String data, RSAPublicKey publicKey){
        try{
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        }catch(Exception e){
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize){
        int maxBlock = 0;
        if(opmode == Cipher.DECRYPT_MODE){
            maxBlock = keySize / 8;
        }else{
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try{
            while(datas.length > offSet){
                if(datas.length-offSet > maxBlock){
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                }else{
                    buff = cipher.doFinal(datas, offSet, datas.length-offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        }catch(Exception e){
            throw new RuntimeException("加解密阀值为["+maxBlock+"]的数据时发生异常", e);
        }
        byte[] resultDatas = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultDatas;
    }
 
    
    public static void main (String[] args) throws Exception {
        //String  publicKey = Config.get("wfc.ywtb.public.key");
        String privateKey = Config.get("wfc.ywtb.private.key");
        //System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        String str = "{\"departCode\":\"SHJWJD-A09\",\"userId\":\"\"," +
        		"\"info\":{\"username\":\"张三\",\"licenseNo\":\"340881196525654845\",\"CONTACT_NAME\":\"\",\"applyReason\":\"\",\"LEGAL_PERS_NAME\":\"\"," +
        		"\"targetName\":\"万达信息\",\"targetNo\":\"91310118755702887Q\",\"targetType\":{\"name\":\"法人\",\"value\":\"b\"},\"licenseType\":{\"name\":\"身份证\",\"value\":\"a\"},\"mobile\":\"13545161135\"},\"itemCode\":\"0141507600-02\"}";
//        System.out.println("\r明文：\r\n" + str);
//        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDd_JcooEXGgBMG-DTHr0V-wQEurcN2FltkImHp1CtIXT3ZYoYJ4zk8M8UkWZWt7oRXKkgnERV_8qJRmNTntqtDcirDCz5j0p6J_QPSuKqasdOIJ3a7-6RI-ur6Ospokcl6-upsW9-c4QxgiinKVHdUkaro1obwTe0fWkaPLThFQQIDAQAB"));
        System.out.println("密文：\r\n" + encodedData);
        //CnvbZf9nRzdJdmcz-HZwpmr6pb63rWy5R9MyKb-mFcRon6XzdAyl31rE0kc3f7Mcyv0UEjBe3M7TnL7q8ZmOCuoM-BAVN8ZHJELkboD4IpVuWdySn3Bz_Ztw4K-x8M9AS1p07o4FQ53fXq0ycHLXCJWNlW63D0INABns9q9osk0OKGGHBrmVEsVg6Jdm9PUJ-21ZCIU-gpolyJxQqAa0qtrBi-c5coMR5WUIchxEATvqwEbwKFLizaqVRYOJF5PZy7pxmZR__jMfEXsfYpP-MGMOdvonAFUZW2qm_BzA__tW7qZqr-ZzAuYu4n5djz9b6Fqq7Q_4_Xbcm8XjGU_Ic0JIqV-EX1ycrv5SRS3Pw9KKQb66XIKEs9Rc8HhtDxSNGj1z2m55LDjLOb3N24po2SNYC-dgYX3GJjOeAZJB_tXrkbgSW2mOpHk6tUqQe4YiuTchDIgAYzMk4TKFegyhwyjonveKkEQu3lTLFIAmOLUX2IcimUz_jRjdwc3MnWfqwgCl7TwUI7-Rd0wbvvHVqTQ41Mwr_-Ywu2_6VoqPENa9hoqoW7qOYDqaX-fyPlnWrDnTjNvp2zl7MvGu5dskx-sxFpHAYug2PCVFCoxEYC-3Ml2sR2ItdNzHY2EYIvYyYk6rK1fyl6PNZ-CTTF1wuWeQ77W0kA2pzuaL8xdXDzA
        //String encodedData="CnvbZf9nRzdJdmcz-HZwpmr6pb63rWy5R9MyKb-mFcRon6XzdAyl31rE0kc3f7Mcyv0UEjBe3M7TnL7q8ZmOCuoM-BAVN8ZHJELkboD4IpVuWdySn3Bz_Ztw4K-x8M9AS1p07o4FQ53fXq0ycHLXCJWNlW63D0INABns9q9osk0OKGGHBrmVEsVg6Jdm9PUJ-21ZCIU-gpolyJxQqAa0qtrBi-c5coMR5WUIchxEATvqwEbwKFLizaqVRYOJF5PZy7pxmZR__jMfEXsfYpP-MGMOdvonAFUZW2qm_BzA__tW7qZqr-ZzAuYu4n5djz9b6Fqq7Q_4_Xbcm8XjGU_Ic0JIqV-EX1ycrv5SRS3Pw9KKQb66XIKEs9Rc8HhtDxSNGj1z2m55LDjLOb3N24po2SNYC-dgYX3GJjOeAZJB_tXrkbgSW2mOpHk6tUqQe4YiuTchDIgAYzMk4TKFegyhwyjonveKkEQu3lTLFIAmOLUX2IcimUz_jRjdwc3MnWfqwgCl7TwUI7-Rd0wbvvHVqTQ41Mwr_-Ywu2_6VoqPENa9hoqoW7qOYDqaX-fyPlnWrDnTjNvp2zl7MvGu5dskx-sxFpHAYug2PCVFCoxEYC-3Ml2sR2ItdNzHY2EYIvYyYk6rK1fyl6PNZ-CTTF1wuWeQ77W0kA2pzuaL8xdXDzA";
        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);

    	/*String info="{\"FEMALENAME\":\"summer\",\"HJXXDZ\":\"xxx弄xx号\",\"targetName\":\"\",\"licenseNo\":\"610523199202038899\",\"JZXXDZ\":\"xxx弄xx号\",\"HKSZD\":\"\",\"NXDZ\":\"上海市浦东新区XXXXX\",\"JZDZ\":\"上海市浦东新区\",\"ST_nationality\":\"{\\\"name\\\":\\\"中国\\\",\\\"value\\\":\\\"156\\\"}\",\"eformCode\":\"312090156000\",\"SEX\":\"{\\\"name\\\":\\\"男\\\",\\\"value\\\":\\\"1\\\"}\",\"MALENAME\":\"杨\",\"CDRSFZ\":\"610523199202038899\",\"licenseType\":\"{\\\"name\\\":\\\"居民身份证\\\",\\\"value\\\":\\\"1\\\"}\",\"CONTACT_NUMBER\":\"\",\"BCXS\":\"测试\",\"CDYT\":\"{\\\"name\\\":\\\"经济适用房\\\",\\\"value\\\":\\\"6\\\"}\",\"mobile\":\"15000493526\",\"CDDZ\":\"{\\\"name\\\":\\\"浦东新区档案馆\\\",\\\"value\\\":\\\"pudong\\\"}\",\"FEMALECSRQ\":\"{value=1992-12-05, unix=723484800000}\",\"MZ\":\"{\\\"name\\\":\\\"汉族\\\",\\\"value\\\":\\\"1\\\"}\",\"CSRQ\":\"{value=1992-02-03, unix=697046400000}\",\"MALECSRQ\":\"{value=1992-02-03, unix=697046400000}\",\"HJDZ\":\"上海市浦东新区\",\"LYFS\":\"{\\\"name\\\":\\\"利用档案证明\\\",\\\"value\\\":\\\"利用证明\\\"}\",\"MARRIEDDATE\":\"{value=2019-02-14, unix=1550073600000}\",\"username\":\"杨\"}";
    	System.out.println(processInfo(info));*/
        
    }
    
}