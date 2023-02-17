package com.wondersgroup.sms.organ.service;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.sms.organ.bean.SmsOrgan;

import coral.base.util.RequestWrapper;

/**
 * 组织机构表业务接口
 * 
 * @author guicb
 * 
 */
public interface SmsOrganService {

	/**
	 * 根据主键 {@link SmsOrgan#ST_ORGAN_ID}获取组织机构表
	 * 
	 * @param stOrganId
	 *            组织机构表主键 {@link SmsOrgan#ST_ORGAN_ID}
	 * @return 组织机构表实例
	 */
	SmsOrgan get(String stOrganId);

	/**
	 * 查询组织机构表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组织机构表列表
	 */
	PaginationArrayList<SmsOrgan> query(RequestWrapper wrapper);

	/**
	 * 查询主管单位表列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 组织机构表列表
	 */
	PaginationArrayList<SmsOrgan> queryCompetent(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SmsOrgan#ST_ORGAN_ID}删除组织机构表
	 * 
	 * @param organIdList
	 *            组织机构表主键 {@link SmsOrgan#ST_ORGAN_ID}
	 */
	void remove(String[] organIdList);

	/**
	 * 保存或更新组织机构表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组织机构表实例
	 */
	SmsOrgan saveOrUpdate(RequestWrapper wrapper);

}
