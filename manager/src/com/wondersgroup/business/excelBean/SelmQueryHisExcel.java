package com.wondersgroup.business.excelBean;
		
import java.io.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.*;
import org.apache.commons.lang.builder.*;
import org.jeecgframework.poi.excel.annotation.Excel;

import coral.base.util.StringUtil;
import wfc.facility.tool.autocode.*;
import wfc.service.util.*;

/**
 * 工作台模块使用历史记录
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_QUERY_HIS")
public class SelmQueryHisExcel implements Serializable {
    
    /**
     * 工作台模块使用历史记录
     */
    public static final String SELM_QUERY_HIS = "SELM_QUERY_HIS";
    
    /**
     * 历史ID
     */
    public static final String ST_QUERY_HIS_ID = "ST_QUERY_HIS_ID";
    
    /**
     * 设备ID
     */
    public static final String ST_MACHINE_ID = "ST_MACHINE_ID";
    
    /**
     * 模块名称
     */
    public static final String ST_MODULE_NAME = "ST_MODULE_NAME";
    
    /**
     * 操作名称
     */
    public static final String ST_MODULE_OP = "ST_MODULE_OP";
    
    /**
     * 姓名
     */
    public static final String ST_NAME = "ST_NAME";
    
    /**
     * 证件号
     */
    public static final String ST_IDENTITY_NO = "ST_IDENTITY_NO";
    
    /**
     * 手机号
     */
    public static final String ST_MOBILE = "ST_MOBILE";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 附件ID1
     */
    public static final String ST_ATTACH_ID1 = "ST_ATTACH_ID1";
    
    /**
     * 附件ID2
     */
    public static final String ST_ATTACH_ID2 = "ST_ATTACH_ID2";
    
    /**
     * 附件ID3
     */
    public static final String ST_ATTACH_ID3 = "ST_ATTACH_ID3";
    
    /**
     * 附件ID4
     */
    public static final String ST_ATTACH_ID4 = "ST_ATTACH_ID4";
    
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
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 业务唯一标识(统一审批编号)
     */
    public static final String ST_BUSINESS_NO = "ST_BUSINESS_NO";
    
    /**
     * 业务办理/查询结果数据ID
     */
    public static final String ST_SUBMIT_DATA_ID = "ST_SUBMIT_DATA_ID";
    
    /**
     * 备注
     */
    public static final String ST_DESC = "ST_DESC";
    
    /**
     * 辅助人员
     */
    public static final String ST_ASSIST_ID = "ST_ASSIST_ID";
    
    /**
     * 操作结果
     */
    public static final String ST_OP_RESULT = "ST_OP_RESULT";
    
    public SelmQueryHisExcel() {
    }
    
    /**
     * 历史ID
     */
    @Id
    @Column(name = "ST_QUERY_HIS_ID")
    private String stQueryHisId;
    
    /**
     * 设备ID
     */
    @Column(name = "ST_MACHINE_ID")
    private String stMachineId;
    /**
     * 事项名称
     */
    @Excel(name="事项名称")
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    
    /**
     * 模块名称
     */
    @Excel(name="模块名称")
    @Column(name = "ST_MODULE_NAME")
    private String stModuleName;
    
    /**
     * 操作名称
     */
    @Excel(name="操作名称")
    @Column(name = "ST_MODULE_OP")
    private String stModuleOp;
    
    /**
     * 姓名
     */
    @Excel(name="姓名")
    @Column(name = "ST_NAME")
    private String stName;
    
    /**
     * 证件号
     */
    @Excel(name="证件号")
    @Column(name = "ST_IDENTITY_NO")
    private String stIdentityNo;
    
    /**
     * 手机号
     */
   // @Excel(name="手机号")
    @Column(name = "ST_MOBILE")
    private String stMobile;
    
    /**
     * 创建时间
     */
    @Excel(name="创建时间")
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 附件ID1
     */
    @Column(name = "ST_ATTACH_ID1")
    private String stAttachId1;
    
    /**
     * 附件ID2
     */
    @Column(name = "ST_ATTACH_ID2")
    private String stAttachId2;
    
    /**
     * 附件ID3
     */
    @Column(name = "ST_ATTACH_ID3")
    private String stAttachId3;
    
    /**
     * 附件ID4
     */
    @Column(name = "ST_ATTACH_ID4")
    private String stAttachId4;
    
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
    @Excel(name="所属区")
    @Column(name = "ST_EXT3")
    private String stExt3;
    
    /**
     * 扩展字段4
     */
    @Excel(name="所属街道")
    @Column(name = "ST_EXT4")
    private String stExt4;
    
    /**
     * 扩展字段5
     */
    @Column(name = "ST_EXT5")
    private String stExt5;
    
    
    /**
     * 业务唯一标识(统一审批编号)
     */
    @Excel(name="业务唯一标识(统一审批编号)")
    @Column(name = "ST_BUSINESS_NO")
    private String stBusinessNo;
    
    /**
     * 业务办理/查询结果数据ID
     */
    @Column(name = "ST_SUBMIT_DATA_ID")
    private String stSubmitDataId;
    
    /**
     * 备注
     */
    @Column(name = "ST_DESC")
    private String stDesc;
    
    /**
     * 操作结果
     */
    @Excel(name="操作结果")
    @Column(name = "ST_OP_RESULT")
    private String stOpResult;
    
    /**
     * 辅助人员
     */
    @Excel(name="辅助人员")
    @Column(name = "ST_ASSIST_ID")
    private String stAssistId;
    
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
     * 设备ID
     */
    public String getStMachineId() {
        return this.stMachineId;
    }
    
    /**
     * 设备ID
     */
    public String stMachineId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMachineId);
    }

    /**
     * 设备ID
     */
    public void setStMachineId(String stMachineId) {
        stMachineId = StringUtil.substringBySize(stMachineId, 50, "GB18030");
        this.stMachineId = stMachineId;
    }
    
	/**
     * 模块名称
     */
    public String getStModuleName() {
        return this.stModuleName;
    }
    
    /**
     * 模块名称
     */
    public String stModuleName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModuleName);
    }

    /**
     * 模块名称
     */
    public void setStModuleName(String stModuleName) {
        stModuleName = StringUtil.substringBySize(stModuleName, 50, "GB18030");
        this.stModuleName = stModuleName;
    }
    
	/**
     * 操作名称
     */
    public String getStModuleOp() {
        return this.stModuleOp;
    }
    
    /**
     * 操作名称
     */
    public String stModuleOp2Html() {
        return StringHelper.replaceHTMLSymbol(this.stModuleOp);
    }

    /**
     * 操作名称
     */
    public void setStModuleOp(String stModuleOp) {
        stModuleOp = StringUtil.substringBySize(stModuleOp, 50, "GB18030");
        this.stModuleOp = stModuleOp;
    }
    
	/**
     * 姓名
     */
    public String getStName() {
        return this.stName;
    }
    
    /**
     * 姓名
     */
    public String stName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stName);
    }

    /**
     * 姓名
     */
    public void setStName(String stName) {
        stName = StringUtil.substringBySize(stName, 50, "GB18030");
        this.stName = stName;
    }
    
	/**
     * 证件号
     */
    public String getStIdentityNo() {
        return this.stIdentityNo;
    }
    
    /**
     * 证件号
     */
    public String stIdentityNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stIdentityNo);
    }

    /**
     * 证件号
     */
    public void setStIdentityNo(String stIdentityNo) {
        stIdentityNo = StringUtil.substringBySize(stIdentityNo, 50, "GB18030");
        this.stIdentityNo = stIdentityNo;
    }
    
	/**
     * 手机号
     */
    public String getStMobile() {
        return this.stMobile;
    }
    
    /**
     * 手机号
     */
    public String stMobile2Html() {
        return StringHelper.replaceHTMLSymbol(this.stMobile);
    }

    /**
     * 手机号
     */
    public void setStMobile(String stMobile) {
        stMobile = StringUtil.substringBySize(stMobile, 50, "GB18030");
        this.stMobile = stMobile;
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
     * 附件ID1
     */
    public String getStAttachId1() {
        return this.stAttachId1;
    }
    
    /**
     * 附件ID1
     */
    public String stAttachId12Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId1);
    }

    /**
     * 附件ID1
     */
    public void setStAttachId1(String stAttachId1) {
        stAttachId1 = StringUtil.substringBySize(stAttachId1, 50, "GB18030");
        this.stAttachId1 = stAttachId1;
    }
    
	/**
     * 附件ID2
     */
    public String getStAttachId2() {
        return this.stAttachId2;
    }
    
    /**
     * 附件ID2
     */
    public String stAttachId22Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId2);
    }

    /**
     * 附件ID2
     */
    public void setStAttachId2(String stAttachId2) {
        stAttachId2 = StringUtil.substringBySize(stAttachId2, 50, "GB18030");
        this.stAttachId2 = stAttachId2;
    }
    
	/**
     * 附件ID3
     */
    public String getStAttachId3() {
        return this.stAttachId3;
    }
    
    /**
     * 附件ID3
     */
    public String stAttachId32Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId3);
    }

    /**
     * 附件ID3
     */
    public void setStAttachId3(String stAttachId3) {
        stAttachId3 = StringUtil.substringBySize(stAttachId3, 50, "GB18030");
        this.stAttachId3 = stAttachId3;
    }
    
	/**
     * 附件ID4
     */
    public String getStAttachId4() {
        return this.stAttachId4;
    }
    
    /**
     * 附件ID4
     */
    public String stAttachId42Html() {
        return StringHelper.replaceHTMLSymbol(this.stAttachId4);
    }

    /**
     * 附件ID4
     */
    public void setStAttachId4(String stAttachId4) {
        stAttachId4 = StringUtil.substringBySize(stAttachId4, 50, "GB18030");
        this.stAttachId4 = stAttachId4;
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
        stExt1 = StringUtil.substringBySize(stExt1, 100, "GB18030");
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
        stExt2 = StringUtil.substringBySize(stExt2, 100, "GB18030");
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
        stExt3 = StringUtil.substringBySize(stExt3, 100, "GB18030");
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
        stExt4 = StringUtil.substringBySize(stExt4, 200, "GB18030");
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
        stExt5 = StringUtil.substringBySize(stExt5, 300, "GB18030");
        this.stExt5 = stExt5;
    }
    
	/**
     * 事项名称
     */
    public String getStItemName() {
        return this.stItemName;
    }
    
    /**
     * 事项名称
     */
    public String stItemName2Html() {
        return StringHelper.replaceHTMLSymbol(this.stItemName);
    }

    /**
     * 事项名称
     */
    public void setStItemName(String stItemName) {
        stItemName = StringUtil.substringBySize(stItemName, 200, "GB18030");
        this.stItemName = stItemName;
    }
    
	/**
     * 业务唯一标识(统一审批编号)
     */
    public String getStBusinessNo() {
        return this.stBusinessNo;
    }
    
    /**
     * 业务唯一标识(统一审批编号)
     */
    public String stBusinessNo2Html() {
        return StringHelper.replaceHTMLSymbol(this.stBusinessNo);
    }

    /**
     * 业务唯一标识(统一审批编号)
     */
    public void setStBusinessNo(String stBusinessNo) {
        stBusinessNo = StringUtil.substringBySize(stBusinessNo, 50, "GB18030");
        this.stBusinessNo = stBusinessNo;
    }
    
	/**
     * 业务办理/查询结果数据ID
     */
    public String getStSubmitDataId() {
        return this.stSubmitDataId;
    }
    
    /**
     * 业务办理/查询结果数据ID
     */
    public String stSubmitDataId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stSubmitDataId);
    }

    /**
     * 业务办理/查询结果数据ID
     */
    public void setStSubmitDataId(String stSubmitDataId) {
        stSubmitDataId = StringUtil.substringBySize(stSubmitDataId, 50, "GB18030");
        this.stSubmitDataId = stSubmitDataId;
    }
    
	/**
     * 备注
     */
    public String getStDesc() {
        return this.stDesc;
    }
    
    /**
     * 备注
     */
    public String stDesc2Html() {
        return StringHelper.replaceHTMLSymbol(this.stDesc);
    }

    /**
     * 备注
     */
    public void setStDesc(String stDesc) {
        stDesc = StringUtil.substringBySize(stDesc, 100, "GB18030");
        this.stDesc = stDesc;
    }
    
	/**
     * 操作结果
     */
    public String getStOpResult() {
        return this.stOpResult;
    }
    
    /**
     * 操作结果
     */
    public String stOpResult2Html() {
        return StringHelper.replaceHTMLSymbol(this.stOpResult);
    }

    /**
     * 操作结果
     */
    public void setStOpResult(String stOpResult) {
        stOpResult = StringUtil.substringBySize(stOpResult, 100, "GB18030");
        this.stOpResult = stOpResult;
    }
    
    /**
     * 辅助人员
     */
    public String getStAssistId() {
        return this.stAssistId;
    }
    
    /**
     * 辅助人员
     */
    public String stAssistId2Html() {
        return StringHelper.replaceHTMLSymbol(this.stAssistId);
    }

    /**
     * 辅助人员
     */
    public void setStAssistId(String stAssistId) {
    	stAssistId = StringUtil.substringBySize(stAssistId, 100, "GB18030");
        this.stAssistId = stAssistId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}