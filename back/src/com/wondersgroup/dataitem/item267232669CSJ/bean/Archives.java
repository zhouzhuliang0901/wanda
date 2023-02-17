package com.wondersgroup.dataitem.item267232669CSJ.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 长三角档案馆数据（查档申请的办理点）
 * 数据由对接放提供
 * @author wanda
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SELFM_ARCHIVES_AREA")
public class Archives implements Serializable{
	
	/**
	 * 省ID
	 */
	@Column(name = "SHENG_ID")
	private String provinceId;
	/**
	 * 省名称
	 */
	@Column(name = "SHENG")
	private String provinceName;
	/**
	 * 市ID
	 */
	@Column(name = "SHI_ID")
	private String cityId;
	/**
	 * 市名称
	 */
	@Column(name = "SHI")
	private String cityName;
	/**
	 * 档案馆ID
	 */
	@Column(name = "NAME_ID")
	private String ArchivesId;
	/**
	 * 档案管名称
	 */
	@Column(name = "NAME")
	private String ArchivesName;
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getArchivesId() {
		return ArchivesId;
	}
	public void setArchivesId(String archivesId) {
		ArchivesId = archivesId;
	}
	public String getArchivesName() {
		return ArchivesName;
	}
	public void setArchivesName(String archivesName) {
		ArchivesName = archivesName;
	}
}
