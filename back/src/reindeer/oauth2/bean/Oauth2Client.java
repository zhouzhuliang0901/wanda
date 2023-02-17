package reindeer.oauth2.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * OAUTH2认证客户端
 * 
 * @author scalffold
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OAUTH2_CLIENT")
public class Oauth2Client implements Serializable {

	/**
	 * OAUTH2认证客户端
	 */
	public static final String OAUTH2_CLIENT = "OAUTH2_CLIENT";

	/**
	 * 认证客户端ID
	 */
	public static final String ST_OAUTH2_ID = "ST_OAUTH2_ID";

	/**
	 * 接口用户名
	 */
	public static final String ST_INTERFACE_USER = "ST_INTERFACE_USER";

	/**
	 * 接口密码
	 */
	public static final String ST_INTERFACE_PWD = "ST_INTERFACE_PWD";

	/**
	 * 客户端名称
	 */
	public static final String ST_CLIENT_NAME = "ST_CLIENT_NAME";

	/**
	 * 客户端ID
	 */
	public static final String ST_CLIENT_ID = "ST_CLIENT_ID";

	/**
	 * 客户端安全KEY
	 */
	public static final String ST_CLIENT_SECRET = "ST_CLIENT_SECRET";

	/**
	 * 备注
	 */
	public static final String ST_DESC = "ST_DESC";

	public Oauth2Client() {
	}

	/**
	 * 认证客户端ID
	 */
	@Id
	@Column(name = "ST_OAUTH2_ID")
	private String stOauth2Id;

	/**
	 * 接口用户名
	 */
	@Column(name = "ST_INTERFACE_USER")
	private String stInterfaceUser;

	/**
	 * 接口密码
	 */
	@Column(name = "ST_INTERFACE_PWD")
	private String stInterfacePwd;

	/**
	 * 客户端名称
	 */
	@Column(name = "ST_CLIENT_NAME")
	private String stClientName;

	/**
	 * 客户端ID
	 */
	@Column(name = "ST_CLIENT_ID")
	private String stClientId;

	/**
	 * 客户端安全KEY
	 */
	@Column(name = "ST_CLIENT_SECRET")
	private String stClientSecret;

	/**
	 * 备注
	 */
	@Column(name = "ST_DESC")
	private String stDesc;

	public String getStOauth2Id() {
		return stOauth2Id;
	}

	public void setStOauth2Id(String stOauth2Id) {
		this.stOauth2Id = stOauth2Id;
	}

	public String getStInterfaceUser() {
		return stInterfaceUser;
	}

	public void setStInterfaceUser(String stInterfaceUser) {
		this.stInterfaceUser = stInterfaceUser;
	}

	public String getStInterfacePwd() {
		return stInterfacePwd;
	}

	public void setStInterfacePwd(String stInterfacePwd) {
		this.stInterfacePwd = stInterfacePwd;
	}

	public String getStClientName() {
		return stClientName;
	}

	public void setStClientName(String stClientName) {
		this.stClientName = stClientName;
	}

	public String getStClientId() {
		return stClientId;
	}

	public void setStClientId(String stClientId) {
		this.stClientId = stClientId;
	}

	public String getStClientSecret() {
		return stClientSecret;
	}

	public void setStClientSecret(String stClientSecret) {
		this.stClientSecret = stClientSecret;
	}

	public String getStDesc() {
		return stDesc;
	}

	public void setStDesc(String stDesc) {
		this.stDesc = stDesc;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}