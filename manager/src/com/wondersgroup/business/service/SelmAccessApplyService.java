package com.wondersgroup.business.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.business.bean.SelmAccessApply;
import com.wondersgroup.business.bean.SelmAttach;


import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 接入申请业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmAccessApplyService {

	/**
	 * 根据主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}获取接入申请
	 * 
	 * @param stAccessApplyId
	 *            接入申请主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}
	 * @return 接入申请实例
	 */
	SelmAccessApply get(String stAccessApplyId);

	/**
	 * 查询接入申请列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 接入申请列表
	 */
	PaginationArrayList<SelmAccessApply> query(RequestWrapper wrapper);

	/**
	 * 根据主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}删除接入申请
	 * 
	 * @param stAccessApplyId
	 *            接入申请主键 {@link SelmAccessApply#ST_ACCESS_APPLY_ID}
	 */
	void remove(String stAccessApplyId);

	/**
	 * 保存或更新接入申请
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 接入申请实例
	 */
	SelmAccessApply saveOrUpdate(RequestWrapper wrapper);

	JSONObject list(HttpReqRes httpReqRes);
	/**
	 * 
	 * @param stApplyUserId 提交人id
	 * @param fileName 文件名称
	 * @param fileType 文件类型
	 * @param file 文件
	 * @param len 文件大小
	 * @param applytitle 提交标题
	 * @param applycontent 提交内容
	 * @return
	 */
	SelmAttach uploadStuff(String stApplyUserId, String fileName, String fileType,
			byte[] file, int len, String applytitle, String applycontent);

	void downLoad(HttpReqRes httpReqRes);

	JSONObject NoApplylist(HttpReqRes httpReqRes);

}
