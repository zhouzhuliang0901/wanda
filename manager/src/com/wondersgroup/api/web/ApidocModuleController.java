package com.wondersgroup.api.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wfc.service.log.Log;

import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.bean.ApidocModuleView;
import com.wondersgroup.api.bean.ApidocProject;
import com.wondersgroup.api.service.ApidocModuleService;
import com.wondersgroup.api.service.ApidocProjectService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 模块 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class ApidocModuleController {

	@RequestMapping("/apidoc/module/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		//获取参数
		RequestWrapper wrapper = new RequestWrapper(req);
		String draw = wrapper.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当然调用的次数:" + drawInt);
		}
		//获取数据
		List<ApidocModule> list = apidocModuleService.query(wrapper);
		//总条数
		String total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, ApidocModule.class))
					.getString("total");
		JSONObject obj = new JSONObject();
		// 调用次数
		obj.put("draw", drawInt);
		// 当前总数
		obj.put("recordsTotal", list.size());
		// 选择总条数
		obj.put("recordsFiltered", total);
		// 返回数据
		obj.put("data", list);
		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	@RequestMapping("/apidoc/module/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocModule.ST_MODULE_ID
		String stModuleId = wrapper.getParameter(ApidocModule.ST_MODULE_ID);
		if (!StringUtils.trimToEmpty(stModuleId).isEmpty()) {
			ApidocModule apidocModule = apidocModuleService.get(stModuleId);
			req.setAttribute(ApidocModule.APIDOC_MODULE, apidocModule);
		}
		return new ModelAndView("/apidoc/module/edit.jsp");
	}

	@RequestMapping("/apidoc/module/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("模块删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// ApidocModule.ST_MODULE_ID
			String[] roleIdList = wrapper.getParameterValues("stModuleId[]");
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stModuleId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("用户菜单ID不能为空");
				}
			}
			apidocModuleService.remove(roleIdList);
			result = ExtAjaxReturnMessage.success("模块删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/apidoc/module/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("模块信息保存失败。", "错误",
				null).toString();
		// 返回json数据
		JSONObject obj = new JSONObject();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			ApidocModule apidocMenu = apidocModuleService.saveOrUpdate(wrapper);
			if (apidocMenu != null)
				result = "模块信息保存成功,";
			// 用户空间下所有的目录
			obj.put("apidocMenu", apidocMenu);
			// 保存状态
			obj.put("result", result);
			EasyUIHelper.writeResponse(res,obj.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取项目列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/module/getProject.do")
	public void getProject(HttpServletRequest req,HttpServletResponse res) throws IOException{
		JSONObject obj = new JSONObject();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<ApidocProject> projectList = apidocprojectService.queryProjectList(wrapper);
			// 用户空间下所有的目录
			obj.put("projectList", projectList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res,obj.toString());
	}
	
	/**
	 * 获取模块下所有子模块及接口信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/module/getAllInfo.do")
	public void getAllInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String stModuleId = req.getParameter("stModuleId");
		JSONObject obj = new JSONObject();
		try {
			ApidocModuleView allInfo = apidocModuleService.getModuleAndInterface(stModuleId);
			// 用户空间下所有的目录
			obj.put("allInfo", allInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res,obj.toString());
	}
	
	

	@Autowired
	private ApidocModuleService apidocModuleService;
	@Autowired
	private ApidocProjectService apidocprojectService;

}
