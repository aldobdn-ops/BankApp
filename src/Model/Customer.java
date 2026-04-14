package Model;

import java.util.ArrayList;

public class Customer extends User {

	private String customerId;
	private int numberOfBankAccounts;
	private ArrayList<BankAccount> bankAccounts;
	private double annualIncome;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getNumberOfBankAccounts() {
		return numberOfBankAccounts;
	}

	public void setNumberOfBankAccounts(int numberOfBankAccounts) {
		this.numberOfBankAccounts = numberOfBankAccounts;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

}
