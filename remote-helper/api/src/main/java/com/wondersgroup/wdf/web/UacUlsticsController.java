package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacUapplyTrack;
import com.wondersgroup.wdf.dao.UacUapplyTrackDao;
import com.wondersgroup.wdf.dao.UacUlstics;
import com.wondersgroup.wdf.dao.UacUlsticsDao;
import com.wondersgroup.wdf.service.UacUlsticsService;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * 办件物流 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacUlsticsController {

	@RequestMapping("/uacUlstics/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUlstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUlstics.ST_UNION_LOGISTICS_ID);
		if (!StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty()) {
			UacUlstics uacUlstics = uacUlsticsService.get(stUnionLogisticsId);
			req.setAttribute(UacUlstics.UAC_ULSTICS, uacUlstics);
		}
		return new ModelAndView("/uacUlstics/edit.jsp");
	}

	@RequestMapping("/uacUlstics/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUlstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUlstics.ST_UNION_LOGISTICS_ID);
		UacUlstics uacUlstics = uacUlsticsService.get(stUnionLogisticsId);
		req.setAttribute(UacUlstics.UAC_ULSTICS, uacUlstics);
		return new ModelAndView("/uacUlstics/info.jsp");
	}

	@RequestMapping("/uacUlstics/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUlstics> list = uacUlsticsService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUlstics.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUlstics/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("办件物流删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUlstics.ST_UNION_LOGISTICS_ID
			String stUnionLogisticsId = wrapper.getParameter(UacUlstics.ST_UNION_LOGISTICS_ID);
			uacUlsticsService.remove(stUnionLogisticsId);
			result = ExtAjaxReturnMessage.success("办件物流删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUlstics/save.do")
	public void save(@RequestBody(required = false) String json)
			throws IOException {
		JSONObject jsonObject = new JSONObject(json);
		UacUlstics uacUlstics1 = uacUlsticsDao.get(jsonObject.getString("stUnionLogisticsId"));
		uacUlstics1.setDtUpdate(Timestamp.valueOf(jsonObject.getString("dtUpdate")));
		uacUlstics1.setStReceiver(jsonObject.getString("stReceiver"));
		uacUlstics1.setStReceiverAddress(jsonObject.getString("stReceiverAddress"));
		uacUlstics1.setStReceiverArea(jsonObject.getString("stReceiverArea"));
		uacUlstics1.setStReceiverCity(jsonObject.getString("stReceiverCity"));
		uacUlstics1.setStReceiverPhone(jsonObject.getString("stReceiverPhone"));
		uacUlstics1.setStReceiverProvince(jsonObject.getString("stReceiverProvince"));
		uacUlstics1.setStReceiverZipcode(jsonObject.getString("stReceiverZipcode"));
		uacUlstics1.setStSender(jsonObject.getString("stSender"));
		uacUlstics1.setStSenderAddress(jsonObject.getString("stSenderAddress"));
		uacUlstics1.setStSenderArea(jsonObject.getString("stSenderArea"));
		uacUlstics1.setStSenderCity(jsonObject.getString("stSenderCity"));
		uacUlstics1.setStSenderPhone(jsonObject.getString("stSenderPhone"));
		uacUlstics1.setStSenderProvince(jsonObject.getString("stSenderProvince"));
		uacUlstics1.setStShipCompany(jsonObject.getString("stShipCompany"));
//		uacUlstics1.  jsonObject.getString("stSno");
//		uacUlstics1.setSt  jsonObject.getString("stTarget");
		uacUlsticsDao.update(uacUlstics1);



//		String result = ExtAjaxReturnMessage.toJsonErrorObj("办件物流保存失败。", "错误",
//				null).toString();
//		try {
//			RequestWrapper wrapper = new RequestWrapper(req);
//			UacUlstics uacUlstics = uacUlsticsService.saveOrUpdate(wrapper);
//			if (uacUlstics != null)
//				result = ExtAjaxReturnMessage.success("办件物流保存成功。", null)
//						.toString();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		EasyUIHelper.writeFormResponse(res, result);
	}

	//根据applyId查询快递信息
	@RequestMapping("/wdf/uacUlstics/infoUistics")
	public UacUlstics infoUistics(String stApplyId){
//		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUlstics.ST_UNION_LOGISTICS_ID
//		String stApplyId = wrapper.getParameter("stApplyId");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(UacUapplyTrack.ST_APPLY_ID, Condition.OT_EQUAL, stApplyId));
		conds.add(new Condition(UacUapplyTrack.ST_OP, Condition.OT_EQUAL, "窗口收件"));
//                UacUapplyLstics uacUapplyLsticsByapplyId = uacUapplyLsticsDao.getByApplyId(uacUnionApply.getStApplyId());
		String suff = "and ST_UNION_LOGISTICS_ID is not null";
		List<UacUapplyTrack> query = uacUapplyTrackDao.query(conds, suff);
		UacUapplyTrack uacUapplyTrack1 = null;
		if (query.size() > 0)
			uacUapplyTrack1 = query.get(0);

		UacUlstics uacUlstics = uacUlsticsService.get(uacUapplyTrack1.getStUnionLogisticsId());
//		req.setAttribute(UacUlstics.UAC_ULSTICS, uacUlstics);
		return uacUlstics == null ? null : uacUlstics;
	}

	@Autowired
	private UacUlsticsService uacUlsticsService;

	@Autowired
	private UacUapplyTrackDao uacUapplyTrackDao;

	@Autowired
	private UacUlsticsDao uacUlsticsDao;
}
