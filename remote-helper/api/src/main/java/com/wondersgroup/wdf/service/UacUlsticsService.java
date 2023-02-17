package com.wondersgroup.wdf.service;

import com.wondersgroup.wdf.dao.UacUlstics;
import coral.base.util.RequestWrapper;
import org.json.JSONObject;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 办件物流业务接口
 * 
 * @author scalffold
 * 
 */
public interface UacUlsticsService {

	/**
	 * 根据主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}获取办件物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件物流主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}
	 * @return 办件物流实例
	 */
	UacUlstics get(String stUnionLogisticsId);

	/**
	 * 查询办件物流列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 办件物流列表
	 */
	PaginationArrayList<UacUlstics> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}删除办件物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件物流主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}
	 */
	void remove(String stUnionLogisticsId);

	/**
	 * 保存或更新办件物流
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 办件物流实例
	 */
	UacUlstics saveOrUpdate(RequestWrapper wrapper);

	/**
	 * 解析JSONObject
	 * @return
	 */
    UacUlstics getUlsticsByJSON(JSONObject jsonObject);
}
