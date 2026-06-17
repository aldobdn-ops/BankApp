package Model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Modelo que representa a un Cliente del banco.
 * Hereda de User e incluye sus cuentas bancarias e ingresos anuales.
 */
public class Customer extends User {

	private List <BankAccount> BankAccounts;

	public List<BankAccount> getBankAccounts() {
		return BankAccounts;
	}
	public void setBankAccounts(List<BankAccount> bankAccounts) {
		BankAccounts = bankAccounts;
	}
	public Customer(int idUser, String nIE, String name, String address, String phoneNumber, String email,
			String password, int passwordAttempts, LocalDateTime lastConnection, LocalDateTime registerDate,
			Role role,double annualIncome) {
		super(idUser, nIE, name, address, phoneNumber, email, password, passwordAttempts, lastConnection, registerDate, role);
		this.annualIncome = annualIncome;
	}
	
	
	public Customer(int idUser, String nIE, String name, String password, int passwordAttempts, Role role) {
		super(idUser, nIE, name, password, passwordAttempts, role);
		// TODO Constructor generado automáticamente
	}


	private double annualIncome;
	
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	

}
