package exceptions;

public class AccountNotFoundException extends BusinessException {

	public AccountNotFoundException(String message) {
		super("Account not found");
		// TODO Constructor generado automáticamente
	}
	public AccountNotFoundException() {
		super("Account not found");
		// TODO Constructor generado automáticamente
	}
	
}
