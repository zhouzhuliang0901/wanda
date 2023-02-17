package com.wondersgroup.selfapi.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_ZHALL_ITEM")
public class SelmZhallItemInfo implements Serializable{
	
	/**
	 * 事项主键
	 */
    @Id
    @Column(name = "ST_ID")
    private String stId;
    
    /**
     * 事项基本编码
     */
    @Column(name = "ST_ITEM_NO")
    private String stItemNo;
    
    /**
     * 事项名称
     */
    @Column(name = "ST_ITEM_NAME")
    private String stItemName;
    
    /**
     * 申请对象
     */
    @Column(name = "NM_BELONG")
    private String nmBelong;
    
    /**
     * 事项类型
     */
    @Column(name = "ST_ITEM_TYPE")
    private String stItemType;
    
    /**
     * 办理机构代码
     */
    @Column(name = "ST_ORG_CODE")
    private String stOrgCode;
    
    /**
     * 办理机构名称
     */
    @Column(name = "ST_ORG_NAME")
    private String stOrgName;
    
    /**
     * 创建时间
     */
    @Column(name = "DT_CREATE")
    private Timestamp dtCreate;
    
    /**
     * 修改时间
     */
    @Column(name = "DT_UPDATE")
    private Timestamp dtUpdate;
    
    /**
     * 删除标记
     */
    @Column(name = "ST_REMOVE")
    private Integer stRemove ;
    
    /**
     * 事项分类代码
     */
    @Column(name = "ST_DIC_CODE")
    private String stDicCode;
    
//    /**
//     * 个人主题
//     */
//    @Column(name = "ST_DIC_CODE_GR")
//    private String stDicCodeGr;
//    
//    /**
//     * 法人主题
//     */
//    @Column(name = "ST_DIC_CODE_FR")
//    private String stDicCodeFr;
    
    /**
     * 关联网上办事编码
     */
    @Column(name = "ST_ITEM_TEN_CODE")
    private String stItemTenCode;
    
    /**
     * 办理项名称(情形名称)
     */
    @Column(name = "ST_TRANSACT_NAME")
    private String stTransactName;
    
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
     * 分项名称
     */
    @Column(name = "ST_SUB_ITEM_NAME")
    private String stSubItemName;
    
    /**
     * 是否是子事项
     */
    @Column(name = "NM_ISSON")
    private Integer nmIsson; 

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
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

	public String getNmBelong() {
		return nmBelong;
	}

	public void setNmBelong(String nmBelong) {
		this.nmBelong = nmBelong;
	}

	public String getStItemType() {
		return stItemType;
	}

	public void setStItemType(String stItemType) {
		this.stItemType = stItemType;
	}

	public String getStOrgCode() {
		return stOrgCode;
	}

	public void setStOrgCode(String stOrgCode) {
		this.stOrgCode = stOrgCode;
	}
	
	public String getStOrgName() {
		return stOrgName;
	}

	public void setStOrgName(String stOrgName) {
		this.stOrgName = stOrgName;
	}

	public Timestamp getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Timestamp dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Timestamp getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(Timestamp dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public Integer getStRemove() {
		return stRemove;
	}

	public void setStRemove(Integer stRemove) {
		this.stRemove = stRemove;
	}

	public String getStDicCode() {
		return stDicCode;
	}

	public void setStDicCode(String stDicCode) {
		this.stDicCode = stDicCode;
	}
	
//	public String getStDicCodeGr() {
//		return stDicCodeGr;
//	}
//
//	public void setStDicCodeGr(String stDicCodeGr) {
//		this.stDicCodeGr = stDicCodeGr;
//	}
//	
//	public String getStDicCodeFr() {
//		return stDicCodeFr;
//	}
//
//	public void setStDicCodeFr(String stDicCodeFr) {
//		this.stDicCodeFr = stDicCodeFr;
//	}

	public String getStItemTenCode() {
		return stItemTenCode;
	}

	public void setStItemTenCode(String stItemTenCode) {
		this.stItemTenCode = stItemTenCode;
	}

	public String getStTransactName() {
		return stTransactName;
	}

	public void setStTransactName(String stTransactName) {
		this.stTransactName = stTransactName;
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

	public String getStExt3() {
		return stExt3;
	}

	public void setStExt3(String stExt3) {
		this.stExt3 = stExt3;
	}

	public String getStExt4() {
		return stExt4;
	}

	public void setStExt4(String stExt4) {
		this.stExt4 = stExt4;
	}

	public String getStSubItemName() {
		return stSubItemName;
	}

	public void setStSubItemName(String stSubItemName) {
		this.stSubItemName = stSubItemName;
	}

	public Integer getNmIsson() {
		return nmIsson;
	}

	public void setNmIsson(Integer nmIsson) {
		this.nmIsson = nmIsson;
	}
}
