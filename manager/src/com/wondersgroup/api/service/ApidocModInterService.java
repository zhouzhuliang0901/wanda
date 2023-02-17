package com.wondersgroup.api.service;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.api.bean.ApidocModInter;

import coral.base.util.RequestWrapper;

/**
 * 模块关联接口业务接口
 * 
 * @author scalffold
 * 
 */
public interface ApidocModInterService {

	/**
	 * 根据主键 {@link ApidocModInter#ST_MODULE_ID} {@link ApidocModInter#ST_INTERFACE_ID}获取模块关联接口
	 * 
	 * @param stModuleId
	 *            模块关联接口主键 {@link ApidocModInter#ST_MODULE_ID}
	 * @param stInterfaceId
	 *            模块关联接口主键 {@link ApidocModInter#ST_INTERFACE_ID}
	 * @return 模块关联接口实例
	 */
	ApidocModInter get(String stModuleId, String stInterfaceId);

	/**
	 * 查询模块关联接口列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 模块关联接口列表
	 */
	PaginationArrayList<ApidocModInter> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link ApidocModInter#ST_MODULE_ID} {@link ApidocModInter#ST_INTERFACE_ID}删除模块关联接口
	 * 
	 * @param stModuleId
	 *            模块关联接口主键 {@link ApidocModInter#ST_MODULE_ID}
	 * @param stInterfaceId
	 *            模块关联接口主键 {@link ApidocModInter#ST_INTERFACE_ID}
	 */
	void remove(String stModuleId, String stInterfaceId);

	/**
	 * 保存或更新模块关联接口
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 模块关联接口实例
	 */
	ApidocModInter saveOrUpdate(RequestWrapper wrapper);

}
