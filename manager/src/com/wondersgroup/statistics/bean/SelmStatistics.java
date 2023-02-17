package com.wondersgroup.statistics.bean;
		

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

/**
* 业务表
* @author scalffold
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_STATISTICS")
public class SelmStatistics implements Serializable {

/**
* 业务表
*/
public static final String SELM_STATISTICS = "SELM_STATISTICS";

/**
* 统计ID
*/
public static final String ST_STATISTICS_ID = "ST_STATISTICS_ID";

/**
* 业务标识
*/
public static final String ST_NET_FLAG = "ST_NET_FLAG";

/**
* 业务子标识
*/
public static final String ST_NET_SUB_FLAG = "ST_NET_SUB_FLAG";

/**
* 业务名称
*/
public static final String ST_NAME = "ST_NAME";

/**
* 业务总数
*/
public static final String NM_COUNT = "NM_COUNT";

/**
* 是否是外设
*/
public static final String NM_ODEVICE = "NM_ODEVICE";

/**
* 排序
*/
public static final String NM_SORT = "NM_SORT";

/**
* 扩展字段1
*/
public static final String ST_EXT1 = "ST_EXT1";

/**
* 扩展字段2
*/
public static final String ST_EXT2 = "ST_EXT2";

public SelmStatistics() {
}

/**
* 统计ID
*/
@Id
@Column(name = "ST_STATISTICS_ID")
private String stStatisticsId;

/**
* 业务标识
*/
@Column(name = "ST_NET_FLAG")
private String stNetFlag;

/**
* 业务子标识
*/
@Column(name = "ST_NET_SUB_FLAG")
private String stNetSubFlag;

/**
* 业务名称
*/
@Column(name = "ST_NAME")
private String stName;

/**
* 业务总数
*/
@Column(name = "NM_COUNT")
private BigDecimal nmCount;

/**
* 是否是外设
*/
@Column(name = "NM_ODEVICE")
private BigDecimal nmOdevice;

/**
* 排序
*/
@Column(name = "NM_SORT")
private BigDecimal nmSort;

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
* 统计ID
*/
public String getStStatisticsId() {
return this.stStatisticsId;
}

/**
* 统计ID
*/
public String stStatisticsId2Html() {
return StringHelper.replaceHTMLSymbol(this.stStatisticsId);
}

/**
* 统计ID
*/
public void setStStatisticsId(String stStatisticsId) {
stStatisticsId = StringUtil.substringBySize(stStatisticsId, 50, "GB18030");
this.stStatisticsId = stStatisticsId;
}

/**
* 业务标识
*/
public String getStNetFlag() {
return this.stNetFlag;
}

/**
* 业务标识
*/
public String stNetFlag2Html() {
return StringHelper.replaceHTMLSymbol(this.stNetFlag);
}

/**
* 业务标识
*/
public void setStNetFlag(String stNetFlag) {
stNetFlag = StringUtil.substringBySize(stNetFlag, 50, "GB18030");
this.stNetFlag = stNetFlag;
}

/**
* 业务子标识
*/
public String getStNetSubFlag() {
return this.stNetSubFlag;
}

/**
* 业务子标识
*/
public String stNetSubFlag2Html() {
return StringHelper.replaceHTMLSymbol(this.stNetSubFlag);
}

/**
* 业务子标识
*/
public void setStNetSubFlag(String stNetSubFlag) {
stNetSubFlag = StringUtil.substringBySize(stNetSubFlag, 50, "GB18030");
this.stNetSubFlag = stNetSubFlag;
}

/**
* 业务名称
*/
public String getStName() {
return this.stName;
}

/**
* 业务名称
*/
public String stName2Html() {
return StringHelper.replaceHTMLSymbol(this.stName);
}

/**
* 业务名称
*/
public void setStName(String stName) {
stName = StringUtil.substringBySize(stName, 50, "GB18030");
this.stName = stName;
}

/**
* 业务总数
*/
public BigDecimal getNmCount() {
return this.nmCount;
}

/**
* 业务总数
*/
public String nmCount2Html(int precision) {
if (this.nmCount == null) {
    return "";
} else {
    String pattern = "0";
    if (precision > 0) {
        pattern += ".";
        for (int i = 0; i < precision; i++) {
            pattern += "0";
        }
    }
    return new DecimalFormat(pattern).format(this.nmCount);
}
}

/**
* 业务总数
*/
public void setNmCount(BigDecimal nmCount) {
this.nmCount = nmCount;
}

/**
* 是否是外设
*/
public BigDecimal getNmOdevice() {
return this.nmOdevice;
}

/**
* 是否是外设
*/
public String nmOdevice2Html(int precision) {
if (this.nmOdevice == null) {
    return "";
} else {
    String pattern = "0";
    if (precision > 0) {
        pattern += ".";
        for (int i = 0; i < precision; i++) {
            pattern += "0";
        }
    }
    return new DecimalFormat(pattern).format(this.nmOdevice);
}
}

/**
* 是否是外设
*/
public void setNmOdevice(BigDecimal nmOdevice) {
this.nmOdevice = nmOdevice;
}

/**
* 排序
*/
public BigDecimal getNmSort() {
return this.nmSort;
}

/**
* 排序
*/
public String nmSort2Html(int precision) {
if (this.nmSort == null) {
    return "";
} else {
    String pattern = "0";
    if (precision > 0) {
        pattern += ".";
        for (int i = 0; i < precision; i++) {
            pattern += "0";
        }
    }
    return new DecimalFormat(pattern).format(this.nmSort);
}
}

/**
* 排序
*/
public void setNmSort(BigDecimal nmSort) {
this.nmSort = nmSort;
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
stExt1 = StringUtil.substringBySize(stExt1, 500, "GB18030");
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