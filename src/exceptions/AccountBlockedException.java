package exceptions;

public class AccountBlockedException extends BusinessException {

	public AccountBlockedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AccountBlockedException() {
		super("Acces denied this account is already blocked.");
		// TODO Auto-generated constructor stub
	}
	
}
