package com.wondersgroup.outdevicestatus.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wondersgroup.dataitem.forward.web.bean.SelmItem;
import com.wondersgroup.outdevicestatus.bean.InfopubDeviceInfo;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;


/**
 * 信息设备
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubDeviceInfoDao {

    private Connection con = null;

    public InfopubDeviceInfoDao() {
    }

    public InfopubDeviceInfoDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubDeviceInfo info) {
        String sql = "insert into INFOPUB_DEVICE_INFO(ST_DEVICE_ID, ST_DEVICE_NAME, ST_DEVICE_CODE, ST_DEVICE_IP, ST_DEVICE_MAC, ST_DEVICE_ADDRESS, ST_TYPE_ID, NM_IS_HOST, NM_ORDER, NM_INTERVAL, NM_RECOVER, NM_DOWN_TRY, NM_NOTIFICATION, NM_LNG, NM_LAT,NM_ONLINE, ST_CHANNEL, ST_CONFIG_ID, ST_USER_ID, DT_CREATE, DT_UPDATE, ST_DESC, ST_AREA_ID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            info.getStUserId(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStAreaId()
          /*  info.getStWorkspaceId()        	*/
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public void update(InfopubDeviceInfo info) {
        String sql = "update INFOPUB_DEVICE_INFO set ST_DEVICE_NAME = ?, ST_DEVICE_CODE = ?, ST_DEVICE_IP = ?, ST_DEVICE_MAC = ?, ST_DEVICE_ADDRESS = ?, ST_TYPE_ID = ?, NM_IS_HOST = ?, NM_ORDER = ?, NM_INTERVAL = ?, NM_RECOVER = ?, NM_DOWN_TRY = ?, NM_NOTIFICATION = ?, NM_LNG = ?, NM_LAT = ?, NM_ONLINE = ?, ST_CHANNEL = ?,ST_CONFIG_ID = ?, ST_USER_ID = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ? where ST_DEVICE_ID = ?";
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
            info.getStUserId(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            //info.getStAreaId(),
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
        info.setStAreaId(rs.getOriginalString("ST_AREA_ID"));
        
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime = dfs.format(rs.getTimestamp("DT_UPDATE"));
        Date date = new Date();
        String nowTime = dfs.format(date);
        Date begin = null;
        Date end = null;
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
        //info.setNmOnline(rs.getBigDecimal("NM_ONLINE"));
        info.setStChannel(rs.getOriginalString("ST_CHANNEL"));
        info.setStUserId(rs.getOriginalString("ST_USER_ID"));
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
	
	
	public InfopubDeviceInfo getByMacId(String macId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_DEVICE_MAC", Condition.OT_EQUAL, macId));
        List<InfopubDeviceInfo> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	
	public List<SelmItem> getItemByMachine(String machineMAC){
		InfopubDeviceInfo machineInfo = getByMacId(machineMAC);
		String sql = "SELECT si.ST_ITEM_ID,si.ST_ITEM_NO,si.ST_TEN_CODE,si.ST_MAIN_NAME,"
					+ " si.ST_ITEM_NAME,si.ST_ORGAN_ID,si.ST_WORK_TYPE FROM selm_item si "
					+ " LEFT JOIN selm_device_item sdi ON sdi.ST_ITEM_ID = si.ST_ITEM_ID "
					+ " WHERE sdi.ST_DEVICE_ID = ? ";
		RecordSet rs;
        if (con == null) {
        	rs =  SQL.execute(sql, new Object[]{machineInfo.getStDeviceId()});
        } else {
        	rs = SQL.execute(con, sql, new Object[]{machineInfo.getStDeviceId()});
        }
        List<SelmItem> itemList = new ArrayList<SelmItem>();
        while(rs.next()){
        	SelmItem item = new SelmItem();
        	item.setStItemId(rs.getOriginalString("ST_ITEM_ID"));
        	item.setStItemNo(rs.getOriginalString("ST_ITEM_NO"));
        	item.setStTenCode(rs.getOriginalString("ST_TEN_CODE"));
        	item.setStMainName(rs.getOriginalString("ST_MAIN_NAME"));
        	item.setStItemName(rs.getOriginalString("ST_ITEM_NAME"));
        	item.setStOrganId(rs.getOriginalString("ST_ORGAN_ID"));
        	item.setStWorkType(rs.getOriginalString("ST_WORK_TYPE"));
        	itemList.add(item);
        }
		
		return itemList;
	}

	public InfopubDeviceInfo getMachineInfoByCertKey(String stCertKey) {
		  Conditions conds = Conditions.newAndConditions();
	        conds.add(new Condition("ST_CERT_KEY", Condition.OT_EQUAL, stCertKey));
	   RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_INFO", "*", conds, "");
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_INFO", "*", conds, "");
        }
        ArrayList<InfopubDeviceInfo> list = new ArrayList<InfopubDeviceInfo>();
        while (rs.next()) {
            InfopubDeviceInfo info = new InfopubDeviceInfo();
            setProperties(info, rs);
            list.add(info);
        }
		return list.size() > 0 ? list.get(0) : null;
	}
}