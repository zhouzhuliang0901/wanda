package com.wondersgroup.dataitem.item382711997735.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_DICTIONARY_CSJHJ")
public class SelmDictionaryCsjhj implements Serializable {

public static final String SELM_DICTIONARY_CSJHJ = "SELM_DICTIONARY_CSJHJ";

	public static final String ST_DICTIONARY_CODE = "ST_DICTIONARY_CODE";

	public static final String ST_DICTIONARY_NAME = "ST_DICTIONARY_NAME";

	public static final String ST_PARENT_DICTIONARY_CODE = "ST_PARENT_DICTIONARY_CODE";

	public static final String ST_DICTIONARY_GROUP = "ST_DICTIONARY_GROUP";

	@Column(name = "ST_DICTIONARY_CODE")
	private String stDictionaryCode;

	@Column(name = "ST_DICTIONARY_NAME")
	private String stDictionaryName;

	@Column(name = "ST_PARENT_DICTIONARY_CODE")
	private String stParentDictionaryCode;

	@Column(name = "ST_DICTIONARY_GROUP")
	private String stDictionaryGroup;

	public String getStDictionaryCode() {
		return stDictionaryCode;
	}

	public void setStDictionaryCode(String stDictionaryCode) {
		this.stDictionaryCode = stDictionaryCode;
	}

	public String getStDictionaryName() {
		return stDictionaryName;
	}

	public void setStDictionaryName(String stDictionaryName) {
		this.stDictionaryName = stDictionaryName;
	}

	public String getStParentDictionaryCode() {
		return stParentDictionaryCode;
	}

	public void setStParentDictionaryCode(String stParentDictionaryCode) {
		this.stParentDictionaryCode = stParentDictionaryCode;
	}

	public String getStDictionaryGroup() {
		return stDictionaryGroup;
	}

	public void setStDictionaryGroup(String stDictionaryGroup) {
		this.stDictionaryGroup = stDictionaryGroup;
	}
}