package reindeer.base.log.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_EXCEPTION_INFO")
public class SelmExceptionInfo implements Serializable {

public static final String SELM_EXCEPTION_INFO = "SELM_EXCEPTION_INFO";

	public static final String ST_ID = "ST_ID";

	public static final String ST_EXCEPTION_METHOD = "ST_EXCEPTION_METHOD";

	public static final String ST_EXCEPTION_PACKAGE = "ST_EXCEPTION_PACKAGE";

	public static final String ST_EXCEPTION_CAUSE = "ST_EXCEPTION_CAUSE";

	public static final String ST_EXCEPTION_LINE = "ST_EXCEPTION_LINE";

	public static final String ST_EXCEPTION_FILE = "ST_EXCEPTION_FILE";

	public static final String DT_EXCEPTION_TIME = "DT_EXCEPTION_TIME";

	public static final String ST_REQUEST_URL = "ST_REQUEST_URL";

	public static final String ST_REQUEST_IP = "ST_REQUEST_IP";

	public static final String ST_REQUEST_METHOD = "ST_REQUEST_METHOD";

	public static final String ST_REQUEST_PARAM = "ST_REQUEST_PARAM";

	public static final String ST_EXT1 = "ST_EXT1";

	public static final String ST_EXT2 = "ST_EXT2";

	public static final String ST_EXT3 = "ST_EXT3";

	public static final String ST_EXT4 = "ST_EXT4";

	@Column(name = "ST_ID")
	private String stId;

	@Column(name = "ST_EXCEPTION_METHOD")
	private String stExceptionMethod;

	@Column(name = "ST_EXCEPTION_PACKAGE")
	private String stExceptionPackage;

	@Column(name = "ST_EXCEPTION_CAUSE")
	private String stExceptionCause;

	@Column(name = "ST_EXCEPTION_LINE")
	private Integer stExceptionLine;

	@Column(name = "ST_EXCEPTION_FILE")
	private String stExceptionFile;

	@Column(name = "DT_EXCEPTION_TIME")
	private Timestamp dtExceptionTime;

	@Column(name = "ST_REQUEST_URL")
	private String stRequestUrl;

	@Column(name = "ST_REQUEST_IP")
	private String stRequestIp;

	@Column(name = "ST_REQUEST_METHOD")
	private String stRequestMethod;

	@Column(name = "ST_REQUEST_PARAM")
	private String stRequestParam;

	@Column(name = "ST_EXT1")
	private String stExt1;

	@Column(name = "ST_EXT2")
	private String stExt2;

	@Column(name = "ST_EXT3")
	private String stExt3;

	@Column(name = "ST_EXT4")
	private String stExt4;

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStExceptionMethod() {
		return stExceptionMethod;
	}

	public void setStExceptionMethod(String stExceptionMethod) {
		this.stExceptionMethod = stExceptionMethod;
	}

	public String getStExceptionPackage() {
		return stExceptionPackage;
	}

	public void setStExceptionPackage(String stExceptionPackage) {
		this.stExceptionPackage = stExceptionPackage;
	}

	public String getStExceptionCause() {
		return stExceptionCause;
	}

	public void setStExceptionCause(String stExceptionCause) {
		this.stExceptionCause = stExceptionCause;
	}

	public Integer getStExceptionLine() {
		return stExceptionLine;
	}

	public void setStExceptionLine(Integer stExceptionLine) {
		this.stExceptionLine = stExceptionLine;
	}

	public String getStExceptionFile() {
		return stExceptionFile;
	}

	public void setStExceptionFile(String stExceptionFile) {
		this.stExceptionFile = stExceptionFile;
	}

	public Timestamp getDtExceptionTime() {
		return dtExceptionTime;
	}

	public void setDtExceptionTime(Timestamp dtExceptionTime) {
		this.dtExceptionTime = dtExceptionTime;
	}

	public String getStRequestUrl() {
		return stRequestUrl;
	}

	public void setStRequestUrl(String stRequestUrl) {
		this.stRequestUrl = stRequestUrl;
	}

	public String getStRequestIp() {
		return stRequestIp;
	}

	public void setStRequestIp(String stRequestIp) {
		this.stRequestIp = stRequestIp;
	}

	public String getStRequestMethod() {
		return stRequestMethod;
	}

	public void setStRequestMethod(String stRequestMethod) {
		this.stRequestMethod = stRequestMethod;
	}

	public String getStRequestParam() {
		return stRequestParam;
	}

	public void setStRequestParam(String stRequestParam) {
		this.stRequestParam = stRequestParam;
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
}