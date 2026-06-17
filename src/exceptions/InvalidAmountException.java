package exceptions;

public class InvalidAmountException extends BusinessException {

	public InvalidAmountException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public InvalidAmountException() {
		super("Invalid amount for transfer.");
		// TODO Constructor generado automáticamente
	}
	
}
