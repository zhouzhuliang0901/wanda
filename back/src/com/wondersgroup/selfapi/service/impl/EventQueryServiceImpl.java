package com.wondersgroup.selfapi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.AppApplyInfo;
import com.wondersgroup.selfapi.bean.ApplyDetailInfo;
import com.wondersgroup.selfapi.bean.NameAndRegex;
import com.wondersgroup.selfapi.dao.EventQueryDao;
import com.wondersgroup.selfapi.service.EventQueryService;
import com.wondersgroup.selfapi.util.RandomChinese;

@Service
public class EventQueryServiceImpl implements EventQueryService {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Autowired
	private EventQueryDao eventQueryDao;
	
	
	/**
	 * 根据办件id返回办件信息
	 */
	@Override
	public AppApplyInfo getWorkApplyInfoById(String stApplyId) {
		if (stApplyId == null) {
			return null;
		}
		AppApplyInfo info = eventQueryDao.getWorkApplyInfoById(stApplyId);
		return info;
	}

	/**
	 * 根据身份证查询办件信息 无分页查询（网站和自助终端）
	 */
	@Override
	public List<AppApplyInfo> getApplyInfoByIdCard(String stIdCard) {
		if (StringUtils.isBlank(stIdCard)) {
			return null;
		}
		List<AppApplyInfo> subList = new ArrayList<AppApplyInfo>();
		List<AppApplyInfo> slList = eventQueryDao.getApplyInfoByIdCard(stIdCard);
		subList.addAll(slList);
		if ("1".equals(Config.get("isWantqueryHK"))) {
				List<AppApplyInfo> hkList = eventQueryDao.getHKApplyInfoByIdCard(stIdCard);
				subList.addAll(hkList);
		}
		return subList;
	}

	/**
	 * 根据身份证查询办件信息 （分页查询）（网站和自助终端） pageSize 每页的显示页数 currentPage 当前页 stIdCard
	 * 身份证号
	 */
	@Override
	public List<AppApplyInfo> getApplyInfoByIdCard(Integer pageSize,
			Integer currentPage, String stIdCard) {
		
		if (StringUtils.isBlank(stIdCard)) {
			return null;
		}
		List<AppApplyInfo> subList = eventQueryDao.getApplyInfoByIdCard(
				pageSize, currentPage, stIdCard);
		return subList;
	}

	/**
	 * 办件查询（自助终端） 根据办件编号查询企业名称或个人姓名，返回之中某一个字符为缺失汉字
	 */
	@Override
	public NameAndRegex getStringsByStApplyNo(String stApplyNo) {
		if (StringUtils.isBlank(stApplyNo) || stApplyNo.length() < 3) {
			return null;
		}
		NameAndRegex info = null;
		String name = eventQueryDao.getNameByStApplyNo(stApplyNo);
		Log.debug("----" + name);
		// String regex = Config.get("work.apply.info.regex");
		String regex = "上海|北京";
		// 正则后的结果
		if (!StringUtils.isBlank(name)) {
			String result = name.replaceAll(regex, "");
			StringBuffer sb = new StringBuffer();
			Random random = new Random();
			// 随机取出来的汉字
			String ss = result.charAt(random.nextInt(result.length())) + "";
			// 去掉汉字后的字符串
			String aa = name.replaceFirst(ss, "*");
			// 随机取到9个汉字
			sb.append(RandomChinese.getRandomJianHan(9));
			// 将取出的字符插入随机位置
			sb.insert(sb.length(), ss);
			String randomString = sb.toString();
			info = new NameAndRegex();
			info.setPrefix(ss);
			info.setName(aa);
			info.setRegex(randomString);
		}
		return info;
	}

	/**
	 * 根据企业名称或者办理者姓名和办件编号查询办件
	 */
	@Override
	public AppApplyInfo getWorkApplyInfo(String name, String stApplyNo) {
		if (StringUtils.isBlank(name) && StringUtils.isBlank(stApplyNo)) {
			return null;
		}
		AppApplyInfo info = null;
		if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(stApplyNo)) {
			info = eventQueryDao.getWorkApplyInfoByNameAndId(name, stApplyNo);
		} else {
			info = eventQueryDao.getWorkApplyInfoByStApplyNo(stApplyNo);
		}
		if (info != null) {
			if (StringUtils.isBlank(info.getStUnit())) {
				info.setStUnit(info.getStName());
			}
		}
		if ("1".equals(Config.get("isWantqueryHK"))) {
			if (info == null) {
				info = eventQueryDao.getHKWorkApplyInfoByNameAndId(name, stApplyNo);
			}
		}
		return info;
	}

	/**
	 * 扫描二维码获得办件信息（自助终端，手机APP）
	 */
	@Override
	public AppApplyInfo getApplyInfoByStApplyNo(String stApplyNo) {
		if (StringUtils.isBlank(stApplyNo)) {
			return null;
		}
		AppApplyInfo appApplyInfo = eventQueryDao
				.getWorkApplyInfoByStApplyNo(stApplyNo);
		if (appApplyInfo != null) {
			// 公司名字没有的话用用户的姓名代替
			if (StringUtils.isBlank(appApplyInfo.getStUnit())) {
				appApplyInfo.setStUnit(appApplyInfo.getStName());
			}
		}
		if ("1".equals(Config.get("isWantqueryHK"))) {
			if (appApplyInfo == null) {
				appApplyInfo = eventQueryDao.getHKWorkApplyInfoByNameAndId("", stApplyNo);
			}
		}
		return appApplyInfo;
	}

	
	/**
	 * 根据办件编码获取办件的详细信息
	 */
	@Override
	public ApplyDetailInfo getApplyDetailInfoByNo(String stApplyNo) {
		return eventQueryDao.getApplyDetailInfoByNo(stApplyNo);
	}

	
	/**
	 * 保存办件满意度评价信息
	 */
	@Override
	public String saveNmSatisfation(String nmSatisfation, String content,
			String stApplyId) {
		return eventQueryDao.saveNmSatisfation(nmSatisfation, content,
				stApplyId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
