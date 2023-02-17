package com.wondersgroup.sms.menu.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
 * 系统菜单表
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SMS_MENU")
public class SmsMenu implements Serializable {

	/**
	 * 系统菜单表
	 */
	public static final String SMS_MENU = "SMS_MENU";

	/**
	 * 菜单ID
	 */
	public static final String ST_MENU_ID = "ST_MENU_ID";

	/**
	 * 资源编号
	 */
	public static final String ST_MENU_CODE = "ST_MENU_CODE";

	/**
	 * 资源名称
	 */
	public static final String ST_MENU_NAME = "ST_MENU_NAME";

	/**
	 * 父ID
	 */
	public static final String ST_PARENT_ID = "ST_PARENT_ID";

	/**
	 * URL
	 */
	public static final String ST_URL = "ST_URL";

	/**
	 * 图标
	 */
	public static final String ST_IMAGE = "ST_IMAGE";

	/**
	 * 目标
	 */
	public static final String ST_TARGET = "ST_TARGET";

	/**
	 * 排序号
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
	 * 菜单描述
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

	public SmsMenu() {
	}

	/**
	 * 菜单ID
	 */
	@Id
	@Column(name = "ST_MENU_ID")
	private String stMenuId;

	/**
	 * 资源编号
	 */
	@Column(name = "ST_MENU_CODE")
	private String stMenuCode;

	/**
	 * 资源名称
	 */
	@Column(name = "ST_MENU_NAME")
	private String stMenuName;

	/**
	 * 父ID
	 */
	@Column(name = "ST_PARENT_ID")
	private String stParentId;

	/**
	 * URL
	 */
	@Column(name = "ST_URL")
	private String stUrl;

	/**
	 * 图标
	 */
	@Column(name = "ST_IMAGE")
	private String stImage;

	/**
	 * 目标
	 */
	@Column(name = "ST_TARGET")
	private String stTarget;

	/**
	 * 排序号
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
	 * 菜单描述
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
	 * 菜单ID
	 */
	public String getStMenuId() {
		return this.stMenuId;
	}

	/**
	 * 菜单ID
	 */
	public String stMenuId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stMenuId);
	}

	/**
	 * 菜单ID
	 */
	public void setStMenuId(String stMenuId) {
		stMenuId = StringUtil.substringBySize(stMenuId, 50, "GB18030");
		this.stMenuId = stMenuId;
	}

	/**
	 * 资源编号
	 */
	public String getStMenuCode() {
		return this.stMenuCode;
	}

	/**
	 * 资源编号
	 */
	public String stMenuCode2Html() {
		return StringHelper.replaceHTMLSymbol(this.stMenuCode);
	}

	/**
	 * 资源编号
	 */
	public void setStMenuCode(String stMenuCode) {
		stMenuCode = StringUtil.substringBySize(stMenuCode, 50, "GB18030");
		this.stMenuCode = stMenuCode;
	}

	/**
	 * 资源名称
	 */
	public String getStMenuName() {
		return this.stMenuName;
	}

	/**
	 * 资源名称
	 */
	public String stMenuName2Html() {
		return StringHelper.replaceHTMLSymbol(this.stMenuName);
	}

	/**
	 * 资源名称
	 */
	public void setStMenuName(String stMenuName) {
		stMenuName = StringUtil.substringBySize(stMenuName, 50, "GB18030");
		this.stMenuName = stMenuName;
	}

	/**
	 * 父ID
	 */
	public String getStParentId() {
		return this.stParentId;
	}

	/**
	 * 父ID
	 */
	public String stParentId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stParentId);
	}

	/**
	 * 父ID
	 */
	public void setStParentId(String stParentId) {
		stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
		this.stParentId = stParentId;
	}

	/**
	 * URL
	 */
	public String getStUrl() {
		return this.stUrl;
	}

	/**
	 * URL
	 */
	public String stUrl2Html() {
		return StringHelper.replaceHTMLSymbol(this.stUrl);
	}

	/**
	 * URL
	 */
	public void setStUrl(String stUrl) {
		stUrl = StringUtil.substringBySize(stUrl, 100, "GB18030");
		this.stUrl = stUrl;
	}

	/**
	 * 图标
	 */
	public String getStImage() {
		return this.stImage;
	}

	/**
	 * 图标
	 */
	public String stImage2Html() {
		return StringHelper.replaceHTMLSymbol(this.stImage);
	}

	/**
	 * 图标
	 */
	public void setStImage(String stImage) {
		stImage = StringUtil.substringBySize(stImage, 50, "GB18030");
		this.stImage = stImage;
	}

	/**
	 * 目标
	 */
	public String getStTarget() {
		return this.stTarget;
	}

	/**
	 * 目标
	 */
	public String stTarget2Html() {
		return StringHelper.replaceHTMLSymbol(this.stTarget);
	}

	/**
	 * 目标
	 */
	public void setStTarget(String stTarget) {
		stTarget = StringUtil.substringBySize(stTarget, 50, "GB18030");
		this.stTarget = stTarget;
	}

	/**
	 * 排序号
	 */
	public BigDecimal getNmOrder() {
		return this.nmOrder;
	}

	/**
	 * 排序号
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
	 * 排序号
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
	 * 菜单描述
	 */
	public String getStDesc() {
		return this.stDesc;
	}

	/**
	 * 菜单描述
	 */
	public String stDesc2Html() {
		return StringHelper.replaceHTMLSymbol(this.stDesc);
	}

	/**
	 * 菜单描述
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