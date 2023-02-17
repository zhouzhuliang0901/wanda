package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import wfc.service.config.Config;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.ArchivesApplyInfo;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.ItemSetPage;
import com.wondersgroup.selfapi.bean.ItemTheme;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.OrganNodeInfoPage;
import com.wondersgroup.selfapi.bean.WindowItemStatus;
import com.wondersgroup.selfapi.bean.WindowItemStatusPage;
import com.wondersgroup.selfapi.bean.WindowZhallItem;

@Repository
public class NewSelfDeclareDao {
		
	
	private Connection con = null;
	/**
	 * 获取个人和法人的目录列表
	 * @param areaCode 匹配区域代码  例如SHSCYP 只进行后两位的匹配YP
	 * @param type 1表示个人 2表示法人
	 * @return
	 */
	public List<ItemTheme> queryItemThemeInArea(String type) {

		/*String sql = "SELECT w3.ST_ITEM_TYPE_NAME,w3.ST_ITEM_TYPE_CODE FROM WINDOW_ZHALL_ITEM_BZ w1 "
				+ " JOIN CS_ORGAN_NODE_XH c ON w1.ST_DEAL_DEPT = c.CODE "
				+ " JOIN WINDOW_ZHALL_ITEM_TYPE w3 on w1.ST_ITEM_CLASS LIKE '%'+w3.ST_ITEM_TYPE_CODE+'%' "
				+ " WHERE w1.ST_REMOVE = 0  AND w1.ST_ISCENTER = 1 "
				+ " AND w3.ST_ITEM_TYPE_CODE LIKE ? "
				+ " AND w1.ST_ITEM_CLASS != '' "
				+ " AND w1.ST_ITEM_TEN_NO IS NOT NULL  "
				+ " AND w1.ST_ITEM_TYPE = '审批' "
				+ " AND PARENT_PLACE_CODE IS NULL AND SUBSTRING(c.CODE, LEN(c.CODE)-1, LEN(c.CODE)) = ? "
				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY w3.ST_ITEM_TYPE_NAME,w3.ST_ITEM_TYPE_CODE "
				+ " ORDER BY w3.ST_ITEM_TYPE_CODE ASC ";
		RecordSet rs;
		if(con == null){
			rs=SQL.execute(sql,new Object[]{
					type+"%",areaCode.substring(areaCode.length()-2, areaCode.length())
			});
		}else{
			rs= SQL.execute(con, sql,new Object[]{
					type+"%",areaCode.substring(areaCode.length()-2, areaCode.length())
			});
		}*/
		String sql = "SELECT ST_ITEM_TYPE_ID,ST_ITEM_TYPE_NAME FROM selm_item_type WHERE ST_PARENT_ID = ?"
				+ " ORDER BY NM_SORT ";
		RecordSet rs;
		if(con == null){
			rs=SQL.execute(sql,new Object[]{type});
		}else{
			rs= SQL.execute(con, sql,new Object[]{type});
		}
			ArrayList<ItemTheme> list = new ArrayList<ItemTheme>();
			while(rs.next()){
				ItemTheme theme = new ItemTheme();
				theme.setItemTypeName(rs.getOriginalString("ST_ITEM_TYPE_ID"));
				//theme.setItemTypeCode(rs.getOriginalString("ST_ITEM_TYPE_CODE"));
				theme.setItemTypeCode(rs.getOriginalString("ST_ITEM_TYPE_NAME"));
				list.add(theme);
			}
		return list;
	}
	/**
	 * 获取部门的目录列表
	 * @param pageSize 
	 * @param currentPage
	 * @param areaCode=SH00JD 区域代码
	 * @return
	 */
	//public OrganNodeInfoPage queryDeptInArea(Integer pageSize, Integer currentPage, String areaCode) {
	public OrganNodeInfoPage queryDeptInArea(Integer pageSize, Integer currentPage, String type) {
		ArrayList<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		/*String sql =  " SELECT c.CODE,c.DESCRIPTION,c.NAME,c.NM_ORDER FROM WINDOW_ZHALL_ITEM_BZ w1 "
				+ " JOIN WINDOW_ZHALL_ITEM_DETAIL_BZ w2 ON w1.ST_ZHALL_ID = w2.ST_ZHALL_ID "
				+ " JOIN CS_ORGAN_NODE_XH c ON w1.ST_DEAL_DEPT = c.CODE "
				+ " WHERE  w1.ST_REMOVE = 0  AND w1.ST_ISCENTER = 1 "
				+ " AND w1.ST_ITEM_TEN_NO IS NOT NULL  AND w1.ST_ITEM_TYPE = '审批' "
				+ " AND PARENT_PLACE_CODE IS NULL AND SUBSTRING(CODE, LEN(CODE)-1, LEN(CODE)) = ?"
				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " GROUP BY c.CODE,c.DESCRIPTION,c.NAME,c.NM_ORDER "
				+ " ORDER BY c.NM_ORDER ASC ";*/
		String sql = "SELECT ST_ITEM_TYPE_ID,ST_ITEM_TYPE_NAME FROM selm_item_type WHERE ST_PARENT_ID = ?"
				+ " ORDER BY NM_SORT ";
		RecordSet rs;
		/*if(con == null){
			rs = SQL.execute(sql,new Object[]{
					areaCode.substring(areaCode.length()-2,areaCode.length())
			});
		}else{
			rs = SQL.execute(con, sql,new Object[]{
					areaCode.substring(areaCode.length()-2,areaCode.length())
					});
		}*/
		if(con == null){
			rs = SQL.execute(sql, pageSize, currentPage, new Object[]{type});
		}else{
			rs = SQL.execute(con, sql, pageSize, currentPage, new Object[]{type});
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
		while(rs.next()){
			OrganNodeInfo info = new OrganNodeInfo();
			/*info.setOrganCode(rs.getOriginalString("CODE"));
			info.setOrganName(rs.getOriginalString("NAME"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));*/
			info.setOrganCode(rs.getOriginalString("ST_ITEM_TYPE_ID"));
			info.setOrganName(rs.getOriginalString("ST_ITEM_TYPE_NAME"));
			info.setDescription(rs.getOriginalString("ST_ITEM_TYPE_NAME"));
			organNodeInfoList.add(info);	
		}
		page.setOrganSetList(organNodeInfoList);
		return page;
	}
	/**
	 * 查询个人 法人 全部事项菜单,下级的目录信息列表
	 * (查询个人、法人、部门目录中的事项)
	 * @param themeCode 
	 * @param areaCode areaCode=SH00JD 区域代码
	 * @param type 1表示个人 2表示法人 3表示部门
	 * @return
	 */
	/*public List<ItemSet> queryItemInTheme(String themeCode, String areaCode,
			String type) {*/
	public List<ItemSet> queryItemInTheme(String themeCode, String type) {
		System.out.println("themeCode = "+themeCode);
		//System.out.println("area"+areaCode);
		System.out.println("type = "+type);
		
		
		Object[] obj = null;
		/*String sql =  "SELECT DISTINCT(ST_ITEM_NO),ST_ITEM_NAME,ST_DEAL_DEPT from WINDOW_ZHALL_ITEM_BZ "
				+" WHERE ST_REMOVE = 0  AND ST_ISCENTER = 1 "
				+" AND ST_ITEM_CLASS != '' "
				+" AND ST_ITEM_TEN_NO IS NOT NULL "
				+" AND ST_ITEM_TYPE = '审批'"
				+" AND SUBSTRING(ST_DEAL_DEPT, LEN(ST_DEAL_DEPT)-1, LEN(ST_DEAL_DEPT)) = ? "
				+" AND ST_TRANSACT_NAME IS NOT NULL AND NM_BELONG = ? ";
		if(StringUtils.isNotEmpty(themeCode)){
			sql +="AND ST_ITEM_CLASS LIKE ? ";
			obj = new Object[]{areaCode.substring(areaCode.length()-2, areaCode.length()),type,"%"+themeCode+"%"};
		} else {
			obj = new Object[]{areaCode.substring(areaCode.length()-2, areaCode.length()),type};
		}
		sql+="ORDER BY ST_ITEM_NO ";*/
		String sql =  "SELECT DISTINCT(ST_ITEM_NO),ST_ITEM_NAME FROM selm_item "
				+ "JOIN selm_item_link ON selm_item.ST_ITEM_ID = selm_item_link.ST_ITEM_ID "
				+ "JOIN selm_item_type ON selm_item_link.ST_ITEM_TYPE_ID = selm_item_type.ST_ITEM_TYPE_ID "
				+ "WHERE selm_item_type.ST_PARENT_ID = ? ";
		if(StringUtils.isNotEmpty(themeCode)){
			sql +="AND selm_item_link.ST_ITEM_TYPE_ID = ? ";
			obj = new Object[]{type,themeCode};
		} else {
			obj = new Object[]{type};
		}
		sql+="ORDER BY ST_ITEM_NO ";
		RecordSet rs;
		if(con==null){
			rs = SQL.execute(sql,obj);
		}else{
			rs = SQL.execute(con, sql,obj);
		}
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()){
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			//info.setOrganCode(rs.getOriginalString("ST_DEAL_DEPT"));
		list.add(info);
		}
		return list;
	}

	/**
	 * 查询部门下面的所有事项列表
	 * @param pageSize
	 * @param currentPage
	 * @param organCode
	 * @return
	 */
	public ItemSetPage getItemListByOrganIdForPage(String model, String organCode, 
			Integer pageSize, Integer currentPage) {
		List<ItemSet> itemSetList = new ArrayList<ItemSet>();
		String sql = " SELECT w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,w1.ST_ORG_CODE " 
				+ " FROM  SELM_ZHALL_ITEM w1 "
				+ " WHERE w1.ST_ORG_CODE = ? AND w1.ST_REMOVE = 0 "
				+ " AND (w1.ST_ITEM_TYPE = '审批' OR w1.ST_ITEM_TYPE = '服务') "
//				+ " AND w1.ST_TRANSACT_NAME IS NOT NULL "
				+ " AND w1.ST_ITEM_TEN_CODE IS NOT NULL ";
				if("1".equals(model)){
					sql += " AND ST_ITEM_TEN_CODE LIKE '113%' ";
				}
				sql += " GROUP BY w1.ST_ITEM_NO,w1.ST_ITEM_NAME,w1.ST_ORG_NAME,w1.ST_ORG_CODE ";
		RecordSet rs;
		if(con ==null){
			rs = SQL.execute(sql,pageSize,currentPage,new Object[]{organCode});
		}else{
			rs = SQL.execute(con, sql,pageSize,currentPage,new Object[]{organCode});
		}
		ItemSetPage page = new ItemSetPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息"+ rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while(rs.next()){
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo( rs.getOriginalString("ST_ITEM_NO"));
			info.setOrganCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setPlaceCode(rs.getOriginalString("ST_ORG_CODE"));
			info.setOrganName(rs.getOriginalString("ST_ORG_NAME"));
			itemSetList.add(info);
		
		}
		page.setItemSetList(itemSetList);
		return page;
	}
		/**
		 * 根据事项十位编码获取其下的情形事项
		 * @param pageSize
		 * @param currentPage
		 * @param deorCode
		 * @param itemNo
		 * @return
		 */
	/*public WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
			Integer currentPage, String deptCode, String itemNo) {
		List<WindowItemStatus> WindowItemStatusList = new ArrayList<WindowItemStatus>();
		Object[] obj = null;
		String sql = " SELECT w.ST_ITEM_NO,w.ST_ITEM_NAME,w.ST_ITEM_TEN_NO,w.ST_ZHALL_ID,w.ST_TRANSACT_NAME,c.CODE,c.PLACECODE,c.NAME,c.DESCRIPTION "
				+ " FROM WINDOW_ZHALL_ITEM_BZ w JOIN CS_ORGAN_NODE_XH c ON w.ST_DEAL_DEPT = c.CODE "
				+ " WHERE w.ST_ITEM_NO = ? AND w.ST_ITEM_TYPE = '审批' AND w.ST_ITEM_TEN_NO IS NOT NULL "
				+ " AND w.ST_TRANSACT_NAME IS NOT NULL ";
		if(StringUtils.isNotEmpty(deptCode)){
			sql += "AND w.ST_DEAL_DEPT = ? ";
			obj = new Object[] {itemNo,deptCode};
		}else{
			obj = new Object[]{itemNo};
		}
		RecordSet rs;
		if(con==null){
			rs = SQL.execute(sql,pageSize,currentPage,obj);
		}else{
			rs = SQL.execute(con, sql,pageSize ,currentPage,obj);
		}*/
		public WindowItemStatusPage getWindowItemStatusList(Integer pageSize,
				Integer currentPage, String itemNo) {
			List<WindowItemStatus> WindowItemStatusList = new ArrayList<WindowItemStatus>();
			String sql = "SELECT ST_ITEM_ID, ST_ITEM_NO, ST_TEN_CODE, ST_MAIN_NAME, ST_ITEM_NAME " 
					+ "FROM selm_item WHERE NM_TYPE = 3 AND ST_ITEM_NO = ? ";
			RecordSet rs;
			if(con==null){
				rs = SQL.execute(sql,pageSize,currentPage,new Object[]{itemNo});
			}else{
				rs = SQL.execute(con, sql,pageSize ,currentPage,new Object[]{itemNo});
			}
		WindowItemStatusPage page = new WindowItemStatusPage();
		page.setCommonPageSize(rs.COMMON_PAGE_SIZE);
		page.setCurrentPage(rs.CURRENT_PAGE);
		page.setCurrentPageSize(rs.CURRENT_PAGE_SIZE);
		page.setTotalItemCount(rs.TOTAL_RECORD_COUNT);
		page.setTotalPageCount(rs.TOTAL_PAGE);
		Log.debug("分页的页数信息" + rs.COMMON_PAGE_SIZE + "--" + rs.CURRENT_PAGE
				+ "--" + rs.CURRENT_PAGE_SIZE + "--" + rs.TOTAL_PAGE + "--"
				+ rs.TOTAL_RECORD_COUNT);
		while(rs.next()){
			WindowItemStatus info = new WindowItemStatus();
			/*info.setStStatusName(rs.getOriginalString("ST_TRANSACT_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setStItemTenNo(rs.getOriginalString("ST_ITEM_TEN_NO"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemId(rs.getOriginalString("ST_ZHALL_ID"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));
			info.setOrganCode(rs.getOriginalString("CODE"));
			info.setPlaceCode(rs.getOriginalString("PLACECODE"));
			info.setOrganName(rs.getOriginalString("NAME"));*/
			info.setStStatusName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
			info.setStItemTenNo(rs.getOriginalString("ST_TEN_CODE"));
			info.setStItemName(rs.getOriginalString("ST_MAIN_NAME"));
			info.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
			WindowItemStatusList.add(info);
		}
		page.setWindowItemStatusList(WindowItemStatusList);
		return page;
	}
		
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
		
	public List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(
			String idCard, String item, String flag) {
		RecordSet rs;
		
		String sql = " SELECT * FROM ARCHIVES_APPLY_INFO WHERE ST_IDENTITY_NO = ?" +
				" AND ST_ARCHIVES_NO = ? ";
		if(StringUtils.isEmpty(flag)){
			sql += " AND NM_STATUS = 0 ";
		} else {
			String sqlType = Config.get("wfc.service.jdbc.driver");
			if(sqlType.indexOf("sqlserver") != -1){
				sql += " AND DateDiff(dd,DT_CREAT,getdate())=0 ";
			} else if(sqlType.indexOf("mysql") != -1){
				sql += " AND to_days(DT_CREAT) = to_days(now()) ";
			}
		}
		sql += " ORDER BY DT_CREAT DESC ";
		rs = SQL.execute(sql, new Object[]{idCard, item});
		List<ArchivesApplyInfo> list = new ArrayList<ArchivesApplyInfo>();
		while (rs.next()) {
			ArchivesApplyInfo info = new ArchivesApplyInfo();
			info.setStApplyId(rs.getOriginalString("ST_APPLY_ID"));
			info.setStApplyNo(rs.getOriginalString("ST_APPLY_NO"));
			info.setStExt2(rs.getOriginalString("ST_EXT2"));
			list.add(info);
		}
		return list;
	}
		
	public void add(ArchivesApplyInfo info) {
		String sql = "INSERT INTO ARCHIVES_APPLY_INFO (ID,ST_APPLY_ID,ST_APPLY_NO,ST_USER_NAME,ST_IDENTITY_NO," +
				     "ST_ARCHIVES_NO,DT_CREAT,DT_UPDATE,NM_STATUS,ST_EXT1,ST_EXT2,ST_EXT3) " +
				     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = {
				info.getStId(),
				info.getStApplyId(),
				info.getStApplyNo(),
				info.getStUserName(),
				info.getStIdentNo(),
				info.getStArchivesNo(),
				info.getDtCreat(),
				info.getDtUpdate(),
				info.getNmStatus(),
				info.getStExt1(),
				info.getStExt2(),
				info.getStExt3(),
		};
		SQL.execute(sql,obj);
	}
}
