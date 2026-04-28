package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends User {

	
	
	public Customer(int idUser, String nIE, String name, String address, String phoneNumber, String email,
			String password, int passwordAttempts, LocalDateTime lastConnection, LocalDateTime registerDate,
			Role role,double annualIncome) {
		super(idUser, nIE, name, address, phoneNumber, email, password, passwordAttempts, lastConnection, registerDate, role);
		this.annualIncome = annualIncome;
	}
	private double annualIncome;
	
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	

}
