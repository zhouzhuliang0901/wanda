package com.wondersgroup.certCabinet.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.certCabinet.bean.CabinetInfo;
import com.wondersgroup.certCabinet.bean.SelmDelivery;
import com.wondersgroup.certCabinet.bean.SelmDeliveryHistory;
import com.wondersgroup.certCabinet.dao.CertCabinetDao;
import com.wondersgroup.certCabinet.service.CertCabinetService;
import com.wondersgroup.certCabinet.table.CertCabinet;
import com.wondersgroup.certCabinet.util.SendMesageUtil;

@Service
public class CertCabinetServiceImpl implements CertCabinetService{
	
	@Autowired
	private CertCabinetDao certCabinetDao;
	
	@Override
	public int addCabinetInfo(HttpServletRequest req) throws IOException{
		String stCabinetMac = req.getParameter("stCabinetMac");
		String stCabinetNum = req.getParameter("stCabinetNum");
		String stCabinetNo = req.getParameter("stCabinetNo");
		String nmCabinet = req.getParameter("nmCabinet");
		String stCabinetStreet = req.getParameter("stCabinetStreet");
		String stStreetAddress = req.getParameter("stStreetAddress");
		String stContacts = req.getParameter("stContacts");
		String stPhone = req.getParameter("stPhone");
		String stExt1 = req.getParameter("stExt1");
		String stExt2 = req.getParameter("stExt2");
		String stExt3 = req.getParameter("stExt3");
		String stExt4 = req.getParameter("stExt4");
		if(StringUtils.isNotEmpty(stCabinetStreet)){
			stCabinetStreet = URLDecoder.decode(stCabinetStreet, "utf-8");
		}
		if(StringUtils.isNotEmpty(stStreetAddress)){
			stStreetAddress = URLDecoder.decode(stStreetAddress, "utf-8");
		}
		if(StringUtils.isNotEmpty(stContacts)){
			stContacts = URLDecoder.decode(stContacts, "utf-8");
		}
		
		CabinetInfo cabinetInfo = certCabinetDao.queryCaCabinetInfo(stCabinetMac);
		int result = 0;
		if(cabinetInfo == null){
			cabinetInfo = new CabinetInfo();
			cabinetInfo.setStCabinetId(UUID.randomUUID().toString());
			cabinetInfo.setStCabinetMac(stCabinetMac);
			cabinetInfo.setStCabinetNum(stCabinetNum);
			cabinetInfo.setStCabinetNo(stCabinetNo == null ? "" : stCabinetNo);
			cabinetInfo.setNmCabinet(new BigDecimal(nmCabinet));
			cabinetInfo.setStCabinetStreet(stCabinetStreet);
			cabinetInfo.setStStreetAddress(stStreetAddress);
			cabinetInfo.setStContacts(stContacts);
			cabinetInfo.setStPhone(stPhone);
			cabinetInfo.setDtCreat(new Timestamp(System.currentTimeMillis()));
			cabinetInfo.setStExt1(stExt1 == null ? "" : stExt1);
			cabinetInfo.setStExt2(stExt2 == null ? "" : stExt2);
			cabinetInfo.setStExt3(stExt3 == null ? "" : stExt3);
			cabinetInfo.setStExt4(stExt4 == null ? "" : stExt4);
			result = certCabinetDao.addCabinetInfo(cabinetInfo);
		} else {
			String stCabinetId = cabinetInfo.getStCabinetId();
			// TODO
//			String oldStCabinetNum = cabinetInfo.getStCabinetNum();			
			Conditions conds = Conditions.newAndConditions();
		    conds.add(new Condition("ST_CABINET_ID", Condition.OT_EQUAL, stCabinetId));
			String feild = CabinetInfo.ST_CABINET_MAC + " = ?,"
						 + CabinetInfo.ST_CABINET_NUM + " = ?,"
						 + CabinetInfo.ST_CABINET_NO + " = ?,"
						 + CabinetInfo.NM_CABINET + " = ?,"
						 + CabinetInfo.ST_CABINET_STREET + " = ?,"
						 + CabinetInfo.ST_STREET_ADDRESS + " = ?,"
						 + CabinetInfo.ST_CONTACTS + " = ?,"
						 + CabinetInfo.ST_PHONE + " = ?,"
						 + CabinetInfo.DT_CREAT + " = ?,"
						 + CabinetInfo.ST_EXT1 + " = ?,"
						 + CabinetInfo.ST_EXT2 + " = ?,"
						 + CabinetInfo.ST_EXT3 + " = ?,"
						 + CabinetInfo.ST_EXT4 + " = ?";
			Object[] params = new Object[]{stCabinetMac,stCabinetNum,stCabinetNo == null ? "" : stCabinetNo,new BigDecimal(nmCabinet),
					stCabinetStreet,stStreetAddress,stContacts,stPhone,new Timestamp(System.currentTimeMillis()),stExt1 == null ? "" : stExt1,
							stExt2 == null ? "" : stExt2,stExt3 == null ? "" : stExt3,stExt4 == null ? "" : stExt4,stCabinetId};

			result = certCabinetDao.updateCabinetInfo(feild, conds, params);
		}
		
		if(1 == result){
			String[] numArr = stCabinetNum.split(",");
			for(String str : numArr){
				addCabinets(stCabinetMac, str);
			}
		}
		return result;
	}
	
	@Override
	public int addCabinets(String machineId, String cabinetNo) {
		Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
        conds.add(new Condition("ST_CABINET_NO", Condition.OT_EQUAL, cabinetNo));
        List<SelmDelivery> list = certCabinetDao.querySelmDelivery(conds, null);
        int result = 0;
        if(list.size() == 0){
        	SelmDelivery info = new SelmDelivery();
        	info.setStDeliveryId(UUID.randomUUID().toString());
        	info.setStMachineId(machineId);
        	info.setStCabinetNo(cabinetNo);
        	info.setNmStatus(new BigDecimal(0));
        	info.setDtCreate(new Timestamp(System.currentTimeMillis()));
        	result = certCabinetDao.addCabinets(info);
        	if(result != 1){
        		Log.debug("设备（"+machineId+"）添加柜号"+cabinetNo+"失败！");
        	}
        } else {
        	result = 10;
        	Log.debug("设备（"+machineId+"）柜号"+cabinetNo+"已存在！");
        }
        return result;		
	}
	
	@Override
	public List<String> queryEmptyCabinet(String machineId) {
		return certCabinetDao.queryEmptyCabinet(machineId);
	}

	@Override
	public int store(HttpServletRequest req) {
		int resultCode = Integer.valueOf(CertCabinet.StoreFlag.MINUS_ONE.getValue());
		String machineId = req.getParameter("stMachineId");
		String cabinetNo = req.getParameter("stCabinetNo");
		String nmCertType = req.getParameter("nmCertType");
		String stApplyId = req.getParameter("stApplyId");
		// 收件人信息
		String stReceiverName = req.getParameter("stReceiverName");
		String stReceiverIdcard = req.getParameter("stReceiverIdcard");
		String stReceiverPhone = req.getParameter("stReceiverPhone");
		// 投件人信息
		String stSenderName = req.getParameter("stSenderName");
		String stSenderId = req.getParameter("stSenderId");
		String stSenderPhone = req.getParameter("stSenderPhone");
		String stReceiveNum = "";
		if(StringUtils.isEmpty(cabinetNo)){
			// 柜号为空
			resultCode = Integer.valueOf(CertCabinet.StoreFlag.ONE.getValue());
			return resultCode;
		}
		Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
        conds.add(new Condition("ST_CABINET_NO", Condition.OT_EQUAL, cabinetNo));
        List<SelmDelivery> list = certCabinetDao.querySelmDelivery(conds, null);
        if(list.size() == 0){
        	// 柜号不存在
        	resultCode = Integer.valueOf(CertCabinet.StoreFlag.TWO.getValue());
        	return resultCode;
        } else {
        	SelmDelivery info = list.get(0);
        	if(info.getNmStatus().intValue() == 1){
        		// 不是空柜子
        		resultCode = Integer.valueOf(CertCabinet.StoreFlag.THREE.getValue());
        		return resultCode;
        	} else {
        		if(StringUtils.isNotEmpty(stReceiverName)){
        			try {
						stReceiverName = URLDecoder.decode(stReceiverName, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
        		}
        		if(StringUtils.isNotEmpty(stSenderName)){
        			try {
        				stSenderName = URLDecoder.decode(stSenderName, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
        		}
        		info.setStReceiverName(stReceiverName);
        		info.setStReceiverIdcard(stReceiverIdcard);
        		info.setStReceiverPhone(stReceiverPhone);
        		info.setStSenderName(stSenderName);
        		info.setStSenderId(stSenderId);
        		info.setStSenderPhone(stSenderPhone);
        		info.setNmStatus(new BigDecimal(1));
        		info.setNmCertType(new BigDecimal(nmCertType));
        		info.setStApplyId(stApplyId);
				stReceiveNum = String.valueOf((Math.random()*9+1)*100000)
						.substring(0, 6);
				stReceiveNum = checkReceiveNum(stReceiveNum, machineId);
				info.setStReceiveNum(stReceiveNum);
				info.setDtStore(new Timestamp(System.currentTimeMillis()));
				certCabinetDao.updateCabinets(info);
				resultCode = Integer.valueOf(CertCabinet.StoreFlag.ZORE.getValue());
				// 记录存放历史
				SelmDeliveryHistory historyInfo = new SelmDeliveryHistory();
				historyInfo.setStDeliveryId(UUID.randomUUID().toString());
				historyInfo.setStMachineId(machineId);
				historyInfo.setStCabinetNo(cabinetNo);
				historyInfo.setStReceiverName(stReceiverName);
				historyInfo.setStReceiverIdcard(stReceiverIdcard);
				historyInfo.setStReceiverPhone(stReceiverPhone);
				historyInfo.setStSenderName(stSenderName);
				historyInfo.setStSenderId(stSenderId);
				historyInfo.setStSenderPhone(stSenderPhone);
				historyInfo.setNmStatus(info.getNmStatus());
				historyInfo.setNmCertType(info.getNmCertType());
				historyInfo.setStApplyId(stApplyId);
				historyInfo.setStReceiveNum(stReceiveNum);
				historyInfo.setDtCreate(info.getDtCreate());
				historyInfo.setDtStore(info.getDtStore());
				certCabinetDao.addCabinetsHistory(historyInfo);
        	}
        }
        
        CabinetInfo cabinetInfo = certCabinetDao.queryCaCabinetInfo(machineId);
        String msgContent = "";
        if("0".equals(nmCertType)){
        	msgContent = "你的证件已经存放在"+cabinetInfo.getStStreetAddress()+"证照柜"+cabinetNo+"（柜子号）号柜子，取件码："+stReceiveNum+"，请您及时取出！" +
						"如有问题请联系："+cabinetInfo.getStContacts()+cabinetInfo.getStPhone()+"【"+cabinetInfo.getStCabinetStreet()+"】";
        } else if("1".equals(nmCertType)){
        	msgContent = "办事人"+stSenderName+"已在柜号"+cabinetNo+"放件，取件码："+stReceiveNum+"，" +
						"请及时取出！如有问题请联系："+cabinetInfo.getStContacts()+cabinetInfo.getStPhone()+"【"+cabinetInfo.getStCabinetStreet()+"】";
        }
        Log.debug("证照短信内容："+msgContent);
        SendMesageUtil.forwardSendMesage(stReceiverPhone, msgContent);
        return resultCode;
	}
	
	@Override
	public JSONObject take(HttpServletRequest req) {
		int resultCode = Integer.valueOf(CertCabinet.TakeFlag.MINUS_ONE.getValue());
		String machineId = req.getParameter("stMachineId");
		String nmCertType = req.getParameter("nmCertType");
		String stReceiveNum = req.getParameter("stReceiveNum");
		String stCabinetNo = req.getParameter("stCabinetNo");
		String stApplyId = req.getParameter("stApplyId");
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(stCabinetNo) && StringUtils.isEmpty(stReceiveNum)){
			// 取件吗和柜号不能同时为空
			resultCode = Integer.valueOf(CertCabinet.TakeFlag.ONE.getValue());
			json.put("resultCode", resultCode);
			json.put("stCabinetNo", "");
			return json;
		}
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
		conds.add(new Condition("NM_CERT_TYPE", Condition.OT_EQUAL, nmCertType));
		conds.add(new Condition("NM_STATUS", Condition.OT_EQUAL, 1));
		if(StringUtils.isNotEmpty(stReceiveNum)){
			conds.add(new Condition("ST_RECEIVE_NUM", Condition.OT_EQUAL, stReceiveNum));
		}
		if(StringUtils.isNotEmpty(stCabinetNo)){
			conds.add(new Condition("ST_CABINET_NO", Condition.OT_EQUAL, stCabinetNo));
		}
		if(StringUtils.isNotEmpty(stApplyId)){
			conds.add(new Condition("ST_APPLY_ID", Condition.OT_EQUAL, stApplyId));
		}
		List<SelmDelivery> list = certCabinetDao.querySelmDelivery(conds, null);
		if(list.size() == 0){
			// 未找到存放证照（材料）的柜子
			resultCode = Integer.valueOf(CertCabinet.TakeFlag.TWO.getValue());
			json.put("resultCode", resultCode);
			json.put("stCabinetNo", "");
		} else {
			// 先保存历史记录，再清空柜子信息
			SelmDelivery info = list.get(0);
			// 保存历史记录
			boolean b = true;
			try{
				List<SelmDeliveryHistory> historyList = certCabinetDao.querySelmDeliveryHistory(conds, null);
				SelmDeliveryHistory historyInfo = historyList.get(0);
				historyInfo.setNmStatus(new BigDecimal(2));
				historyInfo.setDtTake(new Timestamp(System.currentTimeMillis()));
				certCabinetDao.updateCabinetsHistory(historyInfo);
			} catch (Exception e) {
				Log.debug(e);
				b = false;
			}
			if(b){
				// 证照（材料）取出，清空柜子信息
				info.setStReceiverName(null);
	    		info.setStReceiverIdcard(null);
	    		info.setStReceiverPhone(null);
	    		info.setStSenderName(null);
	    		info.setStSenderId(null);
	    		info.setStSenderPhone(null);
	    		info.setNmCertType(null);
	    		info.setStApplyId(null);
				info.setStReceiveNum(null);
				info.setDtStore(null);
	    		// 柜子状态更新为待存
	    		info.setNmStatus(new BigDecimal(0));
				certCabinetDao.updateCabinets(info);
				resultCode = Integer.valueOf(CertCabinet.TakeFlag.ZORE.getValue());
				stCabinetNo = info.getStCabinetNo();
				json.put("resultCode", resultCode);
				json.put("stCabinetNo", stCabinetNo);
			} else {
				resultCode = Integer.valueOf(CertCabinet.TakeFlag.THREE.getValue());
				json.put("resultCode", resultCode);
				json.put("stCabinetNo", "");
			}
		}
		return json;
	}
	
	@Override
	public List<SelmDelivery> queryCabinet(Conditions conds, String suffix) {
		return certCabinetDao.querySelmDelivery(conds, suffix);
	}
	
	public String checkReceiveNum(String stReceiveNum, String machineId){
		//六位随机取件码
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MACHINE_ID", Condition.OT_EQUAL, machineId));
		conds.add(new Condition("ST_RECEIVE_NUM", Condition.OT_EQUAL, stReceiveNum));
		List<SelmDelivery> slist = certCabinetDao.querySelmDelivery(conds, null);
		if(slist.size() > 0){
			stReceiveNum = String.valueOf((Math.random()*9+1)*100000).substring(0, 6);
			checkReceiveNum(stReceiveNum, machineId);
		}
		return stReceiveNum;
	}

	@Override
	public CabinetInfo getByMacId(String machineId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_CABINET_MAC", Condition.OT_EQUAL, machineId));
        List<CabinetInfo> list = certCabinetDao.query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
	}
}
