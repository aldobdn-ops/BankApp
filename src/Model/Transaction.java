package Model;

import Model.TransactionTypes.CardPayment;

public abstract class Transaction {

	protected String idTransaction;
	protected double amount;
	protected String transtactionDate;
	protected int idAccountOrigin;
	protected int idAccountDestination;
	protected enum TransactionType{
		TRANSFER,
		BIZUM,
		CARDPAYMENT;
	}
	protected TransactionType tType;
	protected enum TransactionStatus {
		COMPLETED, PENDING, FAILED, CANCELED
	}
	protected TransactionStatus tStatus;
	protected String originatingAccount;

	// metodos abstractos que comparten cada transacción
	public abstract boolean execute();

	public abstract void generateReceipt();

}
