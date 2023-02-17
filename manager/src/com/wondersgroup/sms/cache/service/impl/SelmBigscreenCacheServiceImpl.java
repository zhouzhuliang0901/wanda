package com.wondersgroup.sms.cache.service.impl;

import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.sms.cache.bean.SelmBigscreenCache;
import com.wondersgroup.sms.cache.dao.SelmBigscreenCacheDao;
import com.wondersgroup.sms.cache.service.SelmBigscreenCacheService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * 大屏统计缓存表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmBigscreenCacheServiceImpl implements SelmBigscreenCacheService {
	
	@Autowired
	private SelmBigscreenCacheDao selmBigscreenCacheDao;

	@Override
	public JSONObject getCacheData(HttpReqRes httpReqRes) {
		String fCode = httpReqRes.getParameter("fCode");
		String sCode = httpReqRes.getParameter("sCode");
		String tCode = httpReqRes.getParameter("tCode");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_FCODE",Condition.OT_EQUAL,fCode));
		conds.add(new Condition("ST_SCODE",Condition.OT_EQUAL,sCode));
		conds.add(new Condition("ST_TCODE",Condition.OT_EQUAL,tCode));
		SelmBigscreenCache sbc = selmBigscreenCacheDao.query(conds, null).get(0);
		JSONObject Object = JSONObject.fromObject(sbc.getStJson());
		return Object;
	}
	
	
	

	

	

}
