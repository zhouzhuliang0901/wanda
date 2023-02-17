package com.wondersgroup.infopub.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import wfc.service.log.Log;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceInfoExt;

@Repository
public class InfopubDeviceInfoDaoExt extends InfopubDeviceInfoDao {
	private Connection con = null;

	public InfopubDeviceInfoDaoExt() {
	}

	public InfopubDeviceInfoDaoExt(Connection con) {
		this.con = con;
	}
	
	public InfopubDeviceInfo getByMacId(String macId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL, macId));
        List<InfopubDeviceInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
    public PaginationArrayList<InfopubDeviceInfoExt> queryWithDeviceType(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        String content = "IDI.*, IDT.ST_SHUTDOWN_TYPE";
        String table = "INFOPUB_DEVICE_INFO IDI LEFT JOIN INFOPUB_DEVICE_TYPE IDT ON IDI.ST_TYPE_ID = IDT.ST_TYPE_ID";
        if (con == null) {
            rs = SQL.query(table, content, conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, table, content, conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubDeviceInfoExt> pal = new PaginationArrayList<InfopubDeviceInfoExt>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
        	InfopubDeviceInfoExt info = new InfopubDeviceInfoExt();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }
    
    public List<InfopubDeviceInfoExt> queryWithDeviceType(Conditions conds, String suffix) {
        RecordSet rs;
        String content = "IDI.*, IDT.ST_SHUTDOWN_TYPE";
        String table = "INFOPUB_DEVICE_INFO IDI LEFT JOIN INFOPUB_DEVICE_TYPE IDT ON IDI.ST_TYPE_ID = IDT.ST_TYPE_ID";
        if (con == null) {
            rs = SQL.query(table, content, conds, suffix);
        } else {
            rs = SQL.query(con, table, content, conds, suffix);
        }
        ArrayList<InfopubDeviceInfoExt> al = new ArrayList<InfopubDeviceInfoExt>();
        while (rs.next()) {
        	InfopubDeviceInfoExt info = new InfopubDeviceInfoExt();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
	public InfopubDeviceInfoExt getWithDeviceType(String stDeviceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("IDI.ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
        List<InfopubDeviceInfoExt> list = queryWithDeviceType(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public static void setProperties(InfopubDeviceInfoExt info, RecordSet rs){
        info.setStDeviceId(rs.getOriginalString("ST_DEVICE_ID"));
        info.setStDeviceName(rs.getOriginalString("ST_DEVICE_NAME"));
        info.setStDeviceCode(rs.getOriginalString("ST_DEVICE_CODE"));
        info.setStDeviceIp(rs.getOriginalString("ST_DEVICE_IP"));
        info.setStDeviceMac(rs.getOriginalString("ST_DEVICE_MAC"));
        info.setStDeviceAddress(rs.getOriginalString("ST_DEVICE_ADDRESS"));
        info.setStTypeId(rs.getOriginalString("ST_TYPE_ID"));
        info.setNmIsHost(rs.getBigDecimal("NM_IS_HOST"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setNmInterval(rs.getBigDecimal("NM_INTERVAL"));
        info.setNmRecover(rs.getBigDecimal("NM_RECOVER"));
        info.setNmDownTry(rs.getBigDecimal("NM_DOWN_TRY"));
        info.setNmNotification(rs.getBigDecimal("NM_NOTIFICATION"));
        info.setNmLng(rs.getBigDecimal("NM_LNG"));
        info.setNmLat(rs.getBigDecimal("NM_LAT"));
        info.setStConfigId(rs.getOriginalString("ST_CONFIG_ID"));
        
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dfs.format(rs.getTimestamp("DT_UPDATE"));
        Date date = new Date();
        String nowTime = dfs.format(date);
        Date begin = null;
        Date end = null;
        //判断建行的设备
        if("29cf0e14-2d45-4ee7-886d-39a062165149".equals(rs.getOriginalString("ST_TYPE_ID"))){
        	try {
    			begin = dfs.parse(updateTime);
    			end = dfs.parse(nowTime);
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}if (begin != null && end != null) {
    			long second=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
    			//时间小于4个小时在线
    			if (second < 14400) {
    				info.setNmOnline(new BigDecimal(1));
    			}else {
    				info.setNmOnline(new BigDecimal(0));
    			}
    		} else {
    			Log.debug("更新时间begin 或 系统现在时间 end 为 null");
    		}
        }else{
        	try {
    			begin = dfs.parse(updateTime);
    			end = dfs.parse(nowTime);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}if (begin != null && end != null) {
    			long second=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
    			if (second < 30) {
    				info.setNmOnline(new BigDecimal(1));
    			}else {
    				info.setNmOnline(new BigDecimal(0));
    			}
    		} else {
    			System.out.println("更新时间begin 或 系统现在时间 end 为 null");
    		}
        }
		
        //info.setNmOnline(rs.getBigDecimal("NM_ONLINE"));
        info.setStChannel(rs.getOriginalString("ST_CHANNEL"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
        info.setStShutdownType(rs.getOriginalString("ST_SHUTDOWN_TYPE"));
    }

    //查找已赋能的设备
	public Set<String> checkItemLinkDevice() {
		String sql = "select distinct ST_DEVICE_ID from selm_device_item";
		Object[] obj ={};
		RecordSet rs;
        if (con == null) {
            rs = SQL.execute(sql,obj);
        } else {
            rs = SQL.execute(con, sql,obj);
        }
        Set<String> deviceIdList = new HashSet<String>();
        while (rs.next()) {
        	deviceIdList.add(rs.getOriginalString("ST_DEVICE_ID"));
            
        }
		return deviceIdList;
	}

	

	

	
}
