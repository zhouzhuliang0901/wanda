package reindeer.base.netwarnitem.until;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reindeer.base.netwarnitem.bean.CompanyInfo;
import reindeer.base.netwarnitem.bean.SendmessageInfo;
import reindeer.base.netwarnitem.bean.UserMobileInfo;
import reindeer.base.netwarnitem.dao.NetWarnIngItemDao;


import wfc.service.config.Config;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.NetReservationInfo;
import com.wondersgroup.selfapi.dao.NetReservationInfoDao;


@Component
public class NetWarnIngItemUtil {

//	@Autowired
//	private SmsService smsService;

	@Autowired
	private NetWarnIngItemDao netWarnItemDao;

	@Autowired
	private NetReservationInfoDao netReservationInfoDao;

	public String sendWarnItemMessage(String itemNo, String itemName,
			String date, String userName, String userMobile,
			String reservationNo, String unit, String unified) {
		Log.debug("-----------预警企业信息短信发送-------------");
		if (StringUtils.isBlank(unified)) {
			unified = "";
		}
		String str = "";
		// 存储查询到企业所属的片区代码集合
		List<String> areaCodeList = new ArrayList<String>();
		String companyAreaCode = "";
		String companyAreaName = "";
		// 存储根据用户输入的企业名称查询到的企业的信息集合
		List<CompanyInfo> areaCompanyInfoList = new ArrayList<CompanyInfo>();
		if (StringUtils.isNotBlank(unit)) {
			if (netWarnItemDao.isWarningItem(itemNo)) {
				List<CompanyInfo> companyInfoList = InitializationUtil.companyInfoList;
				for (CompanyInfo c : companyInfoList) {
					if (unified.equals(c.getStCompanyCreditCode())
							|| dealCompanyName(unit).equals(
									c.getStCompanyName())) {
						areaCompanyInfoList.add(c);
						break;
					}
				}
				// 处理一个企业属于多个片区的问题 选取关键字最长的记录所对应的片区
				if (areaCompanyInfoList != null
						&& areaCompanyInfoList.size() > 0) {
					Log
							.debug("同一个企业通过企业名称或者企业信用代码匹配了几个片区："
									+ areaCompanyInfoList.size());
					CompanyInfo c = get(areaCompanyInfoList).get(0);
					companyAreaCode = c.getStAreaCode();
					companyAreaName = c.getStAreaName();
					areaCodeList.add(companyAreaCode);
				}
				// 通过查询到的片区编码集合查询到所需发送短信的人员列表
				if (areaCodeList != null && areaCodeList.size() > 0) {
					// 更新预约信息状态为预警预约信息
					// updateWarnItemStatus(reservationNo);
					netWarnItemDao.updateWarnItemStatus(reservationNo);
					List<UserMobileInfo> userMobileList = netWarnItemDao
							.getUserMobileList(areaCodeList);
					// 向特定的企业特定的事项所属相关的人员发送预警短信
					for (UserMobileInfo u : userMobileList) {
						// 判断这个人员是不是负责预约的事项
						List<String> warnItemList = u.getWarnItemList();
						// 如果不负责的话就跳过此事项不发送短信 要是不负责事项或者人员关联了预警事项就发送短信
						if (warnItemList.size() > 0
								&& !warnItemList.contains(itemNo)) {
							continue;
						}
						String messageInfo = "重点企业预警："
								+ unit
								+ "预约了"
								+ date
								+ itemName
								+ "，户管地"
								+ companyAreaName
								+ "。"
								+ "请按照预警工作机制，5日内反馈企业相关情况至zdqy@pudong.gov.cn（"+Config.get("wfc.pd.warnItemMessage")+"）。";
						Timestamp time = new Timestamp(System
								.currentTimeMillis());
						SendmessageInfo s = new SendmessageInfo();
						s.setDtSendTimeDate(time);
						s.setStAreaCode(companyAreaCode);
						s.setStAreaName(companyAreaName);
						s.setStMobile(u.getStMobile());
						s.setNmUserLeveal(u.getNmUserLeveal());
						s.setStMessageInfo(messageInfo);
						s.setStUserName(u.getStUserName());
						s.setStItemName(itemName);
						s.setStReservationNo(reservationNo);
						s.setStCompanyName(unit);
						s.setStSendMessageId(UUID.randomUUID().toString());
						// 向发送短信内容信息表中插入数据
						String result = netWarnItemDao.add(s);
						if ("Y".equals(result)) {
							Log.debug("--插入片区管理员" + u.getStMobile()
									+ "的手机，发送短信内容表数据成功--");
						}
						// 发送短信给片区管理者
						// 1
//						smsService.sendMessage(u.getStMobile(), messageInfo);
						// 2
						// String sendSql =
						// "insert into SMS_OUTBOX VALUES(?,?,?,?,?)";
						// RecordSet sendRs = SQL.execute(sendSql, new Object[]
						// {
						// UUID.randomUUID().toString(),
						// u.getStUserName(), u.getStMobile(),
						// messageInfo, time });
						// if (sendRs.TOTAL_RECORD_COUNT > 0) {
						// Log.debug("插入特定企业预约特定事项发送给特定人员的短信内容发送成功");
						// }
						str += u.getStMobile() + "-";
					}
				}
			} else {
				// 非预警事项 重点企业预约情况
				List<CompanyInfo> companyInfoList = InitializationUtil.companyInfoList;
				for (CompanyInfo c : companyInfoList) {
					if (unified.equals(c.getStCompanyCreditCode())
							|| dealCompanyName(unit).equals(
									c.getStCompanyName())) {
						String result = netWarnItemDao
								.updateItemStatusForWarnCompany(reservationNo);
						if ("Y".equals(result)) {
							Log.debug("非预警事项 重点企业预约--非预警事项标注");
						}
						break;
					}
				}
			}
		}
		Log.debug(str);
		return str;
	}

	/**
	 * 处理获取到的大厅编码的
	 * 
	 * @return
	 */
	public String dealAreaCodes(List<String> areaCodeList) {
		String str = "";
		if (areaCodeList != null && areaCodeList.size() > 0) {
			for (String s : areaCodeList) {
				str += "'" + s + "',";
			}
			str = str.substring(0, str.lastIndexOf(","));
		} else {
			str = "''";
		}
		Log.debug("企业所属片区编码集合：" + str);
		return str;
	}

	/**
	 * 跟新预警状态
	 * 
	 * @param reservationNo
	 */
	public void updateWarnItemStatus(String reservationNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(NetReservationInfo.ST_EXT2, "1");
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition(NetReservationInfo.ST_RESERVATION_NO,
				Condition.OT_EQUAL, reservationNo));
		netReservationInfoDao.update(map, conds);
	}

	/**
	 * 处理企业名称
	 * 
	 * @param unit
	 * @return
	 */
	public static String dealCompanyName(String unit) {
		unit = toSemiangle(unit);
		// unit = unit.replaceAll("\\(", "（").replaceAll("\\)", "）").replaceAll(
		// "`", "·").replaceAll("（上海）", "").replaceAll("（中国）", "")
		// .replaceAll("分公司", "").replaceAll("中国", "").replaceAll(
		// "有限责任公司", "").replaceAll("上海", "").replaceAll("股份", "")
		// .replaceAll("有限公司", "").toUpperCase();
		unit = unit.replaceAll("\\(", "（").replaceAll("\\)", "）").replaceAll(
				"`", "·").toUpperCase();
		return unit;
	}

	/**
	 * 全角空格为12288，半角空格为32 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * 将字符串中的全角字符转为半角
	 * 
	 * @param src
	 *            要转换的包含全角的任意字符串
	 * @return 转换之后的字符串
	 */
	public static String toSemiangle(String src) {
		char[] c = src.toCharArray();
		for (int index = 0; index < c.length; index++) {
			if (c[index] == 12288) {// 全角空格
				c[index] = (char) 32;
			} else if (c[index] > 65280 && c[index] < 65375) {// 其他全角字符
				c[index] = (char) (c[index] - 65248);
			}
		}
		return String.valueOf(c);
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static List<CompanyInfo> get(List<CompanyInfo> list) {
		// 对企业中的关键字数量多少匹配的排序
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				CompanyInfo c1 = (CompanyInfo) o1;
				CompanyInfo c2 = (CompanyInfo) o2;
				int l1 = 0;
				int l2 = 0;
				try {
					l1 = c1.getStCompanyKeyName().length();
					l2 = c2.getStCompanyKeyName().length();
				} catch (Exception e) {
					Log.debug(e);
					Log.debug("日期转换异常");
				}
				// 小于时返回1是降序，返回-1是升序
				if (l1 > l2) {
					return -1;
				} else if (l1 < l2) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}

	public static void main(String[] args) {
		String str = "六洲酒店管理(上海)有限公司";
		System.out.println(dealCompanyName(str));
		System.out.println(str);
		System.out.println(dealCompanyName(str).equals("六洲酒店管理（上海）有限公司"));
		// List<CompanyInfo> list = new ArrayList<CompanyInfo>();
		// CompanyInfo c = new CompanyInfo();
		// c.setStAreaCode("1");
		// c.setStCompanyKeyName("建设银行");
		// list.add(c);
		// CompanyInfo c1 = new CompanyInfo();
		// c1.setStAreaCode("2");
		// c1.setStCompanyKeyName("建设银行酒店");
		// list.add(c1);
		// System.out.println(list.get(0).getStCompanyKeyName());
		// System.out.println(list.size());
		// System.out.println(get(list).get(0).getStCompanyKeyName());
	}

}
