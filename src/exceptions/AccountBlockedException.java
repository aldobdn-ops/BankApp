package exceptions;

public class AccountBlockedException extends BusinessException {

	public AccountBlockedException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public AccountBlockedException() {
		super("Acces denied this account is already blocked.");
		// TODO Constructor generado automáticamente
	}
	
}
