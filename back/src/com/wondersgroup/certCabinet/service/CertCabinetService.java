package com.wondersgroup.certCabinet.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import wfc.service.database.Conditions;

import com.wondersgroup.certCabinet.bean.CabinetInfo;
import com.wondersgroup.certCabinet.bean.SelmDelivery;

public interface CertCabinetService {
	
	/**
	 * 添加柜子信息
	 * @param machineId
	 * @param str
	 */
	int addCabinets(String machineId, String cabinetNo);
	
	/**
	 * 查询空柜子列表
	 * @param machineId
	 */
	List<String> queryEmptyCabinet(String machineId);
	
	/**
	 * 开柜-存
	 * @param req
	 */
	int store(HttpServletRequest req);
	
	/**
	 * 开柜-取
	 * @param req
	 * @return
	 */
	JSONObject take(HttpServletRequest req);
	
	/**
	 * 查询放证列表
	 * @param machineId
	 * @param stCabinetNo
	 * @param stSenderId
	 * @return
	 */
	List<SelmDelivery> queryCabinet(Conditions conds, String suffix);
	
	/**
	 * 保存证照柜基本信息
	 * @param req
	 * @return
	 */
	int addCabinetInfo(HttpServletRequest req) throws IOException;

	CabinetInfo getByMacId(String machineId);
}
