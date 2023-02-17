package com.wondersgroup.serverApply.service;

import java.util.List;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.serverApply.bean.SelmServerDlink;

import coral.base.util.RequestWrapper;

/**
 * 服务关联设备业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmServerDlinkService {

	/**
	 * 根据主键 {@link SelmServerDlink#ST_APPLY_ID} {@link SelmServerDlink#ST_MACHINE_ID}获取服务关联设备
	 * 
	 * @param stApplyId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_APPLY_ID}
	 * @param stMachineId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_MACHINE_ID}
	 * @return 服务关联设备实例
	 */
	SelmServerDlink get(String stApplyId, String stMachineId);

	/**
	 * 查询服务关联设备列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务关联设备列表
	 */
	PaginationArrayList<SelmServerDlink> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmServerDlink#ST_APPLY_ID} {@link SelmServerDlink#ST_MACHINE_ID}删除服务关联设备
	 * 
	 * @param stApplyId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_APPLY_ID}
	 * @param stMachineId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_MACHINE_ID}
	 */
	void remove(String stApplyId, String stMachineId);

	/**
	 * 保存或更新服务关联设备
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 服务关联设备实例
	 */
	List<SelmServerDlink> saveOrUpdate(RequestWrapper wrapper);

}
