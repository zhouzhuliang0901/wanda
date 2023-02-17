package com.wondersgroup.common.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import net.sf.json.JSONObject;

public class HuidaoUtil {

	private static final String BASE_URL = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));

	private static final String APP_ID = RdConfig.get("reindeer.huidao.appId."+RdConfig.get("reindeer.huidao.environment"));
	
	private static final String UC_APPID = RdConfig.get("reindeer.huidao.ucappid."+RdConfig.get("reindeer.huidao.environment"));

	private static final String UC_PID_CERT = RdConfig.get("reindeer.huidao.ucpidcert."+RdConfig.get("reindeer.huidao.environment"));
	
//	private static HuidaoUtil thisInstance = null;
//	private static CloseableHttpClient closeableHttpClient = null;
//
//	private HuidaoUtil() {
//		closeableHttpClient = PooledHttpUitl.getCloseableHttp(1000, 500);
//	}
//
//	public static HuidaoUtil getInstance() {
//		if (thisInstance == null) {
//			thisInstance = new HuidaoUtil();
//		}
//		return thisInstance;
//	}
	
	/**
	 * 用户注册接口
	 * 
	 * @author tangjunlin
	 * @param codeSNO
	 * @param mobile
	 * @param authCode
	 * @param idCard
	 * @param name
	 * @param password
	 * @param type
	 * @return
	 * @throws Exception
	 *             2019年10月22日
	 */
	public static JSONObject registerUser(String codeSNO, String mobile,
			String authCode, String idCard, String name, String password,
			String type, String certStartTime, String certEndTime) {
		HttpPost httpPost = new HttpPost(BASE_URL);
		String apiName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			apiName = "115eb40b-b11d-4da5-9c44-ab12a4128fcf";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			apiName = "115eb40b-b11d-4da5-9c44-ab12a4128fcf";
		}
		String signature = HttpUtil.getSignature(apiName);
		httpPost.addHeader("appid", APP_ID);
		httpPost.addHeader("apiname", apiName);
		httpPost.addHeader("signature", signature);

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("uc_appId", UC_APPID));

		JSONObject jso = new JSONObject();
		jso.put("authCode", authCode);
		jso.put("codeSNO", codeSNO);
		jso.put("mobile", mobile);
		jso.put("idCard", idCard);
		jso.put("name", name);
		jso.put("password", password);
		jso.put("certificateType", 1);
		jso.put("registerSource", 24);
		jso.put("authType", 3);
		jso.put("certStartTime", certStartTime);
		jso.put("certEndTime", certEndTime);
		System.out.println("用户注册params参数明文数据：" + jso.toString());
		String params = encrypt(jso.toString(), UC_PID_CERT);
		formParams.add(new BasicNameValuePair("params", params));
		CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse httpResponse = null;
		try {
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
					formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
//			if (closeableHttpClient == null) {
//				if (BASE_URL.startsWith("https://")) {
//					closeableHttpClient = HttpUtil.getHttpsClient();
//				} else {
//					closeableHttpClient = HttpClients.createDefault();
//				}
//			}
			httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String body = "";
			if (httpEntity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(httpEntity, "utf-8");
			}
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				System.out.println("调用注册接口成功:" + body);
				JSONObject json = JSONObject.fromObject(body);
				Boolean encrypted = json.getBoolean("encrypted");
				if (encrypted) {// 注册成功
					String bizResponse = json.getString("biz_response");
					bizResponse = decrypt(bizResponse, UC_PID_CERT);
					System.out.println("注册成功通过解密后的json为:" + bizResponse);
					json = JSONObject.fromObject(bizResponse);
					return json;
				} else {// 注册失败
					JSONObject jsonObject = json.getJSONObject("biz_response");
					System.out.println("错误信息为："
							+ URLDecoder.decode(jsonObject.getString("msg"),
									"UTF-8"));
					return jsonObject;
				}
			} else {
				System.out.println("注册失败：" + statusLine.getStatusCode() + ","
						+ statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.debug("用户注册接口请求异常：" + e);
		} finally {
			try {
				if (httpResponse != null) {
					httpPost.releaseConnection();
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JSONObject();
	}

	/**
	 * 发送短信
	 * 
	 * @author tangjunlin
	 * @param phone
	 * @param codeSNO
	 * @return
	 * @throws Exception
	 *             2019年10月22日
	 */
	public static boolean sendMessage(String phone, String codeSNO){
		HttpPost httpPost = new HttpPost(BASE_URL);
		String apiName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "d4632306-2b4b-4ed1-b149-874f497cccc9";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "d4632306-2b4b-4ed1-b149-874f497cccc9";
		}
		String signature = HttpUtil.getSignature(apiName);
		httpPost.addHeader("appid", APP_ID);
		httpPost.addHeader("apiname", apiName);
		httpPost.addHeader("signature", signature);

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("uc_appId", UC_APPID));

		JSONObject jso = new JSONObject();
		jso.put("codeSNO", codeSNO);
		jso.put("mobile", phone);
		System.out.println("用户注册验证短信发送接口params参数明文数据："+jso.toString());
		String params = encrypt(jso.toString(), UC_PID_CERT);
		formParams.add(new BasicNameValuePair("params", params));
		CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse httpResponse = null;
		try{
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
//			if(closeableHttpClient == null){
//        		if(BASE_URL.startsWith("https://")){
//        			closeableHttpClient= HttpUtil.getHttpsClient();
//	    		} else {
//	    			closeableHttpClient = HttpClients.createDefault();
//	    		}
//        	}
        	httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String body = "";
            if (httpEntity != null) {
                // 按指定编码转换结果实体为String类型
            	body = EntityUtils.toString(httpEntity, "utf-8");
            }
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				body = new String(Base64.decodeBase64((body.getBytes())));
				System.out.println("调用发送短信接口成功:" + body);
				JSONObject json = JSONObject.fromObject(body);
				Boolean success = json.getBoolean("success");
				System.out.println("msg:" + URLDecoder.decode(json.getString("msg"), "UTF-8"));
				if (success) {// 发送成功
					return true;
				} else {// 发送未成功
					return false;
				}
			} else {
				System.out.println("发送短信失败：" + statusLine.getStatusCode() + "," + statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.debug("发送短信接口请求异常："+e);
		} finally {
			try {
				if(httpResponse != null){
					httpPost.releaseConnection();
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 一网通办ca两证对比接口
	 * 
	 * @author tangjunlin
	 * 
	 * @return
	 * @throws Exception
	 *             2019年10月19日
	 */
	public static JSONObject compare2photoLoginGetTokenSNO(String name, String idCard, String facePhoto, String CopyIDPhoto, String certStartTime, String certEndTime){
		HttpPost httpPost = new HttpPost(BASE_URL);
		String apiName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "61144d19-bed9-4e64-a631-e2166ec1bff2";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "61144d19-bed9-4e64-a631-e2166ec1bff2";
		}
		String signature = HttpUtil.getSignature(apiName);
		httpPost.addHeader("appid", APP_ID);
		httpPost.addHeader("apiname", apiName);
		httpPost.addHeader("signature", signature);

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("uc_appId", UC_APPID));
		
		JSONObject jso = new JSONObject();
		jso.put("name", name);
		jso.put("id_card", idCard);
		jso.put("face_photo", facePhoto);
		jso.put("certStartTime", certStartTime);
		jso.put("certEndTime", certEndTime);
		jso.put("transaction_id", UUID.randomUUID().toString());
		jso.put("copyId_photo", CopyIDPhoto);
		jso.put("use_place", 24);
		String params = encrypt(jso.toString(), UC_PID_CERT);
		formParams.add(new BasicNameValuePair("params", params));
		CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse httpResponse = null;
		try{
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
//			if(closeableHttpClient == null){
//        		if(BASE_URL.startsWith("https://")){
//        			closeableHttpClient= HttpUtil.getHttpsClient();
//	    		} else {
//	    			closeableHttpClient = HttpClients.createDefault();
//	    		}
//        	}
        	httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String body = "";
            if (httpEntity != null) {
                // 按指定编码转换结果实体为String类型
            	body = EntityUtils.toString(httpEntity, "utf-8");
            }
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				JSONObject json = JSONObject.fromObject(body);
				Boolean encrypted = json.getBoolean("encrypted");
				if (encrypted) {// 认证通过
					String bizResponse = json.getString("biz_response");
					bizResponse = decrypt(bizResponse, UC_PID_CERT);
					System.out.println("调用两证对比认证登录接口成功!---->解密后的json为:" + bizResponse);
					json = JSONObject.fromObject(bizResponse);
					return json;
				} else {// 认证未通过
					return json;
				}
			} else {
				System.out.println("getTokenSNO失败：" + statusLine.getStatusCode() + "," + statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.debug("一网通办ca两证对比接口请求异常："+e);
		} finally {
			try {
				if(httpResponse != null){
					httpPost.releaseConnection();
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JSONObject();
	}
	
	public static JSONObject getTokenSNOForCorporation(String creditCode,
			String companyName, String use_type, String caCode) {
		HttpPost httpPost = new HttpPost(BASE_URL);
		String apiName = "";
		if ("test".equals(RdConfig.get("reindeer.huidao.environment"))) {
			apiName = "cca89d70-572d-11ec-bebb-a7e38ac68bb1";
		} else if ("product"
				.equals(RdConfig.get("reindeer.huidao.environment"))) {
			apiName = "65ae4810-5730-11ec-92db-0f489cca772b";
		}
		String signature = HttpUtil.getSignature(apiName);
		httpPost.addHeader("appid", APP_ID);
		httpPost.addHeader("apiname", apiName);
		httpPost.addHeader("signature", signature);

		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("uc_appId", UC_APPID));

		JSONObject jso = new JSONObject();
		jso.put("creditCode", creditCode);
		jso.put("companyName", companyName);
		jso.put("use_type", use_type);
		jso.put("caCode", caCode);
		jso.put("transaction_id", UUID.randomUUID().toString());
		String params = encrypt(jso.toString(), UC_PID_CERT);
		formParams.add(new BasicNameValuePair("params", params));
		CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse httpResponse = null;
		try{
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
//			if(closeableHttpClient == null){
//        		if(BASE_URL.startsWith("https://")){
//        			closeableHttpClient= HttpUtil.getHttpsClient();
//	    		} else {
//	    			closeableHttpClient = HttpClients.createDefault();
//	    		}
//        	}
        	httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String body = "";
            if (httpEntity != null) {
                // 按指定编码转换结果实体为String类型
            	body = EntityUtils.toString(httpEntity, "utf-8");
            }
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				JSONObject json = JSONObject.fromObject(body);
				System.out.println(body);
				Boolean encrypted = json.getBoolean("encrypted");
				if (encrypted) {// 认证通过
					String bizResponse = json.getString("biz_response");
					bizResponse = decrypt(bizResponse, UC_PID_CERT);
					System.out.println("法人登录接口成功!---->解密后的json为:" + bizResponse);
					json.put("biz_response", JSONObject.fromObject(bizResponse));
					return json;
				} else {// 认证未通过
					return json;
				}
			} else {
				System.out.println("法人登录getTokenSNO失败：" + statusLine.getStatusCode() + "," + statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.debug("法人登录接口请求异常："+e);
		} finally {
			try {
				if(httpResponse != null){
					httpPost.releaseConnection();
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JSONObject();
	}

	/**
	 * tokenSNO获取access_token
	 * 
	 * @author tangjunlin
	 * @param tokenSNO
	 * @return
	 * @throws Exception
	 *             2019年10月21日
	 */
	public static String getAccessToken(String tokenSNO) throws Exception {
		HttpPost httpPost = new HttpPost(BASE_URL);
		String apiName = "";
		if("test".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "4d97c8eb-7251-4864-ac91-c8daf3e23ca3";
		} else if("product".equals(RdConfig.get("reindeer.huidao.environment"))){
			apiName = "4d97c8eb-7251-4864-ac91-c8daf3e23ca3";
		}
		String signature = HttpUtil.getSignature(apiName);
		httpPost.addHeader("appid", APP_ID);
		httpPost.addHeader("apiname", apiName);
		httpPost.addHeader("signature", signature);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("uc_appId", UC_APPID));
		formParams.add(new BasicNameValuePair("en_type", "2"));
		JSONObject jso = new JSONObject();
		jso.put("tokenSNO", tokenSNO);
		jso.put("transaction_id", UUID.randomUUID().toString());
		System.out.println("getAccessToken参数："+jso.toString());
		String params = encrypt(jso.toString(), UC_PID_CERT);
		formParams.add(new BasicNameValuePair("params", params));
		CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
		CloseableHttpResponse httpResponse = null;
		try{
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPost.setEntity(urlEncodedFormEntity);
        	httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String body = "";
            if (httpEntity != null) {
                // 按指定编码转换结果实体为String类型
            	body = EntityUtils.toString(httpEntity, "utf-8");
            }
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				JSONObject json = JSONObject.fromObject(body);
				System.out.println("getAccessToken返回结果：" +json.toString());
				Boolean encrypted = json.getBoolean("encrypted");
				if (encrypted) {
					String bizResponse = json.getString("biz_response");
					bizResponse = URLDecoder.decode(bizResponse, "UTF-8");
					bizResponse = new String(Base64.decodeBase64((bizResponse.getBytes())),"UTF-8");
					bizResponse = URLDecoder.decode(bizResponse, "UTF-8");
					System.out.println("解密后getAccessToken返回结果："+bizResponse);
					json = JSONObject.fromObject(bizResponse);
					System.out.println("getAccessToken成功：" + json.getString("access_token"));
					return json.getString("access_token");
				} else {
					return "";
				}
			} else {
				System.out.println("getAccessToken失败：" + statusLine.getStatusCode() + "," + statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.debug("获取access_token接口请求异常："+e);
		} finally {
			try {
				if(httpResponse != null){
					httpPost.releaseConnection();
					httpResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * @param password
	 *            密钥
	 * @param content
	 *            明文字符串
	 * @return 密文
	 */
	private static String encrypt(String content, String password) {
		byte[] rawKey = getRawKey(password.getBytes());
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encypted = cipher.doFinal(content.getBytes("UTF-8"));
			return Base64.encodeBase64String(encypted);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param content
	 *            密文
	 * @param password
	 *            密钥
	 * @return 解密后的字符串
	 */
	public static String decrypt(String content, String password) {
		byte[] encrypted = Base64.decodeBase64(content);
		byte[] rawKey = getRawKey(password.getBytes());
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(rawKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decrypted = cipher.doFinal(encrypted);
			return new String(decrypted, "utf-8");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * @param seed
	 * @return 密钥数据
	 */
	private static byte[] getRawKey(byte[] seed) {
		byte[] rawKey = null;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seed);
			// AES加密数据块分组长度必须为128比特，密钥长度可以是128比特、192比特、256比特中的任意一个
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			rawKey = secretKey.getEncoded();
		} catch (NoSuchAlgorithmException e) {
		}
		return rawKey;
	}
}
