package businessLogic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import DAO.BankAccountDAO;
import DAO.TransactionDAO;
import Model.Transaction;
import Model.Transaction.TransactionStatus;
import Model.Transaction.TransactionType;
import Model.TransactionTypes.BizumRequest;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import exceptions.AmountOverTransferLimitException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
/**
 * Gestor de operaciones bancarias
 */
public class TransactionManager {

	private BankAccountDAO bDao;
	private TransactionDAO tDao;

	public TransactionManager() {;
		this.bDao = new BankAccountDAO();
		this.tDao= new TransactionDAO();
	}

	public void transferService(TransferRequest tr) throws SQLException {
		double originAccBalance = bDao.getBalancebyIBAN(tr.getOriginIBAN());
		double destinyAccBalance = bDao.getBalancebyIBAN(tr.getDestinyIBAN());
		double originTrLimit = bDao.getTrLimitbyIBAN(tr.getOriginIBAN());
		if (tr.getAmount() <= 0) {
			throw new InvalidAmountException();
		}
		if (tr.getAmount() > originAccBalance) {
			throw new InsufficientFundsException();
		}
		if (tr.getAmount() > originTrLimit) {
			throw new AmountOverTransferLimitException();
		}
		bDao.updateBaBalance(tr.getOriginIBAN(), originAccBalance - tr.getAmount());
		bDao.updateBaBalance(tr.getDestinyIBAN(), destinyAccBalance + tr.getAmount());
		Transaction t = buildTransferTransaction(tr);
		tDao.saveTransfer(t);
	}
	public void bizumService(BizumRequest bR) throws SQLException {
		String oIBAN=bDao.getIBANbyphone(bR.getOriginPhoneNumber());
		String dIBAN=bDao.getIBANbyphone(bR.getDestinyPhoneNumber());
		double originAccBalance = bDao.getBalancebyIBAN(oIBAN);
		double destinyAccBalance = bDao.getBalancebyIBAN(dIBAN);
		double originTrLimit = bDao.getTrLimitbyIBAN(oIBAN);
		if (bR.getAmount() <= 0) {
			throw new InvalidAmountException();
		}
		if (bR.getAmount() > originAccBalance) {
			throw new InsufficientFundsException();
		}
		if (bR.getAmount() > originTrLimit) {
			throw new AmountOverTransferLimitException();
		}
		bDao.updateBaBalance(oIBAN, originAccBalance - bR.getAmount());
		bDao.updateBaBalance(dIBAN, destinyAccBalance + bR.getAmount());
		Transaction t = buildBizumTransaction(bR);
		tDao.saveBizum(t);
	}
	public void makeDeposit(CashOperationRequest dR) throws SQLException {
		if (dR.getAmount() < 0 || dR.getAmount() == 0) {
			throw new InvalidAmountException();
		}
		dR.settType(TransactionType.DEPOSIT);
		bDao.updateBaBalance(dR.getIban(), (dR.getAmount() + bDao.getBalancebyIBAN(dR.getIban())));
		Transaction t= buildCashTransaction(dR);
		tDao.saveCashOperation(t);
	}

	public void makeWithdraw(CashOperationRequest cR) throws SQLException {
		if (cR.getAmount() < 0 || cR.getAmount() == 0) {
			throw new InvalidAmountException();
		}
		if (bDao.getBalancebyIBAN(cR.getIban()) < cR.getAmount()) {
			throw new InsufficientFundsException();
		}
		cR.settType(TransactionType.WITHDRAWAL);
		bDao.updateBaBalance(cR.getIban(), (bDao.getBalancebyIBAN(cR.getIban())-cR.getAmount()));
		Transaction t=buildCashTransaction(cR);
		tDao.saveCashOperation(t);
	}
	public Transaction buildTransferTransaction(TransferRequest tR) throws SQLException {
		int idBaOrigin=bDao.getIdBankbyIBAN(tR.getOriginIBAN());
		int idBaDestiny=bDao.getIdBankbyIBAN(tR.getDestinyIBAN());
		TransactionStatus ts = TransactionStatus.COMPLETED;
		return new Transaction(tR.getAmount(),idBaOrigin,idBaDestiny,tR.getOriginIBAN(),tR.getDestinyIBAN(),
				tR.gettType(),ts);
	}
	public Transaction buildCashTransaction(CashOperationRequest cO) throws SQLException{
		int oAccount=bDao.getIdBankbyIBAN(cO.getIban());
		TransactionStatus ts= TransactionStatus.COMPLETED;
		return new Transaction(cO.getAmount(),oAccount,cO.getIban(),cO.gettType(),ts);
	}
	public Transaction buildBizumTransaction(BizumRequest bR) throws SQLException {
		String originIBAN = bDao.getIBANbyphone(bR.getOriginPhoneNumber());
		String destinyIBAN = bDao.getIBANbyphone(bR.getDestinyPhoneNumber());
		int idOriginAcc=bDao.getIdBankbyIBAN(originIBAN);
		int idDestinyAcc=bDao.getIdBankbyIBAN(destinyIBAN);
		return new Transaction(bR.getAmount(),idOriginAcc,idDestinyAcc,originIBAN,destinyIBAN,
				bR.getOriginPhoneNumber(),bR.getDestinyPhoneNumber(),TransactionType.BIZUM,
				Transaction.TransactionStatus.COMPLETED);
	}
}
