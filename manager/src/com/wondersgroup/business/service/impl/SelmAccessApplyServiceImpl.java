package com.wondersgroup.business.service.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;
import wfc.service.log.Log;


import coral.base.app.AppContext;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.data.DataSet;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.wondersgroup.business.bean.SelmAccessApply;
import com.wondersgroup.business.bean.SelmAttach;
import com.wondersgroup.business.dao.SelmAccessApplyDao;
import com.wondersgroup.business.dao.SelmAttachDao;
import com.wondersgroup.business.excelBean.SelmQueryHisExcel;
import com.wondersgroup.business.service.SelmAccessApplyService;
import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;

/**
 * 接入申请业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmAccessApplyServiceImpl implements SelmAccessApplyService {

	/**
	 * 根据主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}获取接入申请
	 * 
	 * @param stAccessApplyId
	 *            接入申请主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}
	 * @return 接入申请实例
	 */
	@Override
	public SelmAccessApply get(String stAccessApplyId) {
		if (StringUtils.trimToEmpty(stAccessApplyId).isEmpty())
			throw new NullPointerException("Parameter stAccessApplyId cannot be null.");
		return selmAccessApplyDao.get(stAccessApplyId);
	}
	
	
	@Override
	public JSONObject list(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		String smsUserId = httpReqRes.getParameter("smsUserId");
		String stPermission = httpReqRes.getParameter("stPermission");
		String stAccessApplyId = httpReqRes.getParameter("stAccessApplyId");
		System.out.println(stAccessApplyId+"--------");
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		if (!stPermission.equals("project_admin")) {
			if (smsUserId != null && !StringUtils.trim(smsUserId).isEmpty()) {
				conds.add(new Condition("ST_APPLY_USER_ID", Condition.OT_EQUAL,
						smsUserId));
				}
		}
		if (stAccessApplyId != null && !StringUtils.trim(stAccessApplyId).isEmpty() && !stAccessApplyId.equals("null")) {
			conds.add(new Condition("ST_ACCESS_APPLY_ID", Condition.OT_EQUAL,
					stAccessApplyId));
			}
		String suffix = "ORDER BY DT_CREATE";
		if (orderName != null) {
			if ("stApplyTitle".equals(orderName)) {
				suffix = "ORDER BY ST_APPLY_TITLE " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			} 
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		List<SelmAccessApply> selmAccessApplyList = selmAccessApplyDao.query(conds, suffix, pageSize, currentPage);
		for (SelmAccessApply selmAccessApply : selmAccessApplyList) {
			String stAttachId = selmAccessApply.getStAttachId();
			SelmAttach selmAttach = selmAttachDao.get(stAttachId);
			selmAccessApply.setStExt1(selmAttach.getStFilename());
		}
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmAccessApplyList,
							SelmAccessApply.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmAccessApplyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmAccessApplyList);
		return returnObj;
		
	}

	/**
	 * 查询接入申请列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 接入申请列表
	 */
	@Override
	public PaginationArrayList<SelmAccessApply> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmAccessApply.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmAccessApplyDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}删除接入申请
	 * 
	 * @param stAccessApplyId
	 *            接入申请主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}
	 */
	@Override
	public void remove(String stAccessApplyId) {
		if (StringUtils.trimToEmpty(stAccessApplyId).isEmpty())
			throw new NullPointerException("Parameter stAccessApplyId cannot be null.");
		selmAccessApplyDao.delete(stAccessApplyId);
	}

	/**
	 * 保存或更新接入申请
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 接入申请实例
	 */
	@Override
	public SelmAccessApply saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SelmAccessApply.ST_ACCESS_APPLY_ID
		String stAccessApplyId = wrapper.getParameter(SelmAccessApply.ST_ACCESS_APPLY_ID);
		SelmAccessApply oldSelmAccessApply = null;
		if (!StringUtils.trimToEmpty(stAccessApplyId).isEmpty()) {
			oldSelmAccessApply = selmAccessApplyDao.get(stAccessApplyId);
		}
		if (oldSelmAccessApply == null) {// new
			SelmAccessApply newSelmAccessApply = (SelmAccessApply) t4r.toBean(SelmAccessApply.class);
			newSelmAccessApply.setStAccessApplyId(UUID.randomUUID().toString());
			selmAccessApplyDao.add(newSelmAccessApply);
			return newSelmAccessApply;
		}else {// update
			oldSelmAccessApply = (SelmAccessApply) t4r.toBean(oldSelmAccessApply, SelmAccessApply.class);
			selmAccessApplyDao.update(oldSelmAccessApply);
			return oldSelmAccessApply;
		}
	}

	@Autowired
	private SelmAccessApplyDao selmAccessApplyDao;
	@Autowired
	private SelmAttachDao selmAttachDao;
	@Autowired
	private SmsUserDao smsUserDao;

	@Override
	public SelmAttach uploadStuff(String stApplyUserId, String fileName,
			String fileType, byte[] file, int len, String applytitle, String applycontent) {
		String stAttachId1 = UUID.randomUUID().toString();
		SelmAttach attach = new SelmAttach();
		attach.setStAttachId(stAttachId1);
		attach.setStLinkTable("SELM_ACCESS_APPLY");
		attach.setStAttachType(fileType);
		attach.setStFilename(fileName);
		attach.setStFileSize(Integer.valueOf(len).toString());
		attach.setBlContent(file);
		attach.setBlSmallContent(new byte[]{});
		attach.setStFileType(fileType);
		attach.setDtCreate(new Timestamp(System.currentTimeMillis()));
		
		SmsUser smsUser = smsUserDao.get(stApplyUserId);
		SelmAccessApply selmAccessApply = new SelmAccessApply();
		selmAccessApply.setStAccessApplyId(UUID.randomUUID().toString());//申请id
		selmAccessApply.setStApplyTitle(applytitle);//申请标题
		selmAccessApply.setDtCreate(new Timestamp(System.currentTimeMillis()));//时间
		selmAccessApply.setStApplyUserName(smsUser.getStUserName());//申请姓名
		selmAccessApply.setStApplyUserId(stApplyUserId);//申请人id
		selmAccessApply.setStApplyContent(applycontent);//申请内容
		selmAccessApply.setStAttachId(stAttachId1);
		selmAccessApplyDao.add(selmAccessApply);
		
		/*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		selmAccessApplyDao.update(applytitle,applycontent,stAttachId,timestamp);*/
		SelmAttach selmAttach = selmAttachDao.get(stAttachId1);
		if(selmAttach !=null){
			selmAccessApplyDao.upAttach(attach);
		}else{
			selmAccessApplyDao.saveAttach(attach);
		}
		
		return attach;
	}


	@Override
	public void downLoad(HttpReqRes httpReqRes) {
		SmsUser user = (SmsUser) httpReqRes.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		String stAttachId = httpReqRes.getParameter("stAttachId");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		selmAccessApplyDao.updateStatus("3",user.getStUserId(),user.getStUserName(),timestamp,stAttachId);
		byte[] attachFildById = selmAttachDao.getAttachFildById(stAttachId);
		SelmAttach selmAttach = selmAttachDao.get(stAttachId);
		BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        String fileName = selmAttach.getStFilename();
        try {
            try {
            	java.io.OutputStream o = httpReqRes.getResponse().getOutputStream();
	             // 设置相应类型
	          httpReqRes.getResponse().setContentType("application/vnd.ms-excel");
				//设置相应头，attachment:以附件的形式进行下载
	          httpReqRes.getResponse().setHeader("content-disposition","attachment;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8"));
	          o.write(attachFildById);
			 } catch (Exception e) {
		      e.printStackTrace();
	}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
	}


	@Override
	public JSONObject NoApplylist(HttpReqRes httpReqRes) {
		String draw = httpReqRes.getParameter("draw");
		int drawInt = 0;
		if (draw != null) {
			drawInt = Integer.valueOf(draw) + 1;
			Log.info("当前调用的次数:" + drawInt);
		}
		Conditions conds = Conditions.newAndConditions();
		/*String smsUserId = httpReqRes.getParameter("smsUserId");
		String stPermission = httpReqRes.getParameter("stPermission");*/
		String orderName = httpReqRes.getParameter("columns["
				+ httpReqRes.getParameter("order[0][column]") + "][data]");
		String orderType = httpReqRes.getParameter("order[0][dir]");
		
		/*if (!stPermission.equals("project_admin")) {
			if (smsUserId != null && !StringUtils.trim(smsUserId).isEmpty()) {
				conds.add(new Condition("ST_APPLY_USER_ID", Condition.OT_EQUAL,
						smsUserId));
				}
		}*/
		String suffix = "ORDER BY DT_CREATE";
		if (orderName != null) {
			if ("stApplyTitle".equals(orderName)) {
				suffix = "ORDER BY ST_APPLY_TITLE " + orderType.toUpperCase();
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE " + orderType.toUpperCase();
			} 
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (httpReqRes != null) {
			String length = httpReqRes.getParameter("length");
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (httpReqRes.getParameter("start") != null) {
				int start = Integer.valueOf(httpReqRes.getParameter("start"));
				if (start != 0) {
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
		}
		conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL,
				null));
		List<SelmAccessApply> selmAccessApplyList = selmAccessApplyDao.query(conds, suffix, pageSize, currentPage);
		// 总条数
		String total = null;
		try {
			total = EasyUIJsonConverter.convertDataSetToJson(
					DataSet.convert(selmAccessApplyList,
							SelmAccessApply.class)).getString("total");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONObject returnObj = new JSONObject();
		returnObj.put("draw", drawInt);
		returnObj.put("recordsTotal", selmAccessApplyList.size());
		returnObj.put("recordsFiltered", total);
		returnObj.put("data", selmAccessApplyList);
		return returnObj;
		
	}

}
