package com.wondersgroup.serverApply.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.service.SelmServerDlinkService;

import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;

/**
 * 服务关联设备 web层控制器
 *
 * @author scalffold
 * 
 */
@Controller
public class SelmServerDlinkController {
	
	@Autowired
	private SelmServerDlinkService selmServerDlinkService;
	
	
	@RequestMapping("/serverApply/selmServerDlink/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String result = ExtAjaxReturnMessage.toJsonErrorObj("服务关联设备保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<SelmServerDlink> selmServerDlink = selmServerDlinkService.saveOrUpdate(wrapper);
			if (selmServerDlink != null)
				result = ExtAjaxReturnMessage.success("服务关联设备保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}

	
	

}
