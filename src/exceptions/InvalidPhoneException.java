package exceptions;

public class InvalidPhoneException extends BusinessException{

	public InvalidPhoneException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InvalidPhoneException() {
		super("Invalid phone introduced");
		// TODO Auto-generated constructor stub
	}
}
