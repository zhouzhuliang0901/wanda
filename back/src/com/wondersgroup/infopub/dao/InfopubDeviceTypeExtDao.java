package com.wondersgroup.infopub.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.infopub.bean.InfopubDeviceType;
import com.wondersgroup.infopub.bean.InfopubOdeviceStatus;

/**
 * 设备（外设）分类
 * @author generated by wfc.facility.tool.autocode.InternalDaoGenerator
 */
@Repository
public class InfopubDeviceTypeExtDao {

    private Connection con = null;

    public InfopubDeviceTypeExtDao() {
    }

    public InfopubDeviceTypeExtDao(Connection con) {
        this.con = con;
    }

    public void add(InfopubDeviceType info) {
        String sql = "insert into INFOPUB_DEVICE_TYPE(ST_TYPE_ID, ST_TYPE_NAME, ST_TYPE_CODE, ST_ICON, ST_CLASS, ST_COMPANY_ID, NM_DTYPE, ST_PARENT_TYPE_ID, NM_ORDER, DT_CREATE, DT_UPDATE, ST_DESC) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] obj = {
            info.getStTypeId(),
            info.getStTypeName(),
            info.getStTypeCode(),
            info.getStIcon(),
            info.getStClass(),
            info.getStCompanyId(),
            info.getNmDtype(),
            info.getStParentTypeId(),
            info.getNmOrder(),
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

    public void update(InfopubDeviceType info) {
        String sql = "update INFOPUB_DEVICE_TYPE set ST_TYPE_NAME = ?, ST_TYPE_CODE = ?, ST_ICON = ?, ST_CLASS = ?, ST_COMPANY_ID = ?, NM_DTYPE = ?, ST_PARENT_TYPE_ID = ?, NM_ORDER = ?, DT_CREATE = ?, DT_UPDATE = ?, ST_DESC = ? where ST_TYPE_ID = ?";
        Object[] obj = {
            info.getStTypeName(),
            info.getStTypeCode(),
            info.getStIcon(),
            info.getStClass(),
            info.getStCompanyId(),
            info.getNmDtype(),
            info.getStParentTypeId(),
            info.getNmOrder(),
            info.getDtCreate(),
            info.getDtUpdate(),
            info.getStDesc(),
            info.getStTypeId()        	
        };
        if (con == null) {
            SQL.execute(sql, obj);
        } else {
            SQL.execute(con, sql, obj);
        }
    }

    public int update(Map<String, Object> map, Conditions conds) {
        String sql = "update INFOPUB_DEVICE_TYPE set ";
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
    
    public int delete(Conditions conds) {
        String sql = "delete from INFOPUB_DEVICE_TYPE";
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

	public void delete(String[] stTypeId) {
        Conditions conds = Conditions.newOrConditions();
        for (int i = 0; i < stTypeId.length; i++) {
            Conditions subconds = Conditions.newAndConditions();
            subconds.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, stTypeId[i]));
            conds.add(subconds);
        }
        delete(conds);
    }

	public void delete(String stTypeId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, stTypeId));
        delete(conds);
    }

	

    public PaginationArrayList<InfopubDeviceType> query(Conditions conds, String suffix, int pageSize, int currentPage) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_TYPE", "*", conds, suffix, pageSize, currentPage);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_TYPE", "*", conds, suffix, pageSize, currentPage);
        }
        PaginationArrayList<InfopubDeviceType> pal = new PaginationArrayList<InfopubDeviceType>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
        while (rs.next()) {
            InfopubDeviceType info = new InfopubDeviceType();
            setProperties(info, rs);
            pal.add(info);
        }
        return pal;
    }
    /*public PaginationArrayList<SelmStatistics> queryStatistics(Conditions conds, String suffix, int pageSize, int currentPage) {
    	RecordSet rs;
    	if (con == null) {
    		rs = SQL.query("INFOPUB_DEVICE_TYPE", "*", conds, suffix, pageSize, currentPage);
    	} else {
    		rs = SQL.query(con, "INFOPUB_DEVICE_TYPE", "*", conds, suffix, pageSize, currentPage);
    	}
    	System.out.println("记录总数： " + rs.TOTAL_RECORD_COUNT);
    	System.out.println( "页面大小： " +  rs.COMMON_PAGE_SIZE);
    	System.out.println("当前页面: " +  rs.CURRENT_PAGE);
    	PaginationArrayList<SelmStatistics> pal = new PaginationArrayList<SelmStatistics>(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);
    	while (rs.next()) {
    		InfopubDeviceType info = new InfopubDeviceType();
    		setProperties(info, rs);
    		pal.add(info);
    	}
    	return pal;
    }*/

    public List<InfopubDeviceType> query(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_TYPE", "*", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_TYPE", "*", conds, suffix);
        }
        ArrayList<InfopubDeviceType> al = new ArrayList<InfopubDeviceType>();
        while (rs.next()) {
            InfopubDeviceType info = new InfopubDeviceType();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public List<InfopubDeviceType> queryNmDtype(Conditions conds, String suffix) {
        RecordSet rs;
        if (con == null) {
            rs = SQL.query("INFOPUB_DEVICE_TYPE", "NM_DTYPE", conds, suffix);
        } else {
            rs = SQL.query(con, "INFOPUB_DEVICE_TYPE", "NM_DTYPE", conds, suffix);
        }
        ArrayList<InfopubDeviceType> al = new ArrayList<InfopubDeviceType>();
        while (rs.next()) {
            InfopubDeviceType info = new InfopubDeviceType();
            setProperties(info, rs);
            al.add(info);
        }
        return al;
    }
    
    public static void setProperties(InfopubDeviceType info, RecordSet rs){
        info.setStTypeId(rs.getOriginalString("ST_TYPE_ID"));
        info.setStTypeName(rs.getOriginalString("ST_TYPE_NAME"));
        info.setStTypeCode(rs.getOriginalString("ST_TYPE_CODE"));
        info.setStIcon(rs.getOriginalString("ST_ICON"));
        info.setStClass(rs.getOriginalString("ST_CLASS"));
        info.setStCompanyId(rs.getOriginalString("ST_COMPANY_ID"));
        info.setNmDtype(rs.getBigDecimal("NM_DTYPE"));
        info.setStParentTypeId(rs.getOriginalString("ST_PARENT_TYPE_ID"));
        info.setNmOrder(rs.getBigDecimal("NM_ORDER"));
        info.setDtCreate(rs.getTimestamp("DT_CREATE"));
        info.setDtUpdate(rs.getTimestamp("DT_UPDATE"));
        info.setStDesc(rs.getOriginalString("ST_DESC"));
    }

	public InfopubDeviceType get(String stTypeId) {
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("ST_TYPE_ID", Condition.OT_EQUAL, stTypeId));
        List<InfopubDeviceType> list = query(conds, null);
        return list.size() > 0 ? list.get(0) : null;
    }
	public InfopubDeviceType getByName(String stTypeName) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_NAME", Condition.OT_EQUAL, stTypeName));
		List<InfopubDeviceType> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public InfopubDeviceType checkTypeCode(Conditions conds) {
		List<InfopubDeviceType> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<InfopubDeviceType> getByPrentId(String stTypeId) {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_TYPE_ID", Condition.OT_EQUAL, stTypeId));
		List<InfopubDeviceType> list = query(conds, null);
		return list;
	}

	public InfopubDeviceType getByTypeCode(InfopubOdeviceStatus infopubOdeviceStatus) {
		String stOutDeviceCode = infopubOdeviceStatus.getStOutDeviceCode();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_TYPE_CODE", Condition.OT_EQUAL, stOutDeviceCode));
		List<InfopubDeviceType> list = query(conds, null);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public int suCount(String stTypeId,int nmDtype) {
		String sql = "SELECT count(*) count from infopub_device_type WHERE ST_TYPE_ID=? AND NM_DTYPE=?";
		Object[] obj = { stTypeId,nmDtype};
		RecordSet rs;
		if (con == null) {
			rs = SQL.execute(sql, obj);
		} else {
			rs = SQL.execute(con, sql, obj);
		}
		int cont = 0;
		while (rs.next()) {
			BigDecimal bigDecimal = rs.getBigDecimal("count");
			cont = bigDecimal.intValue();
		}
		return cont;
	}
	
	
}
