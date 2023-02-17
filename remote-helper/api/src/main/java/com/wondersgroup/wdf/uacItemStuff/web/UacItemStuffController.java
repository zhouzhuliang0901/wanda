package com.wondersgroup.wdf.uacItemStuff.web;

import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuff;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuffDao;
import com.wondersgroup.wdf.uacItemStuff.service.UacItemStuffService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 事项材料 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacItemStuffController {

	@RequestMapping("/uacItemStuff/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemStuff.ST_ITEM_STUFF_ID
		String stItemStuffId = wrapper.getParameter(UacItemStuff.ST_ITEM_STUFF_ID);
		if (!StringUtils.trimToEmpty(stItemStuffId).isEmpty()) {
			UacItemStuff uacItemStuff = uacItemStuffService.get(stItemStuffId);
			req.setAttribute(UacItemStuff.UAC_ITEM_STUFF, uacItemStuff);
		}
		return new ModelAndView("/uacItemStuff/edit.jsp");
	}

	@RequestMapping("/uacItemStuff/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacItemStuff.ST_ITEM_STUFF_ID
		String stItemStuffId = wrapper.getParameter(UacItemStuff.ST_ITEM_STUFF_ID);
		UacItemStuff uacItemStuff = uacItemStuffService.get(stItemStuffId);
		req.setAttribute(UacItemStuff.UAC_ITEM_STUFF, uacItemStuff);
		return new ModelAndView("/uacItemStuff/info.jsp");
	}

	@RequestMapping("/uacItemStuff/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacItemStuff> list = uacItemStuffService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacItemStuff.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacItemStuff/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("事项材料删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacItemStuff.ST_ITEM_STUFF_ID
			String stItemStuffId = wrapper.getParameter(UacItemStuff.ST_ITEM_STUFF_ID);
			uacItemStuffService.remove(stItemStuffId);
			result = ExtAjaxReturnMessage.success("事项材料删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacItemStuff/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("事项材料保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacItemStuff uacItemStuff = uacItemStuffService.saveOrUpdate(wrapper);
			if (uacItemStuff != null)
				result = ExtAjaxReturnMessage.success("事项材料保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacItemStuffService uacItemStuffService;

	@Autowired
	private UacItemStuffDao uacItemStuffDao;

}
