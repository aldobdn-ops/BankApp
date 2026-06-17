package exceptions;

/**
 * Excepción base para todos los errores relacionados con las reglas de negocio de la aplicación.
 */
public class BusinessException extends RuntimeException{


	  public BusinessException (String message) {
	        super(message);
	    }
}
