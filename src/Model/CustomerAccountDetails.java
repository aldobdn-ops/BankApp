package Model;

import java.util.List;

/**
 * agrupa la cuenta y la cuenta bancaria del usuario para ejecutar operaciones
 */
public class CustomerAccountDetails {

	private Customer customer;

	public CustomerAccountDetails(Customer customer, List <BankAccount> baList) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

}
