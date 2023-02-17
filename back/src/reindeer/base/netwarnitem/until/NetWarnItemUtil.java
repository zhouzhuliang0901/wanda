package reindeer.base.netwarnitem.until;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reindeer.base.netwarnitem.bean.CompanyInfo;
import reindeer.base.netwarnitem.bean.SendmessageInfo;
import reindeer.base.netwarnitem.bean.UserMobileInfo;
import reindeer.base.netwarnitem.dao.NetWarnIngItemDao;

import wfc.service.log.Log;


@Component
public class NetWarnItemUtil {

	@Autowired
	private NetWarnIngItemDao netWarnItemDao;

	public String sendWarnItemMessage(String itemNo, String itemName,
			String reservationNo, String unit, String unified) {
		String str = "";
		// 存储查询到企业所属的片区代码集合
		List<String> areaCodeList = new ArrayList<String>();
		if (StringUtils.isNotBlank(unit)) {
			if (netWarnItemDao.isWarningItem(itemNo)) {
				List<CompanyInfo> companyInfoList = InitializationUtil.companyInfoList;
				for (CompanyInfo c : companyInfoList) {
					if (unit.indexOf(c.getStCompanyKeyName()) > -1) {
						areaCodeList.add(c.getStAreaCode());
					}
				}
				// 通过查询到的片区编码集合查询到所需发送短信的人员列表
				if (areaCodeList != null && areaCodeList.size() > 0) {
					List<UserMobileInfo> userMobileList = netWarnItemDao
							.getUserMobileList(areaCodeList);
					// 向特定的企业特定的事项所属相关的人员发送预警短信
					for (UserMobileInfo u : userMobileList) {
						String messageInfo = unit + "办理了事项：" + itemName;
						SendmessageInfo s = new SendmessageInfo();
						s.setDtSendTimeDate(new Timestamp(System
								.currentTimeMillis()));
						s.setStAreaCode(u.getStAreaCode());
						s.setStAreaName(u.getStAreaName());
						s.setStMobile(u.getStMobile());
						s.setStMessageInfo(messageInfo);
						s.setStUserName(u.getStUserName());
						s.setStItemName(itemName);
						s.setStReservationNo(reservationNo);
						s.setStCompanyName(unit);
						s.setStSendMessageId(UUID.randomUUID().toString());
						String result = netWarnItemDao.add(s);
						if ("Y".equals(result)) {
							Log.debug("--插入" + u.getStMobile()
									+ "发送短信内容表数据成功--");
						}
						str += u.getStMobile() + "-";
					}
				}
			}
		}
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

}
