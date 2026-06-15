package businessLogic;
import java.sql.SQLException;
import Model.BankAccount;
import Model.Customer;
import exceptions.BusinessException;
import exceptions.InvalidIBANException;
import DAO.BankAccountDAO;
import DAO.CustomerDAO;
public class BankAccountManager {

	private BankAccountDAO bDao;

	
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
	
}
