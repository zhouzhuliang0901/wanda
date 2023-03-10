package com.wondersgroup.infopub.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.infopub.bean.InfopubDeviceInfo;

/**
 * 信息设备
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubDeviceInfoExtDao {

    private Connection con = null;

    public InfopubDeviceInfoExtDao() {
    }

    public InfopubDeviceInfoExtDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubDeviceInfo info) {
        String sql = "insert into INFOPUB_DEVICE_INFO(ST_DEVICE_ID, ST_DEVICE_NAME, ST_DEVICE_CODE, ST_DEVICE_IP, ST_DEVICE_MAC, ST_DEVICE_ADDRESS, ST_TYPE_ID, NM_IS_HOST, NM_ORDER, NM_INTERVAL, NM_RECOVER, NM_DOWN_TRY, NM_NOTIFICATION, NM_LNG, NM_LAT, NM_ONLINE, ST_CHANNEL, ST_CONFIG_ID, NM_SDTYPE, ST_USER_ID, ST_AREA_ID, ST_ADDRESS_ID, ST_CERT_KEY, DT_CREATE, DT_UPDATE, ST_DESC) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStDeviceId(),
            info.getStDeviceName(),
            info.getStDeviceCode(),
            info.getStDeviceIp(),
            info.getStDeviceMac(),
            info.getStDeviceAddress(),
            info.getStTypeId(),
            info.getNmIsHost(),
            info.getNmOrder(),
            info.getNmInterval(),
            info.getNmRecover(),
            info.getNmDownTry(),
            info.getNmNotification(),
            info.getNmLng(),
            info.getNmLat(),
            info.getNmOnline(),
            info.getStChannel(),
            info.getStConfigId(),
            info.getNmSdtype(),
            info.getStUserId(),
            info.getStAreaId(),
            info.getStAddressId(),
            info.getStCertKey(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubDeviceInfo info) {
        String sql = "update INFOPUB_DEVICE_INFO set ST_DEVICE_NAME = ?, ST_DEVICE_CODE = ?, ST_DEVICE_IP = ?, ST_DEVICE_MAC = ?, ST_DEVICE_ADDRESS = ?, ST_TYPE_ID = ?, NM_IS_HOST = ?, NM_ORDER = ?, NM_INTERVAL = ?, NM_RECOVER = ?, NM_DOWN_TRY = ?, NM_NOTIFICATION = ?, NM_LNG = ?, NM_LAT = ?, NM_ONLINE = ?, ST_CHANNEL = ?, ST_CONFIG_ID = ?, NM_SDTYPE = ?, ST_USER_ID = ?, ST_AREA_ID = ?, ST_ADDRESS_ID = ?, ST_CERT_KEY = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ? where ST_DEVICE_ID = ?";
        Object[] obj = {
            info.getStDeviceName(),
            info.getStDeviceCode(),
            info.getStDeviceIp(),
            info.getStDeviceMac(),
            info.getStDeviceAddress(),
            info.getStTypeId(),
            info.getNmIsHost(),
            info.getNmOrder(),
            info.getNmInterval(),
            info.getNmRecover(),
            info.getNmDownTry(),
            info.getNmNotification(),
            info.getNmLng(),
            info.getNmLat(),
            info.getNmOnline(),
            info.getStChannel(),
            info.getStConfigId(),
            info.getNmSdtype(),
            info.getStUserId(),
            info.getStAreaId(),
            info.getStAddressId(),
            info.getStCertKey(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStDeviceId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_DEVICE_INFO set ";
        List<Object> list = new ArrayList<Object>();
        int i = 0;
        for (String field : map.keySet()) {
            if (i++ > 0) {
                sql += ", ";
            }
            sql += field + " = ?";
            list.add(map.get(field));
        }
        String subsql = conds != null ? conds.toString() : "";
        if ("".equals(subsql)) {
            if (con == null) {
                return SQL.execute(sql).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
            }
        } else {
            sql += " where " + subsql;
            list.addAll(conds.getObjectList());
            if (con == null) {
                return SQL.execute(sql, list.toArray()).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql, list.toArray()).TOTAL_RECORD_COUNT;
            }
        }
    }
    
    
    /**
     * 方法描述：刷新更新时间
     */
    public void updateTime(InfopubDeviceInfo info) {
        String sql = "update INFOPUB_DEVICE_INFO set DT_UPDATE = ? where ST_DEVICE_MAC = ?";
        Object[] obj = {
        	info.getDtUpdate(),
            info.getStDeviceMac()    	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }
    
    public int delete(Conditions conds) {
        String sql = "delete from INFOPUB_DEVICE_INFO";
        String subsql = conds != null ? conds.toString() : "";
        if ("".equals(subsql)) {
            if (con == null) {
                return SQL.execute(sql).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql).TOTAL_RECORD_COUNT;
            }
        } else {
            sql += " where " + subsql;
            if (con == null) {
                return SQL.execute(sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
            } else {
                return SQL.execute(con, sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;
            }
        }
    }

	public void delete(String[] stDeviceId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stDeviceId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stDeviceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
        delete(conds);
    }

    public PaginationArrayList<InfopubDeviceInfo> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_INFO", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_INFO", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubDeviceInfo> pal = new PaginationArrayList<InfopubDeviceInfo>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubDeviceInfo info = new InfopubDeviceInfo();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }

    public List<InfopubDeviceInfo> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_INFO", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_INFO", "*", conds, suffix);
        }
        ArrayList<InfopubDeviceInfo> al = new ArrayList<InfopubDeviceInfo>();
        while (rs.next()) {
            InfopubDeviceInfo info = new InfopubDeviceInfo();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubDeviceInfo> queryListMac(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_INFO", "ST_DEVICE_MAC,ST_DEVICE_CODE,ST_DEVICE_ADDRESS,NM_LNG,NM_LAT", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_INFO", "ST_DEVICE_MAC,ST_DEVICE_CODE,ST_DEVICE_ADDRESS,NM_LNG,NM_LAT", conds, suffix);
        }
        ArrayList<InfopubDeviceInfo> al = new ArrayList<InfopubDeviceInfo>();
        while (rs.next()) {
            InfopubDeviceInfo info = new InfopubDeviceInfo();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubDeviceInfo info, RecordSet rs){
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
        
        /*SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dfs.format(rs.getTimestamp("DT_UPDATE"));
        Date date = new Date();
        String nowTime = dfs.format(date);
        Date begin = null;
        Date end = null;
		try {
			begin = dfs.parse(updateTime);
			end = dfs.parse(nowTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}if (begin != null && end != null) {
			long second=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
			if (second < 30) {
				info.setNmOnline(new BigDecimal(1));
			}else {
				info.setNmOnline(new BigDecimal(0));
			}
		} else {
			Log.debug("更新时间begin 或 系统现在时间 end 为 null");
		}*/
        info.setNmOnline(rs.getBigDecimal("NM_ONLINE"));
		info.setStConfigId(rs.getOriginalString("ST_CONFIG_ID"));
        info.setStChannel(rs.getOriginalString("ST_CHANNEL"));
        info.setNmSdtype(rs.getBigDecimal("NM_SDTYPE"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
        info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
        info.setStAddressId(rs.getOriginalString("ST_ADDRESS_ID"));
        info.setStCertKey(rs.getOriginalString("ST_CERT_KEY"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
    }

	public InfopubDeviceInfo get(String stDeviceId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_ID", Condition.OT_EQUAL, stDeviceId));
        List<InfopubDeviceInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubDeviceInfo getCode(String stDeviceCode) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_CODE", Condition.OT_EQUAL, stDeviceCode));
        List<InfopubDeviceInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public InfopubDeviceInfo getMac(String stDeviceMac) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL, stDeviceMac));
        List<InfopubDeviceInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public void updateCretKey(String stCertKey, String stDeviceMac) {
		String sql = "update INFOPUB_DEVICE_INFO set ST_CERT_KEY = ? where ST_DEVICE_MAC = ?";
        Object[] obj = {stCertKey,stDeviceMac};
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
		
	}

	public BigDecimal getAmountByType(Conditions conds) {
		RecordSet rs;
		String table = "INFOPUB_DEVICE_INFO idi left join INFOPUB_DEVICE_TYPE idt on idi.ST_TYPE_ID = idt.ST_TYPE_ID";
        if (con == null) {
            rs = SQL.query(table, "count(idi.ST_DEVICE_ID) cou", conds,null);
        } else {
            rs = SQL.query(con, table, "count(idi.ST_DEVICE_ID) cou", conds, null);
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        while (rs.next()) {
        	bigDecimal = rs.getBigDecimal("cou");     	
        }
        return bigDecimal;
		
	}

	public JSONArray govDeviceCount(Conditions conds, String suffix) {
		RecordSet rs;
		String table = "INFOPUB_DEVICE_INFO idi left join INFOPUB_AREA ia on idi.ST_AREA_ID = ia.ST_AREA_ID left join INFOPUB_DEVICE_TYPE idt on idi.ST_TYPE_ID = idt.ST_TYPE_ID";
        if (con == null) {
            rs = SQL.query(table, "ia.ST_AREA_NAME,count(ia.ST_AREA_NAME) cou", conds,suffix);
        } else {
            rs = SQL.query(con, table, "ia.ST_AREA_NAME,count(ia.ST_AREA_NAME) cou", conds, suffix);
        }
        JSONArray arr = new JSONArray();
        JSONObject obj = null;
        while(rs.next()){
        	obj = new JSONObject();
        	obj.put(rs.getObject("ST_AREA_NAME"), rs.getBigDecimal("cou"));
        	arr.add(obj);
        }
		return arr;
	}

	
}
