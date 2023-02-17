package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUapplyStuff;
import coral.base.util.RequestWrapper;
import org.json.JSONObject;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 综合受理电子材料业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUapplyStuffService {

	/**
	 * 根据主键 {@link UacUapplyStuff#ST_ESTUFF_ID}获取综合受理电子材料
	 * 
	 * @param stEstuffId
	 *            综合受理电子材料主键 {@link UacUapplyStuff#ST_ESTUFF_ID}
	 * @return 综合受理电子材料实例
	 */
	UacUapplyStuff get(String stEstuffId);

	/**
	 * 查询综合受理电子材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理电子材料列表
	 */
	PaginationArrayList<UacUapplyStuff> query(RequestWrapper wrapper);

	/**
	 * 查询以上传材料列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理电子材料列表
	 */
	List<UacUapplyStuff> queryAttach(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUapplyStuff#ST_ESTUFF_ID}删除综合受理电子材料
	 * 
	 * @param stEstuffId
	 *            综合受理电子材料主键 {@link UacUapplyStuff#ST_ESTUFF_ID}
	 */
	void remove(String stEstuffId);

	/**
	 * 保存或更新综合受理电子材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理电子材料实例
	 */
	UacUapplyStuff saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 解析JSONObject
	 * @return
	 */
    UacUapplyStuff getUacUapplyStuffByJSON(JSONObject jsonObject);


	/**
	 * 解析 修改 JSONObject
	 * @return
	 */
	UacUapplyStuff geteditUacUapplyStuffByJSON(JSONObject jsonObject);

}
