package exceptions;

public class InvalidNIEexception extends BusinessException {

	public InvalidNIEexception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidNIEexception() {
		super("Invalid Nie Introduced.");
		// TODO Auto-generated constructor stub
	}
	
}
