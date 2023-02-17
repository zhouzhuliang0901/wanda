package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.WindowZhallItem;

@Repository
public class GuidanceDao {

	private Connection con = null;

	
	/**
	 * 方法描述：获取可自助申报的所有部门列表
	 * @param pageSize
	 * @param currentPage
	 * @return page
	 */
	public OrganNodeInfoPage getOrganListForDeclarePage(Integer pageSize,
			Integer currentPage) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		/*String sql = " SELECT c.CODE,c.DESCRIPTION,c.NAME,c.NM_ORDER FROM WINDOW_ZHALL_ITEM_BZ w1 "
				+ " JOIN WINDOW_ZHALL_ITEM_DETAIL_BZ w2 ON w1.ST_ZHALL_ID = w2.ST_ZHALL_ID "
				+ " JOIN CS_ORGAN_NODE_XH c ON w1.ST_DEAL_DEPT = c.CODE "
				+ " WHERE  w1.ST_REMOVE = 0  AND w1.ST_ISCENTER = 1 "
				+ " AND w1.ST_ITEM_TEN_NO IS NOT NULL  AND w1.ST_ITEM_TYPE = '审批' "
				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY c.CODE,c.DESCRIPTION,c.NAME,c.NM_ORDER "
				+ " ORDER BY c.NM_ORDER ASC ";*/
		String sql = "SELECT ST_ITEM_TYPE_ID, ST_ITEM_TYPE_NAME FROM selm_item_type WHERE ST_PARENT_ID = '3'";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql);
		} else {
			rs = SQL.execute(con, sql);
		}
		OrganNodeInfoPage page = new OrganNodeInfoPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			/*info.setOrganCode(rs.getOriginalString("CODE"));
			info.setOrganName(rs.getOriginalString("NAME"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));*/
			info.setOrganId(rs.getOriginalString("ST_ITEM_TYPE_ID"));
			info.setOrganName(rs.getOriginalString("ST_ITEM_TYPE_NAME"));
			info.setDescription(rs.getOriginalString("ST_ITEM_TYPE_NAME"));
			organNodeInfoList.add(info);
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}


	
	/**
	 * 方法描述：获取可自助申报的所有事项列表，若数据过多可分页返回，每页8条数据
	 * @param pageSize
	 * @param currentPage
	 * @return page
	 */
	public ItemSetPage getAllItemListForPage(Integer pageSize,
			Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		/*String sql = " SELECT w1.ST_ITEM_NO,w1.ST_ITEM_NAME,c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION FROM WINDOW_ZHALL_ITEM_BZ w1 "
				+ " JOIN WINDOW_ZHALL_ITEM_DETAIL_BZ w2 ON w1.ST_ZHALL_ID = w2.ST_ZHALL_ID "
				+ " JOIN CS_ORGAN_NODE_XH c ON w1.ST_DEAL_DEPT = c.CODE "
				+ " WHERE  w1.ST_REMOVE = 0 AND w1.ST_ISCENTER = 1 "
				+ " AND w1.ST_ITEM_TEN_NO IS NOT NULL AND w1.ST_ITEM_TYPE = '审批' "
				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY w1.ST_ITEM_NO,w1.ST_ITEM_NAME,c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION ";*/
		String sql = "SELECT ST_ITEM_NO,ST_ITEM_NAME FROM selm_item WHERE NM_TYPE = 2 ";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, pageSize, currentPage);
		}else {
			rs = SQL.execute(con, sql, pageSize, currentPage);
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			// info.setStItemTenNo(rs.getOriginalString("ST_ITEM_TEN_NO"));
			// ItemSetPage itemSetPage = getSubItemSetList(itemNo,
			// Integer.MAX_VALUE / 2, 1);
			// if (itemSetPage.getItemSetList().size() > 0) {
			// info.setNmType("1");
			// } else {
			// info.setNmType("0");
			// }
			/*info.setOrganCode(rs.getOriginalString("CODE"));
			info.setPlaceCode(rs.getOriginalString("PLACECODE"));
			info.setOrganName(rs.getOriginalString("DESCRIPTION"));*/
			itemSetList.add(info);
		}
		page.setItemSetList(itemSetList);
		return page;
	}



	/**
	 * 方法描述：获取某事项的办事指南
	 * @param stZhallId
	 * @return WindowZhallItemDetailExt
	 */
	/*public WindowZhallItemDetailExt getGuideInfoByZhallIdExt(String stZhallId) {
		WindowZhallItemDetailExt w = new WindowZhallItemDetailExt();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_ZHALL_ID", Condition.OT_EQUAL, stZhallId));
		String clRange = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_RANGE", conds.toString(), conds.getObjectArray());
		String clNameCode = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_NAME_CODE", conds.toString(), conds.getObjectArray());
		String clDealAccording = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_DEAL_ACCORDING", conds.toString(), conds.getObjectArray());
		String clDealOrgan = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_DEAL_ORGAN", conds.toString(), conds.getObjectArray());
		String clApprovalConds = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPROVAL_CONDS", conds.toString(), conds.getObjectArray());
		String clApprovalCount = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPROVAL_COUNT", conds.toString(), conds.getObjectArray());
		String clApprovalMater = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPROVAL_MATER", conds.toString(), conds.getObjectArray());
		String clApprovalLimit = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPROVAL_LIMIT", conds.toString(), conds.getObjectArray());
		String clApprovalCert = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPROVAL_CERT", conds.toString(), conds.getObjectArray());
		String clChargeStd = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_CHARGE_STD", conds.toString(), conds.getObjectArray());
		String clApplyRightsDuties = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPLY_RIGHTS_DUTIES", conds.toString(), conds.getObjectArray());
		String clApplyReceive = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_APPLY_RECEIVE", conds.toString(), conds.getObjectArray());
		String clConsultWay = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_CONSULT_WAY", conds.toString(), conds.getObjectArray());
		String clComplaintChannel = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_COMPLAINT_CHANNEL", conds.toString(), conds.getObjectArray());
		String clDealType = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_DEAL_TYPE", conds.toString(), conds.getObjectArray());
		String clDecidedOpen = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_DECIDED_OPEN", conds.toString(), conds.getObjectArray());
		// 事项告知单
		String clNotify = ClobHelper.getClob("WINDOW_ZHALL_ITEM_DETAIL_BZ", "CL_NOTIFY", conds.toString(), conds.getObjectArray());
		w.setClRange(clRange);
		w.setClApplyReceive(clApplyReceive);
		w.setClApplyRightsDuties(clApplyRightsDuties);
		w.setClApprovalCert(clApprovalCert);
		w.setClApprovalConds(clApprovalConds);
		w.setClApprovalCount(clApprovalCount);
		w.setClApprovalLimit(clApprovalLimit);
		w.setClApprovalMater(clApprovalMater);
		w.setClChargeStd(clChargeStd);
		w.setClConsultWay(clConsultWay);
		w.setClDealAccording(clDealAccording);
		w.setClDealOrgan(clDealOrgan);
		w.setClDealType(clDealType);
		w.setClDecidedOpen(clDecidedOpen);
		w.setClNameCode(clNameCode);
		w.setClComplaintChannel(clComplaintChannel);
		w.setClNotify(clNotify);
		w.setStDetailId(stZhallId);
		return w;
	}*/
	public String getGuideInfoByZhallIdExt(String stZhallId) {
		String w = "";
		String sql = "SELECT CL_CONTENT FROM selm_attach WHERE ST_ATTACH_ID = ?";
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, new Object[]{stZhallId});
		}else {
			rs = SQL.execute(con, sql, new Object[]{stZhallId});
		}
		if (rs.next()) {
			w = rs.getOriginalString("CL_CONTENT");
		}
		return w;
	}


	/*public List<WindowZhallItem> query(Conditions conds, String suffix) {
		RecordSet rs;
		if (con == null) {
			rs = SQL.query("WINDOW_ZHALL_ITEM_BZ", "*", conds, suffix);
		} else {
			rs = SQL.query(con, "WINDOW_ZHALL_ITEM_BZ", "*", conds, suffix);
		}
		List<WindowZhallItem> al = new ArrayList<WindowZhallItem>();
		while (rs.next()) {
			WindowZhallItem info = new WindowZhallItem();
			setProperties(info, rs);
			al.add(info);
		}
		return al;
	}*/



	public static void setProperties(WindowZhallItem info, RecordSet rs) {
		info.setStZhallId(rs.getOriginalString("ST_ZHALL_ID"));
		info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
		info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
		info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
		info.setNmBelong(rs.getBigDecimal("NM_BELONG"));
		info.setStItemType(rs.getOriginalString("ST_ITEM_TYPE"));
		info.setStLegalTime(rs.getOriginalString("ST_LEGAL_TIME"));
		info.setStPromiseTime(rs.getOriginalString("ST_PROMISE_TIME"));
		info.setNmIspay(rs.getBigDecimal("NM_ISPAY"));
		info.setStTelConsult(rs.getOriginalString("ST_TEL_CONSULT"));
		info.setStTelComplaint(rs.getOriginalString("ST_TEL_COMPLAINT"));
		info.setStOnlineComplaintLink(rs.getOriginalString("ST_ONLINE_COMPLAINT_LINK"));
		info.setStConsultLink(rs.getOriginalString("ST_CONSULT_LINK"));
		info.setNmOnline(rs.getOriginalString("NM_ONLINE"));
		info.setStTelOnline(rs.getOriginalString("ST_TEL_ONLINE"));
		info.setNmSort(rs.getBigDecimal("NM_SORT"));
		info.setStDealDept(rs.getOriginalString("ST_DEAL_DEPT"));
		info.setNmFlag(rs.getBigDecimal("NM_FLAG"));
		info.setDtCreate(rs.getTimestamp("DT_CREATE"));
		info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
		info.setStExt1(rs.getOriginalString("ST_EXT1"));
		info.setStExt2(rs.getOriginalString("ST_EXT2"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}