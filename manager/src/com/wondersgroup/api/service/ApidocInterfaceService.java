package com.wondersgroup.api.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.api.bean.ApidocInterface;
import com.wondersgroup.api.bean.ApidocModule;

import coral.base.util.RequestWrapper;

/**
 * 接口业务接口
 * 
 * @author scalffold
 * 
 */
public interface ApidocInterfaceService {

	/**
	 * 根据主键 {@link ApidocInterface#ST_INTERFACE_ID}获取接口
	 * 
	 * @param stInterfaceId
	 *            接口主键 {@link ApidocInterface#ST_INTERFACE_ID}
	 * @return 接口实例
	 */
	ApidocInterface get(String stInterfaceId);

	/**
	 * 查询接口列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 接口列表
	 */
	PaginationArrayList<ApidocInterface> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link ApidocInterface#ST_INTERFACE_ID}删除接口
	 * 
	 * @param stInterfaceId
	 *            接口主键 {@link ApidocInterface#ST_INTERFACE_ID}
	 */
	void remove(String[] stInterfaceId);

	/**
	 * 保存或更新接口
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 接口实例
	 */
	ApidocInterface saveOrUpdate(HttpServletRequest req);
	
	public List<ApidocInterface> queryInterfaceAndLink(String stInterfaceId);
	
	public List<ApidocInterface> queryByModuleId(String stModuleId);
	
	void copy(String[] stInterfaceId);
	
	public List<ApidocModule> getModuleList();

}
