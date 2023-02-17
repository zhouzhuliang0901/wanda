package com.wondersgroup.dataitem.item367103164912.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESUtil {
    
	private static final String KEY = "shjjapps";
    private static final String CODE_TYPE = "UTF-8";
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /**
     * DES加密
     * @param datasource
     * @return
     */
    public static byte[] encode(String datasource){
        try{
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            //现在，获取数据并加密
            byte[] temp = cipher.doFinal(datasource.getBytes(CODE_TYPE));
            return temp;
        }catch(Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES解密
     * @return
     */
    public static byte[] decode(String src) {
        // 创建一个DESKeySpec对象
        
		try {
			DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
	        // 创建一个密匙工厂
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        // 将DESKeySpec对象转换成SecretKey对象
	        SecretKey securekey = keyFactory.generateSecret(desKey);
	        // Cipher对象实际完成解密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        // 用密匙初始化Cipher对象
	        cipher.init(Cipher.DECRYPT_MODE, securekey);
	        // 真正开始解密操作
	        byte[] temp =  cipher.doFinal(hexStringToBytes(src));
	        return temp;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
        return null;
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
	
    public static void main(String[] args) throws Exception{
//		System.out.println(bytesToHexString(encode("1234567890")));
		System.out.println(new String(decode("26631dfc1a162dfe7ba3a2ffc18f4e19587d4bbf41c6cd7d89a340383413dafa6374c974be232df0d4c5b51b98e096d90b730fed0ea7cbeb64bff17d65ad0c86f8a15a0fe67e909b48344256bb9dfe260b50c41d67d148bd"),"utf-8"));
		//{"sfzh":"410183199503102011","sjhm":"13162705219","xm":"张世英"}
    	//{"xm":"于艳娟","sfzh":"410183199503102011","sjhm":"13399586949","hphm":"沪A00000","hpzl":"02","fdjh6":"111111"}
    	//{"xm":"于艳娟","sfzh":"410183199503102011","sjhm":"13399586949","hphm":"沪A00000","hpzl":"02","fdjh6":"111111","xh":"0088881113388888"}
		//{"xm":"于艳娟","sfzh":"410103198405080027","sjhm":"13399586949","hphm":"沪A00014","hpzl":"02","fdjh6":"111111","sjlx":"","pageNo":"1"}
		
    	
//    	long l = 1659196800000l;
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	Timestamp ts = new Timestamp(l);
//    	System.out.println(sdf.format(ts));
	}
}