package exceptions;

/**
 * Excepción lanzada cuando el usuario solicita salir explícitamente escribiendo "exit".
 */
public class ExitRequestedException extends RuntimeException {

	public ExitRequestedException() {
		super("Exiting...");
		// TODO Constructor generado automáticamente
	}

}
