package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacBusiness;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 企业信息表业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacBusinessService {

	/**
	 * 根据主键 {@link UacBusiness#ST_BUSINESS_ID}获取企业信息表
	 * 
	 * @param stBusinessId
	 *            企业信息表主键 {@link UacBusiness#ST_BUSINESS_ID}
	 * @return 企业信息表实例
	 */
	UacBusiness get(String stBusinessId);

	/**
	 * 查询企业信息表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 企业信息表列表
	 */
	PaginationArrayList<UacBusiness> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacBusiness#ST_BUSINESS_ID}删除企业信息表
	 * 
	 * @param stBusinessId
	 *            企业信息表主键 {@link UacBusiness#ST_BUSINESS_ID}
	 */
	void remove(String stBusinessId);

	/**
	 * 保存或更新企业信息表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 企业信息表实例
	 */
	UacBusiness saveOrUpdate(RequestWrapper wrapper);

}
