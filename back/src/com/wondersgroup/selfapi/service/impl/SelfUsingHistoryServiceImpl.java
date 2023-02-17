package com.wondersgroup.selfapi.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.selfapi.bean.MachineInfo;
import com.wondersgroup.selfapi.bean.SelfUsingHistory;
import com.wondersgroup.selfapi.bean.SelmAttach;
import com.wondersgroup.selfapi.dao.SelfUsingHistoryDao;
import com.wondersgroup.selfapi.service.SelfUsingHistoryService;
import com.wondersgroup.selfapi.service.SelmQueryHisService;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

@Service
public class SelfUsingHistoryServiceImpl implements SelfUsingHistoryService {

	@Autowired
	private SelfUsingHistoryDao selfUsingHistoryDao;
	
	@Autowired
	private SelmQueryHisService selmQueryHisService;

//	@Resource
//	private ProducerService producerService;

	@Override
	public SelfUsingHistory saveOrUpdateSelfUsingHistory(
			SelfUsingHistory selfUsingHistory) {
		if (StringUtils.isEmpty(selfUsingHistory.getStModuleName())) {
			Log.debug("保存使用记录失败，模块名称不能为空，机器ID为："
					+ selfUsingHistory.getStMachineId() + ";操作模块为："
					+ selfUsingHistory.getStModuleName());
			return null;
		} 
//		else if (StringUtils.isEmpty(selfUsingHistory.getStMachineId())) {
//			Log.debug("保存使用记录失败，机器ID不能为空，机器ID为："
//					+ selfUsingHistory.getStMachineId() + ";操作模块为："
//					+ selfUsingHistory.getStModuleName());
//			return null;
//		}
		else if (StringUtils.isEmpty(selfUsingHistory.getStItemName())) {
			Log.debug("保存使用记录失败，事项名称不能为空，机器ID为："
					+ selfUsingHistory.getStMachineId() + ";操作模块为："
					+ selfUsingHistory.getStModuleName());
			return null;
		} else if (StringUtils.isEmpty(selfUsingHistory.getStModuleOp())) {
			Log.debug("保存使用记录失败，操作类型不能为空，机器ID为："
					+ selfUsingHistory.getStMachineId() + ";操作模块为："
					+ selfUsingHistory.getStModuleName());
			return null;
		} else if (selfUsingHistory.getStModuleOp().indexOf("办理") != -1
				&& (StringUtils.isEmpty(selfUsingHistory.getStName()) || StringUtils
						.isEmpty(selfUsingHistory.getStIdentityNo()))) {
			Log.debug("保存使用记录失败，办理事项办理人信息不能为空，机器ID为："
					+ selfUsingHistory.getStMachineId() + ";操作模块为："
					+ selfUsingHistory.getStModuleName());
			return null;
		} else if ("undefined".equals(selfUsingHistory.getStName())
				|| "undefined".equals(selfUsingHistory.getStName())) {
			selfUsingHistory.setStHisId(UUID.randomUUID().toString());
			selfUsingHistory.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			selfUsingHistoryDao.addEx(selfUsingHistory);
			return selfUsingHistory;
		} else {
			try {
				String hisId = selfUsingHistory.getStHisId();
				if (StringUtils.isEmpty(hisId)) {
					selfUsingHistory.setStHisId(UUID.randomUUID().toString());
					if (selfUsingHistory.getDtCreate() == null) {
						selfUsingHistory.setDtCreate(new Timestamp(System
								.currentTimeMillis()));
					}
					String stModuleOp = selfUsingHistory.getStModuleOp();
					if ("查询 打印".equals(stModuleOp)) {
						stModuleOp = "查询+打印";
						selfUsingHistory.setStModuleOp(stModuleOp);
					}
					selmQueryHisService.setSelmQueryHis(selfUsingHistory);
//					selfUsingHistoryDao.add(selfUsingHistory);
//					if (1 == total) {
//						producerService.sendOceanQueue("applyInfo", JSONObject
//								.fromObject(selfUsingHistory).toString());
//					}
				} else {
					Log.debug("记录已存在，机器ID为："
							+ selfUsingHistory.getStMachineId() + ";操作模块为："
							+ selfUsingHistory.getStModuleName());
				}
				return selfUsingHistory;
			} catch (Exception e) {
				Log.debug("保存使用记录失败，机器ID为：" + selfUsingHistory.getStMachineId()
						+ ";操作模块为：" + selfUsingHistory.getStModuleName());
				Log.debug(e);
				return null;
			}
		}
	}

	@Override
	public JSONArray queryUsingHistory(Date startTime, Date endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray arr = selfUsingHistoryDao.getAllMachinesAndTimes(
				sdf.format(startTime), sdf.format(endTime));
		for (int iLoop = 0; iLoop < arr.size(); iLoop++) {
			JSONObject json = arr.optJSONObject(iLoop);
			String machineID = json.optString("ST_MACHINE_ID");
			JSONArray moduleArr = selfUsingHistoryDao.getModuleTimesInMachine(
					machineID, sdf.format(startTime), sdf.format(endTime));
			json.put("ST_MODULE_TIMES", moduleArr);
		}
		return arr;
	}

	@Override
	public List<SelfUsingHistory> queryUsingHistoryList(Date startTime,
			Date endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return selfUsingHistoryDao.queryUsingHistoryList(sdf.format(startTime),
				sdf.format(endTime));
	}

	@Override
	public SelmAttach uploadStuff(String linkId, String fileName,
			String fileType, byte[] file, int len) {
		SelmAttach attach = new SelmAttach();
		String stAttachId = UUID.randomUUID().toString();
		attach.setStAttachid(stAttachId);
		attach.setStLinkTable("SELM_QUERY_HIS");
		attach.setStLinkId(linkId);
		attach.setStAttachType(fileType);
		attach.setStFileName(fileName);
		attach.setStFileSize(Integer.valueOf(len).toString());
		attach.setBlContent(file);
		attach.setBlSmallContent(new byte[] {});
		attach.setStFileType(fileType);
		attach.setDtCreate(new Timestamp(System.currentTimeMillis()));

		// 更新附件ID
		SelfUsingHistory info = new SelfUsingHistory();
		if (StringUtils.isNotEmpty(linkId)) {
			info = selfUsingHistoryDao.queryUsingHistoryById(linkId);
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_QUERY_HIS_ID", Condition.OT_EQUAL,
					linkId));
			if (info != null) {
				String feild = "";
				if (StringUtils.isEmpty(info.getStAttachId1())) {
					feild = SelfUsingHistory.ST_ATTACH_ID1 + " = '"
							+ stAttachId + "'";
				} else if (StringUtils.isEmpty(info.getStAttachId2())) {
					feild = SelfUsingHistory.ST_ATTACH_ID2 + " = '"
							+ stAttachId + "'";
				} else if (StringUtils.isEmpty(info.getStAttachId3())) {
					feild = SelfUsingHistory.ST_ATTACH_ID3 + " = '"
							+ stAttachId + "'";
				} else if (StringUtils.isEmpty(info.getStAttachId4())) {
					feild = SelfUsingHistory.ST_ATTACH_ID4 + " = '"
							+ stAttachId + "'";
				}

				if (StringUtils.isNotEmpty(feild)) {
					selfUsingHistoryDao.update(feild, conds, linkId);
				}
			}
		}
		selfUsingHistoryDao.saveAttach(attach);

		return attach;
	}

	@Override
	public SelmAttach saveApplyResult(String linkId, String content) {
		SelmAttach attach = new SelmAttach();
		String stAttachId = UUID.randomUUID().toString();
		attach.setStAttachid(stAttachId);
		attach.setStLinkTable("SELM_QUERY_HIS");
		attach.setStLinkId(linkId);
		attach.setClContent(content);
		// attach.setBlContent(new byte[]{});
		// attach.setBlSmallContent(new byte[]{});
		attach.setDtCreate(new Timestamp(System.currentTimeMillis()));
		selfUsingHistoryDao.saveAttach(attach);

		SelfUsingHistory info = new SelfUsingHistory();
		if (StringUtils.isNotEmpty(linkId)) {
			info = selfUsingHistoryDao.queryUsingHistoryById(linkId);
			if (info != null) {
				String feild = "";
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_QUERY_HIS_ID", Condition.OT_EQUAL,
						linkId));
				if (StringUtils.isEmpty(info.getStSubmitDataId())) {
					feild = SelfUsingHistory.ST_SUBMIT_DATA_ID + " = '"
							+ stAttachId + "'";
				}
				if (StringUtils.isNotEmpty(feild)) {
					selfUsingHistoryDao.update(feild, conds, linkId);
				}
			}
		}
		return attach;
	}

	@Override
	public String getBusinessResultStringById(String businessId) {
		String clContent = selfUsingHistoryDao
				.getBusinessResultStringById(businessId);
		return clContent;
	}

	@Override
	public byte[] getAttchFileById(String attachId) {
		byte[] file = selfUsingHistoryDao.getAttchFileById(attachId);
		return file;
	}

	@Override
	public MachineInfo getMachineInfo(String machineMAC) {
		return selfUsingHistoryDao.getmachineInfoByMAC(machineMAC);
	}

}
