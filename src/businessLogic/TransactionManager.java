package businessLogic;

import java.sql.SQLException;

import DAO.BankAccountDAO;
import Model.BankAccount;
import exceptions.AmountOverTransferLimitException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;

public class TransactionManager {

	private BankAccountDAO bDao;

	public TransactionManager(BankAccountDAO bDao) {
		super();
		this.bDao = bDao;
	}

	public void transferService(double amount, String originIBAN, String destinyIBAN) throws SQLException {
		double originAccBalance = bDao.getBalancebyIBAN(originIBAN);
		double destinyAccBalance = bDao.getBalancebyIBAN(destinyIBAN);
		double originTrLimit = bDao.getTrLimitbyIBAN(originIBAN);
		if (amount <= 0) {
			throw new InvalidAmountException();
		}
		if (amount > originAccBalance) {
			throw new InsufficientFundsException();
		}
		if (amount > originTrLimit) {
			throw new AmountOverTransferLimitException();
		}
		bDao.updateBaBalance(originIBAN, originAccBalance - amount);
		bDao.updateBaBalance(destinyIBAN, destinyAccBalance + amount);
	}

	public void makeDeposit(int amount, String IBAN) throws SQLException {
		if (amount < 0 || amount == 0) {
			throw new InvalidAmountException();
		}
		bDao.updateBaBalance(IBAN, (amount + bDao.getBalancebyIBAN(IBAN)));
	}

	public void makeWithdraw(double amount, String IBAN) throws SQLException {
		if (amount < 0 || amount == 0) {
			throw new InvalidAmountException();
		}
		bDao.updateBaBalance(IBAN, (bDao.getBalancebyIBAN(IBAN)-amount));
	}

}
