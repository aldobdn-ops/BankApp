package Model;

import java.util.List;

public class BankAccount {
	private int idUser;
	private int idBankAccount;
	private double currentBalance;
	private String IBAN;
	private double transferLimit;
	private List<Card> associatedCards;
	private double overdraftLimit;
	private String bizumPhone;

	public BankAccount() {
	}

	public BankAccount(int idUser, int idBankAccount, double currentBalance,String IBAN,
			double transferLimit, /**List<Card> associatedCards,**/ double overdraftLimit,String BizumPhone) {

		this.idUser = idUser;
		this.idBankAccount = idBankAccount;
		this.currentBalance = currentBalance;
		this.IBAN = IBAN;
		this.transferLimit = transferLimit;
		//this.associatedCards = associatedCards;
		this.overdraftLimit = overdraftLimit;
		this.bizumPhone= BizumPhone;
	}

	public String getBizumPhone() {
		return bizumPhone;
	}

	public void setBizumPhone(String bizumPhone) {
		this.bizumPhone = bizumPhone;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdBankAccount() {
		return idBankAccount;
	}

	public void setIdBankAccount(int idBankAccount) {
		this.idBankAccount = idBankAccount;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
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
