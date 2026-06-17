package exceptions;

public class InvalidIBANException extends BusinessException {

	public InvalidIBANException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public InvalidIBANException() {
		super("Invalid IBAN introduced ");
		// TODO Constructor generado automáticamente
	}
}
