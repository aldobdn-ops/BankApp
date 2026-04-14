package Model;

import java.util.List;

public class BankAccount {

	private double currentBalance;
	private double accountBalance;
	private String IBAN;
	private double transferLimit;
	private List<Card> associatedCards;
	private double overdraftLimit;

	public BankAccount() {
	}

	public BankAccount(double currentBalance, double accountBalance, String iBAN, double transferLimit,
			double overdraftLimit) {
		super();
		this.currentBalance = currentBalance;
		this.accountBalance = accountBalance;
		IBAN = iBAN;
		this.transferLimit = transferLimit;
		this.overdraftLimit = overdraftLimit;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public double getTransferLimit() {
		return transferLimit;
	}

	public void setTransferLimit(double transferLimit) {
		this.transferLimit = transferLimit;
	}

	public List<Card> getAssociatedCards() {
		return associatedCards;
	}

	public void setAssociatedCards(List<Card> associatedCards) {
		this.associatedCards = associatedCards;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

}
