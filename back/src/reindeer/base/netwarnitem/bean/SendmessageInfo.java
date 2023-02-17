package reindeer.base.netwarnitem.bean;

import java.io.*;
import java.math.*;
import java.sql.*;

/**
 * 发送短信内容信息表
 * 
 * @author scalffold
 */
@SuppressWarnings("serial")
public class SendmessageInfo implements Serializable {

	private String stSendMessageId;

	/**
	 * 企业名称
	 */

	private String stCompanyName;

	/**
	 * 企业社会信用代码
	 */

	private String stCompanyCreditCode;

	/**
	 * 企业所在片区代码
	 */

	private String stAreaCode;

	/**
	 * 企业所在片区地址
	 */

	private String stAreaName;

	/**
	 * 发送人员手机号
	 */

	private String stMobile;

	/**
	 * 发送人员姓名
	 */

	private String stUserName;

	/**
	 * 发送人员等级
	 */

	private BigDecimal nmUserLeveal;

	/**
	 * 发送短信内容
	 */

	private String stMessageInfo;

	/**
	 * 发送短信时间
	 */

	private Timestamp dtSendTimeDate;

	/**
	 * 预约号
	 */

	private String stReservationNo;

	/**
	 * 预约事项
	 */

	private String stItemName;

	public String getStSendMessageId() {
		return stSendMessageId;
	}

	public void setStSendMessageId(String stSendMessageId) {
		this.stSendMessageId = stSendMessageId;
	}

	public String getStCompanyName() {
		return stCompanyName;
	}

	public void setStCompanyName(String stCompanyName) {
		this.stCompanyName = stCompanyName;
	}

	public String getStCompanyCreditCode() {
		return stCompanyCreditCode;
	}

	public void setStCompanyCreditCode(String stCompanyCreditCode) {
		this.stCompanyCreditCode = stCompanyCreditCode;
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

	public String getStMobile() {
		return stMobile;
	}

	public void setStMobile(String stMobile) {
		this.stMobile = stMobile;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStMessageInfo() {
		return stMessageInfo;
	}

	public void setStMessageInfo(String stMessageInfo) {
		this.stMessageInfo = stMessageInfo;
	}

	public Timestamp getDtSendTimeDate() {
		return dtSendTimeDate;
	}

	public void setDtSendTimeDate(Timestamp dtSendTimeDate) {
		this.dtSendTimeDate = dtSendTimeDate;
	}

	public String getStReservationNo() {
		return stReservationNo;
	}

	public void setStReservationNo(String stReservationNo) {
		this.stReservationNo = stReservationNo;
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	public BigDecimal getNmUserLeveal() {
		return nmUserLeveal;
	}

	public void setNmUserLeveal(BigDecimal nmUserLeveal) {
		this.nmUserLeveal = nmUserLeveal;
	}

}