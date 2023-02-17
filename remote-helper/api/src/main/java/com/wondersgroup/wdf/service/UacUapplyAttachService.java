package com.wondersgroup.wdf.service;


import com.wondersgroup.wdf.dao.UacUapplyAttach;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 综合材料附件业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyAttachService {

	/**
	 * 根据主键 {@link UacUapplyAttach#ST_ATTACH_ID}获取综合材料附件
	 * 
	 * @param stAttachId
	 *            综合材料附件主键 {@link UacUapplyAttach#ST_ATTACH_ID}
	 * @return 综合材料附件实例
	 */
	UacUapplyAttach get(String stAttachId);

	/**
	 * 查询综合材料附件列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合材料附件列表
	 */
	PaginationArrayList<UacUapplyAttach> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyAttach#ST_ATTACH_ID}删除综合材料附件
	 * 
	 * @param stAttachId
	 *            综合材料附件主键 {@link UacUapplyAttach#ST_ATTACH_ID}
	 */
	void remove(String stAttachId);


	/**
	 * 根据主键 {@link UacUapplyAttach#ST_ATTACH_ID}删除综合材料附件
	 *
	 * @param stAttachId
	 *            综合材料附件主键 {@link UacUapplyAttach#ST_ATTACH_ID}
	 */
	void remove(String[] stAttachId);

	/**
	 * 保存或更新综合材料附件
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合材料附件实例
	 */
	UacUapplyAttach saveOrUpdate(RequestWrapper wrapper);

}
