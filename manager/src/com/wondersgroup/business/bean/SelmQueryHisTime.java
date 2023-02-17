package com.wondersgroup.business.bean;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import coral.base.util.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_QUERY_HIS_TIME")
public class SelmQueryHisTime implements Serializable{

	    /**
	     * 工作台模块使用历史记录时间
	     */
	    public static final String SELM_QUERY_HIS_TIME = "SELM_QUERY_HIS_TIME";
	    
	    /**
	     * ID
	     */
	    public static final String ST_QUERY_HIS_TIME_ID = "ST_QUERY_HIS_TIME_ID";
	    
	    /**
	     * 历史ID
	     */
	    public static final String ST_QUERY_HIS_ID = "ST_QUERY_HIS_ID";
	    
	    /**
	     * 创建时间
	     */
	    public static final String DT_CREATE = "DT_CREATE";
	    
	    
	    
	    public SelmQueryHisTime() {
	    }
	    
	    /**
	     * ID
	     */
	    @Id
	    @Column(name = "ST_QUERY_HIS_TIME_ID")
	    private String stQueryHisTimeId;
	    
	    /**
	     * 历史ID
	     */
	    @Id
	    @Column(name = "ST_QUERY_HIS_ID")
	    private String stQueryHisId;
	    
	    /**
	     * 创建时间
	     */
	    @Column(name = "DT_CREATE")
	    private Timestamp dtCreate;
	    
	    
	    /**
	     * ID
	     */
	    public String getStQueryHisTimeId() {
	        return this.stQueryHisTimeId;
	    }
	    
	    /**
	     * ID
	     */
	    public String stQueryHisTimeId2Html() {
	        return StringHelper.replaceHTMLSymbol(this.stQueryHisTimeId);
	    }

	    /**
	     * ID
	     */
	    public void setStQueryHisTimeId(String stQueryHisTimeId) {
	        stQueryHisTimeId = StringUtil.substringBySize(stQueryHisTimeId, 50, "GB18030");
	        this.stQueryHisTimeId = stQueryHisTimeId;
	    }
	    
		/**
	     * 历史ID
	     */
	    public String getStQueryHisId() {
	        return this.stQueryHisId;
	    }
	    
	    /**
	     * 历史ID
	     */
	    public String stQueryHisId2Html() {
	        return StringHelper.replaceHTMLSymbol(this.stQueryHisId);
	    }

	    /**
	     * 历史ID
	     */
	    public void setStQueryHisId(String stQueryHisId) {
	        stQueryHisId = StringUtil.substringBySize(stQueryHisId, 50, "GB18030");
	        this.stQueryHisId = stQueryHisId;
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
	    

	    public String toString() {
	        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	    }

}
