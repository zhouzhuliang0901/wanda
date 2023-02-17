package com.wondersgroup.dataitem.item283852484724.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.dataitem.item283852484724.bean.SelmSatisfactionInfo;
import com.wondersgroup.dataitem.item283852484724.dao.SatisfactionEvaluationDao;
import com.wondersgroup.dataitem.item283852484724.service.SatisfactionEvaluationService;

@Service
public class SatisfactionEvaluationServiceImpl implements
		SatisfactionEvaluationService {
	
	@Autowired
	private SatisfactionEvaluationDao satisfactionEvaluationDao;

	@Override
	public int addSatisfaction(String machineMAC, String nmMachine,
			String nmAppearance, String nmOperation, String nmScreen,
			String context, String name, String idCard, String phone,
			String stEtx1, String stEtx2, String stEtx3) {
		SelmSatisfactionInfo selmSatisfactionInfo = new SelmSatisfactionInfo();
		selmSatisfactionInfo.setStSatisfactionId(UUID.randomUUID().toString());
		selmSatisfactionInfo.setNmSatisfactionMachine(new BigDecimal(nmMachine));
		selmSatisfactionInfo.setNmSatisfactionAppearacne(new BigDecimal(nmAppearance));
		selmSatisfactionInfo.setNmSatisfactionOperation(new BigDecimal(nmOperation));
		selmSatisfactionInfo.setNmSatisfactionScreen(new BigDecimal(nmScreen));
		selmSatisfactionInfo.setStSatisfactionContext(context);
		selmSatisfactionInfo.setStEvaluateMachineMAC(machineMAC);
		selmSatisfactionInfo.setStEvaluateName(name);
		selmSatisfactionInfo.setStEvaluateIdCard(idCard);
		selmSatisfactionInfo.setStEvaluatePhone(phone);
		selmSatisfactionInfo.setDtEvaluateTime(new Timestamp(System.currentTimeMillis()));
		selmSatisfactionInfo.setStExt1(stEtx1);
		selmSatisfactionInfo.setStExt2(stEtx2);
		selmSatisfactionInfo.setStExt3(stEtx3);
		return satisfactionEvaluationDao.addSatisfaction(selmSatisfactionInfo);
	}

	@Override
	public JSONObject getMachineInfoByMAC(String machineMac) {
		return satisfactionEvaluationDao.getMachineInfoByMAC(machineMac);
	}

}
