package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUnionApply;
import coral.base.util.RequestWrapper;
import org.json.JSONObject;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 综合受理业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUnionApplyService {

	/**
	 * 根据主键 {@link UacUnionApply#ST_APPLY_ID}获取综合受理
	 * 
	 * @param stApplyId
	 *            综合受理主键 {@link UacUnionApply#ST_APPLY_ID}
	 * @return 综合受理实例
	 */
	UacUnionApply get(String stApplyId);

	/**
	 * 查询综合受理列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理列表
	 */
	PaginationArrayList<UacUnionApply> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUnionApply#ST_APPLY_ID}删除综合受理
	 * 
	 * @param stApplyId
	 *            综合受理主键 {@link UacUnionApply#ST_APPLY_ID}
	 */
	void remove(String stApplyId);

	/**
	 * 保存或更新综合受理
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理实例
	 */
	UacUnionApply saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 解析JSONObject
	 * @param jsonObject
	 * @return
	 */
	UacUnionApply geuUnionAppByJSON(JSONObject jsonObject);

	/**
	 * 查询综合受理列表/收件接口
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理列表
	 */
	List<UacUnionApply> queryRece(RequestWrapper wrapper);
}
