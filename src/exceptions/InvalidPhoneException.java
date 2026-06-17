package exceptions;

public class InvalidPhoneException extends BusinessException{

	public InvalidPhoneException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public InvalidPhoneException() {
		super("Invalid phone introduced");
		// TODO Constructor generado automáticamente
	}
}
