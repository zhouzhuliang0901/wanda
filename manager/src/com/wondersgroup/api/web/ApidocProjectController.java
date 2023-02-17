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

import com.wondersgroup.api.bean.ApidocAllInfoView;
import com.wondersgroup.api.bean.ApidocProject;
import com.wondersgroup.api.service.ApidocProjectService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 项目 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class ApidocProjectController {

	@RequestMapping("/apidoc/project/list.do")
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
		List<ApidocProject> list = apidocProjectService.query(wrapper);
		//总条数
		String total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, ApidocProject.class))
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
	
	@RequestMapping("/apidoc/project/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocProject.ST_PROJECT_ID
		String stProjectId = wrapper.getParameter(ApidocProject.ST_PROJECT_ID);
		if (!StringUtils.trimToEmpty(stProjectId).isEmpty()) {
			ApidocProject apidocProject = apidocProjectService.get(stProjectId);
			req.setAttribute(ApidocProject.APIDOC_PROJECT, apidocProject);
		}
		return new ModelAndView("/apidoc/project/edit.jsp");
	}

	@RequestMapping("/apidoc/project/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocProject.ST_PROJECT_ID
		String stProjectId = wrapper.getParameter(ApidocProject.ST_PROJECT_ID);
		ApidocProject apidocProject = apidocProjectService.get(stProjectId);
		req.setAttribute(ApidocProject.APIDOC_PROJECT, apidocProject);
		return new ModelAndView("/apidoc/project/info.jsp");
	}

	@RequestMapping("/apidoc/project/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("项目删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String[] roleIdList = wrapper.getParameterValues("stProjectId[]");
			if (roleIdList.length == 0) {
				String roleId = wrapper.getParameter("stProjectId");
				if (roleId != null) {
					roleIdList = new String[1];
					roleIdList[0] = roleId;
				} else {
					throw new NullPointerException("ID不能为空");
				}
			}
			apidocProjectService.remove(roleIdList);
			result = ExtAjaxReturnMessage.success("删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/apidoc/project/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("项目保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			ApidocProject apidocProject = apidocProjectService.saveOrUpdate(wrapper);
			if (apidocProject != null)
				result = ExtAjaxReturnMessage.success("项目保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	/**
	 * 获取项目下所有模块及接口信息
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/project/getInfo.do")
	public void getAllInfo(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String stProjectId = req.getParameter("stProjectId");
		JSONObject obj = new JSONObject();
		try {
			ApidocAllInfoView allInfo = apidocProjectService.queryByProjectId(stProjectId);
			// 用户空间下所有的目录
			obj.put("allInfo", allInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res,obj.toString());
	}
	
	@Autowired
	private ApidocProjectService apidocProjectService;

}
