package com.wondersgroup.infopub.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.DB;

import com.wondersgroup.common.operator.connector.NightOp;
import com.wondersgroup.infopub.bean.SelmBigscreenCache;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoExtDao;
import com.wondersgroup.infopub.dao.SelmBigscreenCacheDao;
import com.wondersgroup.infopub.dao.SelmQueryHisDao;

@Component
public class UpdateDigscreenCacheTask implements NightOp{

	@Autowired
	private InfopubDeviceInfoExtDao infopubDeviceInfoDao;
	@Autowired
	private SelmQueryHisDao selmQueryHisDao;
	@Autowired
	private SelmBigscreenCacheDao selmBigscreenCacheDao;
	
	
	@Override
	public void execute(Timestamp current) {
		deviceAndHandCount();
		itemOfMonth();
		areaItemOfMonth();
		socialCount();
		areaItemCount();
	}
	
	/**
	 * 各类终端数量及各自办理量
	 * /infopub/deviceinfo/deviceAndHandCount.do
	 * @return
	 */
	public void deviceAndHandCount() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		//政务终端
		conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"政务服务"));
		conds.add(new Condition("idt.ST_PARENT_TYPE_ID",Condition.OT_EQUAL,null));
		BigDecimal zwDeviceAmount = infopubDeviceInfoDao.getAmountByType(conds);
		BigDecimal zwAllRecord = selmQueryHisDao.getSelmQueryHisByType(conds);
		JSONObject obj2 = new JSONObject();
		obj2.put("deviceType","政务终端");
		obj2.put("dCount",zwDeviceAmount);
		obj2.put("iCount",zwAllRecord);
		arr.add(obj2);
		//社会化终端
		conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"银行"));
		conds.add(new Condition("idt.ST_PARENT_TYPE_ID",Condition.OT_EQUAL,null));
		BigDecimal shDeviceAmount = infopubDeviceInfoDao.getAmountByType(conds);
		BigDecimal shAllRecord = selmQueryHisDao.getSelmQueryHisByType(conds);
		JSONObject obj3 = new JSONObject();
		obj3.put("deviceType","社会化终端");
		obj3.put("dCount",shDeviceAmount);
		obj3.put("iCount",shAllRecord);
		arr.add(obj3);
		
		//赋能终端及其办件量
		BigDecimal deviceAmount = zwDeviceAmount.add(shDeviceAmount);
		BigDecimal allRecord = zwAllRecord.add(shAllRecord);
		JSONObject obj1 = new JSONObject();
		obj1.put("deviceType","赋能终端");
		obj1.put("dCount",deviceAmount);
		obj1.put("iCount",allRecord);
		arr.add(obj1);
		obj.put("data", arr);
		
		//添加或更新到大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "deviceinfo";
		String st_tcode = "deviceAndHandCount.do";
		insertData(st_fcode,st_scode,st_tcode,obj);
		
	}
	
	
	/**
	 * 热门服务总量top5
	 * /infopub/deviceinfo/itemOfMonth.do
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void itemOfMonth() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by ST_ITEM_NAME order by count(ST_ITEM_NAME) DESC limit 0,5";
		}else{
			suffix = "group by ST_ITEM_NAME order by count(ST_ITEM_NAME) DESC";
		}
		arr = selmQueryHisDao.itemOfMonth(null, suffix);
		obj.put("data", arr);
		//插入大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "deviceinfo";
		String st_tcode = "itemOfMonth.do";
		insertData(st_fcode,st_scode,st_tcode,obj);
	}
	
	/**
	 * 各区设备当月办件量
	 * /infopub/deviceinfo/areaItemOfMonth.do
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void areaItemOfMonth() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);//当前月的第一天
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("sqh.DT_CREATE",Condition.OT_GREATER_EQUAL,time+" 00:00:00"));
		conds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
		String suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by ia.NM_ORDER DESC";
		arr = selmQueryHisDao.areaItemCount1(conds, suffix);
		obj.put("data", arr);
		//插入大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "deviceinfo";
		String st_tcode = "areaItemOfMonth.do";
		insertData(st_fcode,st_scode,st_tcode,obj);
	}
	
	/**
	 * 社会化服务总量top5
	 * /infopub/deviceinfo/socialCount.do
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void socialCount() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("idt.ST_TYPE_NAME",Condition.OT_LIKE,"银行"));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by idt.ST_TYPE_NAME order by count(idt.ST_TYPE_NAME) DESC limit 0,5";
		}else{
			suffix = "group by idt.ST_TYPE_NAME order by count(idt.ST_TYPE_NAME) DESC";
		}
		arr = selmQueryHisDao.socialCount(conds, suffix);
		obj.put("data", arr);
		//插入大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "deviceinfo";
		String st_tcode = "socialCount.do";
		insertData(st_fcode,st_scode,st_tcode,obj);
	}
	
	/**
	 * 各区设备累计办件量
	 * /infopub/deviceinfo/areaItemCount.do
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	public void areaItemCount() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ia.NM_ORDER",Condition.OT_LESS_EQUAL,16));
		String suffix = "";
		if(DB.getDatabaseName().equalsIgnoreCase("mysql")){
			suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by count(ia.ST_AREA_NAME) DESC limit 0,5";
		}else{
			suffix = "group by ia.ST_AREA_NAME,ia.NM_ORDER order by count(ia.ST_AREA_NAME) DESC";
		}
		 
		arr = selmQueryHisDao.areaItemCount(conds, suffix);
		obj.put("data", arr);
		//插入大屏缓存表
		String st_fcode = "infopub";
		String st_scode = "deviceinfo";
		String st_tcode = "areaItemCount.do";
		insertData(st_fcode,st_scode,st_tcode,obj);
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
