package com.wondersgroup.selfapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.dao.GuidanceDao;
import com.wondersgroup.selfapi.service.GuidanceService;
import com.wondersgroup.selfapi.util.XhZzsbUtils;

@Service
public class GuidanceServiceImpl implements GuidanceService {

	@Autowired
		private GuidanceDao guidanceDao;
	/**
	 * 获取可自助申报的所有部门列表
	 */
	@Override
	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage) {
		
		return guidanceDao.getOrganListForDeclarePage(pageSize, currentPage);
	}
	
	/**
	 * 获取可自助申报的所有事项列表，若数据过多可分页返回，每页8条数据
	 */
	@Override
	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage) {
		return guidanceDao.getAllItemListForPage(pageSize, currentPage);
	}

	

	/**
	 * 根据事项编码获取事项的材料列表
	 */
	@Override
	public String getItemStuffList(String itemCode) {
		String str = XhZzsbUtils.getTocken("", "");
		String access_token = JSONObject.parseObject(str).getString(
				"access_token");
		JSONObject obj = new JSONObject();
		obj.put("accessToken", access_token);
		obj.put("itemCode", itemCode);
		return XhZzsbUtils.getItemStuffList(obj.toJSONString());
	}

	/**
	 * 获取事项的办事指南详细信息（新增咨询电话、审批期限）
	 */
	@Override
	/*public WindowZhallItemDetailExt getGuideInfoByZhallIdExt(String stZhallId) {
		WindowZhallItemDetailExt w = new WindowZhallItemDetailExt();
		w = guidanceDao.getGuideInfoByZhallIdExt(stZhallId);
		Conditions conds= Conditions.newAndConditions();
		conds.add(new Condition(WindowZhallItem.ST_ZHALL_ID, Condition.OT_EQUAL, stZhallId));
		List<WindowZhallItem> list = guidanceDao.query(conds, null);
		if (list != null && list.size() > 0) {
			// 存入事项名称
			w.setStZhallId(list.get(0).getStItemName());
			w.setStLegalTime(list.get(0).getStLegalTime());
			w.setStPromiseTime(list.get(0).getStPromiseTime());
			w.setStTelConsult(list.get(0).getStTelConsult());
			w.setStTelComplaint(list.get(0).getStTelComplaint());
		}
		return w;
	}*/
	public String getGuideInfoByZhallIdExt(String stZhallId) {
		String w = guidanceDao.getGuideInfoByZhallIdExt(stZhallId);
		return w;
	}
}
