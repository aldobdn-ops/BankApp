package Model.TransactionTypes;

import Model.Transaction.TransactionType;

/**
 * modelo para la captura de datos de un deposito
 */
public class CashOperationRequest {
	private String iban;
	private double amount;
	private TransactionType tType;
	public CashOperationRequest(String iban, double amount) {
		this.iban = iban;
		this.amount = amount;
	}

	public TransactionType gettType() {
		return tType;
	}

	public void settType(TransactionType tType) {
		this.tType = tType;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
