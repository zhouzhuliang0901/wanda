package com.wondersgroup.infopub.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.common.operator.connector.QuarterOp;
import com.wondersgroup.infopub.bean.SelmBigscreenCache;
import com.wondersgroup.infopub.dao.SelmBigscreenCacheDao;
import com.wondersgroup.infopub.dao.SelmQueryHisDao;

@Component
public class QuarterUpdateBigscreenCacheTask implements QuarterOp{
	
	
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private SelmBigscreenCacheDao selmBigscreenCacheDao;

	@Override
	public void execute(Timestamp current) {
		todaySelmQuery();
	}
	
	/**
	 * 当天办件量
	 * /infopub/selmQueryHis/todaySelmQuery.do
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void todaySelmQuery() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("DT_CREATE",Condition.OT_GREATER_EQUAL,today+" 00:00:00"));
		conds.add(new Condition("DT_CREATE",Condition.OT_LESS_EQUAL,today+" 23:59:59"));
		BigDecimal sum = selmQueryHisDao.getTodaySelmQueryHis(conds);
		JSONObject json = new JSONObject();
		json.put("count", sum);
		//插入大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "selmQueryHis";
		String st_tcode = "todaySelmQuery.do";
		insertData(st_fcode,st_scode,st_tcode,json);
	}
	
	public void insertData(String fCode, String sCode, String tCode,JSONObject obj){
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_FCODE",Condition.OT_EQUAL,fCode));
		conds.add(new Condition("ST_SCODE",Condition.OT_EQUAL,sCode));
		conds.add(new Condition("ST_TCODE",Condition.OT_EQUAL,tCode));
		List<SelmBigscreenCache> sbc = selmBigscreenCacheDao.query(conds, null);
		//System.out.println("sbc= "+sbc);
		if(sbc.size() == 0){
			conds.add(new Condition("ST_JSON",Condition.OT_EQUAL,obj));
			SelmBigscreenCache info = new SelmBigscreenCache();
			info.setStBigscreenCacheId(UUID.randomUUID().toString());
			info.setStFcode(fCode);
			info.setStScode(sCode);
			info.setStTcode(tCode);
			info.setStJson(obj.toString());
			info.setDtCreate(new Timestamp(System.currentTimeMillis()));
			selmBigscreenCacheDao.add(info);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ST_JSON",obj.toString());
			map.put("DT_UPDATE",new Timestamp(System.currentTimeMillis()));
			selmBigscreenCacheDao.update(map, conds);
		}
		
	}

}
