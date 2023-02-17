package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacUapplyTrack;
import com.wondersgroup.wdf.service.UacUapplyTrackService;
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
 * 综合办件跟踪信息 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class UacUapplyTrackController {

	@RequestMapping("/uacUapplyTrack/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyTrack.ST_TRACK_ID
		String stTrackId = wrapper.getParameter(UacUapplyTrack.ST_TRACK_ID);
		if (!StringUtils.trimToEmpty(stTrackId).isEmpty()) {
			UacUapplyTrack uacUapplyTrack = uacUapplyTrackService.get(stTrackId);
			req.setAttribute(UacUapplyTrack.UAC_UAPPLY_TRACK, uacUapplyTrack);
		}
		return new ModelAndView("/uacUapplyTrack/edit.jsp");
	}

	@RequestMapping("/uacUapplyTrack/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyTrack.ST_TRACK_ID
		String stTrackId = wrapper.getParameter(UacUapplyTrack.ST_TRACK_ID);
		UacUapplyTrack uacUapplyTrack = uacUapplyTrackService.get(stTrackId);
		req.setAttribute(UacUapplyTrack.UAC_UAPPLY_TRACK, uacUapplyTrack);
		return new ModelAndView("/uacUapplyTrack/info.jsp");
	}

	@RequestMapping("/uacUapplyTrack/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyTrack> list = uacUapplyTrackService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyTrack.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyTrack/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件跟踪信息删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyTrack.ST_TRACK_ID
			String stTrackId = wrapper.getParameter(UacUapplyTrack.ST_TRACK_ID);
			uacUapplyTrackService.remove(stTrackId);
			result = ExtAjaxReturnMessage.success("综合办件跟踪信息删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyTrack/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合办件跟踪信息保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyTrack uacUapplyTrack = uacUapplyTrackService.saveOrUpdate(wrapper);
			if (uacUapplyTrack != null) {
				result = ExtAjaxReturnMessage.success("综合办件跟踪信息保存成功。", null)
						.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	@Autowired
	private UacUapplyTrackService uacUapplyTrackService;

}
