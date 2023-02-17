package com.wondersgroup.infopub.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wfc.service.database.BlobHelper;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;
import coral.base.util.CloseUtils;

@Controller
public class InfopubCommonController {

	/**
	 * 展示图片
	 */
	@RequestMapping("/infopub/common/getImage.do")
	public void getImage(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// 获取图片ID
		String picId = req.getParameter("picId");
		Log.debug("图片ID:" + picId);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ATTACH_ID", Condition.OT_EQUAL, picId));
		res.setContentType("image/jpeg");
		OutputStream out = res.getOutputStream();
		try {
			BlobHelper.getBlobToStream("INFOPUB_ATTACHMENT", "BL_CONTENT",
					conds.toString(), conds.getObjectArray(), out);
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug("图片IDD:" + picId + ",获取用户图片错误!");
		} finally {
			CloseUtils.close(out);
		}
	}
}
