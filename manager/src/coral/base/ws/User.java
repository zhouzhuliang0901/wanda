package coral.base.ws;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

	private static final long serialVersionUID = 322239714644072625L;

	private String sessionId;

	private String id;

	private String account;

	private String name;

	private byte[] secretKey;

	public User() {
	}

	public User(String account) {
		sessionId = UUID.randomUUID().toString();
		this.account = account;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(byte[] secretKey) {
		this.secretKey = secretKey;
	}

}
