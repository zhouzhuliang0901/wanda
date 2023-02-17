package com.wondersgroup.selfapi.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.utils.AciJsonHelper;

import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.service.GuidanceService;
import com.wondersgroup.selfapi.util.NewZzsbUtils;

/**
 * 办事指南
 * 
 * @author lenovo
 * 
 */
@RestController
public class GuidanceController {

	@Autowired
	private GuidanceService guidanceService;

	/**
	 * 方法描述：获取可自助申报的所有部门列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/guidance/getOrganListForDeclarePage")
	public void getOrganListForDeclarePage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询默认第一页显示所有的办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			currentPage = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		OrganNodeInfoPage page = guidanceService.getOrganListForDeclarePage(
				pageSize, currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 方法描述：获取可自助申报的所有事项列表，若数据过多可分页返回，每页8条数据
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/guidance/getAllItemListForPage")
	public void getAllItemListForPage(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String currentPageStr = req.getParameter("currentPage");
		// 如果没有分页查询，默认第一页显示所有办件列表信息
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (StringUtils.isNotBlank(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		if (StringUtils.isNotBlank(currentPageStr)) {
			currentPage = Integer.valueOf(currentPageStr);
		}
		ItemSetPage page = guidanceService.getAllItemListForPage(pageSize,
				currentPage);
		AciJsonHelper.writeJsonPResponse(req, res, JSONObject.fromObject(page)
				.toString());
	}

	/**
	 * 方法描述：根据事项编码获取事项的材料列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/guidance/getItemStuffList")
	public void getItemStuffList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String itemCode = req.getParameter("itemCode");
		// String result = guidanceService.getItemStuffList(itemCode);
		String str = NewZzsbUtils.getTocken("", "");
		String access_token = com.alibaba.fastjson.JSONObject.parseObject(str)
				.getString("access_token");
		com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
		obj.put("accessToken", access_token);
		obj.put("itemCode", itemCode);
		String result = NewZzsbUtils.getItemStuffList(obj.toJSONString());
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：获取某事项的办事指南(得到WindowZhallItemDetailExt)
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/selfapi/guidance/getGuideInfoByZhallId")
	public void getGuideInfoByItemNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stZhallId = req.getParameter("stZhallId");
		WindowZhallItemDetailExt guide = guidanceService
				.getGuideInfoByZhallIdExt(stZhallId);
		JSONObject obj = new JSONObject();
		obj.put("guide", JSONObject.fromObject(guide));
		obj.put("itemName",
				guide.getStZhallId() == null ? "" : guide.getStZhallId());
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/

	@RequestMapping("/selfapi/guidance/getGuideInfoByZhallId")
	public void getGuideInfoByItemNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String stZhallId = req.getParameter("stZhallId");
		String guide = guidanceService
				.getGuideInfoByZhallIdExt(stZhallId);
		AciJsonHelper.writeJsonPResponse(req, res, guide);
	}
}
