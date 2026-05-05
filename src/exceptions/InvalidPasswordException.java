package exceptions;

public class InvalidPasswordException extends BusinessException {
	
	public InvalidPasswordException() {
		super("Wrong password introduced");
	}

	public InvalidPasswordException(String message) {
		super("Wrong password introduced");
		// TODO Auto-generated constructor stub
	}

	
}
