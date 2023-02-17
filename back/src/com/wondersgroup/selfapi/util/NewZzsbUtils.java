package com.wondersgroup.selfapi.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import reindeer.base.utils.HttpClientUtils;

import com.alibaba.fastjson.JSONObject;


import wfc.service.config.Config;
import wfc.service.log.Log;
import wfc.service.util.StreamHelper;

@SuppressWarnings("deprecation")
public class NewZzsbUtils {
	
	private static String urlStr = Config.get("wfc.ywtb.url") == null ? "http://ywtb.sh.gov.cn:18018/ac-product-api"
			: Config.get("wfc.ywtb.url");
	
	// product
	private static String username = "xhapply";
	private static String password = "xhapply";
	
	// test
	//private static String username = "admin";
	//private static String password = "admin";
	
	private static NewZzsbUtils thisInstance = null;
	private static HttpClient httpClient = null;

	private NewZzsbUtils() {
		httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				50000);
	}

	public static NewZzsbUtils getInstance() {
		if (thisInstance == null) {
			thisInstance = new NewZzsbUtils();
		}
		return thisInstance;
	}
	
	/** 
	 * 获取授权密钥
	 * @param userId
	 * @param pwd
	 * @return
	 */
	public static String getTocken(String userId, String pwd) {
		NewZzsbUtils.getInstance();
		String message = "";
		//HttpPost httpPost = new HttpPost("http://117.184.226.70:8022/ac-product-api" + "/oauth2/getToken"); //test
		HttpPost httpPost = new HttpPost(urlStr + "/oauth2/getToken"); // product
		System.out.println("请求地址："+ urlStr + "/oauth2/getToken");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("clientId", username));
		nvps.add(new BasicNameValuePair("clientSecret", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			// Log.debug("--" + response.getStatusLine().getStatusCode());
			System.out.println("---返回结果---"
					+ response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				// Log.debug("返回的数据结果:" + message);
				System.out.println("返回的秘钥数据结果:" + message);
			} else {
				Log.debug("请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return message;
	}
	
	/**
	 * 保存申报信息
	 * @param applyInfo
	 * @return
	 */
	public static String saveApply(String applyInfo) {
		NewZzsbUtils.getInstance();
		String str = "";
		//String url = "http://117.184.226.70:8022/ac-product-net" + "/smolder/save.do"; // test
		String url = "http://ywtb.sh.gov.cn:18018/ac-product-net" + "/smolder/save.do"; // product
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(applyInfo, "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---保存办件信息返回结果---"
					+ result.getStatusLine().getStatusCode());
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---保存办件信息s返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			Log.debug(e);
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}
	
	/**
	 * 申报提交
	 * @param submitApplyInfo
	 * @return
	 */
	public static String submitApply(String submitApplyInfo){
		NewZzsbUtils.getInstance();
		String str = "";
		//String url = "http://117.184.226.70:8022/ac-product-net" + "/smolder/submit.do"; // test
		String url = "http://ywtb.sh.gov.cn:18018/ac-product-net" + "/smolder/submit.do"; // product
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(submitApplyInfo, "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---返回结果---"
					+ result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}
	
	/**
	 * 根据事项编码获取事项的办理点信息
	 * @param access_token
	 * @param itemCodes
	 * @return
	 */
	public static String getItemAreaList(String access_token, String itemCodes) {
		XhZzsbUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("itemCodes", itemCodes);
		System.out.println("参数:" + obj.toJSONString());
		
		//String url = "http://117.184.226.70:8022/ac-product-api" + "/portal/getItemAreaList"; // test
		String url = urlStr + "/portal/getItemAreaList"; // product
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(obj.toJSONString(), "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---返回结果---"
					+ result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}
	
	/**
	 * 材料上传
	 * @param applyNo
	 * @param stuffId
	 * @param stuffCode
	 * @param stuffName
	 * @param stuffType
	 * @param stuffStatus
	 * @param fileItem
	 * @param bytes
	 * @return
	 */
	public static String uploadUcenterStuff(String applyNo, String stuffId, String stuffCode, String stuffName,
			String stuffType, String stuffStatus, FileItem fileItem,
			byte[] bytes) {
		NewZzsbUtils.getInstance();
		String result = "";
		//String url = "http://117.184.226.70:8022/ac-product-net" + "/smolder/upload.do"; // test
		String url = "http://ywtb.sh.gov.cn:18018/ac-product-net" + "/smolder/upload.do"; // product
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
				.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			multipartEntityBuilder.addPart("applyNo", new StringBody(
					RSAUtils.publicEncrypt(applyNo, RSAUtils.getPublicKey(Config.get("wfc.ywtb.public.key"))),
					ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
							"UTF-8")));
			if("2".equals(stuffStatus)){
				multipartEntityBuilder.addPart("stuffId", new StringBody(
						RSAUtils.publicEncrypt(stuffId, RSAUtils.getPublicKey(Config.get("wfc.ywtb.public.key"))),
						ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
								"UTF-8")));
			} 
//			else if("0".equals(stuffStatus)){
//				multipartEntityBuilder.addPart("stuffId", new StringBody("",
//						ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
//								"UTF-8")));
//			}
			multipartEntityBuilder.addPart("stuffCode", new StringBody(
						RSAUtils.publicEncrypt(stuffCode, RSAUtils.getPublicKey(Config.get("wfc.ywtb.public.key"))), 
						ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			// byte[] bytes =
			// getBytes("C:\\Users\\01053872-pc\\Desktop\\1.jpg");
			System.out.println("文件的大小" + bytes.length);
			InputStream is = new ByteArrayInputStream(bytes);
			multipartEntityBuilder.addBinaryBody("FileData", is,
					ContentType.MULTIPART_FORM_DATA,
					fileItem != null ? fileItem.getName() : stuffName+".png");
			// CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			org.apache.http.HttpEntity reqEntity = multipartEntityBuilder
					.build();
			httpPost.setEntity(reqEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			InputStream is2 = httpEntity.getContent();
			bytes = StreamHelper.toByteArray(is2);
			String responseString = new String(bytes, "UTF-8");
			StatusLine statusLine = httpResponse.getStatusLine();
			System.out.println("----返回结果-----" + statusLine.getStatusCode());
			if (statusLine.getStatusCode() >= 400) {
				result = responseString;
			} else {
				result = responseString;
			}
			System.out.println("-----返回的数据结果信息----" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据事项编码获取事项的材料列表
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getItemStuffList(String itemCodeInfo) {
		NewZzsbUtils.getInstance();
		String str = "";
		//String url = "http://117.184.226.70:8022/ac-product-api" + "/uapply/getItemStuffList"; // test
		String url = "http://ywtb.sh.gov.cn:18018/ac-product-api" + "/uapply/getItemStuffList"; // product
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(itemCodeInfo, "utf-8");
			se.setContentType("text/json");
			// se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
			// "application/json"));
			se.setContentEncoding("UTF-8");
			method.setEntity(se);
			HttpResponse result = httpClient.execute(method);
			System.out.println("---返回结果---"
					+ result.getStatusLine().getStatusCode());
			// url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			if (result.getStatusLine().getStatusCode() == 200) {
				/** 读取服务器返回过来的json字符串数据 **/
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---返回的json字符串---" + str);
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}
}
