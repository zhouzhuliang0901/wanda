package com.wondersgroup.dataitem.item236012130732.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.dataitem.item236012130732.bean.RecordBookInfo;
import com.wondersgroup.dataitem.item236012130732.dao.RecordBookDao;
import com.wondersgroup.dataitem.item236012130732.service.RecordBookService;

import wfc.service.log.Log;

@Service
public class RecordBookServiceImpl implements RecordBookService{
	
	@Autowired
	private RecordBookDao recordBookDao;
	
	@Override
	public void saveBook(String result, String machineId) {
		if(StringUtils.isNotBlank(result)){
			JSONArray arr = JSONArray.fromObject(result);
			RecordBookInfo book = new RecordBookInfo();
			try{
				JSONObject jsonResult = arr.optJSONObject(0);
				book.setRecordBookNo(jsonResult.getString("jlch"));// 记录册号
				book.setName(jsonResult.getString("xm"));
				book.setIdCard(jsonResult.getString("sfzh"));
				book.setSex(jsonResult.getString("xb"));
				book.setCardNo(jsonResult.getString("kh"));
			} catch (Exception e) {
				book = null;
			}
			if(book != null){
				book.setId(UUID.randomUUID().toString());
				book.setDtCreate(new Timestamp(System.currentTimeMillis()));
				book.setStMachineId(machineId);
				try{
					recordBookDao.save(book);
				} catch (Exception e) {
					Log.debug("数据插入失败，记录册号："+book.getRecordBookNo()+"");
				}
			}
		}
	}

	@Override
	public List<RecordBookInfo> getBookInfoByIdCard(String startTime, String endTime, String machineId) {
		return recordBookDao.RecordBookInfo(startTime,endTime,machineId);
	}

	@Override
	public List<RecordBookInfo> getAllRecordBookInfo() {
		return recordBookDao.getAllRecordBookInfo();
	}

	@Override
	public List<String> getAllMachineById(String machineId) {
		return recordBookDao.getAllDeviceByMac(machineId);
	}
	
	@Override
	public int getHistoryBookInfoByIdCard(String identNo, String range) {
		return recordBookDao.getHistoryBookInfoByIdCard(identNo, range);
	}
}
