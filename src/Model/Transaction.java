package Model;

public abstract class Transaction {

	protected String idTransaction;
	protected double amount;
	protected String transtactionDate;

	protected enum transactionStatus {
		COMPLETED, PENDING, FAILED, CANCELED
	}

	protected String originatingAccount;

	// metodos abstractos que comparten cada transacción
	public abstract boolean execute();

	public abstract void generateReceipt();

}
