package exceptions;

public class InvalidIBANException extends BusinessException {

	public InvalidIBANException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InvalidIBANException() {
		super("Invalid IBAN introduced ");
		// TODO Auto-generated constructor stub
	}
}
