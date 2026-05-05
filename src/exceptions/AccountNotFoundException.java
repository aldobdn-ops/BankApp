package exceptions;

public class AccountNotFoundException extends BusinessException {

	public AccountNotFoundException(String message) {
		super("Account not found");
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException() {
		super("Account not found");
		// TODO Auto-generated constructor stub
	}
	
}
