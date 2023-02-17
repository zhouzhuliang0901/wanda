package com.wondersgroup.selfapi.dao;

import org.springframework.stereotype.Repository;

import reindeer.base.bean.WindowItemStuff;

import wfc.service.database.RecordSet;



/**
 * 事项申请材料表
 */
@Repository
public class WindowItemStuffDao {
	  public static void setProperties(WindowItemStuff info, RecordSet rs){
	        info.setStStuffId(rs.getOriginalString("ST_STUFF_ID"));
	        info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
	        info.setStStuffName(rs.getOriginalString("ST_STUFF_NAME"));
	        info.setNmMust(rs.getBigDecimal("NM_MUST"));
	        info.setNmOriginal(rs.getBigDecimal("NM_ORIGINAL"));
	        info.setNmCopy(rs.getBigDecimal("NM_COPY"));
	        info.setNmUpload(rs.getBigDecimal("NM_UPLOAD"));
	        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
	        info.setNmSample(rs.getBigDecimal("NM_SAMPLE"));
	        info.setStFormalId(rs.getOriginalString("ST_FORMAL_ID"));
	        info.setNmOrganNodeId(rs.getBigDecimal("NM_ORGAN_NODE_ID"));
	        info.setStExamineBasis(rs.getOriginalString("ST_EXAMINE_BASIS"));
	        info.setStFaq(rs.getOriginalString("ST_FAQ"));
	        info.setStSource(rs.getOriginalString("ST_SOURCE"));
	        info.setStRefundStatus(rs.getOriginalString("ST_REFUND_STATUS"));
	        info.setStExt1(rs.getOriginalString("ST_EXT1"));
	        info.setStExt2(rs.getOriginalString("ST_EXT2"));
	        info.setStCertCode(rs.getOriginalString("ST_CERT_CODE"));
	        info.setStCertName(rs.getOriginalString("ST_CERT_NAME"));
	        info.setNmType(rs.getBigDecimal("NM_TYPE"));
	    }
}
