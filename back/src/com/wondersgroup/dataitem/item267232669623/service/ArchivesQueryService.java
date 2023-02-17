package com.wondersgroup.dataitem.item267232669623.service;

import java.util.List;

import com.wondersgroup.dataitem.item267232669623.bean.ArchivesApplyInfo;

public interface ArchivesQueryService {
	
	/**
	 * 保存档案查询受理信息
	 * @param slid
	 * @param code
	 * @param name
	 * @param idCard
	 */
	void addArchivesInfo(String slid, String stApplyNo, String code, String name, String idCard);
	
	/**
	 * 根据身份证号和档案类型查询是否存在未受理的查询申请
	 * @param indetNo
	 * @param code
	 * @return
	 */
	List<ArchivesApplyInfo> queryArchivesInfoByIdentNo(String identNo, String code, String flag);
	
	/**
	 * 更新档案查询申请信息有效状态
	 * @param slid
	 * @param slbh
	 */
	void updateArchivesInfo(String slid, String slbh);
	
	/**
	 * 更新档案查询申请信息打印状态状态
	 * @param slid
	 * @param slbh
	 */
	void updateArchivesPrintStatus(String slid, String slbh);
	
	/**
	 * 更新审核结果查看异常返回值的次数
	 * @param slid
	 * @param slbh
	 * @param i
	 */
	void updateArchivesInfoExcTimes(String slid, String slbh, int i);

}
