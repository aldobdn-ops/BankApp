package exceptions;

public class DecimalNotAcceptedException extends BusinessException {

	public DecimalNotAcceptedException(String message) {
		super(message);
		// TODO Constructor generado automáticamente
	}
	public DecimalNotAcceptedException() {
		super("Decimal numbers not accepted.");
		// TODO Constructor generado automáticamente
	}
}
