package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUserGroupLinkTwo;

import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 组关联人员业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUserGroupLinkTwoService {

	/**
	 * 根据主键 {@link UacUserGroupLinkTwo#ST_USER_ID} {@link UacUserGroupLinkTwo#ST_GROUP_ID}获取组关联人员
	 * 
	 * @param stUserId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_USER_ID}
	 * @param stGroupId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_GROUP_ID}
	 * @return 组关联人员实例
	 */
	UacUserGroupLinkTwo get(String stUserId, String stGroupId);

	/**
	 * 查询组关联人员列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组关联人员列表
	 */
	PaginationArrayList<UacUserGroupLinkTwo> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUserGroupLinkTwo#ST_USER_ID} {@link UacUserGroupLinkTwo#ST_GROUP_ID}删除组关联人员
	 * 
	 * @param stUserId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_USER_ID}
	 * @param stGroupId
	 *            组关联人员主键 {@link UacUserGroupLinkTwo#ST_GROUP_ID}
	 */
	void remove(String[] stUserId, String[] stGroupId);

	/**
	 * 保存或更新组关联人员
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组关联人员实例
	 */
	UacUserGroupLinkTwo saveOrUpdate(RequestWrapper wrapper);

	UacUserGroupLinkTwo saveitem(RequestWrapper wrapper);

}
