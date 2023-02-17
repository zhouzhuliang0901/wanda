package com.wondersgroup.wdf.service;

import java.math.*;
import java.sql.*;

import com.wondersgroup.wdf.dao.UacAttachLink;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 电子材料关联附件业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacAttachLinkService {

	/**
	 * 根据主键 {@link UacAttachLink#ST_ESTUFF_ID} {@link UacAttachLink#ST_ATTACH_ID}获取电子材料关联附件
	 * 
	 * @param stEstuffId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ESTUFF_ID}
	 * @param stAttachId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ATTACH_ID}
	 * @return 电子材料关联附件实例
	 */
	UacAttachLink get(String stEstuffId, String stAttachId);

	/**
	 * 查询电子材料关联附件列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 电子材料关联附件列表
	 */
	PaginationArrayList<UacAttachLink> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacAttachLink#ST_ESTUFF_ID} {@link UacAttachLink#ST_ATTACH_ID}删除电子材料关联附件
	 * 
	 * @param stEstuffId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ESTUFF_ID}
	 * @param stAttachId
	 *            电子材料关联附件主键 {@link UacAttachLink#ST_ATTACH_ID}
	 */
	void remove(String stEstuffId, String stAttachId);

	/**
	 * 保存或更新电子材料关联附件
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 电子材料关联附件实例
	 */
	UacAttachLink saveOrUpdate(RequestWrapper wrapper);

}
