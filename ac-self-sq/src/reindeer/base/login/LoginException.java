package reindeer.base.login;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = -2192402620277080295L;

	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
