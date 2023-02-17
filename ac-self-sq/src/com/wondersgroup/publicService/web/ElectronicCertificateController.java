package com.wondersgroup.publicService.web;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;

import wfc.service.log.Log;

import com.wondersgroup.common.utils.Base64Util;
import com.wondersgroup.publicService.utils.ElectronicCertificateUtil;

@Controller
public class ElectronicCertificateController {
	
	/**
	 * 查询证照基本信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/electronicCertificate/getCertBaseData.do")
	public void getCertBaseData(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 用证五要素
		String orgName = req.getParameter("orgName");
		String username = req.getParameter("machineMAC");
		String itemName = req.getParameter("itemName");
		String itemCode = req.getParameter("itemCode");
		String businessCode = req.getParameter("businessCode");
		if(StringUtils.isNotEmpty(orgName)){
			orgName = URLDecoder.decode(orgName, "utf-8");
		}
		if(StringUtils.isNotEmpty(itemName)){
			itemName = URLDecoder.decode(itemName, "utf-8");
		}

		String holderCode = req.getParameter("holderCode");
		// 0-居民身份证;1-统一社会信用代码
		String type = req.getParameter("type");
		String catMainCode = req.getParameter("catMainCode");

		String sessionId = ElectronicCertificateUtil.getSessionId();
		if (StringUtils.isEmpty(sessionId)) {
			Log.debug("证照库登录失败，重新登录一次");
			sessionId = ElectronicCertificateUtil.getSessionId();
		}
		String certInfo = ElectronicCertificateUtil.getCertInfo(type,
				holderCode, catMainCode, sessionId, orgName, username,
				itemName, itemCode, businessCode);
		AciJsonHelper.writeJsonPResponse(req, res, certInfo);
	}
	
	/**
	 * 证照缩略图(材料预览)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/electronicCertificate/preview.do")
	public void preview(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 用证五要素
		String orgName = req.getParameter("orgName");
		String username = req.getParameter("machineMAC");
		String itemName = req.getParameter("itemName");
		String itemCode = req.getParameter("itemCode");
		String businessCode = req.getParameter("businessCode");
		if (StringUtils.isNotEmpty(orgName)) {
			orgName = URLDecoder.decode(orgName, "utf-8");
		}
		if (StringUtils.isNotEmpty(itemName)) {
			itemName = URLDecoder.decode(itemName, "utf-8");
		}

		String certUuid = req.getParameter("certUuid");
		if (StringUtils.isNotEmpty(certUuid)) {
			certUuid = URLDecoder.decode(certUuid, "utf-8");
		}
		String sessionId = ElectronicCertificateUtil.getSessionId();
		if (StringUtils.isEmpty(sessionId)) {
			Log.debug("证照库登录失败，重新登录一次");
			sessionId = ElectronicCertificateUtil.getSessionId();
		}
		byte[] byts = ElectronicCertificateUtil.showPic(certUuid, sessionId,
				orgName, username, itemName, itemCode, businessCode);
		String file = Base64Util.encode(byts);
		AciJsonHelper.writeJsonPResponse(req, res, file);
	}
	
	/**
	 * 获取证照派生照文件(材料上传)
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/electronicCertificate/download.do")
	public void download(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 用证五要素
		String orgName = req.getParameter("orgName");
		String username = req.getParameter("machineMAC");
		String itemName = req.getParameter("itemName");
		String itemCode = req.getParameter("itemCode");
		String businessCode = req.getParameter("businessCode");
		if (StringUtils.isNotEmpty(orgName)) {
			orgName = URLDecoder.decode(orgName, "utf-8");
		}
		if (StringUtils.isNotEmpty(itemName)) {
			itemName = URLDecoder.decode(itemName, "utf-8");
		}

		String certUuid = req.getParameter("certUuid");
		String cause = req.getParameter("cause");
		String startDay = req.getParameter("startDay");
		String endDay = req.getParameter("endDay");
		if (StringUtils.isNotEmpty(certUuid)) {
			certUuid = URLDecoder.decode(certUuid, "utf-8");
		}
		if (StringUtils.isNotEmpty(cause)) {
			cause = URLDecoder.decode(cause, "utf-8");
		} else {
			cause = "自助终端办事服务";
		}
		String sessionId = ElectronicCertificateUtil.getSessionId();
		if (StringUtils.isEmpty(sessionId)) {
			Log.debug("证照库登录失败，重新登录一次");
			sessionId = ElectronicCertificateUtil.getSessionId();
		}
		// 获取派生找ID
		String deriveUuid = ElectronicCertificateUtil.deriveCert(sessionId,
				certUuid, cause, startDay, endDay, orgName, username, itemName,
				itemCode, businessCode);
		String file = "";
		if (StringUtils.isEmpty(deriveUuid))
			throw new RuntimeException("派生照获取失败！");
		// 获取派生照文件
		byte[] byts = ElectronicCertificateUtil
				.getDeriveCertFile(sessionId, deriveUuid, orgName, username,
						itemName, itemCode, businessCode);
		file = Base64Util.encode(byts);
		AciJsonHelper.writeJsonPResponse(req, res, file);
	}
}
