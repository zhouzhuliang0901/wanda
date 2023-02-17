package com.wondersgroup.selfapi.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.PlaceInfo;
import com.wondersgroup.selfapi.bean.WindowGroupInfo;
import com.wondersgroup.selfapi.bean.WindowHall;

/**
 * 类描述：预约查询
 */
@Repository
public class ReservationDao {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/**
	 * 获取预约大厅列表
	 * 
	 * @return
	 */
	public List<WindowHall> getAllHallList() {
		List<WindowHall> windowHallList = new ArrayList<WindowHall>();
		String sql = "SELECT * FROM  WINDOW_HALL";
		RecordSet rs = SQL.execute(sql);
		while (rs.next()) {
			WindowHall window = new WindowHall();
			window.setStHallId(rs.getOriginalString("ST_HALL_ID"));
			window.setStHallCode(rs.getOriginalString("ST_HALL_CODE"));
			window.setStName(rs.getOriginalString("ST_NAME"));
			window.setStFullName(rs.getOriginalString("ST_FULL_NAME"));
			window.setNmOrder(rs.getBigDecimal("NM_ORDER"));
			window.setStAddress(rs.getOriginalString("ST_ADDRESS"));
			window.setStAlpha(rs.getOriginalString("ST_ALPHA"));
			window.setStDesc(rs.getOriginalString("ST_DESC"));
			window.setStExt1(rs.getOriginalString("ST_EXT1"));
			window.setStExt2(rs.getOriginalString("ST_EXT2"));
			window.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			windowHallList.add(window);
		}
		return windowHallList.size() > 0 ? windowHallList : null;
	}

	/**
	 * 获取所有包含可预约事项信息的大厅信息
	 * 
	 * @return
	 */
	public List<WindowHall> getAllHallListForNet() {
		List<WindowHall> windowHallList = new ArrayList<WindowHall>();
		String sql = " SELECT w.st_hall_code,w.st_full_name FROM window_hall w "
				+ " LEFT JOIN window_group_info w1 on w.ST_HALL_ID =  w1.ST_HALL_ID "
				+ " LEFT JOIN window_group_item w2 ON w1.ST_GROUP_ID = w2.ST_GROUP_ID "
				+ " LEFT JOIN window_item_info w3 ON w2.ST_ITEM_ID = w3.ST_ITEM_ID "
				+ " JOIN NET_ITEM_RESERVATION n ON w3.ST_ITEM_NO = n.ST_ITEM_CODE "
				+ " JOIN CS_ORGAN_NODE c ON n.nm_organ_node_id = c.id "
				+ " WHERE c.removed = 0 "
				+ " group by w.st_hall_code,w.st_full_name ";
		RecordSet rs = SQL.execute(sql);
		while (rs.next()) {
			WindowHall window = new WindowHall();
			window.setStFullName(rs.getOriginalString("st_full_name"));
			window.setStHallCode(rs.getOriginalString("st_hall_code"));
			windowHallList.add(window);
		}
		return windowHallList.size() > 0 ? windowHallList : null;
	}

	/**
	 * 根据预约大厅的编码获取其下的包含可预约事项的部门
	 * 
	 * @param stHallCode
	 * @return
	 */
	public List<OrganNodeInfo> getAllOrganNetListByHallCode(String stHallCode) {
		List<OrganNodeInfo> organNodeInfoList = new ArrayList<OrganNodeInfo>();
		String sql = "SELECT c.id,c.code,c.description,c.name FROM window_hall w "
				+ " LEFT JOIN window_group_info w1 on w.ST_HALL_ID =  w1.ST_HALL_ID  "
				+ " LEFT JOIN window_group_item w2 ON w1.ST_GROUP_ID = w2.ST_GROUP_ID  "
				+ " LEFT JOIN window_item_info w3 ON w2.ST_ITEM_ID = w3.ST_ITEM_ID "
				+ " JOIN NET_ITEM_RESERVATION n ON w3.ST_ITEM_NO = n.ST_ITEM_CODE "
				+ " JOIN CS_ORGAN_NODE c ON n.nm_organ_node_id = c.id "
				+ " WHERE w.ST_HALL_CODE = ? "
				+ " AND c.removed = 0 "
				+ " group by c.id,c.code,c.description,c.name ";
		RecordSet rs = SQL.execute(sql, new Object[] { stHallCode });
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setDescription(rs.getOriginalString("description"));
			info.setOrganCode(rs.getOriginalString("code"));
			info.setOrganId(rs.getOriginalString("ID"));
			info.setOrganName(rs.getOriginalString("name"));
			organNodeInfoList.add(info);
		}
		return organNodeInfoList.size() > 0 ? organNodeInfoList : null;
	}

	/**
	 * 根据大厅编码和办理点的ID获取其下所有的可预约事项
	 * 
	 * @param hallCode
	 * @param placeId
	 * @return
	 */
	public ArrayList<ItemSet> getAllItemListByCodeAndId(String hallCode,
			String placeId) {
		ArrayList<ItemSet> list = new ArrayList<ItemSet>();
		String sql = " SELECT w3.ST_ITEM_NO ,n.ST_ITEM_CODE,n.ST_PLACE_ID,n.ST_ITEM_NAME FROM window_hall w "
				+ " LEFT JOIN window_group_info w1 on w.ST_HALL_ID =  w1.ST_HALL_ID "
				+ " LEFT JOIN window_group_item w2 ON w1.ST_GROUP_ID = w2.ST_GROUP_ID "
				+ " LEFT JOIN window_item_info w3 ON w2.ST_ITEM_ID = w3.ST_ITEM_ID "
				+ " LEFT JOIN net_reservation_rule n ON w3.ST_ITEM_NO = n.ST_ITEM_CODE "
				+ " WHERE w.ST_HALL_CODE = ?  AND  n.ST_PLACE_ID = ? "
				+ " group by w3.ST_ITEM_NO,n.ST_ITEM_CODE,n.ST_PLACE_ID,n.ST_ITEM_NAME "
				+ " ORDER BY ST_ITEM_NO ";
		RecordSet rs = SQL.execute(sql, new Object[] { hallCode, placeId });
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			list.add(info);
		}
		return list.size() > 0 ? list : null;
	}

	public ArrayList<ItemSet> getAllItemListByCodeAndIdForPage(int pageSize,
			int currentPage, String hallCode, String placeId) {
		ArrayList<ItemSet> list = new ArrayList<ItemSet>();
		String sql = " SELECT w3.ST_ITEM_NO ,n.ST_ITEM_CODE,n.ST_PLACE_ID,n.ST_ITEM_NAME FROM window_hall w "
				+ " LEFT JOIN window_group_info w1 on w.ST_HALL_ID =  w1.ST_HALL_ID "
				+ " LEFT JOIN window_group_item w2 ON w1.ST_GROUP_ID = w2.ST_GROUP_ID "
				+ " LEFT JOIN window_item_info w3 ON w2.ST_ITEM_ID = w3.ST_ITEM_ID "
				+ " LEFT JOIN net_reservation_rule n ON w3.ST_ITEM_NO = n.ST_ITEM_CODE "
				+ " WHERE w.ST_HALL_CODE = ?  AND  n.ST_PLACE_ID = ? "
				+ " group by w3.ST_ITEM_NO,n.ST_ITEM_CODE,n.ST_PLACE_ID,n.ST_ITEM_NAME "
				+ " ORDER BY ST_ITEM_NO ";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage, new Object[] {
				hallCode, placeId });
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			list.add(info);
		}
		return list.size() > 0 ? list : null;
	}

	/**
	 * 方法描述：获取所有的办理点
	 * 
	 * @param part
	 * @return List<PlaceInfo>
	 */
	public List<PlaceInfo> getAllPlaceInfo(String part) {

		String stuff = "";
		if (StringUtils.isNotBlank(part)) {
			stuff = "WHERE n.ST_EXT2 IS NULL OR N.ST_EXT2 != 1 ";
		}
		String sql = "SELECT DISTINCT z.ST_DEPARTID,z.ST_DEPARTNAME from NET_ITEM_RESERVATION n JOIN ZHALL_PLACE_INFO z ON n.ST_PLACE_ID = z.ST_DEPARTID "
				+ stuff;
		RecordSet rs = SQL.execute(sql);
		List<PlaceInfo> list = new ArrayList<PlaceInfo>();
		while (rs.next()) {
			PlaceInfo info = new PlaceInfo();
			info.setStPlaceId(rs.getOriginalString("ST_DEPARTID"));
			info.setStPlaceName(rs.getOriginalString("ST_DEPARTNAME"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据办理点的父节点获取所有的办理点(根据WINDOW_AREA办理点信息表)
	 * 
	 * @param stParentAreaId
	 * @return List<PlaceInfo>
	 */
	public List<PlaceInfo> getAllPlaceInfos(String stParentAreaId) {
		String sql = "SELECT * FROM WINDOW_AREA WHERE ST_PARENT_AREA_ID = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { stParentAreaId });
		List<PlaceInfo> list = new ArrayList<PlaceInfo>();
		while (rs.next()) {
			PlaceInfo info = new PlaceInfo();
			info.setStPlaceId(rs.getOriginalString("ST_AREA_ID"));
			info.setStPlaceName(rs.getOriginalString("ST_AREA_NAME"));
			info.setStPlaceCode(rs.getOriginalString("ST_AREA_CODE"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据办理点id获取其下的业务组别集合
	 * 
	 * @param stPlaceId
	 * @return List<WindowGroupInfo>
	 */
	public List<WindowGroupInfo> getAllWindowGroupInfoByPlaceId(String stPlaceId) {
		String sql = " SELECT DISTINCT w2.ST_GROUP_ID,w2.ST_GROUP_CODE,w2.ST_GROUP_NAME FROM WINDOW_AREA w JOIN WINDOW_AREA_HALL w1 "
				+ " ON w.ST_AREA_ID = w1.ST_AREA_ID  JOIN WINDOW_GROUP_INFO w2 "
				+ " ON w1.ST_HALL_ID = w2.ST_HALL_ID "
				+ " WHERE w.ST_AREA_ID = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { stPlaceId });
		List<WindowGroupInfo> list = new ArrayList<WindowGroupInfo>();
		while (rs.next()) {
			WindowGroupInfo info = new WindowGroupInfo();
			info.setStGroupCode(rs.getOriginalString("ST_GROUP_CODE"));
			info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
			info.setStGroupName(rs.getOriginalString("ST_GROUP_NAME"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：获取所有的部门（根据部门的编码和办理点的id）
	 * 
	 * @param dept
	 * @param placeId
	 * @return List<OrganNodeInfo>
	 */
	public List<OrganNodeInfo> getAllOrgansByPlaceId(String dept, String placeId) {
		// 对预约显示的部门进行排序
		String sql = " SELECT c.ID,c.DESCRIPTION FROM CS_ORGAN_NODE c "
				+ " JOIN CS_ORGAN_MODEL c1 ON c.ID = c1.ORG_NODE_ID  "
				+ " JOIN NET_ITEM_RESERVATION n ON c.ID = n.NM_ORGAN_NODE_ID "
				+ " WHERE CODE LIKE ? and c.REMOVED = 0  AND c1.REMOVED = 0 "
				+ " AND n.ST_PLACE_ID = ? GROUP BY c.ID,c.DESCRIPTION,c1.ORDERS "
				+ " ORDER BY c1.ORDERS ";
		dept = dept + "%";
		Object[] obj = { dept, placeId };
		List<OrganNodeInfo> list = new ArrayList<OrganNodeInfo>();
		RecordSet rs = SQL.execute(sql, obj);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganId(rs.getBigDecimal("ID").intValue() + "");
			// info.setOrganCode(rs.getString("CODE"));
			// info.setOrganName(rs.getString("NAME"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门编码获取其下的所有部门
	 * 
	 * @param dept
	 * @return
	 */
	public List<OrganNodeInfo> getAllOrgans(String dept) {
		// 对预约显示的部门进行排序
		String sql = " SELECT c.ID,c.DESCRIPTION FROM CS_ORGAN_NODE c "
				+ " JOIN CS_ORGAN_MODEL c1 ON c.ID = c1.ORG_NODE_ID  "
				+ " JOIN NET_ITEM_RESERVATION n ON c.ID = n.NM_ORGAN_NODE_ID "
				+ " WHERE CODE LIKE ? and c.REMOVED = 0  AND c1.REMOVED = 0 "
				+ " GROUP BY c.ID,c.DESCRIPTION,c1.ORDERS "
				+ " ORDER BY c1.ORDERS ";
		dept = dept + "%";
		Object[] obj = { dept };
		List<OrganNodeInfo> list = new ArrayList<OrganNodeInfo>();
		RecordSet rs = SQL.execute(sql, obj);
		while (rs.next()) {
			OrganNodeInfo info = new OrganNodeInfo();
			info.setOrganId(rs.getBigDecimal("ID").longValue() + "");
			// info.setOrganCode(rs.getString("CODE"));
			// info.setOrganName(rs.getString("NAME"));
			info.setDescription(rs.getOriginalString("DESCRIPTION"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门ID获取可预约事项（分页）
	 * 
	 * @param organId
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<ItemSet> getAllItemsByOrganId(String organId, Integer pageSize,
			Integer currentPage) {
		String sql = "SELECT * FROM NET_ITEM_RESERVATION WHERE NM_ORGAN_NODE_ID = ? ORDER BY NM_ORDER ";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage,
				new Object[] { organId });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			// 此处的nmType为 是否申报预约快速预约 （0为快速预约 1 为申报预约）
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门ID获取可预约事项
	 * 
	 * @param organId
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganId(String organId) {
		String sql = "SELECT * FROM NET_ITEM_RESERVATION WHERE NM_ORGAN_NODE_ID = ? ORDER BY NM_ORDER ";
		RecordSet rs = SQL.execute(sql, new Object[] { organId });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			// 此处的nmType为 是否申报预约快速预约 （0为快速预约 1 为申报预约）
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门ID和办理点id获取可预约事项（分页）
	 * 
	 * @param organId
	 * @param placeId
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId, Integer pageSize, Integer currentPage) {
		String sql = "SELECT * FROM NET_ITEM_RESERVATION WHERE NM_ORGAN_NODE_ID = ? AND ST_PLACE_ID = ? ORDER BY NM_ORDER ";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage, new Object[] {
				organId, placeId });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			// 此处的nmType为 是否申报预约快速预约 （0为快速预约 1 为申报预约）
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门ID和办理点id获取可预约事项
	 * 
	 * @param organId
	 * @param placeId
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByOrganIdAndPlaceId(String organId,
			String placeId) {
		String sql = "SELECT * FROM NET_ITEM_RESERVATION WHERE NM_ORGAN_NODE_ID = ? AND ST_PLACE_ID = ? ORDER BY NM_ORDER ";
		RecordSet rs = SQL.execute(sql, new Object[] { organId, placeId });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			// 此处的nmType为 是否申报预约快速预约 （0为快速预约 1 为申报预约）
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
		}
		return list;
	}

	/**
	 * 方法描述：根据部门id获取部门下对应的所有组别信息
	 * 
	 * @param organId
	 * @return
	 */
	public List<WindowGroupInfo> getAllWindowGroupInfo(String organId) {
		String sql = " SELECT DISTINCT w.ST_GROUP_ID,w.ST_GROUP_CODE,w.ST_GROUP_NAME "
				+ " FROM CS_ORGAN_NODE c JOIN WINDOW_GROUP_INFO w ON c.ID = w.NM_ORGAN_NODE_ID "
				+ " JOIN WINDOW_GROUP_ITEM w1 ON w.ST_GROUP_ID = w1.ST_GROUP_ID "
				+ " JOIN WINDOW_ITEM_INFO w2 ON w1.ST_ITEM_ID = w2.ST_ITEM_ID "
				+ " JOIN NET_ITEM_RESERVATION n ON w2.ST_ITEM_NO = n.ST_ITEM_CODE "
				+ " WHERE c.ID = ? ";
		RecordSet rs = SQL.execute(sql, new Object[] { organId });
		List<WindowGroupInfo> list = new ArrayList<WindowGroupInfo>();
		while (rs.next()) {
			WindowGroupInfo info = new WindowGroupInfo();
			info.setStGroupCode(rs.getOriginalString("ST_GROUP_CODE"));
			info.setStGroupId(rs.getOriginalString("ST_GROUP_ID"));
			info.setStGroupName(rs.getOriginalString("ST_GROUP_NAME"));
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据组别编码或者id获取其下可预约的所有事项
	 * 
	 * @param stGroupCode
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByGroupCode(String stGroupCode,
			Integer pageSize, Integer currentPage) {
		String sql = "SELECT n.ST_ITEM_CODE,n.ST_ITEM_NAME,n.NM_TYPE,n.ST_PLACE_ID"
				+ " FROM WINDOW_GROUP_INFO w JOIN WINDOW_GROUP_ITEM w1 ON w.ST_GROUP_ID = w1.ST_GROUP_ID"
				+ " JOIN WINDOW_ITEM_INFO w2 ON w1.ST_ITEM_ID = w2.ST_ITEM_ID"
				+ " JOIN NET_ITEM_RESERVATION n ON w2.ST_ITEM_NO = n.ST_ITEM_CODE WHERE w.ST_GROUP_CODE = ? ";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage,
				new Object[] { stGroupCode });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			// 此处的nmType为 是否申报预约快速预约 （0为快速预约 1 为申报预约）
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据事项名称模糊查询获取可预约事项（分页查询）
	 * 
	 * @param stItemName
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByItemName(String stItemName,
			Integer pageSize, Integer currentPage) {
		if (StringUtils.isBlank(stItemName)) {
			stItemName = "";
		}
		String sql = " SELECT * FROM NET_ITEM_RESERVATION n "
				+ " JOIN CS_ORGAN_NODE c ON n.NM_ORGAN_NODE_ID = c.ID WHERE c.removed = 0 AND ( n.ST_ITEM_NAME LIKE ? OR n.ST_ITEM_CODE LIKE ? )";
		stItemName = "%" + stItemName + "%";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage, new Object[] {
				stItemName, stItemName });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setOrganName(rs.getOriginalString("DESCRIPTION"));
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
			list.add(info);
		}
		return list;
	}

	/**
	 * 方法描述：根据事项名称和办理点id模糊查询获取可预约事项（分页查询）
	 * 
	 * @param stItemName
	 * @param placeId
	 * @param pageSize
	 * @param currentPage
	 * @return List<ItemSet>
	 */
	public List<ItemSet> getAllItemsByItemNameAndPlaceId(String stItemName,
			String placeId, Integer pageSize, Integer currentPage) {
		String itemName = stItemName;
		if (StringUtils.isBlank(stItemName)) {
			stItemName = "";
		}
		String sql = " SELECT * FROM NET_ITEM_RESERVATION n "
				+ " JOIN CS_ORGAN_NODE c ON n.NM_ORGAN_NODE_ID = c.ID WHERE c.removed = 0 "
				+ "AND ( n.ST_ITEM_NAME LIKE ? OR n.ST_ITEM_CODE = ? ) AND n.ST_PLACE_ID = ? ";
		stItemName = "%" + stItemName + "%";
		RecordSet rs = SQL.execute(sql, pageSize, currentPage, new Object[] {
				stItemName, itemName, placeId });
		List<ItemSet> list = new ArrayList<ItemSet>();
		while (rs.next()) {
			ItemSet info = new ItemSet();
			info.setStItemNo(rs.getOriginalString("ST_ITEM_CODE"));
			info.setStPlaceId(rs.getOriginalString("ST_PLACE_ID"));
			info.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
			info.setOrganName(rs.getOriginalString("DESCRIPTION"));
			String nmType = rs.getBigDecimal("NM_TYPE") != null ? rs
					.getBigDecimal("NM_TYPE").intValue() + "" : "0";
			info.setNmType(nmType);
			list.add(info);
		}
		return list;
	}
}
