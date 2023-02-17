package com.wondersgroup.api.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wfc.service.log.Log;

import com.wondersgroup.api.bean.ApidocInterface;
import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.service.ApidocInterfaceService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 接口 web层控制器
 * 
 * @author scalffold
 * 
 */
@Controller
public class ApidocInterfaceController {

	/**
	 * 接口列表首页（分页）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/apidoc/interface/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		// 获取参数
		RequestWrapper wrapper = new RequestWrapper(req);
		String draw = wrapper.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当然调用的次数:" + drawInt);
		}
		// 获取数据
		List<ApidocInterface> list = apidocInterfaceService.query(wrapper);
		// 总条数
		String total = EasyUIJsonConverter.convertDataSetToJson(
				DataSet.convert(list, ApidocInterface.class))
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

	/**
	 * 接口编辑
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/detail.do")
	public ModelAndView detail(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocInterface.ST_INTERFACE_ID
		String stInterfaceId = wrapper
				.getParameter(ApidocInterface.ST_INTERFACE_ID);
		// 获取数据
		ApidocInterface list = apidocInterfaceService.get(stInterfaceId);
		// 接口对象
		req.setAttribute(ApidocInterface.APIDOC_INTERFACE, list);
		// 所属模块名称
		return new ModelAndView("/apidoc/interface/detail.jsp");
	}

	/**
	 * 接口查看
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// ApidocInterface.ST_INTERFACE_ID
		String stInterfaceId = wrapper
				.getParameter(ApidocInterface.ST_INTERFACE_ID);
		// 获取数据
		List<ApidocInterface> list = apidocInterfaceService
				.queryInterfaceAndLink(stInterfaceId);
		// 接口对象
		ApidocInterface apidocInterface = list.get(0);
		// 接口所属模块名称
		String stModuleNmae = "";

		// 返回的数据多条
		if (list.size() > 1) {
			for (ApidocInterface itemSample : list) {
				// 模块名称
				stModuleNmae += itemSample.getStExt1() + ",";
			}
		} else if (list.size() == 1) {
			// 模块名称
			stModuleNmae = apidocInterface.getStExt1();
		}
		// 接口对象
		req.setAttribute(ApidocInterface.APIDOC_INTERFACE, apidocInterface);
		// 所属模块名称
		req.setAttribute("stModuleName", stModuleNmae);
		return new ModelAndView("/apidoc/interface/info.jsp");
	}

	/**
	 * 接口删除（可批量删除）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接口删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// ApidocInterface.ST_INTERFACE_ID
			String[] idList = wrapper.getParameterValues("stInterfaceId[]");
			if (idList.length == 0) {
				String id = wrapper.getParameter("stInterfaceId");
				if (id != null) {
					idList = new String[1];
					idList[0] = id;
				} else {
					throw new NullPointerException("接口ID不能为空");
				}
			}
			apidocInterfaceService.remove(idList);
			result = ExtAjaxReturnMessage.success("接口删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 接口信息添加与更新
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接口保存失败。", "错误",
				null).toString();
		try {
			ApidocInterface apidocInterface = apidocInterfaceService
					.saveOrUpdate(req);
			if (apidocInterface != null)
				result = ExtAjaxReturnMessage.success("接口保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	/**
	 * 接口文档单个预览
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/queryOne.do")
	public void queryByModuleId(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stInterfaceId = req.getParameter("stInterfaceId");
		if (!stInterfaceId.isEmpty()) {
			ApidocInterface i = apidocInterfaceService.get(stInterfaceId);
			JSONObject obj = new JSONObject();
			obj.put("interfaceInfo", i);
			EasyUIHelper.writeResponse(res, obj.toString());
		}
	}

	@RequestMapping("/apidoc/interface/hasInterface.do")
	public void has(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String stModuleId = req.getParameter("stModuleId");
		if (!stModuleId.isEmpty()) {
			List<ApidocInterface> list = apidocInterfaceService
					.queryByModuleId(stModuleId);
			JSONObject obj = new JSONObject();
			int len = list.size();
			obj.put("len", len);
			EasyUIHelper.writeResponse(res, obj.toString());
		}
	}

	@RequestMapping("/apidoc/interface/copy.do")
	public void copy(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("接口复制失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// ApidocInterface.ST_INTERFACE_ID
			String[] idList = wrapper.getParameterValues("stInterfaceId[]");
			if (idList.length == 0) {
				String id = wrapper.getParameter("stInterfaceId");
				if (id != null) {
					idList = new String[1];
					idList[0] = id;
				} else {
					throw new NullPointerException("接口ID不能为空");
				}
			}
			apidocInterfaceService.copy(idList);
			result = ExtAjaxReturnMessage.success("接口复制成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 获取模块列表
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/apidoc/interface/getModule.do")
	public void getProject(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		JSONObject obj = new JSONObject();
		try {
			// RequestWrapper wrapper = new RequestWrapper(req);
			List<ApidocModule> mlist = apidocInterfaceService.getModuleList();
			// 用户空间下所有的目录
			obj.put("module", mlist);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, obj.toString());
	}

	@Autowired
	private ApidocInterfaceService apidocInterfaceService;

}
