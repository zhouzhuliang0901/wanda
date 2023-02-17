package com.wondersgroup.common.dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;
import wfc.service.database.SQL;

import com.wondersgroup.common.bean.ExceptionInfo;

@Repository
public class ExceptionDao {
	
	private Connection con = null;
	
	public ExceptionDao(){}
	public ExceptionDao(Connection con){
		this.con = con;
	}
	
	public void saveExceptionInfo(ExceptionInfo exceptionInfo){
		String sql = " INSERT INTO SELM_EXCEPTION_INFO (ST_ID, ST_EXCEPTION_METHOD, ST_EXCEPTION_PACKAGE,"
				+" ST_EXCEPTION_CAUSE, ST_EXCEPTION_LINE, ST_EXCEPTION_FILE, DT_EXCEPTION_TIME, ST_REQUEST_URL, ST_REQUEST_METHOD,"
				+" ST_REQUEST_PARAM, ST_EXT1, ST_EXT2, ST_EXT3, ST_EXT4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] obj = {
				exceptionInfo.getStId(),
				exceptionInfo.getStExceptionMethod(),
				exceptionInfo.getStExceptionPackage(),
				exceptionInfo.getStExceptionCause(),
				exceptionInfo.getStExceptionLine(),
				exceptionInfo.getStExceptionFile(),
				exceptionInfo.getDtExceptionTime(),
				exceptionInfo.getStRequestUrl(),
				exceptionInfo.getStRequestMethod(),
				exceptionInfo.getStRequestParam(),
				exceptionInfo.getStExt1(),
				exceptionInfo.getStExt2(),
				exceptionInfo.getStExt3(),
				exceptionInfo.getStExt4()
		};
		if(con == null){
			SQL.execute(sql, obj);
		} else {
			SQL.execute(con, sql, obj);
		}
	}
}
