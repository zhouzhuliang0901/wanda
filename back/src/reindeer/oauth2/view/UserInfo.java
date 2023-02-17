package reindeer.oauth2.view;

import java.math.BigDecimal;

/**
 * 返回用户信息
 */
public class UserInfo {
	/**
	 * 用户名
	 */
	private String stUserName;

	/**
	 * 姓名
	 */
	private String stName;

	/**
	 * 性别
	 */
	private BigDecimal nmSex;

	/**
	 * 住址
	 */
	private String stAddress;

	/**
	 * 用户昵称
	 */
	private String stNickName;

	/**
	 * 邮箱
	 */
	private String stEmail;

	/**
	 * 用户状态
	 */
	private BigDecimal nmStatus;

	/**
	 * 手机号码
	 */
	private String stPhone;

	/**
	 * 身份证号码
	 */
	private String stIdcard;

	/**
	 * 账号是否被锁定
	 */
	private BigDecimal nmLocked;

	/**
	 * 是否实名
	 */
	private BigDecimal nmReal;

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public BigDecimal getNmSex() {
		return nmSex;
	}

	public void setNmSex(BigDecimal nmSex) {
		this.nmSex = nmSex;
	}

	public String getStAddress() {
		return stAddress;
	}

	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}

	public String getStNickName() {
		return stNickName;
	}

	public void setStNickName(String stNickName) {
		this.stNickName = stNickName;
	}

	public String getStEmail() {
		return stEmail;
	}

	public void setStEmail(String stEmail) {
		this.stEmail = stEmail;
	}

	public BigDecimal getNmStatus() {
		return nmStatus;
	}

	public void setNmStatus(BigDecimal nmStatus) {
		this.nmStatus = nmStatus;
	}

	public String getStPhone() {
		return stPhone;
	}

	public void setStPhone(String stPhone) {
		this.stPhone = stPhone;
	}

	public String getStIdcard() {
		return stIdcard;
	}

	public void setStIdcard(String stIdcard) {
		this.stIdcard = stIdcard;
	}

	public BigDecimal getNmLocked() {
		return nmLocked;
	}

	public void setNmLocked(BigDecimal nmLocked) {
		this.nmLocked = nmLocked;
	}

	public BigDecimal getNmReal() {
		return nmReal;
	}

	public void setNmReal(BigDecimal nmReal) {
		this.nmReal = nmReal;
	}

}