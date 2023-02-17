package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacWebSystemTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 系统信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacWebSystemServiceTwo {

	/**
	 * 根据主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}获取系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}
	 * @return 系统信息实例
	 */
	UacWebSystemTwo get(String stWebSystemId);

	/**
	 * 查询系统信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统信息列表
	 */
	PaginationArrayList<UacWebSystemTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}删除系统信息
	 * 
	 * @param stWebSystemId
	 *            系统信息主键 {@link UacWebSystemTwo#ST_WEB_SYSTEM_ID}
	 */
	void remove(String stWebSystemId);

	/**
	 * 保存或更新系统信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统信息实例
	 */
	UacWebSystemTwo saveOrUpdate(RequestWrapper wrapper);

}
