package com.wondersgroup.wdf.uacItemInfo.dao;


import coral.base.util.StringUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import wfc.facility.tool.autocode.TimestampXmlAdapter;
import wfc.service.util.StringHelper;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 事项信息&业态信息关联
 * @Author: whp
 * @Date: 2021/7/27 10:18
 */
@SuppressWarnings("serial")
@Entity
public class ItemsView implements Serializable {

    /**
     * 事项信息
     */
    public static final String UAC_ITEM_INFO = "UAC_ITEM_INFO";

    /**
     * 事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";

    /**
     * 所属事项代码
     */
    public static final String ST_ITEM_CODE = "ST_ITEM_CODE";

    /**
     * 所属事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";

    /**
     * 内部唯一码
     */
    public static final String ST_INNER_NO = "ST_INNER_NO";

    /**
     * 继承事项ID
     */
    public static final String ST_INHERIT_ID = "ST_INHERIT_ID";

    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";

    /**
     * 区域代码
     */
    public static final String ST_AREA_CODE = "ST_AREA_CODE";

    /**
     * 部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";

    /**
     * 部门代码
     */
    public static final String ST_DEPART_CODE = "ST_DEPART_CODE";

    /**
     * 部门名称
     */
    public static final String ST_DEPART_NAME = "ST_DEPART_NAME";

    /**
     * 展示顺序
     */
    public static final String NM_ORDER = "NM_ORDER";

    /**
     * 父事项ID
     */
    public static final String ST_PARENT_ID = "ST_PARENT_ID";

    /**
     * 是否有审批短信
     */
    public static final String NM_SMS = "NM_SMS";

    /**
     * 是否全程网办
     */
    public static final String NM_ALL_ONLINE = "NM_ALL_ONLINE";

    /**
     * 继承类型
     */
    public static final String NM_INHERIT_TYPE = "NM_INHERIT_TYPE";

    /**
     * 业态事项类型
     */
    public static final String ST_TYPE = "ST_TYPE";

    /**
     * 证照编码
     */
    public static final String ST_ECERT_CODE = "ST_ECERT_CODE";

    /**
     * 证照名称
     */
    public static final String ST_ECERT_NAME = "ST_ECERT_NAME";

    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";

    /**
     * 主题事项（综合）
     */
    public static final String UAC_ITEMS = "UAC_ITEMS";

    /**
     * 主题ID
     */
    public static final String ST_ITEMS_ID = "ST_ITEMS_ID";

    /**
     * 主题编码
     */
    public static final String ST_ITEMS_CODE = "ST_ITEMS_CODE";

    /**
     * 主题名称
     */
    public static final String ST_ITEMS_NAME = "ST_ITEMS_NAME";

    /**
     * 描述
     */
    public static final String ST_DESC = "ST_DESC";

    /**
     * 状态
     */
    public static final String NM_STATUS = "NM_STATUS";

    /**
     * PC入口页面
     */
    public static final String ST_PC_URL = "ST_PC_URL";

    /**
     * 移动端入口页面
     */
    public static final String ST_APP_URL = "ST_APP_URL";

    /**
     * 自助终端入口页面
     */
    public static final String ST_T_URL = "ST_T_URL";

    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";

    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";

    public ItemsView(){
    }

    /**
     * 事项ID
     */
    @Id
    @Column(name = "ST_ITEM_ID")
    private String stItemId;

    /**
     * 所属事项代码
     */
    @Column(name = "ST_ITEM_CODE")
    private String stItemCode;

    /**
     * 所属事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;

    /**
     * 内部唯一码
     */
    @Column(name = "ST_INNER_NO")
    private String stInnerNo;

    /**
     * 继承事项ID
     */
    @Column(name = "ST_INHERIT_ID")
    private String stInheritId;

    /**
     * 区域ID
     */
    @Column(name = "ST_AREA_ID")
    private String stAreaId;

    /**
     * 区域代码
     */
    @Column(name = "ST_AREA_CODE")
    private String stAreaCode;

    /**
     * 部门ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;

    /**
     * 部门代码
     */
    @Column(name = "ST_DEPART_CODE")
    private String stDepartCode;

    /**
     * 部门名称
     */
    @Column(name = "ST_DEPART_NAME")
    private String stDepartName;

    /**
     * 展示顺序
     */
    @Column(name = "NM_ORDER")
    private BigDecimal nmOrder;

    /**
     * 父事项ID
     */
    @Column(name = "ST_PARENT_ID")
    private String stParentId;

    /**
     * 是否有审批短信
     */
    @Column(name = "NM_SMS")
    private BigDecimal nmSms;

    /**
     * 是否全程网办
     */
    @Column(name = "NM_ALL_ONLINE")
    private BigDecimal nmAllOnline;

    /**
     * 继承类型
     */
    @Column(name = "NM_INHERIT_TYPE")
    private BigDecimal nmInheritType;

    /**
     * 业态事项类型
     */
    @Column(name = "ST_TYPE")
    private String stType;

    /**
     * 证照编码
     */
    @Column(name = "ST_ECERT_CODE")
    private String stEcertCode;

    /**
     * 证照名称
     */
    @Column(name = "ST_ECERT_NAME")
    private String stEcertName;

    /**
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;

    /**
     * 主题ID
     */
    @Id
    @Column(name = "ST_ITEMS_ID")
    private String stItemsId;

    /**
     * 主题编码
     */
    @Column(name = "ST_ITEMS_CODE")
    private String stItemsCode;

    /**
     * 主题名称
     */
    @Column(name = "ST_ITEMS_NAME")
    private String stItemsName;

    /**
     * 描述
     */
    @Column(name = "ST_DESC")
    private String stDesc;

    /**
     * 状态
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;

    /**
     * PC入口页面
     */
    @Column(name = "ST_PC_URL")
    private String stPcUrl;

    /**
     * 移动端入口页面
     */
    @Column(name = "ST_APP_URL")
    private String stAppUrl;

    /**
     * 自助终端入口页面
     */
    @Column(name = "ST_T_URL")
    private String stTUrl;

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
     * 事项ID
     */
    public String getStItemId() {
        return this.stItemId;
    }

    /**
     * 事项ID
     */
    public String stItemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemId);
    }

    /**
     * 事项ID
     */
    public void setStItemId(String stItemId) {
        stItemId = StringUtil.substringBySize(stItemId, 50, "GB18030");
        this.stItemId = stItemId;
    }

    /**
     * 所属事项代码
     */
    public String getStItemCode() {
        return this.stItemCode;
    }

    /**
     * 所属事项代码
     */
    public String stItemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemCode);
    }

    /**
     * 所属事项代码
     */
    public void setStItemCode(String stItemCode) {
        stItemCode = StringUtil.substringBySize(stItemCode, 50, "GB18030");
        this.stItemCode = stItemCode;
    }

    /**
     * 所属事项名称
     */
    public String getStItemName() {
        return this.stItemName;
    }

    /**
     * 所属事项名称
     */
    public String stItemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemName);
    }

    /**
     * 所属事项名称
     */
    public void setStItemName(String stItemName) {
        stItemName = StringUtil.substringBySize(stItemName, 200, "GB18030");
        this.stItemName = stItemName;
    }

    /**
     * 内部唯一码
     */
    public String getStInnerNo() {
        return this.stInnerNo;
    }

    /**
     * 内部唯一码
     */
    public String stInnerNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInnerNo);
    }

    /**
     * 内部唯一码
     */
    public void setStInnerNo(String stInnerNo) {
        stInnerNo = StringUtil.substringBySize(stInnerNo, 50, "GB18030");
        this.stInnerNo = stInnerNo;
    }

    /**
     * 继承事项ID
     */
    public String getStInheritId() {
        return this.stInheritId;
    }

    /**
     * 继承事项ID
     */
    public String stInheritId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stInheritId);
    }

    /**
     * 继承事项ID
     */
    public void setStInheritId(String stInheritId) {
        stInheritId = StringUtil.substringBySize(stInheritId, 50, "GB18030");
        this.stInheritId = stInheritId;
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
     * 区域代码
     */
    public String getStAreaCode() {
        return this.stAreaCode;
    }

    /**
     * 区域代码
     */
    public String stAreaCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaCode);
    }

    /**
     * 区域代码
     */
    public void setStAreaCode(String stAreaCode) {
        stAreaCode = StringUtil.substringBySize(stAreaCode, 50, "GB18030");
        this.stAreaCode = stAreaCode;
    }

    /**
     * 部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }

    /**
     * 部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }

    /**
     * 部门代码
     */
    public String getStDepartCode() {
        return this.stDepartCode;
    }

    /**
     * 部门代码
     */
    public String stDepartCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartCode);
    }

    /**
     * 部门代码
     */
    public void setStDepartCode(String stDepartCode) {
        stDepartCode = StringUtil.substringBySize(stDepartCode, 50, "GB18030");
        this.stDepartCode = stDepartCode;
    }

    /**
     * 部门名称
     */
    public String getStDepartName() {
        return this.stDepartName;
    }

    /**
     * 部门名称
     */
    public String stDepartName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartName);
    }

    /**
     * 部门名称
     */
    public void setStDepartName(String stDepartName) {
        stDepartName = StringUtil.substringBySize(stDepartName, 50, "GB18030");
        this.stDepartName = stDepartName;
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
     * 父事项ID
     */
    public String getStParentId() {
        return this.stParentId;
    }

    /**
     * 父事项ID
     */
    public String stParentId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stParentId);
    }

    /**
     * 父事项ID
     */
    public void setStParentId(String stParentId) {
        stParentId = StringUtil.substringBySize(stParentId, 50, "GB18030");
        this.stParentId = stParentId;
    }

    /**
     * 是否有审批短信
     */
    public BigDecimal getNmSms() {
        return this.nmSms;
    }

    /**
     * 是否有审批短信
     */
    public String nmSms2Html(int precision) {
        if (this.nmSms == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSms);
        }
    }

    /**
     * 是否有审批短信
     */
    public void setNmSms(BigDecimal nmSms) {
        this.nmSms = nmSms;
    }

    /**
     * 是否全程网办
     */
    public BigDecimal getNmAllOnline() {
        return this.nmAllOnline;
    }

    /**
     * 是否全程网办
     */
    public String nmAllOnline2Html(int precision) {
        if (this.nmAllOnline == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmAllOnline);
        }
    }

    /**
     * 是否全程网办
     */
    public void setNmAllOnline(BigDecimal nmAllOnline) {
        this.nmAllOnline = nmAllOnline;
    }

    /**
     * 继承类型
     */
    public BigDecimal getNmInheritType() {
        return this.nmInheritType;
    }

    /**
     * 继承类型
     */
    public String nmInheritType2Html(int precision) {
        if (this.nmInheritType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmInheritType);
        }
    }

    /**
     * 继承类型
     */
    public void setNmInheritType(BigDecimal nmInheritType) {
        this.nmInheritType = nmInheritType;
    }

    /**
     * 业态事项类型
     */
    public String getStType() {
        return this.stType;
    }

    /**
     * 业态事项类型
     */
    public String stType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stType);
    }

    /**
     * 业态事项类型
     */
    public void setStType(String stType) {
        stType = StringUtil.substringBySize(stType, 50, "GB18030");
        this.stType = stType;
    }

    /**
     * 证照编码
     */
    public String getStEcertCode() {
        return this.stEcertCode;
    }

    /**
     * 证照编码
     */
    public String stEcertCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEcertCode);
    }

    /**
     * 证照编码
     */
    public void setStEcertCode(String stEcertCode) {
        stEcertCode = StringUtil.substringBySize(stEcertCode, 50, "GB18030");
        this.stEcertCode = stEcertCode;
    }

    /**
     * 证照名称
     */
    public String getStEcertName() {
        return this.stEcertName;
    }

    /**
     * 证照名称
     */
    public String stEcertName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stEcertName);
    }

    /**
     * 证照名称
     */
    public void setStEcertName(String stEcertName) {
        stEcertName = StringUtil.substringBySize(stEcertName, 50, "GB18030");
        this.stEcertName = stEcertName;
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
     * 主题ID
     */
    public String getStItemsId() {
        return this.stItemsId;
    }

    /**
     * 主题ID
     */
    public String stItemsId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsId);
    }

    /**
     * 主题ID
     */
    public void setStItemsId(String stItemsId) {
        stItemsId = StringUtil.substringBySize(stItemsId, 50, "GB18030");
        this.stItemsId = stItemsId;
    }

    /**
     * 主题编码
     */
    public String getStItemsCode() {
        return this.stItemsCode;
    }

    /**
     * 主题编码
     */
    public String stItemsCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsCode);
    }

    /**
     * 主题编码
     */
    public void setStItemsCode(String stItemsCode) {
        stItemsCode = StringUtil.substringBySize(stItemsCode, 50, "GB18030");
        this.stItemsCode = stItemsCode;
    }

    /**
     * 主题名称
     */
    public String getStItemsName() {
        return this.stItemsName;
    }

    /**
     * 主题名称
     */
    public String stItemsName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemsName);
    }

    /**
     * 主题名称
     */
    public void setStItemsName(String stItemsName) {
        stItemsName = StringUtil.substringBySize(stItemsName, 50, "GB18030");
        this.stItemsName = stItemsName;
    }

    /**
     * 描述
     */
    public String getStDesc() {
        return this.stDesc;
    }

    /**
     * 描述
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 描述
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }

    /**
     * 状态
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }

    /**
     * 状态
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
     * 状态
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
    }

    /**
     * PC入口页面
     */
    public String getStPcUrl() {
        return this.stPcUrl;
    }

    /**
     * PC入口页面
     */
    public String stPcUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stPcUrl);
    }

    /**
     * PC入口页面
     */
    public void setStPcUrl(String stPcUrl) {
        stPcUrl = StringUtil.substringBySize(stPcUrl, 200, "GB18030");
        this.stPcUrl = stPcUrl;
    }

    /**
     * 移动端入口页面
     */
    public String getStAppUrl() {
        return this.stAppUrl;
    }

    /**
     * 移动端入口页面
     */
    public String stAppUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAppUrl);
    }

    /**
     * 移动端入口页面
     */
    public void setStAppUrl(String stAppUrl) {
        stAppUrl = StringUtil.substringBySize(stAppUrl, 200, "GB18030");
        this.stAppUrl = stAppUrl;
    }

    /**
     * 自助终端入口页面
     */
    public String getStTUrl() {
        return this.stTUrl;
    }

    /**
     * 自助终端入口页面
     */
    public String stTUrl2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTUrl);
    }

    /**
     * 自助终端入口页面
     */
    public void setStTUrl(String stTUrl) {
        stTUrl = StringUtil.substringBySize(stTUrl, 200, "GB18030");
        this.stTUrl = stTUrl;
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

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
