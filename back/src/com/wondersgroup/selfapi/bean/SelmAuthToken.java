package com.wondersgroup.selfapi.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SELM_AUTH_TOKEN")
public class SelmAuthToken implements Serializable{
	
	private static final long serialVersionUID = 7712895077682855442L;
	
	/**
	 * 授权接口名
	 */
	@Column(name = "ST_AUTH_API_NAME")
	private String stAuthApiName;
	
	/**
	 * 授权码值
	 */
	@Column(name = "ST_AUTH_TOKEN")
	private String stAuthToken;

	public String getStAuthApiName() {
		return stAuthApiName;
	}

	public void setStAuthApiName(String stAuthApiName) {
		this.stAuthApiName = stAuthApiName;
	}

	public String getStAuthToken() {
		return stAuthToken;
	}
	
	public void setStAuthToken(String stAuthToken) {
		this.stAuthToken = stAuthToken;
	}
}
