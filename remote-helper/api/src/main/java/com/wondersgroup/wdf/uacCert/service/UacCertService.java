package com.wondersgroup.wdf.uacCert.service;

import com.wondersgroup.wdf.uacCert.dao.UacCert;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 发证信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacCertService {

	/**
	 * 根据主键 {@link UacCert#ST_CERT_ID}获取发证信息
	 * 
	 * @param stCertId
	 *            发证信息主键 {@link UacCert#ST_CERT_ID}
	 * @return 发证信息实例
	 */
	UacCert get(String stCertId);

	/**
	 * 查询发证信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 发证信息列表
	 */
	PaginationArrayList<UacCert> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacCert#ST_CERT_ID}删除发证信息
	 * 
	 * @param stCertId
	 *            发证信息主键 {@link UacCert#ST_CERT_ID}
	 */
	void remove(String stCertId);

	/**
	 * 保存或更新发证信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 发证信息实例
	 */
	UacCert saveOrUpdate(RequestWrapper wrapper);


	/**
	 * 根据主键 {@link UacCert#ST_CERT_ID}获取发证信息
	 *
	 * @param stApplyId
	 *            发证信息主键 {@link UacCert#ST_CERT_ID}
	 * @return 发证信息实例
	 */
	UacCert getByApplyId(String stApplyId);

}
