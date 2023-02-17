package com.wondersgroup.selfapi.dao;

import org.springframework.stereotype.Repository;

import reindeer.base.bean.WindowItemStatus;

import wfc.service.database.RecordSet;


@Repository
public class WindowItemStatusDao {
	  public static void setProperties(WindowItemStatus info, RecordSet rs){
	        info.setStStatusId(rs.getOriginalString("ST_STATUS_ID"));
	        info.setStStatusNo(rs.getOriginalString("ST_STATUS_No"));
	        info.setStStatusName(rs.getOriginalString("ST_STATUS_NAME"));
	        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
	        info.setStItemSonId(rs.getOriginalString("ST_ITEM_SON_ID"));
	        info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
	        info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
	        info.setNmType(rs.getBigDecimal("NM_TYPE"));
	        info.setNmWindowApplyType(rs.getBigDecimal("NM_WINDOW_APPLY_TYPE"));
	        info.setNmNotifyPromise(rs.getBigDecimal("NM_NOTIFY_PROMISE"));
	        info.setNmExamineType(rs.getBigDecimal("NM_EXAMINE_TYPE"));
	        info.setNmOnTheSpot(rs.getBigDecimal("NM_ON_THE_SPOT"));
	        info.setNmAdvanceServe(rs.getBigDecimal("NM_ADVANCE_SERVE"));
	        info.setNmLeastTime(rs.getBigDecimal("NM_LEAST_TIME"));
	        info.setStChargeStandard(rs.getOriginalString("ST_CHARGE_STANDARD"));
	        info.setStLegalLimit(rs.getOriginalString("ST_LEGAL_LIMIT"));
	        info.setStPromiseLimit(rs.getOriginalString("ST_PROMISE_LIMIT"));
	        info.setNmOnlineDeclare(rs.getBigDecimal("NM_ONLINE_DECLARE"));
	        info.setStUrl(rs.getOriginalString("ST_URL"));
	        info.setNmPrivateNetwork(rs.getBigDecimal("NM_PRIVATE_NETWORK"));
	        info.setNmPretrial(rs.getBigDecimal("NM_PRETRIAL"));
	        info.setStPresetBasis(rs.getOriginalString("ST_PRESET_BASIS"));
	        info.setStApplyTerms(rs.getOriginalString("ST_APPLY_TERMS"));
	        info.setStExamineBasis(rs.getOriginalString("ST_EXAMINE_BASIS"));
	        info.setStFaq(rs.getOriginalString("ST_FAQ"));
	        info.setStRemarks(rs.getOriginalString("ST_REMARKS"));
	        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
	    }
}
