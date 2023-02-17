package com.wondersgroup.selfapi.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import wfc.service.log.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.selfapi.bean.AppApplyInfo;

@SuppressWarnings("deprecation")
public class LGQueryUtil {
	
	private static HttpClient httpClient = new DefaultHttpClient();

	// 获取办件信息
	public static AppApplyInfo getQueryInfo(String type, String stApplyNo) {
		AppApplyInfo info = new AppApplyInfo();
		String message = "";
//		HttpPost httpPost = new HttpPost(
//				"http://zwdtlg.sh.gov.cn:9999/main/weixin/status?swbh="+stApplyNo);
		//List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		//nvps.add(new BasicNameValuePair("type", "0"));
		//nvps.add(new BasicNameValuePair("swbh", stApplyNo));
		
		String url = "http://zwdtlg.sh.gov.cn:9999/main/zzsb/status";
		HttpPost httpPost = new HttpPost(url+"?swbh="+stApplyNo);
		try {
			//httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				String status = JSONObject.parseObject(message).getString(
						"status");
				Log.debug("status：" + status);
				// status状态为1表示正确，为0表示出现错误，message返回错误信息或者正确返回“success”，total返回查询到的个数。
				if ("1".equals(status)) {
					// message = "0";
					JSONObject obj = JSONObject.parseObject(message)
							.getJSONArray("records").getJSONObject(0);
					info.setMobile("");
					info.setStApply("");
					info.setStApplyId(obj.getString("id"));
					info.setStApplyNo(obj.getString("PRJ_SWBH"));
					info.setStApplyStr(obj.getString("PRJ_SWDATE"));
					info.setStFinalState(obj.getString("STATUS"));
					info.setStFinish("");
					info.setStFinishStr(obj.getString("PRJ_COMPLETEDATE"));
					info.setStItemName(obj.getString("PRJ_APPLYITEM"));
					info.setStName(obj.getString("PRJ_NAME"));
					info.setStOrganName(obj.getString("PRJ_ZBBM"));
					info.setStUnit(obj.getString("PRJ_UNITNAME"));
				} else {
					return null;
				}
				//Log.debug("网厅返回的数据结果:" + message);
			} else {
				Log.debug("请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// httpPost.completed();
			httpPost.releaseConnection();
		}
		return info;
	}

	// 根據身份證信息獲取辦件信息列表
	public static List<AppApplyInfo> getApplyInfoByCertNo(String certNo) {
		List<AppApplyInfo> list = new ArrayList<AppApplyInfo>();
		String message = "";
		HttpPost httpPost = new HttpPost(
				"http://zwdtlg.sh.gov.cn/main/weixin/getStateByCardnum");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cardnum", certNo));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				message = EntityUtils.toString(entity, "utf-8");
				String status = JSONObject.parseObject(message).getString(
						"status");
				Log.debug("status：" + status);
				// status状态为1表示正确，为0表示出现错误，message返回错误信息或者正确返回“success”，total返回查询到的个数。
				if ("1".equals(status)) {
					// message = "0";
					JSONArray arrays = JSONObject.parseObject(message)
							.getJSONArray("records");
					if (arrays != null && arrays.size() > 0) {
						for (int i = 0; i < arrays.size(); i++) {
							JSONObject obj = arrays.getJSONObject(i);
							AppApplyInfo info = new AppApplyInfo();
							String stApplyNo = obj.getString("PRJ_SWBH");
							if (StringUtils.isNotBlank(stApplyNo)) {
								stApplyNo = stApplyNo.substring(
										stApplyNo.indexOf("﹝") + 1).replaceAll(
										"号", "").replaceAll("﹞", "");
								System.out.println("stApplyNo" + stApplyNo);

							}
							info.setMobile("");
							info.setStApply("");
							info.setStApplyId(stApplyNo);
							info.setStApplyNo(stApplyNo);
							info.setStApplyStr(obj.getString("PRJ_SWDATE"));
							info.setStFinalState(obj.getString("STATUS"));
							info.setStFinish("");
							info.setStFinishStr(obj
									.getString("PRJ_COMPLETEDATE"));
							info.setStItemName(obj.getString("PRJ_APPLYITEM"));
							info.setStName(obj.getString("PRJ_NAME"));
							info.setStOrganName(obj.getString("PRJ_ZBBM"));
							info.setStUnit(obj.getString("PRJ_UNITNAME"));
							list.add(info);
						}
					}
				}
				Log.debug("网厅返回的数据结果:" + message);
			} else {
				Log.debug("请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// httpPost.completed();
			httpPost.releaseConnection();
		}
		return list;
	}

	public static void main(String[] args) {
		getApplyInfoByCertNo("430426199107067253");
		// String str = LGQueryUtil.getQueryInfo("0", "201");
		// System.out.println(str);
	}

}
