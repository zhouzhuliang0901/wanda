package com.wondersgroup.wdf.uacCert.web;

import com.wondersgroup.wdf.dao.UacUapplyFeedbackDao;
import com.wondersgroup.wdf.dao.UacUnionApplyDao;
import com.wondersgroup.wdf.uacCert.dao.UacCert;
import com.wondersgroup.wdf.uacCert.dao.UacCertDao;
import com.wondersgroup.wdf.uacCert.service.UacCertService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reindeer.base.bean.WdfResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * 发证信息 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacCertController {

	@RequestMapping("/uacCert/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacCert.ST_CERT_ID
		String stCertId = wrapper.getParameter(UacCert.ST_CERT_ID);
		if (!StringUtils.trimToEmpty(stCertId).isEmpty()) {
			UacCert uacCert = uacCertService.get(stCertId);
			req.setAttribute(UacCert.UAC_CERT, uacCert);
		}
		return new ModelAndView("/uacCert/edit.jsp");
	}

	@RequestMapping("/uacCert/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacCert.ST_CERT_ID
		String stCertId = wrapper.getParameter(UacCert.ST_CERT_ID);
		UacCert uacCert = uacCertService.get(stCertId);
		req.setAttribute(UacCert.UAC_CERT, uacCert);
		return new ModelAndView("/uacCert/info.jsp");
	}

	@RequestMapping("/uacCert/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacCert> list = uacCertService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacCert.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacCert/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("发证信息删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacCert.ST_CERT_ID
			String stCertId = wrapper.getParameter(UacCert.ST_CERT_ID);
			uacCertService.remove(stCertId);
			result = ExtAjaxReturnMessage.success("发证信息删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacCert/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("发证信息保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacCert uacCert = uacCertService.saveOrUpdate(wrapper);
			if (uacCert != null)
				result = ExtAjaxReturnMessage.success("发证信息保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}


	/**
	 * 证照补充 查询接口
	 *
	 * @param stApplyId
	 * @return
	 */
	@RequestMapping("/wdf/uacCert/getCert")
	public UacCert getCert(String stApplyId) {
//		WdfResult result = WdfResult.getSuccessResult();
		try {
			UacCert byApplyId = uacCertService.getByApplyId(stApplyId);
//			result.setData(byApplyId);
			return byApplyId;
		} catch (Exception e) {
			e.printStackTrace();
//			result.failed().setMsg(e.getMessage());
		}
		return null;
	}
	/**
	 * 统一发证 操作接口
	 *
	 * @param json
	 * @return
	 */
	@RequestMapping("/wdf/uacApply/addCert")
	@Transactional(rollbackFor = Exception.class)
	public WdfResult addCert(@RequestBody(required = false) String json) {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			JSONObject jsonObject = new JSONObject(json);
			String stApplyId = jsonObject.optString("stApplyId");
			String stLicenseNo = jsonObject.optString("stLicenseNo", null);//证书许可编号
			String stArea = jsonObject.optString("stArea", null);//经营地域
			String stValidDate = jsonObject.optString("stValidDate", null);//证书有效期
			String dtCertification = jsonObject.optString("dtCertification", null);//发证日期
			String stOrgan = jsonObject.optString("stOrgan", null);//发证机关
			String stBcontent = jsonObject.optString("stBcontent", null);//从事业务内容

			UacCert uacCert = new UacCert();
			uacCert.setStCertId(UUID.randomUUID().toString());
			uacCert.setStApplyId(stApplyId);
			uacCert.setStLicenseNo(stLicenseNo);
			uacCert.setStArea(stArea);
			uacCert.setStValidDate(stValidDate);
			uacCert.setDtCertification(Timestamp.valueOf(dtCertification));
			uacCert.setStOrgan(stOrgan);
			uacCert.setStBcontent(stBcontent);
			uacCert.setDtCreate(new Timestamp(System.currentTimeMillis()));
			uacCertDao.add(uacCert);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.failed().setMsg(e.getMessage());
		}
		return result;
	}



	@Autowired
	private UacCertService uacCertService;

	@Autowired
	private UacUapplyFeedbackDao uacUapplyFeedbackDao;

	@Autowired
	private UacUnionApplyDao uacUnionApplyDao;

	@Autowired
	private UacCertDao uacCertDao;
}
