package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.utils.AciJsonHelper;
import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.AppApplyInfo;
import com.wondersgroup.selfapi.bean.ApplyDetailInfo;
import com.wondersgroup.selfapi.bean.NameAndRegex;
import com.wondersgroup.selfapi.service.EventQueryService;
import com.wondersgroup.selfapi.util.LGQueryUtil;

/**
 * 进度查询（办件查询）
 * 
 * @author lenovo
 * 
 */
@RestController
public class ProgressQueryController {

	@Autowired
	private EventQueryService eventQueryService;

	/**
	 * 方法描述：根据办件id返回办件信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/progressquery/applyInfo")
	public void applyInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取接口需要参数
		String stApplyId = req.getParameter("stApplyId");
		String result = "";
		try {
			AppApplyInfo info = eventQueryService
					.getWorkApplyInfoById(stApplyId);
			if (info != null) {
				JSONObject.fromObject(info).toString();
			} else {
				result = "0";
				// 临港定制化查询开发
				if ("lingang".equals(Config.get("server.SmsService.module"))) {
					info = LGQueryUtil.getQueryInfo("0", stApplyId);
					if (info != null) {
						result = JSONObject.fromObject(info).toString();
					}
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 通过身份证号码查询办件列表，返回JSON 方法描述：
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/progressquery/applyList")
	public void applyList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取接口参数
		String stIdCard = req.getParameter("stIdCard");
		String result = "";
		try {
			// 自助终端刷身份证查询
			List<AppApplyInfo> list = eventQueryService
					.getApplyInfoByIdCard(stIdCard);
			if (list != null && list.size() > 0) {
				JSONArray json = JSONArray.fromObject(list);
				result = json.toString();
			} else {
				result = "0";
				// 临港定制化查询开发
				if ("lingang".equals(Config.get("server.SmsService.module"))) {
					list = LGQueryUtil.getApplyInfoByCertNo(stIdCard);
					if (list != null && list.size() > 0) {
						JSONArray json = JSONArray.fromObject(list);
						result = json.toString();
					} else {
						result = "0";
					}
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：通过身份证号码查询办件列表返回JSON(分页查询)
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/progressquery/applyPageList")
	public void applyPageList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		// 获取接口需要参数
		String stIdCard = req.getParameter("stIdCard");
		String page = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		// 如果没有分页查询，默认第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (!StringUtils.isBlank(page)) {
			pageSize = Integer.valueOf(page);
		}
		if (!StringUtils.isBlank(current)) {
			currentPage = Integer.valueOf(current);
		}

		String result = "";
		try {
			// 自助终端刷身份证查询
			Log.debug("--" + pageSize + "--" + currentPage);
			List<AppApplyInfo> list = eventQueryService.getApplyInfoByIdCard(
					pageSize, currentPage, stIdCard);
			if (list != null && list.size() > 0) {
				JSONArray json = JSONArray.fromObject(list);
				result = json.toString();
			} else {
				result = "0";
				// 临港定制化查询开发
				if ("lingang".equals(Config.get("server.SmsService.module"))) {
					list = LGQueryUtil.getApplyInfoByCertNo(stIdCard);
					if (list != null && list.size() > 0) {
						JSONArray json = JSONArray.fromObject(list);
						result = json.toString();
					} else {
						result = "0";
					}
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：根据办件编号查询企业名称或个人姓名，返回之中某一个字符为缺失汉字
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/progressquery/regexName")
	public void regexName(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取接口需要参数
		String stApplyNo = req.getParameter("stApplyNo");
		String result = "";
		try {
			NameAndRegex info = eventQueryService
					.getStringsByStApplyNo(stApplyNo);
			if (info != null) {
				result = JSONObject.fromObject(info).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：通过办件编号查询办件的详细信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/progressquery/applyInfoDetail")
	public void applyInfoDetail(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stApplyNo = req.getParameter("stApplyNo");
		String result = "";
		try {
			ApplyDetailInfo info = eventQueryService
					.getApplyDetailInfoByNo(stApplyNo);
			if (info != null) {
				result = JSONObject.fromObject(info).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

}
