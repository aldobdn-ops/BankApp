package exceptions;

public class InvalidAmountException extends BusinessException {

	public InvalidAmountException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InvalidAmountException() {
		super("Invalid amount for transfer.");
		// TODO Auto-generated construor stub
	}
	
}
