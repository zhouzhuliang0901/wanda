package com.wondersgroup.selfapi.bean;

import java.math.*;
import java.sql.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import wfc.facility.tool.autocode.TimestampXmlAdapter;

/**
 * 预约信息表
 * @author scalffold
 */

public class NetReservationVo {
   
    /**
     * 预约信息ID
     */
    private String stReservationId;
    
    /**
     * 统一审批编码（work系统需要）
     */
    private String stBusinessNo;
    
    /**
     * 预约号
     */
    private String stReservationNo;
    
    /**
     * 事项信息ID
     */
    private String stItemId;
    
    /**
     * 事项名称
     */
    private String stItemName;
    
    /**
     * 组别号
     */
    private String stGroupCode;
    
    /**
     * 预约操作时间
     */
    private Timestamp dtOperation;
    
    /**
     * 预约开始时间
     */
    private Timestamp dtReservationStart;
    
    /**
     * 预约结束时间
     */
    private Timestamp dtReservationEnd;
    
    /**
     * 用户账户ID
     */
    private String stUserId;
    
    /**
     * 用户姓名
     */
    private String stUserName;
    
    /**
     * 用户手机号
     */
    private String stMobile;
    
    /**
     * 用户证件类型
     */
    private BigDecimal nmIdentityType;
    
    /**
     * 用户证件号
     */
    private String stIdentityNo;
    
    /**
     * 状态位
     */
    private BigDecimal nmRemoved;
    
    /**
     * 预约规则时间详细ID
     */
    private String stDetailId;
    
    /**
     * 预约来源
     */
    private BigDecimal nmDataSource;
    
    /**
     * 部门ID
     */
    private BigDecimal nmOrganNodeId;
    
    /**
     * 部门完整名字
     */
    private String stOrganName;
    
    /**
     * 部门代码
     */
    private String stOrganCode;
    
    /**
     * 规则显示时间
     */
    private String stShow;
    
    /**
     * 扩展字段1
     */
    private String stExt1;
    
    /**
     * 扩展字段2
     */
    private String stExt2;
    
    /**
     * 事项编码
     */
    private String stItemNo;
    
    /**
     * 企业名称
     */
    private String stUnit;
    
    /**
     * 统一社会信用代码
     */
    private String stUnified;

    /**
     * 办理点信息
     */
    private String stHallInfo;

    
    public String getStUnit() {
		return stUnit;
	}

	public void setStUnit(String stUnit) {
		this.stUnit = stUnit;
	}

	public String getStUnified() {
		return stUnified;
	}

	public void setStUnified(String stUnified) {
		this.stUnified = stUnified;
	}
	public String getStReservationId() {
		return stReservationId;
	}

	public void setStReservationId(String stReservationId) {
		this.stReservationId = stReservationId;
	}

	public String getStBusinessNo() {
		return stBusinessNo;
	}

	public void setStBusinessNo(String stBusinessNo) {
		this.stBusinessNo = stBusinessNo;
	}

	public String getStReservationNo() {
		return stReservationNo;
	}

	public void setStReservationNo(String stReservationNo) {
		this.stReservationNo = stReservationNo;
	}

	public String getStItemId() {
		return stItemId;
	}

	public void setStItemId(String stItemId) {
		this.stItemId = stItemId;
	}

	public String getStItemName() {
		return stItemName;
	}

	public void setStItemName(String stItemName) {
		this.stItemName = stItemName;
	}

	public String getStGroupCode() {
		return stGroupCode;
	}

	public void setStGroupCode(String stGroupCode) {
		this.stGroupCode = stGroupCode;
	}

	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtOperation() {
		return dtOperation;
	}

	public void setDtOperation(Timestamp dtOperation) {
		this.dtOperation = dtOperation;
	}
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtReservationStart() {
		return dtReservationStart;
	}

	public void setDtReservationStart(Timestamp dtReservationStart) {
		this.dtReservationStart = dtReservationStart;
	}
	@XmlJavaTypeAdapter(TimestampXmlAdapter.class)
	public Timestamp getDtReservationEnd() {
		return dtReservationEnd;
	}

	public void setDtReservationEnd(Timestamp dtReservationEnd) {
		this.dtReservationEnd = dtReservationEnd;
	}

	public String getStUserId() {
		return stUserId;
	}

	public void setStUserId(String stUserId) {
		this.stUserId = stUserId;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStMobile() {
		return stMobile;
	}

	public void setStMobile(String stMobile) {
		this.stMobile = stMobile;
	}

	public BigDecimal getNmIdentityType() {
		return nmIdentityType;
	}

	public void setNmIdentityType(BigDecimal nmIdentityType) {
		this.nmIdentityType = nmIdentityType;
	}

	public String getStIdentityNo() {
		return stIdentityNo;
	}

	public void setStIdentityNo(String stIdentityNo) {
		this.stIdentityNo = stIdentityNo;
	}

	public BigDecimal getNmRemoved() {
		return nmRemoved;
	}

	public void setNmRemoved(BigDecimal nmRemoved) {
		this.nmRemoved = nmRemoved;
	}

	public String getStDetailId() {
		return stDetailId;
	}

	public void setStDetailId(String stDetailId) {
		this.stDetailId = stDetailId;
	}

	public BigDecimal getNmDataSource() {
		return nmDataSource;
	}

	public void setNmDataSource(BigDecimal nmDataSource) {
		this.nmDataSource = nmDataSource;
	}

	public BigDecimal getNmOrganNodeId() {
		return nmOrganNodeId;
	}

	public void setNmOrganNodeId(BigDecimal nmOrganNodeId) {
		this.nmOrganNodeId = nmOrganNodeId;
	}

	public String getStOrganName() {
		return stOrganName;
	}

	public void setStOrganName(String stOrganName) {
		this.stOrganName = stOrganName;
	}

	public String getStOrganCode() {
		return stOrganCode;
	}

	public void setStOrganCode(String stOrganCode) {
		this.stOrganCode = stOrganCode;
	}

	public String getStShow() {
		return stShow;
	}

	public void setStShow(String stShow) {
		this.stShow = stShow;
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

	public String getStItemNo() {
		return stItemNo;
	}

	public void setStItemNo(String stItemNo) {
		this.stItemNo = stItemNo;
	}

	public String getStHallInfo() {
		return stHallInfo;
	}

	public void setStHallInfo(String stHallInfo) {
		this.stHallInfo = stHallInfo;
	}
    

}