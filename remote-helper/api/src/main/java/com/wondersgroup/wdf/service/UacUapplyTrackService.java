package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUapplyTrack;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 综合办件跟踪信息业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyTrackService {

	/**
	 * 根据主键 {@link UacUapplyTrack#ST_TRACK_ID}获取综合办件跟踪信息
	 * 
	 * @param stTrackId
	 *            综合办件跟踪信息主键 {@link UacUapplyTrack#ST_TRACK_ID}
	 * @return 综合办件跟踪信息实例
	 */
	UacUapplyTrack get(String stTrackId);

	/**
	 * 查询综合办件跟踪信息列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合办件跟踪信息列表
	 */
	PaginationArrayList<UacUapplyTrack> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyTrack#ST_TRACK_ID}删除综合办件跟踪信息
	 * 
	 * @param stTrackId
	 *            综合办件跟踪信息主键 {@link UacUapplyTrack#ST_TRACK_ID}
	 */
	void remove(String stTrackId);

	/**
	 * 保存或更新综合办件跟踪信息
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合办件跟踪信息实例
	 */
	UacUapplyTrack saveOrUpdate(RequestWrapper wrapper);

}
