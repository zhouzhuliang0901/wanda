package com.wondersgroup.api.service;

import java.util.List;

import wfc.facility.tool.autocode.PaginationArrayList;

import com.wondersgroup.api.bean.ApidocAllInfoView;
import com.wondersgroup.api.bean.ApidocProject;

import coral.base.util.RequestWrapper;

/**
 * 项目业务接口
 * 
 * @author scalffold
 * 
 */
public interface ApidocProjectService {

	/**
	 * 根据主键 {@link ApidocProject#ST_PROJECT_ID}获取项目
	 * 
	 * @param stProjectId
	 *            项目主键 {@link ApidocProject#ST_PROJECT_ID}
	 * @return 项目实例
	 */
	ApidocProject get(String stProjectId);

	/**
	 * 查询项目列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 项目列表
	 */
	PaginationArrayList<ApidocProject> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link ApidocProject#ST_PROJECT_ID}删除项目
	 * 
	 * @param stProjectId
	 *            项目主键 {@link ApidocProject#ST_PROJECT_ID}
	 */
	void remove(String[] stProjectId);

	/**
	 * 保存或更新项目
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 项目实例
	 */
	ApidocProject saveOrUpdate(RequestWrapper wrapper);
	
	List<ApidocProject> queryProjectList(RequestWrapper wrapper);
	
	public ApidocAllInfoView queryByProjectId(String stProjectId);

}
