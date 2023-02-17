package com.wondersgroup.wdf.dao;

import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 综合受理电子材料
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_STUFF")
public class UacUapplyStuff implements Serializable {
    
    /**
     * 综合受理电子材料
     */
    public static final String UAC_UAPPLY_STUFF = "UAC_UAPPLY_STUFF";
    
    /**
     * 电子材料ID
     */
    public static final String ST_ESTUFF_ID = "ST_ESTUFF_ID";
    
    /**
     * 所属办件ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";
    
    /**
     * 申请材料ID
     */
    public static final String ST_CSTUFF_ID = "ST_CSTUFF_ID";
    
    /**
     * 文件唯一标识
     */
    public static final String ST_FILE_ID = "ST_FILE_ID";
    
    /**
     * 实体表唯一标识
     */
    public static final String ST_TABLE_ID = "ST_TABLE_ID";
    
    /**
     * 材料实体类型
     */
    public static final String ST_ENTITY_TYPE = "ST_ENTITY_TYPE";
    
    /**
     * 材料实体表
     */
    public static final String ST_ENTITY_TABLE = "ST_ENTITY_TABLE";
    
    /**
     * 材料类型
     */
    public static final String ST_STUFF_TYPE = "ST_STUFF_TYPE";
    
    /**
     * 材料作用
     */
    public static final String ST_STUFF_USE = "ST_STUFF_USE";
    
    /**
     * 材料描述
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 材料修改意见
     */
    public static final String ST_OPINION = "ST_OPINION";
    
    /**
     * 材料状态
     */
    public static final String NM_STATUS = "NM_STATUS";

    /**
     * 材料结果
     */
    public static final String ST_RESULT = "ST_RESULT";
    
    /**
     * 现场提交
     */
    public static final String NM_OFFLINE_SUBMIT = "NM_OFFLINE_SUBMIT";
    
    /**
     * 材料来源
     */
    public static final String ST_SOURCE = "ST_SOURCE";
    
    /**
     * 来源唯一标识
     */
    public static final String ST_SOURCE_ID = "ST_SOURCE_ID";
    
    /**
     * 原件数
     */
    public static final String NM_ORIGINAL = "NM_ORIGINAL";
    
    /**
     * 复印件数
     */
    public static final String NM_COPY = "NM_COPY";

    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";

    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";

    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    public UacUapplyStuff() {
    }
    
    /**
     * 电子材料ID
     */
    @Id
    @Column(name = "ST_ESTUFF_ID")
    private String stEstuffId;
    
    /**
     * 所属办件ID
     */
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;
    
    /**
     * 申请材料ID
     */
    @Column(name = "ST_CSTUFF_ID")
    private String stCstuffId;
    
    /**
     * 文件唯一标识
     */
    @Column(name = "ST_FILE_ID")
    private String stFileId;
    
    /**
     * 实体表唯一标识
     */
    @Column(name = "ST_TABLE_ID")
    private String stTableId;
    
    /**
     * 材料实体类型
     */
    @Column(name = "ST_ENTITY_TYPE")
    private String stEntityType;
    
    /**
     * 材料实体表
     */
    @Column(name = "ST_ENTITY_TABLE")
    private String stEntityTable;
    
    /**
     * 材料类型
     */
    @Column(name = "ST_STUFF_TYPE")
    private String stStuffType;
    
    /**
     * 材料作用
     */
    @Column(name = "ST_STUFF_USE")
    private String stStuffUse;
    
    /**
     * 材料描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 材料修改意见
     */
    @Column(name = "ST_OPINION")
    private String stOpinion;
    
    /**
     * 材料状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;

    /**
     * 材料结果
     */
    @Column(name = "ST_RESULT")
    private String stResult;
    
    /**
     * 现场提交
     */
    @Column(name = "NM_OFFLINE_SUBMIT")
    private BigDecimal nmOfflineSubmit;
    
    /**
     * 材料来源
     */
    @Column(name = "ST_SOURCE")
    private String stSource;
    
    /**
     * 来源唯一标识
     */
    @Column(name = "ST_SOURCE_ID")
    private String stSourceId;
    
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
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 更新时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
	/**
     * 电子材料ID
     */
    public String getStEstuffId() {
        return this.stEstuffId;
    }
    
    /**
     * 电子材料ID
     */
    public String stEstuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEstuffId);
    }

    /**
     * 电子材料ID
     */
    public void setStEstuffId(String stEstuffId) {
        stEstuffId = StringUtil.substringBySize(stEstuffId, 50, "GB18030");
        this.stEstuffId = stEstuffId;
    }
    
	/**
     * 所属办件ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }
    
    /**
     * 所属办件ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 所属办件ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }
    
	/**
     * 申请材料ID
     */
    public String getStCstuffId() {
        return this.stCstuffId;
    }
    
    /**
     * 申请材料ID
     */
    public String stCstuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCstuffId);
    }

    /**
     * 申请材料ID
     */
    public void setStCstuffId(String stCstuffId) {
        stCstuffId = StringUtil.substringBySize(stCstuffId, 50, "GB18030");
        this.stCstuffId = stCstuffId;
    }
    
	/**
     * 文件唯一标识
     */
    public String getStFileId() {
        return this.stFileId;
    }
    
    /**
     * 文件唯一标识
     */
    public String stFileId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stFileId);
    }

    /**
     * 文件唯一标识
     */
    public void setStFileId(String stFileId) {
        stFileId = StringUtil.substringBySize(stFileId, 50, "GB18030");
        this.stFileId = stFileId;
    }
    
	/**
     * 实体表唯一标识
     */
    public String getStTableId() {
        return this.stTableId;
    }
    
    /**
     * 实体表唯一标识
     */
    public String stTableId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTableId);
    }

    /**
     * 实体表唯一标识
     */
    public void setStTableId(String stTableId) {
        stTableId = StringUtil.substringBySize(stTableId, 50, "GB18030");
        this.stTableId = stTableId;
    }
    
	/**
     * 材料实体类型
     */
    public String getStEntityType() {
        return this.stEntityType;
    }
    
    /**
     * 材料实体类型
     */
    public String stEntityType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEntityType);
    }

    /**
     * 材料实体类型
     */
    public void setStEntityType(String stEntityType) {
        stEntityType = StringUtil.substringBySize(stEntityType, 50, "GB18030");
        this.stEntityType = stEntityType;
    }
    
	/**
     * 材料实体表
     */
    public String getStEntityTable() {
        return this.stEntityTable;
    }
    
    /**
     * 材料实体表
     */
    public String stEntityTable2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEntityTable);
    }

    /**
     * 材料实体表
     */
    public void setStEntityTable(String stEntityTable) {
        stEntityTable = StringUtil.substringBySize(stEntityTable, 50, "GB18030");
        this.stEntityTable = stEntityTable;
    }
    
	/**
     * 材料类型
     */
    public String getStStuffType() {
        return this.stStuffType;
    }
    
    /**
     * 材料类型
     */
    public String stStuffType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffType);
    }

    /**
     * 材料类型
     */
    public void setStStuffType(String stStuffType) {
        stStuffType = StringUtil.substringBySize(stStuffType, 50, "GB18030");
        this.stStuffType = stStuffType;
    }
    
	/**
     * 材料作用
     */
    public String getStStuffUse() {
        return this.stStuffUse;
    }
    
    /**
     * 材料作用
     */
    public String stStuffUse2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffUse);
    }

    /**
     * 材料作用
     */
    public void setStStuffUse(String stStuffUse) {
        stStuffUse = StringUtil.substringBySize(stStuffUse, 50, "GB18030");
        this.stStuffUse = stStuffUse;
    }
    
	/**
     * 材料描述
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 材料描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 材料描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 200, "GB18030");
        this.stDesc = stDesc;
    }
    
	/**
     * 材料修改意见
     */
    public String getStOpinion() {
        return this.stOpinion;
    }
    
    /**
     * 材料修改意见
     */
    public String stOpinion2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpinion);
    }

    /**
     * 材料修改意见
     */
    public void setStOpinion(String stOpinion) {
        stOpinion = StringUtil.substringBySize(stOpinion, 200, "GB18030");
        this.stOpinion = stOpinion;
    }

    /**
     * 材料结果
     */
    public String getStResult() {
        return this.stResult;
    }

    /**
     * 材料结果
     */
    public String stResult2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResult);
    }

    /**
     * 材料结果
     */
    public void setStResult(String stResult) {
        stResult = StringUtil.substringBySize(stResult, 50, "GB18030");
        this.stResult = stResult;
    }

	/**
     * 材料状态
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }
    
    /**
     * 材料状态
     */
    public String nmStatus2Html(int precision) {
        if (this.nmStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmStatus);
        }
    }

    /**
     * 材料状态
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
    }

	/**
     * 现场提交
     */
    public BigDecimal getNmOfflineSubmit() {
        return this.nmOfflineSubmit;
    }
    
    /**
     * 现场提交
     */
    public String nmOfflineSubmit2Html(int precision) {
        if (this.nmOfflineSubmit == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmOfflineSubmit);
        }
    }

    /**
     * 现场提交
     */
    public void setNmOfflineSubmit(BigDecimal nmOfflineSubmit) {
        this.nmOfflineSubmit = nmOfflineSubmit;
    }
    
	/**
     * 材料来源
     */
    public String getStSource() {
        return this.stSource;
    }
    
    /**
     * 材料来源
     */
    public String stSource2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSource);
    }

    /**
     * 材料来源
     */
    public void setStSource(String stSource) {
        stSource = StringUtil.substringBySize(stSource, 50, "GB18030");
        this.stSource = stSource;
    }
    
	/**
     * 来源唯一标识
     */
    public String getStSourceId() {
        return this.stSourceId;
    }
    
    /**
     * 来源唯一标识
     */
    public String stSourceId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSourceId);
    }

    /**
     * 来源唯一标识
     */
    public void setStSourceId(String stSourceId) {
        stSourceId = StringUtil.substringBySize(stSourceId, 50, "GB18030");
        this.stSourceId = stSourceId;
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
    public String stExt1Html() {
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
    public String stExt2Html() {
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
     * 更新时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtUpdate() {
        return this.dtUpdate;
    }
    
    /**
     * 更新时间
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
     * 更新时间
     */
    public void setDtUpdate(Timestamp dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}