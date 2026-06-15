package exceptions;

public class AdminNotFoundException extends BusinessException {

	public AdminNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AdminNotFoundException() {
		super("Admin not found");
		// TODO Auto-generated constructor stub
	}
	
}
