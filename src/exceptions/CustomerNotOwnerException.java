package exceptions;
/**
 * Excepcion que indica que el cliente no es dueño de la cuenta con la que ha intentado
 * hacer la transaccion
 */
public class CustomerNotOwnerException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotOwnerException() {
		super("You are not owner of this Bank account");
		// TODO Auto-generated constructor stub
	}

}
