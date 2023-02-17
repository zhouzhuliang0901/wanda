package reindeer.base.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import reindeer.base.utils.StringUtil;

import wfc.service.util.StringHelper;


/**
 * 事项申请材料表
 * 
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_ITEM_STUFF")
public class WindowItemStuff implements Serializable {

	/**
	 * 事项申请材料表
	 */
	public static final String WINDOW_ITEM_STUFF = "WINDOW_ITEM_STUFF";

	/**
	 * 事项申请材料ID
	 */
	public static final String ST_STUFF_ID = "ST_STUFF_ID";

	/**
	 * 事项信息编号
	 */
	public static final String ST_ITEM_ID = "ST_ITEM_ID";

	/**
	 * 事项信息名称
	 */
	public static final String ST_ITEM_NAME = "ST_ITEM_NAME";

	/**
	 * 材料名称
	 */
	public static final String ST_STUFF_NAME = "ST_STUFF_NAME";

	/**
	 * 是否必须
	 */
	public static final String NM_MUST = "NM_MUST";

	/**
	 * 原件数
	 */
	public static final String NM_ORIGINAL = "NM_ORIGINAL";

	/**
	 * 复印件数
	 */
	public static final String NM_COPY = "NM_COPY";

	/**
	 * 是否需要拍照上传
	 */
	public static final String NM_UPLOAD = "NM_UPLOAD";

	/**
	 * 展示顺序
	 */
	public static final String NM_ORDER = "NM_ORDER";

	/**
	 * 是否需要样张
	 */
	public static final String NM_SAMPLE = "NM_SAMPLE";

	/**
	 * 对应证照正规名称编号
	 */
	public static final String ST_FORMAL_ID = "ST_FORMAL_ID";

	/**
	 * 部门ID
	 */
	public static final String NM_ORGAN_NODE_ID = "NM_ORGAN_NODE_ID";

	/**
	 * 部门NAME
	 */
	public static final String NM_ORGAN_NODE_NAME = "NM_ORGAN_NODE_NAME";

    /**
     * 审查要点
     */
    public static final String ST_EXAMINE_BASIS = "ST_EXAMINE_BASIS";
    
    /**
     * 说明
     */
    public static final String ST_FAQ = "ST_FAQ";
    
    /**
     * 渠道来源
     */
    public static final String ST_SOURCE = "ST_SOURCE";
    
    /**
     * 常见退件情形
     */
    public static final String ST_REFUND_STATUS = "ST_REFUND_STATUS";
	
	/**
	 * 扩展字段1
	 */
	public static final String ST_EXT1 = "ST_EXT1";

	/**
	 * 扩展字段2
	 */
	public static final String ST_EXT2 = "ST_EXT2";
	
	/**
	 * 电子证照库目录编码
	 */
	public static final String ST_CERT_CODE = "ST_CERT_CODE";
	
	/**
	 * 电子证照库目录名称
	 */
	public static final String ST_CERT_NAME = "ST_CERT_NAME";
	
	/**
	 * 材料类型，0：申请表；
	 */
	public static final String NM_TYPE = "NM_TYPE";

	public WindowItemStuff() {
	}

	/**
	 * 事项申请材料ID
	 */
	@Id
	@Column(name = "ST_STUFF_ID")
	private String stStuffId;

	/**
	 * 事项信息编号
	 */
	@Column(name = "ST_ITEM_ID")
	private String stItemId;

	/**
	 * 事项信息名称
	 */
	@Column(name = "ST_ITEM_NAME")
	private String stItemName;

	/**
	 * 材料名称
	 */
	@Column(name = "ST_STUFF_NAME")
	private String stStuffName;

	/**
	 * 是否必须
	 */
	@Column(name = "NM_MUST")
	private BigDecimal nmMust;

	/**
	 * 原件数
	 */
	@Column(name = "NM_ORIGINAL")
	private BigDecimal nmOriginal;

	/**
	 * 复印件数
	 */
	@Column(name = "NM_COPY")
	private BigDecimal nmCopy;

	/**
	 * 是否需要拍照上传
	 */
	@Column(name = "NM_UPLOAD")
	private BigDecimal nmUpload;

	/**
	 * 展示顺序
	 */
	@Column(name = "NM_ORDER")
	private BigDecimal nmOrder;

	/**
	 * 是否需要样张
	 */
	@Column(name = "NM_SAMPLE")
	private BigDecimal nmSample;

	/**
	 * 对应证照正规名称编号
	 */
	@Column(name = "ST_FORMAL_ID")
	private String stFormalId;

	/**
	 * 部门ID
	 */
	@Column(name = "NM_ORGAN_NODE_ID")
	private BigDecimal nmOrganNodeId;

	/**
	 * 部门NAME
	 */
	@Column(name = "NM_ORGAN_NODE_NAME")
	private String nmOrganNodeName;

	/**
     * 审查要点
     */
    @Column(name = "ST_EXAMINE_BASIS")
    private String stExamineBasis;
    
    /**
     * 说明
     */
    @Column(name = "ST_FAQ")
    private String stFaq;
    
    /**
     * 渠道来源
     */
    @Column(name = "ST_SOURCE")
    private String stSource;
    
    /**
     * 常见退件情形
     */
    @Column(name = "ST_REFUND_STATUS")
    private String stRefundStatus;
	
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
	 * 电子证照库目录编码
	 */
	@Column(name = "ST_CERT_CODE")
	private String stCertCode;

	/**
	 * 电子证照库目录名称
	 */
	@Column(name = "ST_CERT_NAME")
	private String stCertName;
	
	/**
	 * 材料类型，0：申请表
	 */
	@Column(name = "NM_TYPE")
	private BigDecimal nmType;
	
	/**
	 * 事项申请材料ID
	 */
	public String getStStuffId() {
		return this.stStuffId;
	}

	/**
	 * 事项申请材料ID
	 */
	public String stStuffId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stStuffId);
	}

	/**
	 * 事项申请材料ID
	 */
	public void setStStuffId(String stStuffId) {
		stStuffId = StringUtil.substringBySize(stStuffId, 50, "GB18030");
		this.stStuffId = stStuffId;
	}

	/**
	 * 事项信息编号
	 */
	public String getStItemId() {
		return this.stItemId;
	}

	/**
	 * 事项信息编号
	 */
	public String stItemId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stItemId);
	}

	/**
	 * 事项信息编号
	 */
	public void setStItemId(String stItemId) {
		stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
		this.stItemId = stItemId;
	}

	public String stItemName2Html() {
		return StringHelper.replaceHTMLSymbol(this.stItemName);
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	/**
	 * 材料名称
	 */
	public String getStStuffName() {
		return this.stStuffName;
	}

	/**
	 * 材料名称
	 */
	public String stStuffName2Html() {
		return StringHelper.replaceHTMLSymbol(this.stStuffName);
	}

	/**
	 * 材料名称
	 */
	public void setStStuffName(String stStuffName) {
		stStuffName = StringUtil.substringBySize(stStuffName, 200, "GB18030");
		this.stStuffName = stStuffName;
	}

	/**
	 * 是否必须
	 */
	public BigDecimal getNmMust() {
		return this.nmMust;
	}

	/**
	 * 是否必须
	 */
	public String nmMust2Html(int precision) {
		if (this.nmMust == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmMust);
		}
	}

	/**
	 * 是否必须
	 */
	public void setNmMust(BigDecimal nmMust) {
		this.nmMust = nmMust;
	}

	/**
	 * 原件数
	 */
	public BigDecimal getNmOriginal() {
		return this.nmOriginal;
	}

	/**
	 * 原件数
	 */
	public String nmOriginal2Html(int precision) {
		if (this.nmOriginal == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmOriginal);
		}
	}

	/**
	 * 原件数
	 */
	public void setNmOriginal(BigDecimal nmOriginal) {
		this.nmOriginal = nmOriginal;
	}

	/**
	 * 复印件数
	 */
	public BigDecimal getNmCopy() {
		return this.nmCopy;
	}

	/**
	 * 复印件数
	 */
	public String nmCopy2Html(int precision) {
		if (this.nmCopy == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmCopy);
		}
	}

	/**
	 * 复印件数
	 */
	public void setNmCopy(BigDecimal nmCopy) {
		this.nmCopy = nmCopy;
	}

	/**
	 * 是否需要拍照上传
	 */
	public BigDecimal getNmUpload() {
		return this.nmUpload;
	}

	/**
	 * 是否需要拍照上传
	 */
	public String nmUpload2Html(int precision) {
		if (this.nmUpload == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmUpload);
		}
	}

	/**
	 * 是否需要拍照上传
	 */
	public void setNmUpload(BigDecimal nmUpload) {
		this.nmUpload = nmUpload;
	}

	/**
	 * 展示顺序
	 */
	public BigDecimal getNmOrder() {
		return this.nmOrder;
	}

	/**
	 * 展示顺序
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
	 * 展示顺序
	 */
	public void setNmOrder(BigDecimal nmOrder) {
		this.nmOrder = nmOrder;
	}

	/**
	 * 是否需要样张
	 */
	public BigDecimal getNmSample() {
		return this.nmSample;
	}

	/**
	 * 是否需要样张
	 */
	public String nmSample2Html(int precision) {
		if (this.nmSample == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmSample);
		}
	}

	/**
	 * 是否需要样张
	 */
	public void setNmSample(BigDecimal nmSample) {
		this.nmSample = nmSample;
	}

	/**
	 * 对应证照正规名称编号
	 */
	public String getStFormalId() {
		return this.stFormalId;
	}

	/**
	 * 对应证照正规名称编号
	 */
	public String stFormalId2Html() {
		return StringHelper.replaceHTMLSymbol(this.stFormalId);
	}

	/**
	 * 对应证照正规名称编号
	 */
	public void setStFormalId(String stFormalId) {
		stFormalId = StringUtil.substringBySize(stFormalId, 100, "GB18030");
		this.stFormalId = stFormalId;
	}

	/**
	 * 部门ID
	 */
	public BigDecimal getNmOrganNodeId() {
		return this.nmOrganNodeId;
	}

	/**
	 * 部门ID
	 */
	public String nmOrganNodeId2Html(int precision) {
		if (this.nmOrganNodeId == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmOrganNodeId);
		}
	}

	/**
	 * 部门ID
	 */
	public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
		this.nmOrganNodeId = nmOrganNodeId;
	}

	/**
	 * 部门名称nmOrganNodeName
	 */
	public String getNmOrganNodeName() {
		return this.nmOrganNodeName;
	}

	/**
	 * 部门名称nmOrganNodeName
	 */
	public String nmOrganNodeName2Html() {
		return StringHelper.replaceHTMLSymbol(this.nmOrganNodeName);
	}

	/**
	 * 部门名称nmOrganNodeName
	 */
	public void setNmOrganNodeName(String nmOrganNodeName) {
		nmOrganNodeName = StringUtil.substringBySize(nmOrganNodeName, 50,
				"GB18030");
		this.nmOrganNodeName = nmOrganNodeName;
	}

	/**
     * 审查要点
     */
    public String getStExamineBasis() {
        return this.stExamineBasis;
    }
    
    /**
     * 审查要点
     */
    public String stExamineBasis2Html() {
        return StringHelper.replaceHTMLSymbol(this.stExamineBasis);
    }

    /**
     * 审查要点
     */
    public void setStExamineBasis(String stExamineBasis) {
        stExamineBasis = StringUtil.substringBySize(stExamineBasis, 10000, "GB18030");
        this.stExamineBasis = stExamineBasis;
    }
    
	/**
     * 说明
     */
    public String getStFaq() {
        return this.stFaq;
    }
    
    /**
     * 说明
     */
    public String stFaq2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFaq);
    }

    /**
     * 说明
     */
    public void setStFaq(String stFaq) {
        stFaq = StringUtil.substringBySize(stFaq, 10000, "GB18030");
        this.stFaq = stFaq;
    }
    
	/**
     * 渠道来源
     */
    public String getStSource() {
        return this.stSource;
    }
    
    /**
     * 渠道来源
     */
    public String stSource2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSource);
    }

    /**
     * 渠道来源
     */
    public void setStSource(String stSource) {
        stSource = StringUtil.substringBySize(stSource, 200, "GB18030");
        this.stSource = stSource;
    }
    
	/**
     * 常见退件情形
     */
    public String getStRefundStatus() {
        return this.stRefundStatus;
    }
    
    /**
     * 常见退件情形
     */
    public String stRefundStatus2Html() {
        return StringHelper.replaceHTMLSymbol(this.stRefundStatus);
    }

    /**
     * 常见退件情形
     */
    public void setStRefundStatus(String stRefundStatus) {
        stRefundStatus = StringUtil.substringBySize(stRefundStatus, 10000, "GB18030");
        this.stRefundStatus = stRefundStatus;
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

	/**
	 * 电子证照库目录编码
	 */
	public String getStCertCode() {
		return this.stCertCode;
	}

	/**
	 * 电子证照库目录编码
	 */
	public String stCertCode2Html() {
		return StringHelper.replaceHTMLSymbol(this.stCertCode);
	}

	/**
	 * 电子证照库目录编码
	 */
	public void setStCertCode(String stCertCode) {
		this.stCertCode = stCertCode;
	}

	/**
	 * 电子证照库目录名称
	 */
	public String getStCertName() {
		return this.stCertName;
	}

	/**
	 *电子证照库目录名称
	 */
	public String stCertName2Html() {
		return StringHelper.replaceHTMLSymbol(this.stCertName);
	}

	/**
	 * 电子证照库目录名称
	 */
	public void setStCertName(String stCertName) {
		this.stCertName = stCertName;
	}
	
	/**
	 * 材料类型，0：申请表；
	 */
	public BigDecimal getNmType() {
		return this.nmType;
	}

	/**
	 * 材料类型，0：申请表；
	 */
	public String nmType2Html(int precision) {
		if (this.nmType == null) {
			return "";
		} else {
			String pattern = "0";
			if (precision > 0) {
				pattern += ".";
				for (int i = 0; i < precision; i++) {
					pattern += "0";
				}
			}
			return new DecimalFormat(pattern).format(this.nmType);
		}
	}

	/**
	 * 材料类型，0：申请表；
	 */
	public void setNmType(BigDecimal nmType) {
		this.nmType = nmType;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}