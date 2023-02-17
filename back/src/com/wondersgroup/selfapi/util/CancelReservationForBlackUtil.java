package com.wondersgroup.selfapi.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.NetReservationBlacklist;
import com.wondersgroup.selfapi.bean.NetReservationDatedTemp;
import com.wondersgroup.selfapi.bean.NetReservationVo;
import com.wondersgroup.selfapi.dao.NetReservationBlacklistDao;
import com.wondersgroup.selfapi.dao.NetReservationDatedTempDao;

@Component
public class CancelReservationForBlackUtil {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	@Autowired
	private NetReservationBlacklistDao netReservationBlacklistDao;

	@Autowired
	private NetReservationDatedTempDao netReservationDatedTempDao;

	public void CancelReservationForBlack(NetReservationVo n) {

		// 查询预约信息中的预约了当天没有来办理业务的预约号 进行状态的修改为4
		try {
			// 向临时黑名单表中插入数据
			if (netReservationDatedTempDao != null) {
				// 将预约信存储到临时黑名单信息表
				NetReservationDatedTemp netTemp = new NetReservationDatedTemp();
				// BeanUtils.copyProperties(n,netTemp);
				reservationToDatedTemp(n, netTemp);
				// 初始化集合List<NetReservationDatedTemp>
				List<NetReservationDatedTemp> datedTemps = null;
				Conditions conds1 = Conditions.newAndConditions();
				// 配置文件配置0 手机号查询 1 证件号查询
				String forBlack = Config.get("forBlack");
				if (forBlack == null) {
					forBlack = "0";
				}
				// 根据手机号做判断
				if ("0".equals(forBlack)) {
					// 判断临时事项表中有没有此手机号的黑名单信息
					conds1.add(new Condition(NetReservationDatedTemp.ST_MOBILE,
							Condition.OT_EQUAL, n.getStMobile()));
					datedTemps = netReservationDatedTempDao.query(conds1, null);
				} else {
					// 通过身份证给进行黑名单的判断
					conds1.add(new Condition(
							NetReservationDatedTemp.ST_IDENTITY_NO,
							Condition.OT_EQUAL, n.getStIdentityNo()));
					datedTemps = netReservationDatedTempDao.query(conds1, null);
				}
				// 如果临时黑名单表中没有此手机的黑名单信息
				if (datedTemps.size() == 0) {
					netTemp.setStTempId(UUID.randomUUID().toString());
					netTemp.setNmCount(new BigDecimal(1));
					netTemp.setDtCreate(new Timestamp(System
							.currentTimeMillis()));
					netReservationDatedTempDao.add(netTemp);
				} else {
					// 获取次数 临时黑名单表中的失约次数增1
					NetReservationDatedTemp datedTemp = datedTemps.get(0);
					BigDecimal nuCount = datedTemp.getNmCount();
					// 当临时黑名单事项表中的次数达到多少次了 就删除临时黑名单的数据信息 存到黑名单信息中
					String blackCount = Config.get("blackCount");
					if (blackCount == null) {
						blackCount = "3";
					}
					// 当临时黑名单中的黑名单次数达到配置文件中的 次数的时候 删除临时黑名单中的数据信息
					// 然后插入黑名单信息表
					if (nuCount.intValue() >= (Integer.valueOf(blackCount) - 1)) {
						NetReservationBlacklist info = new NetReservationBlacklist();
						// BeanUtils.copyProperties(datedTemp, info);
						datedTempToBlacklist(datedTemp, info);
						info.setStBlacklistId(UUID.randomUUID().toString());
						info.setDtCreate(new Timestamp(System
								.currentTimeMillis()));
						info.setNmStatus(new BigDecimal(1));
						// 向黑名单表中插入黑名单的数据信息
						if (netReservationBlacklistDao != null) {
							netReservationBlacklistDao.add(info);
						}
						// 然后删除临时黑名单表中的数据信息
						netReservationDatedTempDao.delete(datedTemp
								.getStTempId());
					} else {
						// 如果没有达到想黑名单表插入黑名单信息的话就在临时黑名单表+1
						// datedTemp.setDtUpdate(new
						// Timestamp(System.currentTimeMillis()));
						// datedTemp.setNmCount(new
						// BigDecimal(nuCount.intValue()+1));
						// netReservationDatedTempDao.update(datedTemp);
						// 2
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put(NetReservationDatedTemp.DT_UPDATE,
								new Timestamp(System.currentTimeMillis()));
						map1.put(NetReservationDatedTemp.NM_COUNT, nuCount
								.intValue() + 1);
						Conditions conds2 = Conditions.newAndConditions();
						conds2.add(new Condition(
								NetReservationDatedTemp.ST_TEMP_ID,
								Condition.OT_EQUAL, datedTemp.getStTempId()));
						netReservationDatedTempDao.update(map1, conds2);
					}
				}
			}
			Log.debug("---end---");
		} catch (Exception e) {
			Log.error(e);
		}
	}

	/**
	 * 將預約信息中部分信息存儲到臨時黑名單表中
	 * 
	 * @param info
	 * @param temp
	 */
	private void reservationToDatedTemp(NetReservationVo info,
			NetReservationDatedTemp temp) {
		temp.setNmIdentityType(info.getNmIdentityType());
		temp.setStExt1(info.getStExt1());
		temp.setStExt2(info.getStExt2());
		temp.setStIdentityNo(info.getStIdentityNo());
		temp.setStMobile(info.getStMobile());
		temp.setStUnified(info.getStUnified());
		temp.setStUnit(info.getStUnit());
		temp.setStUserId(info.getStUserId());
		temp.setStUserName(info.getStUserName());
	}

	/**
	 * 将临时黑名单表中的信息存储到黑名单信息表
	 * 
	 * @param temp
	 * @param blacklist
	 */
	private void datedTempToBlacklist(NetReservationDatedTemp temp,
			NetReservationBlacklist blacklist) {
		blacklist.setNmIdentityType(temp.getNmIdentityType());
		blacklist.setStExt1(temp.getStExt1());
		blacklist.setStExt2(temp.getStExt2());
		blacklist.setStIdentityNo(temp.getStIdentityNo());
		blacklist.setStMobile(temp.getStMobile());
		blacklist.setStUnified(temp.getStUnified());
		blacklist.setStUnit(temp.getStUnit());
		blacklist.setStUserId(temp.getStUserId());
		blacklist.setStUserName(temp.getStUserName());
	}

}
