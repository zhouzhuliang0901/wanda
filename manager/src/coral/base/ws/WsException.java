package coral.base.ws;

public class WsException extends RuntimeException {

	private static final long serialVersionUID = -5290359996454386437L;

	public WsException() {
	}

	public WsException(String message) {
		super(message);
	}

	public WsException(Throwable cause) {
		super(cause);
	}

	public WsException(String message, Throwable cause) {
		super(message, cause);
	}

}
