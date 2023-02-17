package reindeer.base.netwarnitem.bean;

import java.math.BigDecimal;
import java.util.List;

public class UserMobileInfo {

	/**
	 * 人员信息表主键
	 */
	private String stUsermobileId;

	/**
	 * 人员姓名
	 */
	private String stUserName;

	/**
	 * 人员手机号
	 */

	private String stMobile;

	/**
	 * 人员所属片区编码
	 */

	private String stAreaCode;

	/**
	 * 人员所属片区地址
	 */

	private String stAreaName;

	/**
	 * 人员所负责预警事项集合
	 */
	private List<String> warnItemList;

	/**
	 * 人员发送短信权限等级
	 */

	private BigDecimal nmUserLeveal;

	public String getStUsermobileId() {
		return stUsermobileId;
	}

	public void setStUsermobileId(String stUsermobileId) {
		this.stUsermobileId = stUsermobileId;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStMobile() {
		return stMobile;
	}

	public void setStMobile(String stMobile) {
		this.stMobile = stMobile;
	}

	public String getStAreaCode() {
		return stAreaCode;
	}

	public void setStAreaCode(String stAreaCode) {
		this.stAreaCode = stAreaCode;
	}

	public String getStAreaName() {
		return stAreaName;
	}

	public void setStAreaName(String stAreaName) {
		this.stAreaName = stAreaName;
	}

	public BigDecimal getNmUserLeveal() {
		return nmUserLeveal;
	}

	public void setNmUserLeveal(BigDecimal nmUserLeveal) {
		this.nmUserLeveal = nmUserLeveal;
	}

	public List<String> getWarnItemList() {
		return warnItemList;
	}

	public void setWarnItemList(List<String> warnItemList) {
		this.warnItemList = warnItemList;
	}

}
