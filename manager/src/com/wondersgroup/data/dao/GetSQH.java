package com.wondersgroup.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wondersgroup.data.bean.OfflineCount;
import com.wondersgroup.data.bean.SqhCount;
import com.wondersgroup.data.util.JDBCUtils;

import coral.base.util.StringUtil;

@Component
public class GetSQH {
	
	@Autowired
	private JDBCUtils jdbc;
	
	public SqhCount getSqhByTime(HttpServletRequest req,
			HttpServletResponse res){
		
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		String conds = "where ";
		if((null != startDate && !StringUtils.isEmpty(startDate))||(null != endDate && !StringUtils.isEmpty(endDate))){
			if(null != startDate && !StringUtils.isEmpty(startDate)){
				conds = conds + " sqh.DT_CREATE >= '"+startDate+" 00:00:00'";
				if(null != endDate && !StringUtils.isEmpty(endDate)){
					conds = conds + " and sqh.DT_CREATE <= '"+endDate+" 23:59:59'";
				}
			}
			if((null == startDate || StringUtils.isEmpty(startDate))&&(null != endDate && !StringUtils.isEmpty(endDate))){
				conds = conds + " sqh.DT_CREATE <= '"+endDate+" 23:59:59'";
			}
			
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String start = sdf.format(date)+" 00:00:00";
			String end = sdf.format(date)+" 23:59:59";
			conds = conds + " sqh.DT_CREATE BETWEEN '"+start+"' and '"+end+"'";
		}
		
		
		//JDBCUtils jdbc = new JDBCUtils();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select COUNT(CASE WHEN sqh.ST_MODULE_OP='预览' THEN 1 END) yl," +
				"COUNT(CASE WHEN sqh.ST_MODULE_OP='挂失' THEN 1 END) gs,"+
				"COUNT(CASE WHEN sqh.ST_MODULE_OP='申领' THEN 1 END) sy,"+
				"COUNT(CASE WHEN sqh.ST_MODULE_OP='补领' THEN 1 END) bl "+
				"from SELM_QUERY_HIS_2022 sqh "+conds;

		//System.out.println("-----"+sql);
		SqhCount sqh = new SqhCount();
		try {
			conn = jdbc.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				sqh.setYl(rs.getInt("yl"));
				sqh.setGs(rs.getInt("gs"));
				sqh.setSl(rs.getInt("sy"));
				sqh.setBl(rs.getInt("bl"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				jdbc.close(ps,conn,rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return sqh;
	}

	public OfflineCount getOfflineByTime(HttpServletRequest req,
								 HttpServletResponse res) {

		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		String conds = "where ";
		if((null != startDate && !StringUtils.isEmpty(startDate))||(null != endDate && !StringUtils.isEmpty(endDate))){
			if(null != startDate && !StringUtils.isEmpty(startDate)){
				conds = conds + " sqh.DT_CREATE >= '"+startDate+" 00:00:00'";
				if(null != endDate && !StringUtils.isEmpty(endDate)){
					conds = conds + " and sqh.DT_CREATE <= '"+endDate+" 23:59:59'";
				}
			}
			if((null == startDate || StringUtils.isEmpty(startDate))&&(null != endDate && !StringUtils.isEmpty(endDate))){
				conds = conds + " sqh.DT_CREATE <= '"+endDate+" 23:59:59'";
			}
			
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String start = sdf.format(date)+" 00:00:00";
			String end = sdf.format(date)+" 23:59:59";
			conds = conds + " sqh.DT_CREATE BETWEEN '"+start+"' and '"+end+"'";
		}
		
		
		//JDBCUtils jdbc = new JDBCUtils();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psCount = null;
		ResultSet rs = null;
		String sql = "select COUNT(CASE WHEN idt.ST_TYPE_ID = 'LX003' or idt.ST_TYPE_ID = '1bce65c2-2c14-427a-b61b-28aa281a9e39' or idt.ST_TYPE_ID = '181f4f94-22fd-4b43-bc60-6675abbdb4d8' THEN 1 END) 'wd' ," +
				"	COUNT(CASE WHEN idt.ST_COMPANY_ID = 'f5c2de7b-b4f5-457e-8357-2f8cee2072ca' THEN 1 END) 'zf' ," +
				"	COUNT(CASE WHEN idt.ST_COMPANY_ID = 'bd948178-8f03-4dd5-b62a-109ae511339e' THEN 1 END) 'xd' ," +
				"	COUNT(CASE WHEN idt.ST_COMPANY_ID = '68400662-3757-4ad9-96e4-9bc88290666a' THEN 1 END) 'jf' ," +
				"	COUNT(CASE WHEN idt.ST_COMPANY_ID = '4be3e737-94ce-484c-b761-116ea4f03ab1' THEN 1 END) 'xz' ," +
				"	COUNT ( CASE WHEN idt.NM_DTYPE = '1' THEN 1 END ) 'countYh' " +
				"from 	selm_query_his_2022 sqh "+
				"JOIN infopub_device_info idi ON sqh.ST_MACHINE_ID = idi.ST_DEVICE_MAC "+
				"JOIN infopub_device_type idt ON idi.ST_TYPE_ID = idt.ST_TYPE_ID "+conds+ "AND sqh.ST_ITEM_NAME = '随申码离线服务' AND sqh.ST_MODULE_OP != '挂失'";
		
		String sqlCount = "select COUNT (sqh.ST_QUERY_HIS_ID) 'count' " +
				"from 	selm_query_his_2022 sqh "+conds+ "AND sqh.ST_ITEM_NAME = '随申码离线服务' AND sqh.ST_MODULE_OP != '挂失'";
		//System.out.println("-----"+sql);
		OfflineCount offline = new OfflineCount();
		try {
			conn = jdbc.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				offline.setWd(rs.getInt("wd"));
				offline.setZf(rs.getInt("zf"));
				offline.setXd(rs.getInt("xd"));
				offline.setXz(rs.getInt("xz"));
				offline.setJf(rs.getInt("jf"));
				offline.setCountYh(rs.getInt("countYh"));
			}
			
			psCount = conn.prepareStatement(sqlCount);
			rs = psCount.executeQuery();
			
			while(rs.next()){
				offline.setCount(rs.getInt("count"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				jdbc.close(ps,conn,rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return offline;
	}
}
