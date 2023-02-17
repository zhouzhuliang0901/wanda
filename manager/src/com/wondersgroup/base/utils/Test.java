package com.wondersgroup.base.utils;

import java.sql.Timestamp;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.business.bean.SelmQueryHis;

public class Test {
	
	public static void main(String[] args){
		DoPostByJSON p = new DoPostByJSON();
		String url = "http://localhost:8080/ac-self-his/business/selmQueryHis/setHis.do";
		SelmQueryHis sqh = new SelmQueryHis();
		sqh.setStQueryHisId(UUID.randomUUID().toString());
		sqh.setStMachineId("1");
		sqh.setStModuleName("1");
		sqh.setStModuleOp("1");
		sqh.setStName("1");
		sqh.setStIdentityNo("1");
		sqh.setStMobile("1");
		sqh.setStAttachId1("1");
		sqh.setStAttachId2("1");
		sqh.setStAttachId3("1");
		sqh.setStAttachId4("1");
		sqh.setStExt1("1");
		sqh.setStExt2("1");
		sqh.setStExt3("1");
		sqh.setStExt4("1");
		sqh.setStExt5("1");
		sqh.setStItemName("1");
		sqh.setStBusinessNo("1");
		sqh.setStSubmitDataId("1");
		sqh.setStDesc("1");
		sqh.setStOpResult("1");
		sqh.setStItemNo("1");
		sqh.setStAssistId("1");
		String json = JSON.toJSONString(sqh);
		JSONObject o  = JSON.parseObject(json);
		try {
			p.doPostByJSONObect(url, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
