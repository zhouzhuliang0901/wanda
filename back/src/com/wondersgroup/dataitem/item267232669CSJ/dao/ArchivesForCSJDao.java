package com.wondersgroup.dataitem.item267232669CSJ.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import com.wondersgroup.dataitem.item267232669CSJ.bean.Archives;

@Repository
public class ArchivesForCSJDao {
	
	public List<Archives> queryCitiesByProvinceName(String provinceName){
		RecordSet rs;
		String sql = "select SHI,SHI_ID from SELFM_ARCHIVES_AREA "
					+" where SHENG = ? "
					+" group by SHI,SHI_ID "
					+" order by SHI_ID";
		rs = SQL.execute(sql, new Object[]{provinceName});
		List<Archives> list = new ArrayList<Archives>();
		while(rs.next()){
			Archives archives = new Archives();
			archives.setCityId(rs.getOriginalString("SHI_ID"));
			archives.setCityName(rs.getOriginalString("SHI"));
			list.add(archives);
		}
		return list;
	}
	
	public List<Archives> queryArchivesByCityId(String cityId){
		RecordSet rs;
		String sql = "select NAME,NAME_ID from SELFM_ARCHIVES_AREA "
					+" where SHI_ID = ? "
					+" group by NAME,NAME_ID "
					+" order by NAME_ID";
		rs = SQL.execute(sql, new Object[]{cityId});
		List<Archives> list = new ArrayList<Archives>();
		while(rs.next()){
			Archives archives = new Archives();
			archives.setArchivesId(rs.getOriginalString("NAME_ID"));
			archives.setArchivesName(rs.getOriginalString("NAME"));
			list.add(archives);
		}
		return list;
	}
}
