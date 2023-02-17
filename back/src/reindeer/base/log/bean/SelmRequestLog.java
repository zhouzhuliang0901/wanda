package reindeer.base.log.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@SuppressWarnings("serial")
@Entity
@Table(name = "SELM_REQUEST_LOG")
public class SelmRequestLog implements Serializable {

public static final String SELM_REQUEST_LOG = "SELM_REQUEST_LOG";

	public static final String ST_REQUEST_LOG_ID = "ST_REQUEST_LOG_ID";

	public static final String ST_REQUEST_IP = "ST_REQUEST_IP";

	public static final String ST_REQUSET_ADDRESS = "ST_REQUSET_ADDRESS";

	public static final String ST_REQUEST_METHOD_TYPE = "ST_REQUEST_METHOD_TYPE";

	public static final String ST_REQUEST_PACKAGE = "ST_REQUEST_PACKAGE";

	public static final String ST_REQUEST_METHOD_NAME = "ST_REQUEST_METHOD_NAME";

	public static final String ST_REQUEST_URL = "ST_REQUEST_URL";

	public static final String DT_REQUEST_TIME = "DT_REQUEST_TIME";

	public static final String ST_REQUEST_PARAM = "ST_REQUEST_PARAM";

	public static final String ST_REQUEST_RESPONSE = "ST_REQUEST_RESPONSE";

	public static final String ST_REQUEST_MACHINE_MAC = "ST_REQUEST_MACHINE_MAC";

	public static final String ST_REQUEST_MACHINE_NAME = "ST_REQUEST_MACHINE_NAME";

	public static final String ST_REQUEST_MACHINE_ADDRESS = "ST_REQUEST_MACHINE_ADDRESS";

	public static final String ST_EXT1 = "ST_EXT1";

	public static final String ST_EXT2 = "ST_EXT2";

	@Column(name = "ST_REQUEST_LOG_ID")
	private String stRequestLogId;

	@Column(name = "ST_REQUEST_IP")
	private String stRequestIp;

	@Column(name = "ST_REQUSET_ADDRESS")
	private String stRequsetAddress;

	@Column(name = "ST_REQUEST_METHOD_TYPE")
	private String stRequestMethodType;

	@Column(name = "ST_REQUEST_PACKAGE")
	private String stRequestPackage;

	@Column(name = "ST_REQUEST_METHOD_NAME")
	private String stRequestMethodName;

	@Column(name = "ST_REQUEST_URL")
	private String stRequestUrl;

	@Column(name = "DT_REQUEST_TIME")
	private Timestamp dtRequestTime;

	@Column(name = "ST_REQUEST_PARAM")
	private String stRequestParam;

	@Column(name = "ST_REQUEST_RESPONSE")
	private String stRequestResponse;

	@Column(name = "ST_REQUEST_MACHINE_MAC")
	private String stRequestMachineMac;

	@Column(name = "ST_REQUEST_MACHINE_NAME")
	private String stRequestMachineName;

	@Column(name = "ST_REQUEST_MACHINE_ADDRESS")
	private String stRequestMachineAddress;

	@Column(name = "ST_EXT1")
	private String stExt1;

	@Column(name = "ST_EXT2")
	private String stExt2;

	public String getStRequestLogId() {
		return stRequestLogId;
	}

	public void setStRequestLogId(String stRequestLogId) {
		this.stRequestLogId = stRequestLogId;
	}

	public String getStRequestIp() {
		return stRequestIp;
	}

	public void setStRequestIp(String stRequestIp) {
		this.stRequestIp = stRequestIp;
	}

	public String getStRequsetAddress() {
		return stRequsetAddress;
	}

	public void setStRequsetAddress(String stRequsetAddress) {
		this.stRequsetAddress = stRequsetAddress;
	}

	public String getStRequestMethodType() {
		return stRequestMethodType;
	}

	public void setStRequestMethodType(String stRequestMethodType) {
		this.stRequestMethodType = stRequestMethodType;
	}

	public String getStRequestPackage() {
		return stRequestPackage;
	}

	public void setStRequestPackage(String stRequestPackage) {
		this.stRequestPackage = stRequestPackage;
	}

	public String getStRequestMethodName() {
		return stRequestMethodName;
	}

	public void setStRequestMethodName(String stRequestMethodName) {
		this.stRequestMethodName = stRequestMethodName;
	}

	public String getStRequestUrl() {
		return stRequestUrl;
	}

	public void setStRequestUrl(String stRequestUrl) {
		this.stRequestUrl = stRequestUrl;
	}

	public Timestamp getDtRequestTime() {
		return dtRequestTime;
	}

	public void setDtRequestTime(Timestamp dtRequestTime) {
		this.dtRequestTime = dtRequestTime;
	}

	public String getStRequestParam() {
		return stRequestParam;
	}

	public void setStRequestParam(String stRequestParam) {
		this.stRequestParam = stRequestParam;
	}

	public String getStRequestResponse() {
		return stRequestResponse;
	}

	public void setStRequestResponse(String stRequestResponse) {
		this.stRequestResponse = stRequestResponse;
	}

	public String getStRequestMachineMac() {
		return stRequestMachineMac;
	}

	public void setStRequestMachineMac(String stRequestMachineMac) {
		this.stRequestMachineMac = stRequestMachineMac;
	}

	public String getStRequestMachineName() {
		return stRequestMachineName;
	}

	public void setStRequestMachineName(String stRequestMachineName) {
		this.stRequestMachineName = stRequestMachineName;
	}

	public String getStRequestMachineAddress() {
		return stRequestMachineAddress;
	}

	public void setStRequestMachineAddress(String stRequestMachineAddress) {
		this.stRequestMachineAddress = stRequestMachineAddress;
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