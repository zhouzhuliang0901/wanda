package coral.base.ws;

public class WsLoginException extends RuntimeException {

	private static final long serialVersionUID = -2192402620277080295L;

	public WsLoginException() {
	}

	public WsLoginException(String message) {
		super(message);
	}

	public WsLoginException(Throwable cause) {
		super(cause);
	}

	public WsLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
