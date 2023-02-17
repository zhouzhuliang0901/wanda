package com.wondersgroup.wdf.dao;
		
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
 * 综合受理一表式
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UAPPLY_BASIC")
public class UacUapplyBasic implements Serializable {

    /**
     * 综合受理一表式
     */
    public static final String UAC_UAPPLY_BASIC = "UAC_UAPPLY_BASIC";

    /**
     * 办件信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";

    /**
     * 业务字符串1
     */
    public static final String ST_QUERY1 = "ST_QUERY1";

    /**
     * 业务字符串2
     */
    public static final String ST_QUERY2 = "ST_QUERY2";

    /**
     * 业务字符串3
     */
    public static final String ST_QUERY3 = "ST_QUERY3";

    /**
     * 业务字符串4
     */
    public static final String ST_QUERY4 = "ST_QUERY4";

    /**
     * 业务字符串5
     */
    public static final String ST_QUERY5 = "ST_QUERY5";

    /**
     * 业务字符串6
     */
    public static final String ST_QUERY6 = "ST_QUERY6";

    /**
     * 业务时间1
     */
    public static final String DT_DATE1 = "DT_DATE1";

    /**
     * 业务时间2
     */
    public static final String DT_DATE2 = "DT_DATE2";

    /**
     * 业务时间3
     */
    public static final String DT_DATE3 = "DT_DATE3";

    /**
     * 业务时间4
     */
    public static final String DT_DATE4 = "DT_DATE4";

    /**
     * 业务时间5
     */
    public static final String DT_DATE5 = "DT_DATE5";

    /**
     * 业务数字1
     */
    public static final String NM_DIGITAL1 = "NM_DIGITAL1";

    /**
     * 业务数字2
     */
    public static final String NM_DIGITAL2 = "NM_DIGITAL2";

    /**
     * 业务数字3
     */
    public static final String NM_DIGITAL3 = "NM_DIGITAL3";

    /**
     * 业务数字4
     */
    public static final String NM_DIGITAL4 = "NM_DIGITAL4";

    /**
     * 业务数字5
     */
    public static final String NM_DIGITAL5 = "NM_DIGITAL5";

    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";

    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";

    /**
     * 扩展字段3
     */
    public static final String ST_EXT3 = "ST_EXT3";

    /**
     * 扩展字段4
     */
    public static final String ST_EXT4 = "ST_EXT4";

    /**
     * 扩展字段5
     */
    public static final String ST_EXT5 = "ST_EXT5";

    /**
     * 申请内容描述
     */
    public static final String ST_CONTENT_DESC = "ST_CONTENT_DESC";

    /**
     * 邮编
     */
    public static final String ST_ZIP_CODE = "ST_ZIP_CODE";

    /**
     * 通讯地址
     */
    public static final String ST_ADDRESS = "ST_ADDRESS";

    /**
     * 物流收件人联系人
     */
    public static final String ST_LRECEIVOR = "ST_LRECEIVOR";

    /**
     * 物流收件人联系方式
     */
    public static final String ST_LRECEIVE_PHONE = "ST_LRECEIVE_PHONE";

    /**
     * 收货人省区市
     */
    public static final String ST_RECEIVER_PROVINCE = "ST_RECEIVER_PROVINCE";

    /**
     * 收货人城市
     */
    public static final String ST_RECEIVER_CITY = "ST_RECEIVER_CITY";

    /**
     * 收货人区县
     */
    public static final String ST_RECEIVER_AREA = "ST_RECEIVER_AREA";

    /**
     * 物流收件地址
     */
    public static final String ST_LRECEIVE_ADDRESS = "ST_LRECEIVE_ADDRESS";

    /**
     * 配送公司
     */
    public static final String ST_SHIP_COMPANY = "ST_SHIP_COMPANY";

    /**
     * 办理项类型
     */
    public static final String ST_SUBMIT_TYPE = "ST_SUBMIT_TYPE";

    /**
     * 其他扩展项
     */
    public static final String ST_OTHER_EXT = "ST_OTHER_EXT";

    /**
     * 基本信息
     */
    public static final String ST_CONTENT_ID = "ST_CONTENT_ID";

    /**
     * 结果信息
     */
    public static final String ST_RESULT_ID = "ST_RESULT_ID";

    public UacUapplyBasic() {
    }

    /**
     * 办件信息ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;

    /**
     * 业务字符串1
     */
    @Column(name = "ST_QUERY1")
    private String stQuery1;

    /**
     * 业务字符串2
     */
    @Column(name = "ST_QUERY2")
    private String stQuery2;

    /**
     * 业务字符串3
     */
    @Column(name = "ST_QUERY3")
    private String stQuery3;

    /**
     * 业务字符串4
     */
    @Column(name = "ST_QUERY4")
    private String stQuery4;

    /**
     * 业务字符串5
     */
    @Column(name = "ST_QUERY5")
    private String stQuery5;

    /**
     * 业务字符串6
     */
    @Column(name = "ST_QUERY6")
    private String stQuery6;

    /**
     * 业务时间1
     */
    @Column(name = "DT_DATE1")
    private Timestamp dtDate1;

    /**
     * 业务时间2
     */
    @Column(name = "DT_DATE2")
    private Timestamp dtDate2;

    /**
     * 业务时间3
     */
    @Column(name = "DT_DATE3")
    private Timestamp dtDate3;

    /**
     * 业务时间4
     */
    @Column(name = "DT_DATE4")
    private Timestamp dtDate4;

    /**
     * 业务时间5
     */
    @Column(name = "DT_DATE5")
    private Timestamp dtDate5;

    /**
     * 业务数字1
     */
    @Column(name = "NM_DIGITAL1")
    private BigDecimal nmDigital1;

    /**
     * 业务数字2
     */
    @Column(name = "NM_DIGITAL2")
    private BigDecimal nmDigital2;

    /**
     * 业务数字3
     */
    @Column(name = "NM_DIGITAL3")
    private BigDecimal nmDigital3;

    /**
     * 业务数字4
     */
    @Column(name = "NM_DIGITAL4")
    private BigDecimal nmDigital4;

    /**
     * 业务数字5
     */
    @Column(name = "NM_DIGITAL5")
    private BigDecimal nmDigital5;

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
     * 扩展字段3
     */
    @Column(name = "ST_EXT3")
    private String stExt3;

    /**
     * 扩展字段4
     */
    @Column(name = "ST_EXT4")
    private String stExt4;

    /**
     * 扩展字段5
     */
    @Column(name = "ST_EXT5")
    private String stExt5;

    /**
     * 申请内容描述
     */
    @Column(name = "ST_CONTENT_DESC")
    private String stContentDesc;

    /**
     * 邮编
     */
    @Column(name = "ST_ZIP_CODE")
    private String stZipCode;

    /**
     * 通讯地址
     */
    @Column(name = "ST_ADDRESS")
    private String stAddress;

    /**
     * 物流收件人联系人
     */
    @Column(name = "ST_LRECEIVOR")
    private String stLreceivor;

    /**
     * 物流收件人联系方式
     */
    @Column(name = "ST_LRECEIVE_PHONE")
    private String stLreceivePhone;

    /**
     * 收货人省区市
     */
    @Column(name = "ST_RECEIVER_PROVINCE")
    private String stReceiverProvince;

    /**
     * 收货人城市
     */
    @Column(name = "ST_RECEIVER_CITY")
    private String stReceiverCity;

    /**
     * 收货人区县
     */
    @Column(name = "ST_RECEIVER_AREA")
    private String stReceiverArea;

    /**
     * 物流收件地址
     */
    @Column(name = "ST_LRECEIVE_ADDRESS")
    private String stLreceiveAddress;

    /**
     * 配送公司
     */
    @Column(name = "ST_SHIP_COMPANY")
    private String stShipCompany;

    /**
     * 办理项类型
     */
    @Column(name = "ST_SUBMIT_TYPE")
    private String stSubmitType;

    /**
     * 其他扩展项
     */
    @Column(name = "ST_OTHER_EXT")
    private String stOtherExt;

    /**
     * 基本信息
     */
    @Column(name = "ST_CONTENT_ID")
    private String stContentId;

    /**
     * 结果信息
     */
    @Column(name = "ST_RESULT_ID")
    private String stResultId;

    /**
     * 办件信息ID
     */
    public String getStApplyId() {
        return this.stApplyId;
    }

    /**
     * 办件信息ID
     */
    public String stApplyId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyId);
    }

    /**
     * 办件信息ID
     */
    public void setStApplyId(String stApplyId) {
        stApplyId = StringUtil.substringBySize(stApplyId, 50, "GB18030");
        this.stApplyId = stApplyId;
    }

    /**
     * 业务字符串1
     */
    public String getStQuery1() {
        return this.stQuery1;
    }

    /**
     * 业务字符串1
     */
    public String stQuery12Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery1);
    }

    /**
     * 业务字符串1
     */
    public void setStQuery1(String stQuery1) {
        stQuery1 = StringUtil.substringBySize(stQuery1, 50, "GB18030");
        this.stQuery1 = stQuery1;
    }

    /**
     * 业务字符串2
     */
    public String getStQuery2() {
        return this.stQuery2;
    }

    /**
     * 业务字符串2
     */
    public String stQuery22Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery2);
    }

    /**
     * 业务字符串2
     */
    public void setStQuery2(String stQuery2) {
        stQuery2 = StringUtil.substringBySize(stQuery2, 50, "GB18030");
        this.stQuery2 = stQuery2;
    }

    /**
     * 业务字符串3
     */
    public String getStQuery3() {
        return this.stQuery3;
    }

    /**
     * 业务字符串3
     */
    public String stQuery32Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery3);
    }

    /**
     * 业务字符串3
     */
    public void setStQuery3(String stQuery3) {
        stQuery3 = StringUtil.substringBySize(stQuery3, 100, "GB18030");
        this.stQuery3 = stQuery3;
    }

    /**
     * 业务字符串4
     */
    public String getStQuery4() {
        return this.stQuery4;
    }

    /**
     * 业务字符串4
     */
    public String stQuery42Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery4);
    }

    /**
     * 业务字符串4
     */
    public void setStQuery4(String stQuery4) {
        stQuery4 = StringUtil.substringBySize(stQuery4, 100, "GB18030");
        this.stQuery4 = stQuery4;
    }

    /**
     * 业务字符串5
     */
    public String getStQuery5() {
        return this.stQuery5;
    }

    /**
     * 业务字符串5
     */
    public String stQuery52Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery5);
    }

    /**
     * 业务字符串5
     */
    public void setStQuery5(String stQuery5) {
        stQuery5 = StringUtil.substringBySize(stQuery5, 200, "GB18030");
        this.stQuery5 = stQuery5;
    }

    /**
     * 业务字符串6
     */
    public String getStQuery6() {
        return this.stQuery6;
    }

    /**
     * 业务字符串6
     */
    public String stQuery62Html() {
        return StringHelper.replaceHTMLSymbol(this.stQuery6);
    }

    /**
     * 业务字符串6
     */
    public void setStQuery6(String stQuery6) {
        stQuery6 = StringUtil.substringBySize(stQuery6, 200, "GB18030");
        this.stQuery6 = stQuery6;
    }

    /**
     * 业务时间1
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDate1() {
        return this.dtDate1;
    }

    /**
     * 业务时间1
     */
    public String dtDate12Html(String pattern) {
        if (this.dtDate1 == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDate1);
        }
    }

    /**
     * 业务时间1
     */
    public void setDtDate1(Timestamp dtDate1) {
        this.dtDate1 = dtDate1;
    }

    /**
     * 业务时间2
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDate2() {
        return this.dtDate2;
    }

    /**
     * 业务时间2
     */
    public String dtDate22Html(String pattern) {
        if (this.dtDate2 == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDate2);
        }
    }

    /**
     * 业务时间2
     */
    public void setDtDate2(Timestamp dtDate2) {
        this.dtDate2 = dtDate2;
    }

    /**
     * 业务时间3
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDate3() {
        return this.dtDate3;
    }

    /**
     * 业务时间3
     */
    public String dtDate32Html(String pattern) {
        if (this.dtDate3 == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDate3);
        }
    }

    /**
     * 业务时间3
     */
    public void setDtDate3(Timestamp dtDate3) {
        this.dtDate3 = dtDate3;
    }

    /**
     * 业务时间4
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDate4() {
        return this.dtDate4;
    }

    /**
     * 业务时间4
     */
    public String dtDate42Html(String pattern) {
        if (this.dtDate4 == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDate4);
        }
    }

    /**
     * 业务时间4
     */
    public void setDtDate4(Timestamp dtDate4) {
        this.dtDate4 = dtDate4;
    }

    /**
     * 业务时间5
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtDate5() {
        return this.dtDate5;
    }

    /**
     * 业务时间5
     */
    public String dtDate52Html(String pattern) {
        if (this.dtDate5 == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtDate5);
        }
    }

    /**
     * 业务时间5
     */
    public void setDtDate5(Timestamp dtDate5) {
        this.dtDate5 = dtDate5;
    }

    /**
     * 业务数字1
     */
    public BigDecimal getNmDigital1() {
        return this.nmDigital1;
    }

    /**
     * 业务数字1
     */
    public String nmDigital12Html(int precision) {
        if (this.nmDigital1 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDigital1);
        }
    }

    /**
     * 业务数字1
     */
    public void setNmDigital1(BigDecimal nmDigital1) {
        this.nmDigital1 = nmDigital1;
    }

    /**
     * 业务数字2
     */
    public BigDecimal getNmDigital2() {
        return this.nmDigital2;
    }

    /**
     * 业务数字2
     */
    public String nmDigital22Html(int precision) {
        if (this.nmDigital2 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDigital2);
        }
    }

    /**
     * 业务数字2
     */
    public void setNmDigital2(BigDecimal nmDigital2) {
        this.nmDigital2 = nmDigital2;
    }

    /**
     * 业务数字3
     */
    public BigDecimal getNmDigital3() {
        return this.nmDigital3;
    }

    /**
     * 业务数字3
     */
    public String nmDigital32Html(int precision) {
        if (this.nmDigital3 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDigital3);
        }
    }

    /**
     * 业务数字3
     */
    public void setNmDigital3(BigDecimal nmDigital3) {
        this.nmDigital3 = nmDigital3;
    }

    /**
     * 业务数字4
     */
    public BigDecimal getNmDigital4() {
        return this.nmDigital4;
    }

    /**
     * 业务数字4
     */
    public String nmDigital42Html(int precision) {
        if (this.nmDigital4 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDigital4);
        }
    }

    /**
     * 业务数字4
     */
    public void setNmDigital4(BigDecimal nmDigital4) {
        this.nmDigital4 = nmDigital4;
    }

    /**
     * 业务数字5
     */
    public BigDecimal getNmDigital5() {
        return this.nmDigital5;
    }

    /**
     * 业务数字5
     */
    public String nmDigital52Html(int precision) {
        if (this.nmDigital5 == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmDigital5);
        }
    }

    /**
     * 业务数字5
     */
    public void setNmDigital5(BigDecimal nmDigital5) {
        this.nmDigital5 = nmDigital5;
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
     * 扩展字段3
     */
    public String getStExt3() {
        return this.stExt3;
    }

    /**
     * 扩展字段3
     */
    public String stExt32Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt3);
    }

    /**
     * 扩展字段3
     */
    public void setStExt3(String stExt3) {
        stExt3 = StringUtil.substringBySize(stExt3, 50, "GB18030");
        this.stExt3 = stExt3;
    }

    /**
     * 扩展字段4
     */
    public String getStExt4() {
        return this.stExt4;
    }

    /**
     * 扩展字段4
     */
    public String stExt42Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt4);
    }

    /**
     * 扩展字段4
     */
    public void setStExt4(String stExt4) {
        stExt4 = StringUtil.substringBySize(stExt4, 50, "GB18030");
        this.stExt4 = stExt4;
    }

    /**
     * 扩展字段5
     */
    public String getStExt5() {
        return this.stExt5;
    }

    /**
     * 扩展字段5
     */
    public String stExt52Html() {
        return StringHelper.replaceHTMLSymbol(this.stExt5);
    }

    /**
     * 扩展字段5
     */
    public void setStExt5(String stExt5) {
        stExt5 = StringUtil.substringBySize(stExt5, 50, "GB18030");
        this.stExt5 = stExt5;
    }

    /**
     * 申请内容描述
     */
    public String getStContentDesc() {
        return this.stContentDesc;
    }

    /**
     * 申请内容描述
     */
    public String stContentDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContentDesc);
    }

    /**
     * 申请内容描述
     */
    public void setStContentDesc(String stContentDesc) {
        stContentDesc = StringUtil.substringBySize(stContentDesc, 500, "GB18030");
        this.stContentDesc = stContentDesc;
    }

    /**
     * 邮编
     */
    public String getStZipCode() {
        return this.stZipCode;
    }

    /**
     * 邮编
     */
    public String stZipCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stZipCode);
    }

    /**
     * 邮编
     */
    public void setStZipCode(String stZipCode) {
        stZipCode = StringUtil.substringBySize(stZipCode, 50, "GB18030");
        this.stZipCode = stZipCode;
    }

    /**
     * 通讯地址
     */
    public String getStAddress() {
        return this.stAddress;
    }

    /**
     * 通讯地址
     */
    public String stAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAddress);
    }

    /**
     * 通讯地址
     */
    public void setStAddress(String stAddress) {
        stAddress = StringUtil.substringBySize(stAddress, 200, "GB18030");
        this.stAddress = stAddress;
    }

    /**
     * 物流收件人联系人
     */
    public String getStLreceivor() {
        return this.stLreceivor;
    }

    /**
     * 物流收件人联系人
     */
    public String stLreceivor2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLreceivor);
    }

    /**
     * 物流收件人联系人
     */
    public void setStLreceivor(String stLreceivor) {
        stLreceivor = StringUtil.substringBySize(stLreceivor, 50, "GB18030");
        this.stLreceivor = stLreceivor;
    }

    /**
     * 物流收件人联系方式
     */
    public String getStLreceivePhone() {
        return this.stLreceivePhone;
    }

    /**
     * 物流收件人联系方式
     */
    public String stLreceivePhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLreceivePhone);
    }

    /**
     * 物流收件人联系方式
     */
    public void setStLreceivePhone(String stLreceivePhone) {
        stLreceivePhone = StringUtil.substringBySize(stLreceivePhone, 50, "GB18030");
        this.stLreceivePhone = stLreceivePhone;
    }

    /**
     * 收货人省区市
     */
    public String getStReceiverProvince() {
        return this.stReceiverProvince;
    }

    /**
     * 收货人省区市
     */
    public String stReceiverProvince2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverProvince);
    }

    /**
     * 收货人省区市
     */
    public void setStReceiverProvince(String stReceiverProvince) {
        stReceiverProvince = StringUtil.substringBySize(stReceiverProvince, 20, "GB18030");
        this.stReceiverProvince = stReceiverProvince;
    }

    /**
     * 收货人城市
     */
    public String getStReceiverCity() {
        return this.stReceiverCity;
    }

    /**
     * 收货人城市
     */
    public String stReceiverCity2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverCity);
    }

    /**
     * 收货人城市
     */
    public void setStReceiverCity(String stReceiverCity) {
        stReceiverCity = StringUtil.substringBySize(stReceiverCity, 50, "GB18030");
        this.stReceiverCity = stReceiverCity;
    }

    /**
     * 收货人区县
     */
    public String getStReceiverArea() {
        return this.stReceiverArea;
    }

    /**
     * 收货人区县
     */
    public String stReceiverArea2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceiverArea);
    }

    /**
     * 收货人区县
     */
    public void setStReceiverArea(String stReceiverArea) {
        stReceiverArea = StringUtil.substringBySize(stReceiverArea, 50, "GB18030");
        this.stReceiverArea = stReceiverArea;
    }

    /**
     * 物流收件地址
     */
    public String getStLreceiveAddress() {
        return this.stLreceiveAddress;
    }

    /**
     * 物流收件地址
     */
    public String stLreceiveAddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLreceiveAddress);
    }

    /**
     * 物流收件地址
     */
    public void setStLreceiveAddress(String stLreceiveAddress) {
        stLreceiveAddress = StringUtil.substringBySize(stLreceiveAddress, 200, "GB18030");
        this.stLreceiveAddress = stLreceiveAddress;
    }

    /**
     * 配送公司
     */
    public String getStShipCompany() {
        return this.stShipCompany;
    }

    /**
     * 配送公司
     */
    public String stShipCompany2Html() {
        return StringHelper.replaceHTMLSymbol(this.stShipCompany);
    }

    /**
     * 配送公司
     */
    public void setStShipCompany(String stShipCompany) {
        stShipCompany = StringUtil.substringBySize(stShipCompany, 50, "GB18030");
        this.stShipCompany = stShipCompany;
    }

    /**
     * 办理项类型
     */
    public String getStSubmitType() {
        return this.stSubmitType;
    }

    /**
     * 办理项类型
     */
    public String stSubmitType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubmitType);
    }

    /**
     * 办理项类型
     */
    public void setStSubmitType(String stSubmitType) {
        stSubmitType = StringUtil.substringBySize(stSubmitType, 50, "GB18030");
        this.stSubmitType = stSubmitType;
    }

    /**
     * 其他扩展项
     */
    public String getStOtherExt() {
        return this.stOtherExt;
    }

    /**
     * 其他扩展项
     */
    public String stOtherExt2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOtherExt);
    }

    /**
     * 其他扩展项
     */
    public void setStOtherExt(String stOtherExt) {
        stOtherExt = StringUtil.substringBySize(stOtherExt, 50, "GB18030");
        this.stOtherExt = stOtherExt;
    }

    /**
     * 基本信息
     */
    public String getStContentId() {
        return this.stContentId;
    }

    /**
     * 基本信息
     */
    public String stContentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stContentId);
    }

    /**
     * 基本信息
     */
    public void setStContentId(String stContentId) {
        stContentId = StringUtil.substringBySize(stContentId, 50, "GB18030");
        this.stContentId = stContentId;
    }

    /**
     * 结果信息
     */
    public String getStResultId() {
        return this.stResultId;
    }

    /**
     * 结果信息
     */
    public String stResultId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stResultId);
    }

    /**
     * 结果信息
     */
    public void setStResultId(String stResultId) {
        stResultId = StringUtil.substringBySize(stResultId, 50, "GB18030");
        this.stResultId = stResultId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}