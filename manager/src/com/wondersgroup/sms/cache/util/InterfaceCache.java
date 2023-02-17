package com.wondersgroup.sms.cache.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;


import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.sms.cache.bean.SelmBigscreenCache;
import com.wondersgroup.sms.cache.dao.SelmBigscreenCacheDao;

import coral.base.quartz.Task;
import coral.base.quartz.TaskScheduled;

/**
 * 每隔5小时获取一次接口数据
 * @author biany
 *
 */
@Component
@TaskScheduled(cron = "0 0 */10 * * ?")
//@TaskScheduled(cron = "0 0 */1 * * ?")
//@TaskScheduled(cron = "0 0/10 * * * ? ")
public class InterfaceCache implements Task{
	
	SelmBigscreenCacheDao sbcd = new SelmBigscreenCacheDao();
	InterfaceMethod interfaceMethod = new InterfaceMethod();
	

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//        try{
            System.out.println(1);
            JSONObject aqh = interfaceMethod.areaQueryHis();
            areaQueryHis(aqh);
            System.out.println(2);
            JSONObject lead = interfaceMethod.leading();
            leading(lead);
            System.out.println(3);
            JSONObject atd = interfaceMethod.addresslistTypeDevice();
            addresslistTypeDevice(atd);
            System.out.println(4);
            JSONObject sqt = interfaceMethod.selmQuertTop();
            areaQueryTop(sqt);
            System.out.println(5);
//        }catch(Exception e){
//            System.out.println(e);
//            e.printStackTrace();
//        }catch(Error e){
//            System.out.println(e);
//            e.printStackTrace();
//        }catch(Throwable t){
//            System.out.println(t);
//            t.printStackTrace();
//        }

	}
	
	/**
	 * 办件量top20数据持久化
	 * /business/selmQueryHis/selmQuertTop.do
	 */
	public void areaQueryTop(JSONObject obj){
		insertData("business","selmQueryHis", "selmQuertTop.do",obj);
	}
	
	/**
	 * 30天办件量数据持久化
	 * /business/selmQueryHis/areaQueryHis.do
	 * @param obj
	 */
	public void areaQueryHis(JSONObject obj){
		insertData("business","selmQueryHis", "areaQueryHis.do",obj);
	}
	
	/**
	 * 首页头部数据持久化
	 * /infopub/home/leading.do
	 */
	public void leading(JSONObject obj){
		insertData("infopub","home","leading.do",obj);
	}
	
	/**
	 * 区数据
	 * /statistics/selmStatistics/addresslistTypeDevice.do
	 */
	public void addresslistTypeDevice(JSONObject obj){
		insertData("statistics","selmStatistics","addresslistTypeDevice.do",obj);
	}
	
	
	
	public void insertData(String fCode, String sCode, String tCode,JSONObject obj){
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_FCODE",Condition.OT_EQUAL,fCode));
		conds.add(new Condition("ST_SCODE",Condition.OT_EQUAL,sCode));
		conds.add(new Condition("ST_TCODE",Condition.OT_EQUAL,tCode));
		List<SelmBigscreenCache> sbc = sbcd.query(conds, null);
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
			sbcd.add(info);
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ST_JSON",obj.toString());
			map.put("DT_UPDATE",new Timestamp(System.currentTimeMillis()));
			sbcd.update(map, conds);
		}
		
	}
	
	
	
	
	
	
	
}
