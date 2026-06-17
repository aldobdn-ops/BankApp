package exceptions;

public class AdminNotFoundException extends BusinessException {

	public AdminNotFoundException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public AdminNotFoundException() {
		super("Admin not found");
		// TODO Constructor generado automáticamente
	}
	
}
