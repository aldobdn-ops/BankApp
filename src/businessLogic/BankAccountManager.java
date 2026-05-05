package businessLogic;
import Model.BankAccount;
import exceptions.BusinessException;
public class BankAccountManager {

	
	public boolean validBankAccount(BankAccount bA) throws BusinessException {
		if(bA==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
}
