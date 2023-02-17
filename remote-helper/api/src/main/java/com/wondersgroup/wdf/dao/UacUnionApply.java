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
 * 综合受理
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UAC_UNION_APPLY")
public class UacUnionApply implements Serializable {

    /**
     * 综合受理
     */
    public static final String UAC_UNION_APPLY = "UAC_UNION_APPLY";

    /**
     * 办件信息ID
     */
    public static final String ST_APPLY_ID = "ST_APPLY_ID";

    /**
     * 办件流水号
     */
    public static final String ST_SNO = "ST_SNO";

    /**
     * 外网用户ID
     */
    public static final String ST_USER_ID = "ST_USER_ID";

    /**
     * 统一联合编码
     */
    public static final String ST_APPLY_NO = "ST_APPLY_NO";

    /**
     * 综合事项ID
     */
    public static final String ST_CITEM_ID = "ST_CITEM_ID";

    /**
     * 综合事项编码
     */
    public static final String ST_CITEM_CODE = "ST_CITEM_CODE";

    /**
     * 综合事项名称
     */
    public static final String ST_CITEM_NAME = "ST_CITEM_NAME";

    /**
     * 情形
     */
    public static final String ST_TRANSACT_NAME = "ST_TRANSACT_NAME";

    /**
     * 收件部门ID
     */
    public static final String ST_ORGAN_ID = "ST_ORGAN_ID";

    /**
     * 收件部门代码
     */
    public static final String ST_DEPART_CODE = "ST_DEPART_CODE";

    /**
     * 收件部门名称
     */
    public static final String ST_DEPART_NAME = "ST_DEPART_NAME";

    /**
     * 收件人ID
     */
    public static final String ST_RECEIVOR_ID = "ST_RECEIVOR_ID";

    /**
     * 收件人姓名
     */
    public static final String ST_RECEIVOR_NAME = "ST_RECEIVOR_NAME";

    /**
     * 区域ID
     */
    public static final String ST_AREA_ID = "ST_AREA_ID";

    /**
     * 区域代码
     */
    public static final String ST_AREA_CODE = "ST_AREA_CODE";

    /**
     * 区域名称
     */
    public static final String ST_AREA_NAME = "ST_AREA_NAME";

    /**
     * 申请人
     */
    public static final String ST_APPLY_USER = "ST_APPLY_USER";

    /**
     * 申请人手机
     */
    public static final String ST_APPLY_USER_PHONE = "ST_APPLY_USER_PHONE";

    /**
     * 申请人证件类型
     */
    public static final String NM_IDENTITY_TYPE = "NM_IDENTITY_TYPE";

    /**
     * 申请人证件号
     */
    public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";

    /**
     * 法人姓名
     */
    public static final String ST_LEGAL_NAME = "ST_LEGAL_NAME";

    /**
     * 法人证件号
     */
    public static final String ST_LEGAL_IDCARD = "ST_LEGAL_IDCARD";

    /**
     * 法人联系方法
     */
    public static final String ST_LEGAL_TEL = "ST_LEGAL_TEL";

    /**
     * 办理对象
     */
    public static final String ST_TARGET = "ST_TARGET";

    /**
     * 办理对象类型
     */
    public static final String NM_TARGET_TYPE = "NM_TARGET_TYPE";

    /**
     * 办理对象证件类型
     */
    public static final String NM_LICENSE_TYPE = "NM_LICENSE_TYPE";

    /**
     * 办理对象证件号
     */
    public static final String ST_LICENSE_NO = "ST_LICENSE_NO";

    /**
     * 联系电话
     */
    public static final String ST_TEL = "ST_TEL";

    /**
     * 经营场所
     */
    public static final String ST_BADDRESS = "ST_BADDRESS";

    /**
     * 是否用户取消
     */
    public static final String NM_IS_CANCEL = "NM_IS_CANCEL";

    /**
     * 办件来源终端
     */
    public static final String ST_TSOURCE = "ST_TSOURCE";

    /**
     * 办件来源（是否网上申报）
     */
    public static final String NM_SOURCE = "NM_SOURCE";

    /**
     * 是否即办件
     */
    public static final String NM_IMT = "NM_IMT";

    /**
     * 办件申请状态
     */
    public static final String ST_APPLY_STATE = "ST_APPLY_STATE";

    /**
     * 是否是综合办件
     */
    public static final String NM_UAPPLY = "NM_UAPPLY";

    /**
     * 证照物流递送
     */
    public static final String NM_LOGISTICS = "NM_LOGISTICS";

    /**
     * 是否已删除
     */
    public static final String NM_REMOVED = "NM_REMOVED";

    /**
     * 发证方式
     */
    public static final String ST_CERTIFICATION_TYPE = "ST_CERTIFICATION_TYPE";

    /**
     * 发证状态
     */
    public static final String NM_CERTIFICATION_STATUS = "NM_CERTIFICATION_STATUS";

    /**
     * 领证方式
     */
    public static final String ST_LTYPE = "ST_LTYPE";

    /**
     * 发证日期
     */
    public static final String DT_CERTIFICATION = "DT_CERTIFICATION";

    /**
     * 材料接收人部门ID
     */
    public static final String ST_MF_ORGAN_ID = "ST_MF_ORGAN_ID";

    /**
     * 材料接收人部门
     */
    public static final String ST_MF_ORGAN_NAME = "ST_MF_ORGAN_NAME";

    /**
     * 材料环节接收人ID
     */
    public static final String ST_MF_USER_ID = "ST_MF_USER_ID";

    /**
     * 材料环节接收人姓名
     */
    public static final String ST_MF_USER_NAME = "ST_MF_USER_NAME";

    /**
     * 材料接收操作时间
     */
    public static final String DT_MF_TIME = "DT_MF_TIME";

    /**
     * 材料接收批量标识
     */
    public static final String ST_ID = "ST_ID";

    /**
     * 材料接收状态
     */
    public static final String NM_MF_STATUS = "NM_MF_STATUS";

    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";

    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";

    /**
     * 收件时间
     */
    public static final String DT_RECEIVE = "DT_RECEIVE";

    /**
     * 预审时间
     */
    public static final String DT_AUDIT = "DT_AUDIT";

    /**
     * 最后看件时间
     */
    public static final String DT_LOOK_LAST = "DT_LOOK_LAST";

    /**
     * 看件次数
     */
    public static final String NM_LOOK = "NM_LOOK";

    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";

    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";

    /**
     * 办件状态数
     */
    public static final String NM_STATUS = "NM_STATUS";

    /**
     * 办件标识，0：普通办件；1：寄递办理；2：跨域通办
     */
    public static final String NM_UID = "NM_UID";

    public UacUnionApply() {
    }

    /**
     * 办件信息ID
     */
    @Id
    @Column(name = "ST_APPLY_ID")
    private String stApplyId;

    /**
     * 办件流水号
     */
    @Column(name = "ST_SNO")
    private String stSno;

    /**
     * 外网用户ID
     */
    @Column(name = "ST_USER_ID")
    private String stUserId;

    /**
     * 统一联合编码
     */
    @Column(name = "ST_APPLY_NO")
    private String stApplyNo;

    /**
     * 综合事项ID
     */
    @Column(name = "ST_CITEM_ID")
    private String stCitemId;

    /**
     * 综合事项编码
     */
    @Column(name = "ST_CITEM_CODE")
    private String stCitemCode;

    /**
     * 收件部门ID
     */
    @Column(name = "ST_ORGAN_ID")
    private String stOrganId;

    /**
     * 收件部门代码
     */
    @Column(name = "ST_DEPART_CODE")
    private String stDepartCode;

    /**
     * 收件部门名称
     */
    @Column(name = "ST_DEPART_NAME")
    private String stDepartName;

    /**
     * 综合事项名称
     */
    @Column(name = "ST_CITEM_NAME")
    private String stCitemName;

    /**
     * 综合事项名称
     */
    @Column(name = "ST_TRANSACT_NAME")
    private String stTransactName;

    /**
     * 收件人ID
     */
    @Column(name = "ST_RECEIVOR_ID")
    private String stReceivorId;

    /**
     * 收件人姓名
     */
    @Column(name = "ST_RECEIVOR_NAME")
    private String stReceivorName;

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
     * 区域名称
     */
    @Column(name = "ST_AREA_NAME")
    private String stAreaName;

    /**
     * 申请人
     */
    @Column(name = "ST_APPLY_USER")
    private String stApplyUser;

    /**
     * 申请人手机
     */
    @Column(name = "ST_APPLY_USER_PHONE")
    private String stApplyUserPhone;

    /**
     * 申请人证件类型
     */
    @Column(name = "NM_IDENTITY_TYPE")
    private BigDecimal nmIdentityType;

    /**
     * 申请人证件号
     */
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;

    /**
     * 法人姓名
     */
    @Column(name = "ST_LEGAL_NAME")
    private String stLegalName;

    /**
     * 法人证件号
     */
    @Column(name = "ST_LEGAL_IDCARD")
    private String stLegalIdcard;

    /**
     * 法人联系方法
     */
    @Column(name = "ST_LEGAL_TEL")
    private String stLegalTel;

    /**
     * 办理对象
     */
    @Column(name = "ST_TARGET")
    private String stTarget;

    /**
     * 办理对象类型
     */
    @Column(name = "NM_TARGET_TYPE")
    private String nmTargetType;

    /**
     * 办理对象证件类型
     */
    @Column(name = "NM_LICENSE_TYPE")
    private String nmLicenseType;

    /**
     * 办理对象证件号
     */
    @Column(name = "ST_LICENSE_NO")
    private String stLicenseNo;

    /**
     * 联系电话
     */
    @Column(name = "ST_TEL")
    private String stTel;

    /**
     * 经营场所
     */
    @Column(name = "ST_BADDRESS")
    private String stBaddress;

    /**
     * 是否用户取消
     */
    @Column(name = "NM_IS_CANCEL")
    private BigDecimal nmIsCancel;

    /**
     * 办件来源终端
     */
    @Column(name = "ST_TSOURCE")
    private String stTsource;

    /**
     * 办件来源（是否网上申报）
     */
    @Column(name = "NM_SOURCE")
    private BigDecimal nmSource;

    /**
     * 是否即办件
     */
    @Column(name = "NM_IMT")
    private BigDecimal nmImt;

    /**
     * 办件申请状态
     */
    @Column(name = "ST_APPLY_STATE")
    private String stApplyState;

    /**
     * 是否是综合办件
     */
    @Column(name = "NM_UAPPLY")
    private BigDecimal nmUapply;

    /**
     * 证照物流递送
     */
    @Column(name = "NM_LOGISTICS")
    private BigDecimal nmLogistics;

    /**
     * 是否已删除
     */
    @Column(name = "NM_REMOVED")
    private BigDecimal nmRemoved;

    /**
     * 发证方式
     */
    @Column(name = "ST_CERTIFICATION_TYPE")
    private String stCertificationType;

    /**
     * 发证状态
     */
    @Column(name = "NM_CERTIFICATION_STATUS")
    private BigDecimal nmCertificationStatus;

    /**
     * 领证方式
     */
    @Column(name = "ST_LTYPE")
    private String stLtype;

    /**
     * 发证日期
     */
    @Column(name = "DT_CERTIFICATION")
    private Timestamp dtCertification;

    /**
     * 材料接收人部门ID
     */
    @Column(name = "ST_MF_ORGAN_ID")
    private String stMfOrganId;

    /**
     * 材料接收人部门
     */
    @Column(name = "ST_MF_ORGAN_NAME")
    private String stMfOrganName;

    /**
     * 材料环节接收人ID
     */
    @Column(name = "ST_MF_USER_ID")
    private String stMfUserId;

    /**
     * 材料环节接收人姓名
     */
    @Column(name = "ST_MF_USER_NAME")
    private String stMfUserName;

    /**
     * 材料接收操作时间
     */
    @Column(name = "DT_MF_TIME")
    private Timestamp dtMfTime;

    /**
     * 材料接收批量标识
     */
    @Column(name = "ST_ID")
    private String stId;

    /**
     * 材料接收状态
     */
    @Column(name = "NM_MF_STATUS")
    private BigDecimal nmMfStatus;

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
     * 收件时间
     */
    @Column(name = "DT_RECEIVE")
    private Timestamp dtReceive;

    /**
     * 预审时间
     */
    @Column(name = "DT_AUDIT")
    private Timestamp dtAudit;

    /**
     * 最后看件时间
     */
    @Column(name = "DT_LOOK_LAST")
    private Timestamp dtLookLast;

    /**
     * 看件次数
     */
    @Column(name = "NM_LOOK")
    private BigDecimal nmLook;

    /**
     *办件状态数
     */
    @Column(name = "NM_STATUS")
    private BigDecimal nmStatus;

    /**
     * 办件标识
     */
    @Column(name = "NM_UID")
    private BigDecimal nmUid;

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
     * 办件流水号
     */
    public String getStSno() {
        return this.stSno;
    }

    /**
     * 办件流水号
     */
    public String stSno2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSno);
    }

    /**
     * 办件流水号
     */
    public void setStSno(String stSno) {
        stSno = StringUtil.substringBySize(stSno, 50, "GB18030");
        this.stSno = stSno;
    }

    /**
     * 外网用户ID
     */
    public String getStUserId() {
        return this.stUserId;
    }

    /**
     * 外网用户ID
     */
    public String stUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stUserId);
    }

    /**
     * 外网用户ID
     */
    public void setStUserId(String stUserId) {
        stUserId = StringUtil.substringBySize(stUserId, 50, "GB18030");
        this.stUserId = stUserId;
    }

    /**
     * 统一联合编码
     */
    public String getStApplyNo() {
        return this.stApplyNo;
    }

    /**
     * 统一联合编码
     */
    public String stApplyNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyNo);
    }

    /**
     * 统一联合编码
     */
    public void setStApplyNo(String stApplyNo) {
        stApplyNo = StringUtil.substringBySize(stApplyNo, 50, "GB18030");
        this.stApplyNo = stApplyNo;
    }

    /**
     * 综合事项ID
     */
    public String getStCitemId() {
        return this.stCitemId;
    }

    /**
     * 综合事项ID
     */
    public String stCitemId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemId);
    }

    /**
     * 综合事项ID
     */
    public void setStCitemId(String stCitemId) {
        stCitemId = StringUtil.substringBySize(stCitemId, 50, "GB18030");
        this.stCitemId = stCitemId;
    }

    /**
     * 综合事项编码
     */
    public String getStCitemCode() {
        return this.stCitemCode;
    }

    /**
     * 综合事项编码
     */
    public String stCitemCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemCode);
    }

    /**
     * 综合事项编码
     */
    public void setStCitemCode(String stCitemCode) {
        stCitemCode = StringUtil.substringBySize(stCitemCode, 50, "GB18030");
        this.stCitemCode = stCitemCode;
    }

    /**
     * 收件部门ID
     */
    public String getStOrganId() {
        return this.stOrganId;
    }

    /**
     * 收件部门ID
     */
    public String stOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOrganId);
    }

    /**
     * 收件部门ID
     */
    public void setStOrganId(String stOrganId) {
        stOrganId = StringUtil.substringBySize(stOrganId, 50, "GB18030");
        this.stOrganId = stOrganId;
    }

    /**
     * 收件部门代码
     */
    public String getStDepartCode() {
        return this.stDepartCode;
    }

    /**
     * 收件部门代码
     */
    public String stDepartCode2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartCode);
    }

    /**
     * 收件部门代码
     */
    public void setStDepartCode(String stDepartCode) {
        stDepartCode = StringUtil.substringBySize(stDepartCode, 50, "GB18030");
        this.stDepartCode = stDepartCode;
    }

    /**
     * 收件部门名称
     */
    public String getStDepartName() {
        return this.stDepartName;
    }

    /**
     * 收件部门名称
     */
    public String stDepartName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDepartName);
    }

    /**
     * 收件部门名称
     */
    public void setStDepartName(String stDepartName) {
        stDepartName = StringUtil.substringBySize(stDepartName, 50, "GB18030");
        this.stDepartName = stDepartName;
    }

    /**
     * 综合事项名称
     */
    public String getStCitemName() {
        return this.stCitemName;
    }

    /**
     * 综合事项名称
     */
    public String stCitemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCitemName);
    }

    /**
     * 综合事项名称
     */
    public void setStCitemName(String stCitemName) {
        stCitemName = StringUtil.substringBySize(stCitemName, 200, "GB18030");
        this.stCitemName = stCitemName;
    }

    /**
     * 情形
     */
    public String getStTransactName() {
        return this.stTransactName;
    }

    /**
     * 情形
     */
    public String stTransactName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTransactName);
    }

    /**
     * 情形
     */
    public void setStTransactName(String stTransactName) {
        stTransactName = StringUtil.substringBySize(stTransactName, 500, "GB18030");
        this.stTransactName = stTransactName;
    }

    /**
     * 收件人ID
     */
    public String getStReceivorId() {
        return this.stReceivorId;
    }

    /**
     * 收件人ID
     */
    public String stReceivorId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceivorId);
    }

    /**
     * 收件人ID
     */
    public void setStReceivorId(String stReceivorId) {
        stReceivorId = StringUtil.substringBySize(stReceivorId, 50, "GB18030");
        this.stReceivorId = stReceivorId;
    }

    /**
     * 收件人姓名
     */
    public String getStReceivorName() {
        return this.stReceivorName;
    }

    /**
     * 收件人姓名
     */
    public String stReceivorName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stReceivorName);
    }

    /**
     * 收件人姓名
     */
    public void setStReceivorName(String stReceivorName) {
        stReceivorName = StringUtil.substringBySize(stReceivorName, 50, "GB18030");
        this.stReceivorName = stReceivorName;
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
     * 区域名称
     */
    public String getStAreaName() {
        return this.stAreaName;
    }

    /**
     * 区域名称
     */
    public String stAreaName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAreaName);
    }

    /**
     * 区域名称
     */
    public void setStAreaName(String stAreaName) {
        stAreaName = StringUtil.substringBySize(stAreaName, 50, "GB18030");
        this.stAreaName = stAreaName;
    }

    /**
     * 申请人
     */
    public String getStApplyUser() {
        return this.stApplyUser;
    }

    /**
     * 申请人
     */
    public String stApplyUser2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUser);
    }

    /**
     * 申请人
     */
    public void setStApplyUser(String stApplyUser) {
        stApplyUser = StringUtil.substringBySize(stApplyUser, 50, "GB18030");
        this.stApplyUser = stApplyUser;
    }

    /**
     * 申请人手机
     */
    public String getStApplyUserPhone() {
        return this.stApplyUserPhone;
    }

    /**
     * 申请人手机
     */
    public String stApplyUserPhone2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyUserPhone);
    }

    /**
     * 申请人手机
     */
    public void setStApplyUserPhone(String stApplyUserPhone) {
        stApplyUserPhone = StringUtil.substringBySize(stApplyUserPhone, 20, "GB18030");
        this.stApplyUserPhone = stApplyUserPhone;
    }

    /**
     * 申请人证件类型
     */
    public BigDecimal getNmIdentityType() {
        return this.nmIdentityType;
    }

    /**
     * 申请人证件类型
     */
    public String nmIdentityType2Html(int precision) {
        if (this.nmIdentityType == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIdentityType);
        }
    }

    /**
     * 申请人证件类型
     */
    public void setNmIdentityType(BigDecimal nmIdentityType) {
        this.nmIdentityType = nmIdentityType;
    }

    /**
     * 申请人证件号
     */
    public String getStIdentityNo() {
        return this.stIdentityNo;
    }

    /**
     * 申请人证件号
     */
    public String stIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdentityNo);
    }

    /**
     * 申请人证件号
     */
    public void setStIdentityNo(String stIdentityNo) {
        stIdentityNo = StringUtil.substringBySize(stIdentityNo, 50, "GB18030");
        this.stIdentityNo = stIdentityNo;
    }

    /**
     * 法人姓名
     */
    public String getStLegalName() {
        return this.stLegalName;
    }

    /**
     * 法人姓名
     */
    public String stLegalName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalName);
    }

    /**
     * 法人姓名
     */
    public void setStLegalName(String stLegalName) {
        stLegalName = StringUtil.substringBySize(stLegalName, 50, "GB18030");
        this.stLegalName = stLegalName;
    }

    /**
     * 法人证件号
     */
    public String getStLegalIdcard() {
        return this.stLegalIdcard;
    }

    /**
     * 法人证件号
     */
    public String stLegalIdcard2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalIdcard);
    }

    /**
     * 法人证件号
     */
    public void setStLegalIdcard(String stLegalIdcard) {
        stLegalIdcard = StringUtil.substringBySize(stLegalIdcard, 50, "GB18030");
        this.stLegalIdcard = stLegalIdcard;
    }

    /**
     * 法人联系方法
     */
    public String getStLegalTel() {
        return this.stLegalTel;
    }

    /**
     * 法人联系方法
     */
    public String stLegalTel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLegalTel);
    }

    /**
     * 法人联系方法
     */
    public void setStLegalTel(String stLegalTel) {
        stLegalTel = StringUtil.substringBySize(stLegalTel, 50, "GB18030");
        this.stLegalTel = stLegalTel;
    }

    /**
     * 办理对象
     */
    public String getStTarget() {
        return this.stTarget;
    }

    /**
     * 办理对象
     */
    public String stTarget2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTarget);
    }

    /**
     * 办理对象
     */
    public void setStTarget(String stTarget) {
        stTarget = StringUtil.substringBySize(stTarget, 50, "GB18030");
        this.stTarget = stTarget;
    }

    /**
     * 办理对象类型
     */
    public String getNmTargetType() {
        return this.nmTargetType;
    }

    /**
     * 办理对象类型
     */
    public String nmTargetType2Html() {
        return StringHelper.replaceHTMLSymbol(this.nmTargetType);
    }

    /**
     * 办理对象类型
     */
    public void setNmTargetType(String nmTargetType) {
        nmTargetType = StringUtil.substringBySize(nmTargetType, 20, "GB18030");
        this.nmTargetType = nmTargetType;
    }

    /**
     * 办理对象证件类型
     */
    public String getNmLicenseType() {
        return this.nmLicenseType;
    }

    /**
     * 办理对象证件类型
     */
    public String nmLicenseType2Html() {
        return StringHelper.replaceHTMLSymbol(this.nmLicenseType);
    }

    /**
     * 办理对象证件类型
     */
    public void setNmLicenseType(String nmLicenseType) {
        nmLicenseType = StringUtil.substringBySize(nmLicenseType, 20, "GB18030");
        this.nmLicenseType = nmLicenseType;
    }

    /**
     * 办理对象证件号
     */
    public String getStLicenseNo() {
        return this.stLicenseNo;
    }

    /**
     * 办理对象证件号
     */
    public String stLicenseNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLicenseNo);
    }

    /**
     * 办理对象证件号
     */
    public void setStLicenseNo(String stLicenseNo) {
        stLicenseNo = StringUtil.substringBySize(stLicenseNo, 50, "GB18030");
        this.stLicenseNo = stLicenseNo;
    }

    /**
     * 联系电话
     */
    public String getStTel() {
        return this.stTel;
    }

    /**
     * 联系电话
     */
    public String stTel2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTel);
    }

    /**
     * 联系电话
     */
    public void setStTel(String stTel) {
        stTel = StringUtil.substringBySize(stTel, 50, "GB18030");
        this.stTel = stTel;
    }

    /**
     * 经营场所
     */
    public String getStBaddress() {
        return this.stBaddress;
    }

    /**
     * 经营场所
     */
    public String stBaddress2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBaddress);
    }

    /**
     * 经营场所
     */
    public void setStBaddress(String stBaddress) {
        stBaddress = StringUtil.substringBySize(stBaddress, 100, "GB18030");
        this.stBaddress = stBaddress;
    }

    /**
     * 是否用户取消
     */
    public BigDecimal getNmIsCancel() {
        return this.nmIsCancel;
    }

    /**
     * 是否用户取消
     */
    public String nmIsCancel2Html(int precision) {
        if (this.nmIsCancel == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmIsCancel);
        }
    }

    /**
     * 是否用户取消
     */
    public void setNmIsCancel(BigDecimal nmIsCancel) {
        this.nmIsCancel = nmIsCancel;
    }

    /**
     * 办件来源终端
     */
    public String getStTsource() {
        return this.stTsource;
    }

    /**
     * 办件来源终端
     */
    public String stTsource2Html() {
        return StringHelper.replaceHTMLSymbol(this.stTsource);
    }

    /**
     * 办件来源终端
     */
    public void setStTsource(String stTsource) {
        stTsource = StringUtil.substringBySize(stTsource, 20, "GB18030");
        this.stTsource = stTsource;
    }

    /**
     * 办件来源（是否网上申报）
     */
    public BigDecimal getNmSource() {
        return this.nmSource;
    }

    /**
     * 办件来源（是否网上申报）
     */
    public String nmSource2Html(int precision) {
        if (this.nmSource == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmSource);
        }
    }

    /**
     * 办件来源（是否网上申报）
     */
    public void setNmSource(BigDecimal nmSource) {
        this.nmSource = nmSource;
    }

    /**
     * 是否即办件
     */
    public BigDecimal getNmImt() {
        return this.nmImt;
    }

    /**
     * 是否即办件
     */
    public String nmImt2Html(int precision) {
        if (this.nmImt == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmImt);
        }
    }

    /**
     * 是否即办件
     */
    public void setNmImt(BigDecimal nmImt) {
        this.nmImt = nmImt;
    }

    /**
     * 办件申请状态
     */
    public String getStApplyState() {
        return this.stApplyState;
    }

    /**
     * 办件申请状态
     */
    public String stApplyState2Html() {
        return StringHelper.replaceHTMLSymbol(this.stApplyState);
    }

    /**
     * 办件申请状态
     */
    public void setStApplyState(String stApplyState) {
        stApplyState = StringUtil.substringBySize(stApplyState, 20, "GB18030");
        this.stApplyState = stApplyState;
    }

    /**
     * 是否是综合办件
     */
    public BigDecimal getNmUapply() {
        return this.nmUapply;
    }

    /**
     * 是否是综合办件
     */
    public String nmUapply2Html(int precision) {
        if (this.nmUapply == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmUapply);
        }
    }

    /**
     * 是否是综合办件
     */
    public void setNmUapply(BigDecimal nmUapply) {
        this.nmUapply = nmUapply;
    }

    /**
     * 证照物流递送
     */
    public BigDecimal getNmLogistics() {
        return this.nmLogistics;
    }

    /**
     * 证照物流递送
     */
    public String nmLogistics2Html(int precision) {
        if (this.nmLogistics == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLogistics);
        }
    }

    /**
     * 证照物流递送
     */
    public void setNmLogistics(BigDecimal nmLogistics) {
        this.nmLogistics = nmLogistics;
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
     * 发证方式
     */
    public String getStCertificationType() {
        return this.stCertificationType;
    }

    /**
     * 发证方式
     */
    public String stCertificationType2Html() {
        return StringHelper.replaceHTMLSymbol(this.stCertificationType);
    }

    /**
     * 发证方式
     */
    public void setStCertificationType(String stCertificationType) {
        stCertificationType = StringUtil.substringBySize(stCertificationType, 50, "GB18030");
        this.stCertificationType = stCertificationType;
    }

    /**
     * 发证状态
     */
    public BigDecimal getNmCertificationStatus() {
        return this.nmCertificationStatus;
    }

    /**
     * 发证状态
     */
    public String nmCertificationStatus2Html(int precision) {
        if (this.nmCertificationStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmCertificationStatus);
        }
    }

    /**
     * 发证状态
     */
    public void setNmCertificationStatus(BigDecimal nmCertificationStatus) {
        this.nmCertificationStatus = nmCertificationStatus;
    }

    /**
     * 领证方式
     */
    public String getStLtype() {
        return this.stLtype;
    }

    /**
     * 领证方式
     */
    public String stLtype2Html() {
        return StringHelper.replaceHTMLSymbol(this.stLtype);
    }

    /**
     * 领证方式
     */
    public void setStLtype(String stLtype) {
        stLtype = StringUtil.substringBySize(stLtype, 50, "GB18030");
        this.stLtype = stLtype;
    }

    /**
     * 发证日期
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtCertification() {
        return this.dtCertification;
    }

    /**
     * 发证日期
     */
    public String dtCertification2Html(String pattern) {
        if (this.dtCertification == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtCertification);
        }
    }

    /**
     * 发证日期
     */
    public void setDtCertification(Timestamp dtCertification) {
        this.dtCertification = dtCertification;
    }

    /**
     * 材料接收人部门ID
     */
    public String getStMfOrganId() {
        return this.stMfOrganId;
    }

    /**
     * 材料接收人部门ID
     */
    public String stMfOrganId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMfOrganId);
    }

    /**
     * 材料接收人部门ID
     */
    public void setStMfOrganId(String stMfOrganId) {
        stMfOrganId = StringUtil.substringBySize(stMfOrganId, 50, "GB18030");
        this.stMfOrganId = stMfOrganId;
    }

    /**
     * 材料接收人部门
     */
    public String getStMfOrganName() {
        return this.stMfOrganName;
    }

    /**
     * 材料接收人部门
     */
    public String stMfOrganName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMfOrganName);
    }

    /**
     * 材料接收人部门
     */
    public void setStMfOrganName(String stMfOrganName) {
        stMfOrganName = StringUtil.substringBySize(stMfOrganName, 50, "GB18030");
        this.stMfOrganName = stMfOrganName;
    }

    /**
     * 材料环节接收人ID
     */
    public String getStMfUserId() {
        return this.stMfUserId;
    }

    /**
     * 材料环节接收人ID
     */
    public String stMfUserId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMfUserId);
    }

    /**
     * 材料环节接收人ID
     */
    public void setStMfUserId(String stMfUserId) {
        stMfUserId = StringUtil.substringBySize(stMfUserId, 50, "GB18030");
        this.stMfUserId = stMfUserId;
    }

    /**
     * 材料环节接收人姓名
     */
    public String getStMfUserName() {
        return this.stMfUserName;
    }

    /**
     * 材料环节接收人姓名
     */
    public String stMfUserName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMfUserName);
    }

    /**
     * 材料环节接收人姓名
     */
    public void setStMfUserName(String stMfUserName) {
        stMfUserName = StringUtil.substringBySize(stMfUserName, 50, "GB18030");
        this.stMfUserName = stMfUserName;
    }

    /**
     * 材料接收操作时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtMfTime() {
        return this.dtMfTime;
    }

    /**
     * 材料接收操作时间
     */
    public String dtMfTime2Html(String pattern) {
        if (this.dtMfTime == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtMfTime);
        }
    }

    /**
     * 材料接收操作时间
     */
    public void setDtMfTime(Timestamp dtMfTime) {
        this.dtMfTime = dtMfTime;
    }

    /**
     * 材料接收批量标识
     */
    public String getStId() {
        return this.stId;
    }

    /**
     * 材料接收批量标识
     */
    public String stId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stId);
    }

    /**
     * 材料接收批量标识
     */
    public void setStId(String stId) {
        stId = StringUtil.substringBySize(stId, 50, "GB18030");
        this.stId = stId;
    }

    /**
     * 材料接收状态
     */
    public BigDecimal getNmMfStatus() {
        return this.nmMfStatus;
    }

    /**
     * 材料接收状态
     */
    public String nmMfStatus2Html(int precision) {
        if (this.nmMfStatus == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmMfStatus);
        }
    }

    /**
     * 材料接收状态
     */
    public void setNmMfStatus(BigDecimal nmMfStatus) {
        this.nmMfStatus = nmMfStatus;
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
     * 收件时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtReceive() {
        return this.dtReceive;
    }

    /**
     * 收件时间
     */
    public String dtReceive2Html(String pattern) {
        if (this.dtReceive == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtReceive);
        }
    }

    /**
     * 收件时间
     */
    public void setDtReceive(Timestamp dtReceive) {
        this.dtReceive = dtReceive;
    }

    /**
     * 预审时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtAudit() {
        return this.dtAudit;
    }

    /**
     * 预审时间
     */
    public String dtAudit2Html(String pattern) {
        if (this.dtAudit == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtAudit);
        }
    }

    /**
     * 预审时间
     */
    public void setDtAudit(Timestamp dtAudit) {
        this.dtAudit = dtAudit;
    }

    /**
     * 最后看件时间
     */
    @XmlJavaTypeAdapter(TimestampXmlAdapter.class)
    public Timestamp getDtLookLast() {
        return this.dtLookLast;
    }

    /**
     * 最后看件时间
     */
    public String dtLookLast2Html(String pattern) {
        if (this.dtLookLast == null) {
            return "";
        } else {
            if (pattern == null) {
                pattern = "yyyy年MM月dd日 HH时mm分";
            }
            return new SimpleDateFormat(pattern).format(this.dtLookLast);
        }
    }

    /**
     * 最后看件时间
     */
    public void setDtLookLast(Timestamp dtLookLast) {
        this.dtLookLast = dtLookLast;
    }

    /**
     * 看件次数
     */
    public BigDecimal getNmLook() {
        return this.nmLook;
    }

    /**
     * 看件次数
     */
    public String nmLook2Html(int precision) {
        if (this.nmLook == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmLook);
        }
    }

    /**
     * 看件次数
     */
    public void setNmLook(BigDecimal nmLook) {
        this.nmLook = nmLook;
    }

    /**
     * 办件状态数
     */
    public BigDecimal getNmStatus() {
        return this.nmStatus;
    }

    /**
     * 办件状态数
     */
    public String NmStatusHtml(int precision) {
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
     * 办件状态数
     */
    public void setNmStatus(BigDecimal nmStatus) {
        this.nmStatus = nmStatus;
    }

    /**
     * 办件标识
     */
    public BigDecimal getNmUid() {
        return this.nmUid;
    }

    /**
     * 办件标识
     */
    public String NmUidHtml(int precision) {
        if (this.nmUid == null) {
            return "";
        } else {
            String pattern = "0";
            if (precision > 0) {
                pattern += ".";
                for (int i = 0; i < precision; i++) {
                    pattern += "0";
                }
            }
            return new DecimalFormat(pattern).format(this.nmUid);
        }
    }

    /**
     * 办件标识
     */
    public void setNmUid(BigDecimal nmUid) {
        this.nmUid = nmUid;
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