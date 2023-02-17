package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacUapplyNodeTrack;
import com.wondersgroup.wdf.service.UacUapplyNodeTrackService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 综合办件环节跟踪信息 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacUapplyNodeTrackController {

	@RequestMapping("/uacUapplyNodeTrack/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyNodeTrack.ST_NODE_TRACK_ID
		String stNodeTrackId = wrapper.getParameter(UacUapplyNodeTrack.ST_NODE_TRACK_ID);
		if (!StringUtils.trimToEmpty(stNodeTrackId).isEmpty()) {
			UacUapplyNodeTrack uacUapplyNodeTrack = uacUapplyNodeTrackService.get(stNodeTrackId);
			req.setAttribute(UacUapplyNodeTrack.UAC_UAPPLY_NODE_TRACK, uacUapplyNodeTrack);
		}
		return new ModelAndView("/uacUapplyNodeTrack/edit.jsp");
	}

	@RequestMapping("/uacUapplyNodeTrack/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyNodeTrack.ST_NODE_TRACK_ID
		String stNodeTrackId = wrapper.getParameter(UacUapplyNodeTrack.ST_NODE_TRACK_ID);
		UacUapplyNodeTrack uacUapplyNodeTrack = uacUapplyNodeTrackService.get(stNodeTrackId);
		req.setAttribute(UacUapplyNodeTrack.UAC_UAPPLY_NODE_TRACK, uacUapplyNodeTrack);
		return new ModelAndView("/uacUapplyNodeTrack/info.jsp");
	}

	@RequestMapping("/uacUapplyNodeTrack/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyNodeTrack> list = uacUapplyNodeTrackService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyNodeTrack.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyNodeTrack/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件环节跟踪信息删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyNodeTrack.ST_NODE_TRACK_ID
			String stNodeTrackId = wrapper.getParameter(UacUapplyNodeTrack.ST_NODE_TRACK_ID);
			uacUapplyNodeTrackService.remove(stNodeTrackId);
			result = ExtAjaxReturnMessage.success("综合办件环节跟踪信息删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyNodeTrack/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件环节跟踪信息保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyNodeTrack uacUapplyNodeTrack = uacUapplyNodeTrackService.saveOrUpdate(wrapper);
			if (uacUapplyNodeTrack != null) {
				result = ExtAjaxReturnMessage.success("综合办件环节跟踪信息保存成功。", null)
						.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacUapplyNodeTrackService uacUapplyNodeTrackService;

}
