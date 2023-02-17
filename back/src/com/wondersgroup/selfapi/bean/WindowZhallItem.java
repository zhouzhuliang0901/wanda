package com.wondersgroup.selfapi.bean;
		
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import wfc.facility.tool.autocode.TimestampXmlAdapter;

/**
 * 事项表
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "WINDOW_ZHALL_ITEM")
public class WindowZhallItem implements Serializable {
    
    /**
     * 事项表
     */
    public static final String WINDOW_ZHALL_ITEM = "WINDOW_ZHALL_ITEM";
    
    /**
     * 报送事项ID
     */
    public static final String ST_ZHALL_ID = "ST_ZHALL_ID";
    
    /**
     * 窗口事项ID
     */
    public static final String ST_ITEM_ID = "ST_ITEM_ID";
    
    /**
     * 事项编码
     */
    public static final String ST_ITEM_NO = "ST_ITEM_NO";
    
    /**
     * 事项名称
     */
    public static final String ST_ITEM_NAME = "ST_ITEM_NAME";
    
    /**
     * 事项所属
     */
    public static final String NM_BELONG = "NM_BELONG";
    
    /**
     * 事项类型
     */
    public static final String ST_ITEM_TYPE = "ST_ITEM_TYPE";
    
    /**
     * 法定时限
     */
    public static final String ST_LEGAL_TIME = "ST_LEGAL_TIME";
    
    /**
     * 承诺时限
     */
    public static final String ST_PROMISE_TIME = "ST_PROMISE_TIME";
    
    /**
     * 是否收费
     */
    public static final String NM_ISPAY = "NM_ISPAY";
    
    /**
     * 咨询电话
     */
    public static final String ST_TEL_CONSULT = "ST_TEL_CONSULT";
    
    /**
     * 业务办理投诉电话
     */
    public static final String ST_TEL_COMPLAINT = "ST_TEL_COMPLAINT";
    
    /**
     * 在线投诉链接
     */
    public static final String ST_ONLINE_COMPLAINT_LINK = "ST_ONLINE_COMPLAINT_LINK";
    
    /**
     * 我要咨询链接
     */
    public static final String ST_CONSULT_LINK = "ST_CONSULT_LINK";
    
    /**
     * 是否提供网上申报
     */
    public static final String NM_ONLINE = "NM_ONLINE";
    
    /**
     * 网上申报咨询电话
     */
    public static final String ST_TEL_ONLINE = "ST_TEL_ONLINE";
    
    /**
     * 排序
     */
    public static final String NM_SORT = "NM_SORT";
    
    /**
     * 办理部门代码
     */
    public static final String ST_DEAL_DEPT = "ST_DEAL_DEPT";
    
    /**
     * 事项标识
     */
    public static final String NM_FLAG = "NM_FLAG";
    
    /**
     * 创建时间
     */
    public static final String DT_CREATE = "DT_CREATE";
    
    /**
     * 更新时间
     */
    public static final String DT_UPDATE = "DT_UPDATE";
    
    /**
     * 扩展字段1
     */
    public static final String ST_EXT1 = "ST_EXT1";
    
    /**
     * 扩展字段2
     */
    public static final String ST_EXT2 = "ST_EXT2";
    
    public WindowZhallItem() {
    }
    
    /**
     * 报送事项ID
     */
    @Id
    @Column(name = "ST_ZHALL_ID")
    private String stZhallId;
    
    /**
     * 窗口事项ID
     */
    @Column(name = "ST_ITEM_ID")
    private String stItemId;
    
    /**
     * 事项编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 事项所属
     */
    @Column(name = "NM_BELONG")
    private BigDecimal nmBelong;
    
    /**
     * 事项类型
     */
    @Column(name = "ST_ITEM_TYPE")
    private String stItemType;
    
    /**
     * 法定时限
     */
    @Column(name = "ST_LEGAL_TIME")
    private String stLegalTime;
    
    /**
     * 承诺时限
     */
    @Column(name = "ST_PROMISE_TIME")
    private String stPromiseTime;
    
    /**
     * 是否收费
     */
    @Column(name = "NM_ISPAY")
    private BigDecimal nmIspay;
    
    /**
     * 咨询电话
     */
    @Column(name = "ST_TEL_CONSULT")
    private String stTelConsult;
    
    /**
     * 业务办理投诉电话
     */
    @Column(name = "ST_TEL_COMPLAINT")
    private String stTelComplaint;
    
    /**
     * 在线投诉链接
     */
    @Column(name = "ST_ONLINE_COMPLAINT_LINK")
    private String stOnlineComplaintLink;
    
    /**
     * 我要咨询链接
     */
    @Column(name = "ST_CONSULT_LINK")
    private String stConsultLink;
    
    /**
     * 是否提供网上申报
     */
    @Column(name = "NM_ONLINE")
    private String nmOnline;
    
    /**
     * 网上申报咨询电话
     */
    @Column(name = "ST_TEL_ONLINE")
    private String stTelOnline;
    
    /**
     * 排序
     */
    @Column(name = "NM_SORT")
    private BigDecimal nmSort;
    
    /**
     * 办理部门代码
     */
    @Column(name = "ST_DEAL_DEPT")
    private String stDealDept;
    
    /**
     * 事项标识
     */
    @Column(name = "NM_FLAG")
    private BigDecimal nmFlag;
    
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
     * 扩展字段1
     */
    @Column(name = "ST_EXT1")
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    @Column(name = "ST_EXT2")
    private String stExt2;

	public String getStZhallId() {
		return stZhallId;
	}

	public void setStZhallId(String stZhallId) {
		this.stZhallId = stZhallId;
	}

	public String getStItemId() {
		return stItemId;
	}

	public void setStItemId(String stItemId) {
		this.stItemId = stItemId;
	}

	public String getStItemNo() {
		return stItemNo;
	}

	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	public BigDecimal getNmBelong() {
		return nmBelong;
	}

	public void setNmBelong(BigDecimal nmBelong) {
		this.nmBelong = nmBelong;
	}

	public String getStItemType() {
		return stItemType;
	}

	public void setStItemType(String stItemType) {
		this.stItemType = stItemType;
	}

	public String getStLegalTime() {
		return stLegalTime;
	}

	public void setStLegalTime(String stLegalTime) {
		this.stLegalTime = stLegalTime;
	}

	public String getStPromiseTime() {
		return stPromiseTime;
	}

	public void setStPromiseTime(String stPromiseTime) {
		this.stPromiseTime = stPromiseTime;
	}

	public BigDecimal getNmIspay() {
		return nmIspay;
	}

	public void setNmIspay(BigDecimal nmIspay) {
		this.nmIspay = nmIspay;
	}

	public String getStTelConsult() {
		return stTelConsult;
	}

	public void setStTelConsult(String stTelConsult) {
		this.stTelConsult = stTelConsult;
	}

	public String getStTelComplaint() {
		return stTelComplaint;
	}

	public void setStTelComplaint(String stTelComplaint) {
		this.stTelComplaint = stTelComplaint;
	}

	public String getStOnlineComplaintLink() {
		return stOnlineComplaintLink;
	}

	public void setStOnlineComplaintLink(String stOnlineComplaintLink) {
		this.stOnlineComplaintLink = stOnlineComplaintLink;
	}

	public String getStConsultLink() {
		return stConsultLink;
	}

	public void setStConsultLink(String stConsultLink) {
		this.stConsultLink = stConsultLink;
	}

	public String getNmOnline() {
		return nmOnline;
	}

	public void setNmOnline(String nmOnline) {
		this.nmOnline = nmOnline;
	}

	public String getStTelOnline() {
		return stTelOnline;
	}

	public void setStTelOnline(String stTelOnline) {
		this.stTelOnline = stTelOnline;
	}

	public BigDecimal getNmSort() {
		return nmSort;
	}

	public void setNmSort(BigDecimal nmSort) {
		this.nmSort = nmSort;
	}

	public String getStDealDept() {
		return stDealDept;
	}

	public void setStDealDept(String stDealDept) {
		this.stDealDept = stDealDept;
	}

	public BigDecimal getNmFlag() {
		return nmFlag;
	}

	public void setNmFlag(BigDecimal nmFlag) {
		this.nmFlag = nmFlag;
	}
	
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}
	
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getStExt1() {
		return stExt1;
	}

	public void setStExt1(String stExt1) {
		this.stExt1 = stExt1;
	}

	public String getStExt2() {
		return stExt2;
	}

	public void setStExt2(String stExt2) {
		this.stExt2 = stExt2;
	}
    


}