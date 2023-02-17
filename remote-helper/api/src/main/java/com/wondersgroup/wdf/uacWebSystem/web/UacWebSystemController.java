package com.wondersgroup.wdf.uacWebSystem.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystem;
import com.wondersgroup.wdf.uacWebSystem.service.UacWebSystemService;
import coral.base.util.RequestWrapper;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 系统信息 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacWebSystemController {

//	@RequestMapping("/wdf/uacWebSystem/edit")
//	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
//			throws IOException {
//		RequestWrapper wrapper = new RequestWrapper(req);
//		// UacWebSystem.ST_WEB_SYSTEM_ID
//		String stWebSystemId = wrapper.getParameter(UacWebSystem.ST_WEB_SYSTEM_ID);
//		if (!StringUtils.trimToEmpty(stWebSystemId).isEmpty()) {
//			UacWebSystem uacWebSystem = uacWebSystemService.get(stWebSystemId);
//			req.setAttribute(UacWebSystem.UAC_WEB_SYSTEM, uacWebSystem);
//		}
//		return new ModelAndView("/uacWebSystem/edit.jsp");
//	}


	@RequestMapping("/wdf/uacWebSystem/info")
	public WdfResult info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		String stWebSystemId = wrapper.getParameter(UacWebSystem.ST_WEB_SYSTEM_ID);
		UacWebSystem uacWebSystem = uacWebSystemService.get(stWebSystemId);
		req.setAttribute(UacWebSystem.UAC_WEB_SYSTEM, uacWebSystem);
		return WdfResult.getResult().success().setData(JsonUtils.toJson(uacWebSystem));
	}

	/**
	 * 查询
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/wdf/uacWebSystem/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacWebSystem> list = uacWebSystemService.query(wrapper);
			result.setData(JsonUtils.toJson(list,UacWebSystem.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 删除
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/wdf/uacWebSystem/remove")
	public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacWebSystem.ST_WEB_SYSTEM_ID
			String stWebSystemId = wrapper.getParameter(UacWebSystem.ST_WEB_SYSTEM_ID);
			uacWebSystemService.remove(stWebSystemId);

		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 新增
	 * @param req
	 * @param res
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/wdf/uacWebSystem/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacWebSystem uacWebSystem = uacWebSystemService.saveOrUpdate(wrapper);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@Autowired
	private UacWebSystemService uacWebSystemService;

}
