package com.wondersgroup.selfapi.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import reindeer.base.bean.WindowItemStatus;
import reindeer.base.bean.WindowItemStuff;

import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.selfapi.bean.CsOrgan;
import com.wondersgroup.selfapi.bean.WindowItemInfo;
import com.wondersgroup.selfapi.bean.WorkAttachment;

@Repository
public class MaterialUpDao {
	
	Connection con = null;
	public MaterialUpDao() {
	}
	public MaterialUpDao(Connection con) {
		this.con = con;
	}
	/**
	 * 获取部门列表
	 * @param conds
	 * @param suffix
	 * @return
	 */
	public List<CsOrgan> queryOrgan(Conditions conds, String suffix) {
		RecordSet rs;
		if(con ==null){
			rs = SQL.query("CS_ORGAN_NODE", "*", conds, suffix);
		}else{
			rs = SQL.query(con, "CS_ORGAN_NODE", "*", conds, suffix);
		}
		ArrayList<CsOrgan> al = new ArrayList<CsOrgan>();
		while(rs.next()){
			CsOrgan info = new CsOrgan();
			info.setId(rs.getString("ID"));
			info.setCode(rs.getString("CODE"));
			info.setOrganName(rs.getString("NAME"));
			info.setShortName(rs.getString("DESCRIPTION"));
			al.add(info);
		}
		
		return al;
	}
	/**
	 * 根据部门id（organId）获取事项列表
	 * @param conds
	 * @param string
	 * @return
	 */
	public List<WindowItemInfo> queryItemByOrganId(Conditions conds,
			String suffix) {  //suffix为impl中的参数
		ArrayList<WindowItemInfo> al = new ArrayList<WindowItemInfo>();
		RecordSet rs;
		String sql = "SELECT a.* FROM WINDOW_ITEM_INFO A LEFT JOIN CS_ORGAN_NODE b "
					+"ON a.NM_ORGAN_NODE_ID = b.ID ";
		String subsql = conds !=null ?conds.toString() : "";
		suffix = suffix != null ?suffix : "";
	
		if("".equals(subsql)){
			sql +=suffix;
			rs =SQL.execute(sql,conds.getObjectArray());
		}else{
			sql +=" where "+subsql + suffix;
			rs = SQL.execute(sql,conds.getObjectArray());
		}
		while(rs.next()){
			WindowItemInfo info = new WindowItemInfo();
			WindowItemInfoDao.setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
	/**
	 * 获取事项列表(可根据事项名称模糊查询itemName)
	 * @param conds
	 * @param string
	 * @return
	 */
	public List<WindowItemInfo> queryItem(Conditions conds, String suffix) {
		RecordSet rs;
		if(con == null){
			rs = SQL.query("WINDOW_ITEM_INFO", "*", conds, suffix);
		}else{
			rs = SQL.query(con,"WINDOW_ITEM_INFO", "*", conds, suffix);
		}
		
		ArrayList<WindowItemInfo> al = new ArrayList<WindowItemInfo>();
		while(rs.next()){
			WindowItemInfo info = new WindowItemInfo();
			WindowItemInfoDao.setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
	/**
	 * 根据事项ID获取情形列表(itemId)
	 * @param conds
	 * @param suffix
	 * @return
	 */
	public List<WindowItemStatus> queryStatusList(Conditions conds,
			String suffix) {
		RecordSet rs;
		if(con==null){
			rs = SQL.query("WINDOW_ITEM_STATUS", "*", conds, suffix);
		}else{
			rs = SQL.query(con, "WINDOW_ITEM_STATUS", "*", conds, suffix);
		}
		ArrayList<WindowItemStatus> al = new ArrayList<WindowItemStatus>();
		while(rs.next()){
			WindowItemStatus info = new WindowItemStatus();
			WindowItemStatusDao.setProperties(info, rs);
			al.add(info);
		}
		return al;
	}
	
	/**
	 * 根据情形Id查询材料
	 * @param conds
	 * @param string
	 * @return
	 */
	public List<WindowItemStuff> getWindowItemApplyStuff(Conditions conds,
			String suffix) {
		ArrayList<WindowItemStuff> al = new ArrayList<WindowItemStuff>();
		RecordSet rs;
		String sql ="SELECT DISTINCT b.ST_STUFF_ID,b.ST_FORMAL_ID," +
				" b.ST_ITEM_ID,b.ST_STUFF_NAME,b.NM_ORIGINAL," +
				" b.NM_COPY,b.NM_SAMPLE,b.NM_MUST,b.NM_ORDER " +
				" from WINDOW_ITEM_STATUS_STUFF a INNER JOIN WINDOW_ITEM_STUFF b "+
				" on a.ST_ITEM_APPLY_STUFF_ID = b.ST_STUFF_ID ";
		String subsql = conds !=null ?conds.toString() : "";
		suffix = suffix !=null ?suffix : "";
		if("".equals(subsql)){
			
		}else{
			sql +=" where " +subsql+suffix;
			rs = SQL.execute(sql,conds.getObjectArray());
			while (rs.next()){
				WindowItemStuff info = new WindowItemStuff();
				WindowItemStuffDao.setProperties(info,rs);
				al.add(info);
			}
		}
		return al;
	}
	public List<WorkAttachment> queryWorkAttachment(Conditions conds,
			String suffix) {
		RecordSet rs;
		if(con==null){
			rs = SQL.query("WORK_ATTACHMENT", "*", conds, suffix);
		}else{
			rs = SQL.query(con, "WORK_ATTACHMENT", "*", conds, suffix);
		}
		ArrayList<WorkAttachment> al = new ArrayList<WorkAttachment>();
		while(rs.next()){
			WorkAttachment info = new WorkAttachment();
			WorkAttachmentDao.setProperties(info, rs);
			al.add(info);
		}
		return al;
	}

}
