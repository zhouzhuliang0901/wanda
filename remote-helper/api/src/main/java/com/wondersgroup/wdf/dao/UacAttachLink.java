package com.wondersgroup.wdf.dao;
		
import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.service.util.StringHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 电子材料关联附件
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_ATTACH_LINK")
public class UacAttachLink implements Serializable {
    
    /**
     * 电子材料关联附件
     */
    public static final String UAC_ATTACH_LINK = "UAC_ATTACH_LINK";
    
    /**
     * 电子材料ID
     */
    public static final String ST_ESTUFF_ID = "ST_ESTUFF_ID";
    
    /**
     * 附件ID
     */
    public static final String ST_ATTACH_ID = "ST_ATTACH_ID";
    
    /**
     * 排序
     */
    public static final String NM_ORDER = "NM_ORDER";

    /**
     * 文件名
     */
    public static final String ST_FILENAME = "ST_FILENAME";

    public UacAttachLink() {
    }
    
    /**
     * 电子材料ID
     */
    @Id
    @Column(name = "ST_ESTUFF_ID")
    private String stEstuffId;
    
    /**
     * 附件ID
     */
    @Id
    @Column(name = "ST_ATTACH_ID")
    private String stAttachId;

    /**
     * 文件名
     */
    @Column(name = "ST_FILENAME")
    private String stFileName;

    /**
     * 排序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;
    
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
     * 附件ID
     */
    public String getStAttachId() {
        return this.stAttachId;
    }
    
    /**
     * 附件ID
     */
    public String stAttachId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId);
    }

    /**
     * 附件ID
     */
    public void setStAttachId(String stAttachId) {
        stAttachId = StringUtil.substringBySize(stAttachId, 50, "GB18030");
        this.stAttachId = stAttachId;
    }

    /**
     * 文件名
     */
    public String getStFileName() {
        return this.stFileName;
    }

    /**
     * 文件名
     */
    public String stFileNameHtml() {
        return StringHelper.replaceHTMLSymbol(this.stFileName);
    }

    /**
     * 文件名
     */
    public void setStFileName(String stFileName) {
        stFileName = StringUtil.substringBySize(stFileName, 100, "GB18030");
        this.stFileName = stFileName;
    }
	/**
     * 排序
     */
    public BigDecimal getNmOrder() {
        return this.nmOrder;
    }
    
    /**
     * 排序
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
     * 排序
     */
    public void setNmOrder(BigDecimal nmOrder) {
        this.nmOrder = nmOrder;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}