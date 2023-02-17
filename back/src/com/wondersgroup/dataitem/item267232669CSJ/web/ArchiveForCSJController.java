package com.wondersgroup.dataitem.item267232669CSJ.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.dataitem.item267232669CSJ.bean.Archives;
import com.wondersgroup.dataitem.item267232669CSJ.service.ArchivesService;
import com.wondersgroup.dataitem.item382711997735.utils.CSJutil;

@Controller
public class ArchiveForCSJController {

	@Autowired
	private ArchivesService archivesService;

	/**
	 * 长三角查档申请
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/archivesForCSJ/CSJArchiveApply.do")
	public void CSJArchiveApply(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String basicParams = req.getParameter("basicParams");
		String detailParams = req.getParameter("detailParams");
		String Addressee = req.getParameter("Addressee");
		String Mailingaddress = req.getParameter("Mailingaddress");
		String yzbm = req.getParameter("yzbm");
		String itemCode = req.getParameter("itemCode");
		if (StringUtils.isNotEmpty(basicParams)) {
			basicParams = URLDecoder.decode(basicParams, "utf-8");
		}
		if (StringUtils.isNotEmpty(detailParams)) {
			detailParams = URLDecoder.decode(detailParams, "utf-8");
		}
		if (StringUtils.isNotEmpty(Addressee)) {
			Addressee = URLDecoder.decode(Addressee, "utf-8");
		}
		if (StringUtils.isNotEmpty(Mailingaddress)) {
			Mailingaddress = URLDecoder.decode(Mailingaddress, "utf-8");
		}
		try {
			JSONObject basicJson = JSONObject.fromObject(basicParams);
			JSONObject detailJson = JSONObject.fromObject(detailParams);
			basicJson.putAll(detailJson);
			// JSONObject applyNoJson = new JSONObject();
			// String accessToken = HttpApiClient2.getAccessToken("zwdtuser",
			// "zwdtuser");
			// applyNoJson.put("itemCode", "3120901600000");
			// applyNoJson.put("accessToken", accessToken);
			// String applyNo =
			// HttpApiClient2.apiResData("/portal/generateApplyNo",
			// applyNoJson);
			// JSONObject jsons = JSONObject.fromObject(applyNo);
			// String app = jsons.getString("data");
			// JSONObject jsonss = JSONObject.fromObject(app);
			// String ApplyNo = "CSJ"+jsonss.getString("applyNo");
			String ApplyNo = CSJutil.getCSJApplyNo(itemCode);
			basicJson.put("ApplyNo", ApplyNo);
			System.out.println("长三角查档参数：" + basicJson.toString());
			String str = send(basicJson.toString(), Addressee, Mailingaddress,
					yzbm);
			JSONObject json = JSONObject.fromObject(str);
			json.put("ApplyNo", ApplyNo);
			AciJsonHelper.writeJsonPResponse(req, res, json.toString());
		} catch (JSONException je) {
			Log.debug(je);
			AciJsonHelper.writeJsonPResponse(req, res,
					"Illegal JSONObject format of the parameters!");
		} catch (Exception e) {
			AciJsonHelper.writeJsonPResponse(req, res, e.getMessage());
		}
	};

	/**
	 * 长三角查档办理点信息查询
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/archivesForCSJ/getArchivesArea.do")
	public void getArchivesArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String provinceName = req.getParameter("provinceName");
		String cityId = req.getParameter("cityId");
		List<Archives> archivesList = new ArrayList<Archives>();
		if (StringUtils.isNotEmpty(provinceName)) {
			provinceName = URLDecoder.decode(provinceName, "utf-8");
			archivesList = archivesService
					.getCitiesByProvinceName(provinceName);
		} else if (StringUtils.isNotEmpty(cityId)) {
			archivesList = archivesService.getArchivesByCityId(cityId);
		}
		AciJsonHelper.writeJsonPResponse(req, res,
				JSONArray.fromObject(archivesList).toString());
	}

	private String send(String param, String Addressee, String Mailingaddress,
			String yzbm) {
		String str = "";
		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
		// String url =
		// "http://117.184.226.70:8022/ac-product-net/dangAn/zzj.do";// test
		// String url =
		// "http://10.81.16.161:7070/ac-product-net-csj/dangAn/zzj.do";//
		// product
		String url = RdConfig.get("reindeer.huidao.url."
				+ RdConfig.get("reindeer.huidao.environment"));
		Addressee = Addressee == null ? "" : Addressee;
		Mailingaddress = Mailingaddress == null ? "" : Mailingaddress;
		yzbm = yzbm == null ? "" : yzbm;
		url += "?Addressee=" + Addressee + "&Mailingaddress=" + Mailingaddress
				+ "&yzbm=" + yzbm;
		HttpPost httpPost = new HttpPost(url);

		String signature = HttpUtil
				.getSignature("499ff350-a8e1-11ec-8d2d-c77fd338b4a7");
		Map<String, String> head = HttpUtil.setHttpHeard(signature,
				"499ff350-a8e1-11ec-8d2d-c77fd338b4a7");
		for (Map.Entry<String, String> entry : head.entrySet()) {
			System.out.println(entry.getKey() + "：" + entry.getValue());
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		httpPost.setHeader("Content-type", "application/json;charset=utf-8");
		// 设置参数到请求对象中
		StringEntity se = new StringEntity(param, "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json;charset=utf-8"));
		httpPost.setEntity(se);
		CloseableHttpResponse response;
		try {
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				str = EntityUtils.toString(entity, "utf-8");
			}
			response.close();
		} catch (Exception e) {
			Log.debug("访问失败");
			Log.debug(e);
			str = e.getMessage();
		}
		System.out.println("长三角查档申请返回：" + str);
		return str;
	}
	
	public static void main(String[] args) {
		JSONObject o1 = new JSONObject();
		JSONObject o2 = new JSONObject();
		JSONObject o3 = new JSONObject();
		JSONObject o4 = new JSONObject();
		o1.put("aaa", "111");
		o1.put("bbb", "222");
		o2.put("ccc", "333");
		o3.put("o3", o1);
		o4.put("o4", o2);
		o3.putAll(o4);
		System.out.println(o3.toString());
	}
}
