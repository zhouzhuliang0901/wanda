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
 * 材料信息
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_STUFF")
public class UacStuff implements Serializable {
    
    /**
     * 材料信息
     */
    public static final String UAC_STUFF = "UAC_STUFF";
    
    /**
     * 申请材料ID
     */
    public static final String ST_STUFF_ID = "ST_STUFF_ID";
    
    /**
     * 材料代码
     */
    public static final String ST_STUFF_CODE = "ST_STUFF_CODE";
    
    /**
     * 材料名称
     */
    public static final String ST_STUFF_NAME = "ST_STUFF_NAME";
    
    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";
    
    /**
     * 别名
     */
    public static final String ST_ALIAS = "ST_ALIAS";
    
    /**
     * 材料来源
     */
    public static final String ST_SOURCE = "ST_SOURCE";
    
    /**
     * 对应证照正规名称编号
     */
    public static final String ST_FORMAL_ID = "ST_FORMAL_ID";
    
    /**
     * 材料类型
     */
    public static final String NM_TYPE = "NM_TYPE";
    
    /**
     * 样表ID
     */
    public static final String ST_SAMPLE_ID = "ST_SAMPLE_ID";
    
    /**
     * 空表ID
     */
    public static final String ST_EMPTY_ID = "ST_EMPTY_ID";
    
    /**
     * 导出模板
     */
    public static final String ST_ATTACH_TMPID = "ST_ATTACH_TMPID";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public UacStuff() {
    }
    
    /**
     * 申请材料ID
     */
    @Id
    @Column(name = "ST_STUFF_ID")
    private String stStuffId;
    
    /**
     * 材料代码
     */
    @Column(name = "ST_STUFF_CODE")
    private String stStuffCode;
    
    /**
     * 材料名称
     */
    @Column(name = "ST_STUFF_NAME")
    private String stStuffName;
    
    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;
    
    /**
     * 别名
     */
    @Column(name = "ST_ALIAS")
    private String stAlias;
    
    /**
     * 材料来源
     */
    @Column(name = "ST_SOURCE")
    private String stSource;
    
    /**
     * 对应证照正规名称编号
     */
    @Column(name = "ST_FORMAL_ID")
    private String stFormalId;
    
    /**
     * 材料类型
     */
    @Column(name = "NM_TYPE")
    private BigDecimal nmType;
    
    /**
     * 样表ID
     */
    @Column(name = "ST_SAMPLE_ID")
    private String stSampleId;
    
    /**
     * 空表ID
     */
    @Column(name = "ST_EMPTY_ID")
    private String stEmptyId;
    
    /**
     * 导出模板
     */
    @Column(name = "ST_ATTACH_TMPID")
    private String stAttachTmpid;
    
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
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;
    
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
     * 申请材料ID
     */
    public String getStStuffId() {
        return this.stStuffId;
    }
    
    /**
     * 申请材料ID
     */
    public String stStuffId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffId);
    }

    /**
     * 申请材料ID
     */
    public void setStStuffId(String stStuffId) {
        stStuffId = StringUtil.substringBySize(stStuffId, 50, "GB18030");
        this.stStuffId = stStuffId;
    }
    
	/**
     * 材料代码
     */
    public String getStStuffCode() {
        return this.stStuffCode;
    }
    
    /**
     * 材料代码
     */
    public String stStuffCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stStuffCode);
    }

    /**
     * 材料代码
     */
    public void setStStuffCode(String stStuffCode) {
        stStuffCode = StringUtil.substringBySize(stStuffCode, 50, "GB18030");
        this.stStuffCode = stStuffCode;
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
     * 区域ID
     */
    public String getStAreaId() {
        return this.stAreaId;
    }
    
    /**
     * 区域ID
     */
    public String stAreaId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaId);
    }

    /**
     * 区域ID
     */
    public void setStAreaId(String stAreaId) {
        stAreaId = StringUtil.substringBySize(stAreaId, 50, "GB18030");
        this.stAreaId = stAreaId;
    }
    
	/**
     * 别名
     */
    public String getStAlias() {
        return this.stAlias;
    }
    
    /**
     * 别名
     */
    public String stAlias2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAlias);
    }

    /**
     * 别名
     */
    public void setStAlias(String stAlias) {
        stAlias = StringUtil.substringBySize(stAlias, 200, "GB18030");
        this.stAlias = stAlias;
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
        stFormalId = StringUtil.substringBySize(stFormalId, 50, "GB18030");
        this.stFormalId = stFormalId;
    }

	/**
     * 材料类型
     */
    public BigDecimal getNmType() {
        return this.nmType;
    }
    
    /**
     * 材料类型
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
     * 材料类型
     */
    public void setNmType(BigDecimal nmType) {
        this.nmType = nmType;
    }
    
	/**
     * 样表ID
     */
    public String getStSampleId() {
        return this.stSampleId;
    }
    
    /**
     * 样表ID
     */
    public String stSampleId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSampleId);
    }

    /**
     * 样表ID
     */
    public void setStSampleId(String stSampleId) {
        stSampleId = StringUtil.substringBySize(stSampleId, 50, "GB18030");
        this.stSampleId = stSampleId;
    }
    
	/**
     * 空表ID
     */
    public String getStEmptyId() {
        return this.stEmptyId;
    }
    
    /**
     * 空表ID
     */
    public String stEmptyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEmptyId);
    }

    /**
     * 空表ID
     */
    public void setStEmptyId(String stEmptyId) {
        stEmptyId = StringUtil.substringBySize(stEmptyId, 50, "GB18030");
        this.stEmptyId = stEmptyId;
    }
    
	/**
     * 导出模板
     */
    public String getStAttachTmpid() {
        return this.stAttachTmpid;
    }
    
    /**
     * 导出模板
     */
    public String stAttachTmpid2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachTmpid);
    }

    /**
     * 导出模板
     */
    public void setStAttachTmpid(String stAttachTmpid) {
        stAttachTmpid = StringUtil.substringBySize(stAttachTmpid, 50, "GB18030");
        this.stAttachTmpid = stAttachTmpid;
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

	/**
     * 是否已删除
     */
    public BigDecimal getNmRemoved() {
        return this.nmRemoved;
    }
    
    /**
     * 是否已删除
     */
    public String nmRemoved2Html(int precision) {
        if (this.nmRemoved == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmRemoved);
        }
    }

    /**
     * 是否已删除
     */
    public void setNmRemoved(BigDecimal nmRemoved) {
        this.nmRemoved = nmRemoved;
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}