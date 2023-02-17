package reindeer.ssologin.exception;

public class SsoLoginException extends RuntimeException {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	public SsoLoginException() {
	}

	public SsoLoginException(String message) {
		super(message);
	}

	public SsoLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public SsoLoginException(Throwable cause) {
		super(cause);
	}

}
