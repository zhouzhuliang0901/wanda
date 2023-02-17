package com.wondersgroup.api.service;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.bean.ApidocModuleView;

import coral.base.util.RequestWrapper;

/**
 * 模块业务接口
 * 
 * @author scalffold
 * 
 */
public interface ApidocModuleService {

	/**
	 * 根据主键 {@link ApidocModule#ST_MODULE_ID}获取模块
	 * 
	 * @param stModuleId
	 *            模块主键 {@link ApidocModule#ST_MODULE_ID}
	 * @return 模块实例
	 */
	ApidocModule get(String stModuleId);

	/**
	 * 查询模块列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 模块列表
	 */
	PaginationArrayList<ApidocModule> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link ApidocModule#ST_MODULE_ID}删除模块
	 * 
	 * @param stModuleId
	 *            模块主键 {@link ApidocModule#ST_MODULE_ID}
	 */
	void remove(String[] stModuleId);

	/**
	 * 保存或更新模块
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 模块实例
	 */
	ApidocModule saveOrUpdate(RequestWrapper wrapper);
	
	/**
	 * 根据模块ID查询模块信息和模块下的所有接口信息
	 * 
	 * @param stModuleId
	 * @return 模块信息和模块下的所有接口信息
	 */
	public ApidocModuleView getModuleAndInterface(String stModuleId);

}
