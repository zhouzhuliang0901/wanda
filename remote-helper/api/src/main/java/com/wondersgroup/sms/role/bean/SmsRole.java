package com.wondersgroup.sms.role.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;
import coral.base.util.StringUtil;

/**
 * 角色表
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_ROLE")
public class SmsRole implements Serializable {

	/**
	 * 角色表
	 */
	public static final String SMS_ROLE = "SMS_ROLE";

	/**
	 * 角色ID
	 */
	public static final String ST_ROLE_ID = "ST_ROLE_ID";

	/**
	 * 角色ID
	 */
	public static final String ST_ROLE_IDS = "ST_ROLE_ID[]";

	/**
	 * 角色代码
	 */
	public static final String ST_ROLE_CODE = "ST_ROLE_CODE";

	/**
	 * 角色名称
	 */
	public static final String ST_ROLE_NAME = "ST_ROLE_NAME";

	/**
	 * 排序字段
	 */
	public static final String NM_ORDER = "NM_ORDER";

	/**
	 * 创建时间
	 */
	public static final String DT_CREATE = "DT_CREATE";

	/**
	 * 修改时间
	 */
	public static final String DT_UPDATE = "DT_UPDATE";

	/**
	 * 角色描述
	 */
	public static final String ST_DESC = "ST_DESC";

	/**
	 * 扩展字段1
	 */
	public static final String ST_EXT1 = "ST_EXT1";

	/**
	 * 扩展字段2
	 */
	public static final String ST_EXT2 = "ST_EXT2";

	public SmsRole() {
	}

	private List<String> menuIds;

	public List<String> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}

	/**
	 * 角色ID
	 */
	@Id
	@Column(name = "ST_ROLE_ID")
	private String stRoleId;

	/**
	 * 角色代码
	 */
	@Column(name = "ST_ROLE_CODE")
	private String stRoleCode;

	/**
	 * 角色名称
	 */
	@Column(name = "ST_ROLE_NAME")
	private String stRoleName;

	/**
	 * 排序字段
	 */
	@Column(name = "NM_ORDER")
	private BigDecimal nmOrder;

	/**
	 * 创建时间
	 */
	@Column(name = "DT_CREATE")
	private Timestamp dtCreate;

	/**
	 * 修改时间
	 */
	@Column(name = "DT_UPDATE")
	private Timestamp dtUpdate;

	/**
	 * 角色描述
	 */
	@Column(name = "ST_DESC")
	private String stDesc;

	/**
	 * 扩展字段1
	 */
	@Column(name = "ST_EXT1")
	private String stExt1;

	/**
	 * 扩展字段2
	 */
	@Column(name = "ST_EXT2")
	private String stExt2;

	/**
	 * 角色ID
	 */
	public String getStRoleId() {
		return this.stRoleId;
	}

	/**
	 * 角色ID
	 */
	public String stRoleId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stRoleId);
	}

	/**
	 * 角色ID
	 */
	public void setStRoleId(String stRoleId) {
		stRoleId = StringUtil.substringBySize(stRoleId, 50, "GB18030");
		this.stRoleId = stRoleId;
	}

	/**
	 * 角色代码
	 */
	public String getStRoleCode() {
		return this.stRoleCode;
	}

	/**
	 * 角色代码
	 */
	public String stRoleCode2Html() {
		return StringHelper.replaceHTMLSymbol(this.stRoleCode);
	}

	/**
	 * 角色代码
	 */
	public void setStRoleCode(String stRoleCode) {
		stRoleCode = StringUtil.substringBySize(stRoleCode, 50, "GB18030");
		this.stRoleCode = stRoleCode;
	}

	/**
	 * 角色名称
	 */
	public String getStRoleName() {
		return this.stRoleName;
	}

	/**
	 * 角色名称
	 */
	public String stRoleName2Html() {
		return StringHelper.replaceHTMLSymbol(this.stRoleName);
	}

	/**
	 * 角色名称
	 */
	public void setStRoleName(String stRoleName) {
		stRoleName = StringUtil.substringBySize(stRoleName, 50, "GB18030");
		this.stRoleName = stRoleName;
	}

	/**
	 * 排序字段
	 */
	public BigDecimal getNmOrder() {
		return this.nmOrder;
	}

	/**
	 * 排序字段
	 */
	public String nmOrder2Html(int precision) {
		if (this.nmOrder == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmOrder);
		}
	}

	/**
	 * 排序字段
	 */
	public void setNmOrder(BigDecimal nmOrder) {
		this.nmOrder = nmOrder;
	}

	/**
	 * 创建时间
	 */
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtCreate() {
		return this.dtCreate;
	}

	/**
	 * 创建时间
	 */
	public String dtCreate2Html(String pattern) {
		if (this.dtCreate == null) {
			return "";
		} else {
			if (pattern == null) {
				pattern = "yyyy年MM月dd日 HH时mm分";
			}
			return new SimpleDateFormat(pattern).format(this.dtCreate);
		}
	}

	/**
	 * 创建时间
	 */
	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	/**
	 * 修改时间
	 */
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtUpdate() {
		return this.dtUpdate;
	}

	/**
	 * 修改时间
	 */
	public String dtUpdate2Html(String pattern) {
		if (this.dtUpdate == null) {
			return "";
		} else {
			if (pattern == null) {
				pattern = "yyyy年MM月dd日 HH时mm分";
			}
			return new SimpleDateFormat(pattern).format(this.dtUpdate);
		}
	}

	/**
	 * 修改时间
	 */
	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	/**
	 * 角色描述
	 */
	public String getStDesc() {
		return this.stDesc;
	}

	/**
	 * 角色描述
	 */
	public String stDesc2Html() {
		return StringHelper.replaceHTMLSymbol(this.stDesc);
	}

	/**
	 * 角色描述
	 */
	public void setStDesc(String stDesc) {
		stDesc = StringUtil.substringBySize(stDesc, 50, "GB18030");
		this.stDesc = stDesc;
	}

	/**
	 * 扩展字段1
	 */
	public String getStExt1() {
		return this.stExt1;
	}

	/**
	 * 扩展字段1
	 */
	public String stExt12Html() {
		return StringHelper.replaceHTMLSymbol(this.stExt1);
	}

	/**
	 * 扩展字段1
	 */
	public void setStExt1(String stExt1) {
		stExt1 = StringUtil.substringBySize(stExt1, 50, "GB18030");
		this.stExt1 = stExt1;
	}

	/**
	 * 扩展字段2
	 */
	public String getStExt2() {
		return this.stExt2;
	}

	/**
	 * 扩展字段2
	 */
	public String stExt22Html() {
		return StringHelper.replaceHTMLSymbol(this.stExt2);
	}

	/**
	 * 扩展字段2
	 */
	public void setStExt2(String stExt2) {
		stExt2 = StringUtil.substringBySize(stExt2, 50, "GB18030");
		this.stExt2 = stExt2;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}