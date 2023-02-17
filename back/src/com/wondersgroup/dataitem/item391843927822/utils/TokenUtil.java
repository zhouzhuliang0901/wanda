package com.wondersgroup.dataitem.item391843927822.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.selfapi.dao.SelmAuthTokenDao;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import net.sf.json.JSONObject;

@Component
public class TokenUtil {
	
	@Autowired
	private SelmAuthTokenDao selmAuthTokenDao;
	
	// 第三方标识
	private static final String ID = "wondersgroup";
	
	// 第三方名称
	private static final String NAME = "万达信息";
	
	// 密码
	private static final String PASSWORD = "jOpH9iF5QR$AEej0";
	
	// 第三方网站地址
	private static final String WEBSITE = "http://www.wondersgroup.com";
	
    // 编码
    private static final String ENCODING = "UTF-8";
    //算法
    private static final String ALGORITHM = "AES";
    // 默认的加密算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";//AES/CBC/PKCS5Padding
	
	public String getToken(){
		JSONObject obj = new JSONObject();
		obj.put("id", ID);
		obj.put("name", NAME);
		obj.put("website", WEBSITE);
		obj.put("gkey", UUID.randomUUID().toString());
		

		String validate = "";
		try {
			validate = encode(getSecret(), getIV(), obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String appName = "1e5cf6a9-87e1-4946-bd69-2bbac0bb3d99";
		
		String signature = HttpUtil.getSignature(appName);
		Map<String, String> head = HttpUtil.setHttpHeard(signature, appName);
		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"))+"?id="+ID+"&validate="+validate+"&recheck=1";
		System.out.println(url);
		String str = HttpUtil.doGet(head,url,"GET");
		str = str.replace("\"", "");
		if(StringUtils.isNotEmpty(str)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ST_AUTH_TOKEN", str);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_AUTH_API_NAME", Condition.OT_EQUAL, "foodAndBeverage"));
			selmAuthTokenDao.update(map, conds);
		}
		return str;
	}
	
	/**
	 * 偏移量IV
	 * @return
	 */
	private static byte[] getIV(){
		String iv = ID+NAME;
		try {
			byte[] byts = iv.getBytes("utf-8");
			int len = byts.length;
			byte[] copyByts = null;
			while(len < 16){
				copyByts = new byte[len*2];
				System.arraycopy(byts, 0, copyByts, 0, byts.length);
				System.arraycopy(byts, 0, copyByts, byts.length, byts.length);
				byts = copyByts;
				len = copyByts.length;
			}
			byte[] ivByts = new byte[16];
			System.arraycopy(byts, 0, ivByts, 0, 16);
			return ivByts;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	/**
	 * 密钥secret
	 * @return
	 */
	private static byte[] getSecret(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String secret = PASSWORD+WEBSITE+sdf.format(new Date());
		try {
			byte[] byts = secret.getBytes("utf-8");
			int len = byts.length;
			byte[] copyByts = null;
			while(len < 32){
				copyByts = new byte[len*2];
				System.arraycopy(byts, 0, copyByts, 0, byts.length);
				System.arraycopy(byts, 0, copyByts, byts.length, byts.length);
				byts = copyByts;
				len = copyByts.length;
			}
			byte[] secretByts = new byte[32];
			System.arraycopy(byts, 0, secretByts, 0, 32);
			return secretByts;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
    public static String encode(byte[] secretBytes, byte[] iv, String content) throws Exception {
        SecretKey secretKey = new SecretKeySpec(secretBytes, ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] contentBytes = content.getBytes(ENCODING);
        byte[] bytes = cipher.doFinal(contentBytes);
        String encode = Base64Util.encode(bytes);
        encode = URLEncoder.encode(encode, "utf-8");
        return encode;
    }
}
