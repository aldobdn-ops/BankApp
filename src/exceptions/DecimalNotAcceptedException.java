package exceptions;

public class DecimalNotAcceptedException extends BusinessException {

	public DecimalNotAcceptedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public DecimalNotAcceptedException() {
		super("Decimal numbers not accepted.");
		// TODO Auto-generated constructor stub
	}
}
