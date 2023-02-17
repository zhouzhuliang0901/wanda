package com.wondersgroup.selfapi.task;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.operator.connector.ZeroOp;
import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.dao.SelfUsingHistoryDao;

@Component
public class SynchronizationHisInfoTask implements ZeroOp{
	
	@Autowired
	private SelfUsingHistoryDao selfUsingHistoryDao;

	@Override
	public void execute(Timestamp current) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = getAllHisInfo();
		JSONArray arr = JSONObject.fromObject(str).optJSONArray("data");
		for(int i = 0;i<arr.size();i++){
			JSONObject o = arr.getJSONObject(i);
			SelfUsingHistory his = (SelfUsingHistory) JSONObject.toBean(o, SelfUsingHistory.class);
			String dateStr = o.optString("dtCreaterStr");
			try {
				his.setDtCreate(new Timestamp(sdf.parse(dateStr).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			selfUsingHistoryDao.add(his);
		}
	}
	
	private static String getAllHisInfo(){
		String body = "";
		String url = RdConfig.get("reindeer.async.addApi.ip")+"/business/selmQueryHis/getTodayHis.do";
        HttpPost httpPost = new HttpPost(url);
        
        CloseableHttpClient closeableHttpClient = PooledHttpUitl.closeableHttpClient;
        CloseableHttpResponse response = null;
        try{
        	response = closeableHttpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
        	Log.debug("访问失败："+e);
		} finally {
            try {
            	if(response != null){
            		// 释放链接
            		httpPost.releaseConnection();
            		response.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
}
