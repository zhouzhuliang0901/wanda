package com.wondersgroup.wdf.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wondersgroup.wdf.dao.ExcelListener;
import com.wondersgroup.wdf.dao.UacBusiness;
import com.wondersgroup.wdf.dao.UacBusinessDao;
import com.wondersgroup.wdf.service.UacBusinessService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 企业信息表 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacBusinessController {

	@RequestMapping("/uacBusiness/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacBusiness.ST_BUSINESS_ID
		String stBusinessId = wrapper.getParameter(UacBusiness.ST_BUSINESS_ID);
		if (!StringUtils.trimToEmpty(stBusinessId).isEmpty()) {
			UacBusiness uacBusiness = uacBusinessService.get(stBusinessId);
			req.setAttribute(UacBusiness.UAC_BUSINESS, uacBusiness);
		}
		return new ModelAndView("/uacBusiness/edit.jsp");
	}

	@RequestMapping("/uacBusiness/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacBusiness.ST_BUSINESS_ID
		String stBusinessId = wrapper.getParameter(UacBusiness.ST_BUSINESS_ID);
		UacBusiness uacBusiness = uacBusinessService.get(stBusinessId);
		req.setAttribute(UacBusiness.UAC_BUSINESS, uacBusiness);
		return new ModelAndView("/uacBusiness/info.jsp");
	}

	@RequestMapping("/uacBusiness/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacBusiness> list = uacBusinessService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacBusiness.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacBusiness/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("企业信息表删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacBusiness.ST_BUSINESS_ID
			String stBusinessId = wrapper.getParameter(UacBusiness.ST_BUSINESS_ID);
			uacBusinessService.remove(stBusinessId);
			result = ExtAjaxReturnMessage.success("企业信息表删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacBusiness/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("企业信息表保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacBusiness uacBusiness = uacBusinessService.saveOrUpdate(wrapper);
			if (uacBusiness != null)
				result = ExtAjaxReturnMessage.success("企业信息表保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	/**
	 * 企业信息查询接口
	 * @param json
	 * @return
	 */
	@RequestMapping("/wdf/uacBusiness/selectBusiness")
	public WdfResult selectBusiness(@RequestBody(required = false) String json){
		WdfResult result = WdfResult.getSuccessResult();
		try {
			JSONObject jsonObject = new JSONObject(json);
			String st_business_name = jsonObject.optString("ST_BUSINESS_NAME", null);
			String st_corporation_orgid = jsonObject.optString("ST_CORPORATION_ORGID", null);
			int pageSize = jsonObject.optInt("pageSize",10);
			int currentPage = jsonObject.optInt("pageIndex",1);
			Conditions conds = Conditions.newAndConditions();
			if (st_business_name != null){
				conds.add(new Condition(UacBusiness.ST_BUSINESS_NAME,Condition.OT_LIKE,st_business_name));
			}if (st_corporation_orgid != null){
				conds.add(new Condition(UacBusiness.ST_CORPORATION_ORGID,Condition.OT_LIKE,st_corporation_orgid));
			}
			PaginationArrayList<UacBusiness> list = uacBusinessDao.query(conds, null, pageSize, currentPage);
			result.setData(JsonUtils.toJson(list,UacBusiness.class));

		}catch (Exception e){
			e.printStackTrace();
			result.failed().setMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacBusiness/importCourse")
	public List<UacBusiness> importCourse(HttpServletRequest req, HttpServletResponse res) throws IOException{
		//获取导入Excel文件名
		String url = req.getParameter("url");
		File file = new File(url);
		FileInputStream fis = new FileInputStream(file);
		MockMultipartFile multipartFile = new MockMultipartFile(file.getName(), fis);
		String filename = multipartFile.getOriginalFilename();
		if (StringUtils.isBlank(filename)) {
			Log.error("导入失败，文件名为空");
		}
		ExcelListener listener = new ExcelListener();
		try {
			EasyExcel.read(multipartFile.getInputStream(), UacBusiness.class, listener).sheet().doRead();
		} catch (IOException e) {
			Log.error("Excel写入失败");
			e.printStackTrace();
		}
		//解析数据
		List<UacBusiness> courseList = JSON.parseArray(JSON.toJSONString(listener.getDatas()), UacBusiness.class);
		JSONArray objects = JSON.parseArray(JSON.toJSONString(listener.getDatas()));
		int size = courseList.size();
		courseList.forEach(item -> {
			UacBusiness uacBusiness = new UacBusiness();
			uacBusiness.setStBusinessId(UUID.randomUUID().toString());
			uacBusiness.setStBusinessName(item.getStBusinessName());
			uacBusiness.setStCorporationOrgid(item.getStCorporationOrgid());
			uacBusiness.setStLegalName(item.getStLegalName());
			uacBusiness.setStBusinessAddress(item.getStBusinessAddress());
			uacBusiness.setStApplyUserName(item.getStApplyUserName());
			uacBusiness.setStApplyUserPhone(item.getStApplyUserPhone());
			uacBusiness.setStApplyUserIdcard(item.getStApplyUserIdcard());
			uacBusiness.setStLegalName(item.getStLegalName());
			uacBusiness.setStLegalIdcard(item.getStLegalIdcard());
			uacBusinessDao.add(uacBusiness);
		});
		return courseList;
	}


	@Autowired
	private UacBusinessService uacBusinessService;

	@Autowired
	private UacBusinessDao uacBusinessDao;

}
