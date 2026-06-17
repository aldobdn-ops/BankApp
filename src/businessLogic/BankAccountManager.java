package businessLogic;
import java.sql.SQLException;
import Model.BankAccount;
import Model.Customer;
import exceptions.BusinessException;
import exceptions.InvalidIBANException;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
/**
 * Gestor de lógica de negocio para las cuentas bancarias.
 * Valida cuentas y realiza consultas sobre los titulares de las mismas.
 */
public class BankAccountManager {

	private BankAccountDAO bDao = new BankAccountDAO();

	
	public boolean validBankAccount(Customer C) throws BusinessException {
		if(C.getBankAccounts()==null) {
			return false;
		}
		else {
			return true;
		}
	}
	public String searchAccountHolderbyIban(String iban) throws BusinessException,SQLException {
		if (iban==null||iban.isBlank()) {
			throw new InvalidIBANException();
		}
		else {
			return bDao.getAccountOwnerByIBAN(iban);
		}
	}

	/**
	 * Recupera la información completa de una cuenta bancaria buscando por su IBAN.
	 * @param iban El IBAN de la cuenta
	 * @return El objeto BankAccount correspondiente
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public BankAccount getBankAccountByIBAN(String iban) throws SQLException {
		return bDao.getBankAccountByIBAN(iban);
	}
}
