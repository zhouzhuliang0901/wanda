package com.wondersgroup.selfapi.dao;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;

import com.wondersgroup.selfapi.bean.WindowItemInfo;
@Repository
public class WindowItemInfoDao {

	  public static void setProperties(WindowItemInfo info, RecordSet rs){
	        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
	        info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
	        info.setStItemPrefix(rs.getOriginalString("ST_ITEM_PREFIX"));
	        info.setStIncludePath(rs.getOriginalString("ST_INCLUDE_PATH"));
	        info.setNmType(rs.getBigDecimal("NM_TYPE"));
	        info.setNmPrintType(rs.getBigDecimal("NM_PRINT_TYPE"));
	        info.setNmNeedBusinessCode(rs.getBigDecimal("NM_NEED_BUSINESS_CODE"));
	        info.setNmNeedReservation(rs.getBigDecimal("NM_NEED_RESERVATION"));
	        info.setStReservationUrl(rs.getOriginalString("ST_RESERVATION_URL"));
	        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
	        info.setNmNetOrder(rs.getBigDecimal("NM_NET_ORDER"));
	        info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
	        info.setNmCost(rs.getBigDecimal("NM_COST"));
	        info.setStGroupCompleteName(rs.getOriginalString("ST_GROUP_COMPLETE_NAME"));
	        info.setStCredentaialsOuts(rs.getOriginalString("ST_CREDENTAIALS_OUTS"));
	        info.setNmCorrectionDate(rs.getBigDecimal("NM_CORRECTION_DATE"));
	        info.setStLegalTime(rs.getOriginalString("ST_LEGAL_TIME"));
	        info.setStPromiseTime(rs.getOriginalString("ST_PROMISE_TIME"));
	        info.setNmDisplay(rs.getBigDecimal("NM_DISPLAY"));
	        info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
	        info.setNmItemSource(rs.getBigDecimal("NM_ITEM_SOURCE"));
	        info.setStItemType(rs.getOriginalString("ST_ITEM_TYPE"));
	        info.setStLinkId(rs.getOriginalString("ST_LINK_ID"));
	        info.setStExt1(rs.getOriginalString("ST_EXT1"));
	        info.setStExt2(rs.getOriginalString("ST_EXT2"));
	    }
}
