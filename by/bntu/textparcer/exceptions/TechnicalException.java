package by.bntu.textparcer.exceptions;

public class TechnicalException extends RuntimeException {

	public TechnicalException() {
	}

	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public TechnicalException(String message) {
		super(message);
	}

	public TechnicalException(Throwable cause) {
		super(cause);
	}

}