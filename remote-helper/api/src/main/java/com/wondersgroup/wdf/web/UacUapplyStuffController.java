package com.wondersgroup.wdf.web;

import com.alibaba.fastjson.JSON;
import com.wondersgroup.wdf.dao.*;
import com.wondersgroup.wdf.service.UacItemStuffTwoService;
import com.wondersgroup.wdf.service.UacUapplyStuffService;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuff;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuffDao;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reindeer.base.bean.WdfResult;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 综合受理电子材料 web层控制器
 *
 * @author scalffold
 * 
 */
@RestController
public class UacUapplyStuffController {

	@RequestMapping("/uacUapplyStuff/edit.do")
	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyStuff.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacUapplyStuff.ST_ESTUFF_ID);
		if (!StringUtils.trimToEmpty(stEstuffId).isEmpty()) {
			UacUapplyStuff uacUapplyStuff = uacUapplyStuffService.get(stEstuffId);
			req.setAttribute(UacUapplyStuff.UAC_UAPPLY_STUFF, uacUapplyStuff);
		}
		return new ModelAndView("/uacUapplyStuff/edit.jsp");
	}

	@RequestMapping("/uacUapplyStuff/info.do")
	public ModelAndView info(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		RequestWrapper wrapper = new RequestWrapper(req);
		// UacUapplyStuff.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacUapplyStuff.ST_ESTUFF_ID);
		UacUapplyStuff uacUapplyStuff = uacUapplyStuffService.get(stEstuffId);
		req.setAttribute(UacUapplyStuff.UAC_UAPPLY_STUFF, uacUapplyStuff);
		return new ModelAndView("/uacUapplyStuff/info.jsp");
	}

	@RequestMapping("/uacUapplyStuff/list.do")
	public void list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyStuff> list = uacUapplyStuffService.query(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyStuff.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 审批环节
	 * 获取已上传材料列表
	 * @param req ST_APPLY_ID
	 * @param res ST_ITEM_ID (ST_CITEM_ID)
	 * @throws IOException
	 */
	@RequestMapping("/uacUapplyStuff/listUploaded")
	public void listUploaded(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);

			List<UacUapplyStuff> listStuff = uacUapplyStuffService.queryAttach(wrapper);
			List<UacItemStuffTwo> uacItemStuffTwos = uacItemStuffTwoService.queryStuff(wrapper);

			List<UacUapplyStuff> uacUapplyStuffs = new ArrayList<UacUapplyStuff>();
			for (UacUapplyStuff stuff : listStuff){
				for (UacItemStuffTwo stuffTwo : uacItemStuffTwos){
					if (stuff.getStCstuffId().equals(stuffTwo.getStStuffId())){
						stuff.setStExt1(stuffTwo.getStLabName());
						uacUapplyStuffs.add(stuff);
						break;
					}
				}
				UacAttachLink ath = uacAttachLinkDao.getAth(stuff.getStEstuffId());
				if (ath!=null){
					stuff.setStExt2("1");
				}else {
					stuff.setStExt2("0");
				}
			}

			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(uacUapplyStuffs, UacUapplyStuff.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}


	/**
	 * 获取已上传材料列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/uacUapplyStuff/listAttach.do")
	public void listAttach(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyStuff> list = uacUapplyStuffService.queryAttach(wrapper);
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(list, UacUapplyStuff.class)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	/**
	 * 获取未上传材料列表
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/wdf/uacUapplyStuff/listStuff")
	public void listStuff(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = StringUtils.EMPTY;
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			List<UacUapplyStuff> list = uacUapplyStuffService.queryAttach(wrapper);//获取已上传文件列表
			String stApplyId = wrapper.getParameter("ST_APPLY_ID");
			UacUnionApply uacUnionApply = uacUnionApplyDao.get(stApplyId);
			uacUnionApply.getStCitemId();

			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition(UacItemStuff.ST_ITEM_ID, Condition.OT_EQUAL, uacUnionApply.getStCitemId()));
			conds.add(new Condition(UacItemStuff.NM_REMOVED, Condition.OT_EQUAL, "0"));
			List<UacItemStuff> query = uacItemStuffDao.query(conds, "");//获取事项所需的文件

			List<UacItemStuff> uacItemStuffs = new ArrayList<UacItemStuff>();//遍历是否已经上传，返回未上传list
			for (UacItemStuff stuffTwo : query){
				int i = 0;
				for (UacUapplyStuff stuff : list){
					if (stuff.getStCstuffId().equals(stuffTwo.getStStuffId())){
						i = 1;
						break;
					}
				}
				if (i == 0){
					uacItemStuffs.add(stuffTwo);
				}
			}
			result = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(uacItemStuffs, UacItemStuff.class)).toString();
//			return uacItemStuffs;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
//		return null;
	}


	/**
	 * 统一发证 补交材料接口
	 *
	 * @param json
	 * @return
	 */
	@RequestMapping("/wdf/uacUapplyStuff/getAwardApply")
	public WdfResult getAwardApply(@RequestBody(required = false) String json) {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray uapplyStuff = jsonObject.getJSONArray("uapplyStuff");//补交材料ID
			String stApplyId = jsonObject.optString("stApplyId");//办件信息ID
			String str = uapplyStuff.toString();
			List<String> list = JSON.parseArray(str, String.class);
			for (String stuffId : list){
				UacItemStuffTwo byStuff = uacItemStuffTwoDao.getStuffId(stuffId);
				UacUapplyStuff stuff = new UacUapplyStuff();
				stuff.setStEstuffId(UUID.randomUUID().toString());
				stuff.setStApplyId(stApplyId);
				stuff.setStCstuffId(stuffId);
				stuff.setNmCopy(byStuff.getNmCopy());
				stuff.setNmOriginal(byStuff.getNmOriginal());
				stuff.setNmOfflineSubmit(new BigDecimal(1));
				stuff.setNmStatus(new BigDecimal(0));
				stuff.setStEntityType("FILE");
				stuff.setStStuffType("APPLY");
				stuff.setStStuffUse("申请材料");
				stuff.setStOpinion("正常");
				stuff.setDtCreate(new Timestamp(System.currentTimeMillis()));
				uacUapplyStuffDao.add(stuff);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.failed().setMsg(e.getMessage());
		}
		return result;
	}

	@RequestMapping("/uacUapplyStuff/remove.do")
	public void remove(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合受理电子材料删除失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// UacUapplyStuff.ST_ESTUFF_ID
			String stEstuffId = wrapper.getParameter(UacUapplyStuff.ST_ESTUFF_ID);
			uacUapplyStuffService.remove(stEstuffId);
			result = ExtAjaxReturnMessage.success("综合受理电子材料删除成功。", null).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeResponse(res, result);
	}

	@RequestMapping("/uacUapplyStuff/save.do")
	public void save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String result = ExtAjaxReturnMessage.toJsonErrorObj("综合受理电子材料保存失败。", "错误",
				null).toString();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacUapplyStuff uacUapplyStuff = uacUapplyStuffService.saveOrUpdate(wrapper);
			if (uacUapplyStuff != null)
				result = ExtAjaxReturnMessage.success("综合受理电子材料保存成功。", null)
						.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EasyUIHelper.writeFormResponse(res, result);
	}



	@Autowired
	private UacUapplyStuffService uacUapplyStuffService;

	@Autowired
	private UacItemStuffTwoService uacItemStuffTwoService;

	@Autowired
	private UacAttachLinkDao uacAttachLinkDao;

	@Autowired
	private UacUnionApplyDao uacUnionApplyDao;

	@Autowired
	private UacItemStuffDao uacItemStuffDao;

	@Autowired
	private UacItemStuffTwoDao uacItemStuffTwoDao;

	@Autowired
	private UacUapplyStuffDao uacUapplyStuffDao;
}
