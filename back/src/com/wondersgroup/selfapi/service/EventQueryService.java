package com.wondersgroup.selfapi.service;

import java.util.List;

import com.wondersgroup.selfapi.bean.AppApplyInfo;
import com.wondersgroup.selfapi.bean.ApplyDetailInfo;
import com.wondersgroup.selfapi.bean.NameAndRegex;


public interface EventQueryService {

	/**
	 * 方法描述：根据办件id返回办件信息
	 * @param stApplyId
	 * table WORK_APPLY_INFO 条件:st_apply_id
	 */
	public AppApplyInfo getWorkApplyInfoById(String stApplyId);

	
	/**
	 * 方法描述：通过身份证获取对应的办件列表信息 table:WORK_APPLY_INFO
	 * @param stIdCard
	 * 条件：ST_DEAL_IDENTITY_NO,NsM_DEAL_IDENTITY_TYPE
	 */
	public List<AppApplyInfo> getApplyInfoByIdCard(String stIdCard);


	
	/**
	 * 方法描述：通过身份证获取对应的办件列表信息（分页查询） table:WORK_APPLY_INFO
	 * @param pageSize
	 * @param currentPage
	 * @param stIdCard
	 * @条件：ST_DEAL_IDENTITY_NO,NsM_DEAL_IDENTITY_TYPE
	 */
	public List<AppApplyInfo> getApplyInfoByIdCard(Integer pageSize,
			Integer currentPage, String stIdCard);


	/**
	 * 方法描述：根据办件编号查询企业名称或个人姓名，返回之中某一个字符为缺失汉字
	 * @param stApplyNo
	 * @return NameAndRegex
	 */
	public NameAndRegex getStringsByStApplyNo(String stApplyNo);


	/**
	 * 方法描述：根据企业名称或者用户姓名和办件编号查询办件 
	 * @param name
	 * @param stApplyNo
	 * @return AppApplyInfo
	 */
	public AppApplyInfo getWorkApplyInfo(String name, String stApplyNo);


	/**
	 * 方法描述：查询办件 掃一掃票上的二维码（通过办件编号查询）
	 * @param stApplyNo
	 * @return AppApplyInfo
	 */
	public AppApplyInfo getApplyInfoByStApplyNo(String stApplyNo);


	/**
	 * 方法描述：根据办件编码获取办件的详细信息
	 * @param stApplyNo
	 * @return ApplyDetailInfo
	 */
	public ApplyDetailInfo getApplyDetailInfoByNo(String stApplyNo);


	/**
	 * 方法描述：保存办件满意度评价信息
	 * @param nmSatisfation
	 * @param content
	 * @param stApplyId
	 * @return String
	 */
	public String saveNmSatisfation(String nmSatisfation, String content,
			String stApplyId);

	
}
