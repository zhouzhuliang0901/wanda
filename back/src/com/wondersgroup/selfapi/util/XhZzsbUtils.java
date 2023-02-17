package com.wondersgroup.selfapi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import wfc.service.config.Config;
import wfc.service.log.Log;
import wfc.service.util.StreamHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class XhZzsbUtils {
	
	private static String urlStr = Config.get("wfc.ywtb.url") == null ? "http://ywtb.sh.gov.cn:18018/ac-product-api"
			: Config.get("wfc.ywtb.url");

	// admin
	private static String username = "xhapply";

	private static String password = "xhapply";

	private static XhZzsbUtils thisInstance = null;
	private static HttpClient httpClient = null;

	
	private XhZzsbUtils() {
		httpClient = HttpClientUtils.newPooledHttpClient(200, 100);
		httpClient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				50000);
	}

	public static XhZzsbUtils getInstance() {
		if (thisInstance == null) {
			thisInstance = new XhZzsbUtils();
		}
		return thisInstance;
	}

	// 获取秘钥（通过用户名和密码）
	public static String getTocken(String userId, String pwd) {
		XhZzsbUtils.getInstance();
		String message = "";
		HttpPost httpPost = new HttpPost(urlStr + "/oauth2/getToken");
		System.out.println("请求地址："+urlStr + "/oauth2/getToken");
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
			// httpPost.releaseConnection();
		}
		return message;
	}

	/**
	 * 根据事项编码获取事项的模式
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getItemAppplyInfo(String itemCode) {
		XhZzsbUtils.getInstance();
		String str = "";
		String url = urlStr + "/uapply/getItemAppplyInfo";
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(itemCode, "utf-8");
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
	 * 保存办件信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String saveApply(String applyInfo) {
		XhZzsbUtils.getInstance();
		String str = "";
		String url = urlStr + "/uapply/saveApply";
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
			Log.debug(e);
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return str;
	}

	/**
	 * 提交申报信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String submitApply(String submitApplyInfo) {
		XhZzsbUtils.getInstance();
		String str = "";
		String url = urlStr + "/uapply/submitApply";
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
	 * 4.4 获取办件过程完整信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getApplyProcess(String access_token, String stApplyNo) {
		XhZzsbUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("applyNo", stApplyNo);
		System.out.println("参数:" + obj.toJSONString());

		String url = urlStr + "/uapply/getApplyProcess";
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
	 * 3.6.1获取办件基本信息
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getApplyBase(String access_token, String stApplyNo) {
		XhZzsbUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("applyNo", stApplyNo);
		System.out.println("参数:" + obj.toJSONString());

		String url = urlStr + "/uapply/getApplyBase";
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
	 * 根据事项编码获取事项的材料列表
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getItemStuffList(String itemCodeInfo) {
		XhZzsbUtils.getInstance();
		String str = "";
		String url = urlStr + "/uapply/getItemStuffList";
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

	/**
	 * 上传材料接口
	 * 
	 * @param access_token
	 * @return
	 */
	public static String uploadUcenterStuff(String access_token,
			String applyNo, String stuffId, String stuffCode, String stuffName,
			String stuffType, String stuffStatus, FileItem fileItem,
			byte[] bytes) {
		XhZzsbUtils.getInstance();
		String result = "";
		String url = urlStr + "/uapply/uploadStuff";
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
				.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		try {
			multipartEntityBuilder.setCharset(CharsetUtils.get("UTF-8"));
			multipartEntityBuilder.addPart("accessToken", new StringBody(
					access_token, ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart("applyNo", new StringBody(applyNo,
					ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
							"UTF-8")));
			multipartEntityBuilder.addPart("stuffId", new StringBody(stuffId,
					ContentType.create(ContentType.TEXT_PLAIN.getMimeType(),
							"UTF-8")));
			multipartEntityBuilder.addPart("stuffCode", new StringBody(
					stuffCode, ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart("stuffName", new StringBody(
			// fileItem != null ? fileItem.getName() : stuffName,
					stuffName, ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart("stuffType", new StringBody(
					stuffType, ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			multipartEntityBuilder.addPart("stuffStatus", new StringBody(
					stuffStatus, ContentType.create(ContentType.TEXT_PLAIN
							.getMimeType(), "UTF-8")));
			// byte[] bytes =
			// getBytes("C:\\Users\\01053872-pc\\Desktop\\1.jpg");
			System.out.println("文件的大小" + bytes.length);
			InputStream is = new ByteArrayInputStream(bytes);
			multipartEntityBuilder.addBinaryBody("file", is,
					ContentType.MULTIPART_FORM_DATA,
					fileItem != null ? fileItem.getName()+"."+fileItem.getContentType() : stuffName+".png");
			// CloseableHttpClient httpClient = HttpClients.createDefault();
			System.out.println("请求地址："+url);
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
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	/**
	 * 4.8 线上补充材料
	 * 
	 * @param access_token
	 * @return
	 */
	public static String corretApply(String access_token) {
		XhZzsbUtils.getInstance();
		String str = "";
		// 发送的json字符串的数据
		JSONObject obj = new JSONObject();

		JSONArray array = new JSONArray();
		JSONObject o = new JSONObject();
		o.put("stuffId", "76ad563f-0e77-46ed-ae7b-c8abeb5b0793");
		o.put("suggestion", "该材料有误，需补充材料");
		array.add(0);

		obj.put("accessToken", access_token);
		obj.put("applyNo", "13928ad3c4931185b51ca356a13a1844");
		obj.put("suggestion", access_token);
		obj.put("opDepartCode", "SHMZSH");
		obj.put("opDepartName", "上海市民政局");
		obj.put("opUserId", "229726");
		obj.put("opUsername", "李四");
		obj.put("opTime", "2018-09-09 23:31:22");
		obj.put("stuffs", array);
		System.out.println("参数:" + obj.toJSONString());

		String url = urlStr + "/uapply/corretApply";
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
	 * 根据附件主键id获取上传的办件材料
	 * 
	 * @param access_token
	 * @return
	 */
	public static byte[] downloadStuff(String downloadStuffStr) {
		XhZzsbUtils.getInstance();
		byte[] bytes = null;
		InputStream in = null;
		String str = "";
		String url = urlStr + "/uapply/downloadStuff";
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(downloadStuffStr, "utf-8");
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
				System.out.println("文件获取成功");
				HttpEntity entity = result.getEntity();
				in = entity.getContent();
				bytes = StreamHelper.toByteArray(in);
				in.close();
			} else {
				str = EntityUtils.toString(result.getEntity());
				System.out.println("---error---" + str);
			}
		} catch (Exception e) {
			System.out.println("访问接口失败");
		} finally {
			method.releaseConnection();
		}

		return bytes;
	}

	/**
	 * 删除附件信息通过附件id
	 * 
	 * @param access_token
	 * @return
	 */
	public static String deleteStuffAttach(String deleteStuffAttachInfo) {
		XhZzsbUtils.getInstance();
		String str = "";
		String url = urlStr + "/uapply/deleteStuffAttach";
		HttpPost method = new HttpPost(url);
		try {
			method.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity se = new StringEntity(deleteStuffAttachInfo, "utf-8");
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
	 * 
	 * @param access_token
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

		String url = urlStr + "/portal/getItemAreaList";
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

	public static void main(String[] args) {

		// 获取密钥
		String str = getTocken("", "");
		String access_token = JSONObject.parseObject(str).getString(
				"access_token");
		System.out.println(access_token);

		// // 保存办件信息
		// String result = saveApply(access_token);
		//
		// // 提交申报信息
		// String result1 = submitApply(access_token);
		//
		// // 获取办件过程完整信息
		// String result2 = getApplyProcess(access_token);
		//
		// 上传办件材料
		// String result3 = uploadStuff1(access_token);
		//
		// // 线上补充材料
		// String result4 = corretApply(access_token);

		// JSONObject obj = new JSONObject();
		// obj.put("accessToken", access_token);
		// obj.put("itemCode", "0045065000");
		// String result = getItemStuffList(obj.toJSONString());
		// System.out.println(result);

		String result = getItemAreaList(access_token, "01000");
		System.out.println(result);
	}

}
