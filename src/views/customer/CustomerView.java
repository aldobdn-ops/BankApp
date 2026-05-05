package views.customer;

import Model.BankAccount;
import Model.Customer;
import exceptions.ExitException;
import messageService.Messages;
import views.BaseView;
import Model.BankAccount;

public class CustomerView extends BaseView {

	
	public void showCustomerMenu(int id, String iban, double balance) {
	    System.out.println("\n========================================================");
	    System.out.println("                BANKAPP - CUSTOMER AREA                 ");
	    System.out.println("========================================================");

	    System.out.println("User ID: " + id);
	    System.out.println("IBAN: " + iban);
	    System.out.println("Balance: " + balance + " €");

	    System.out.println("--------------------------------------------------------");

	    // [1] Banking Operations
	    System.out.println("  1. BANKING OPERATIONS");

	    // [2] Account Management
	    System.out.println("  2. ACCOUNT MANAGEMENT");

	    // [3] Support
	    System.out.println("  3. SUPPORT");

	    System.out.println("--------------------------------------------------------");
	    System.out.println("  0. LOGOUT");
	}
	public void showUserStats(Customer c,BankAccount cBank) {

	    System.out.println("\n========================================");
	    System.out.println("        BANK ACCOUNT DETAILS            ");
	    System.out.println("========================================");

	 
	    System.out.println("User ID: " + c.getIdUser());
	    System.out.println("Name: " + c.getName());


	    System.out.println("----------------------------------------");
	    System.out.println("IBAN: " + cBank.getIBAN());
	    System.out.println("Balance: " + cBank.getAccountBalance());
	    System.out.println("Current Balance: " + cBank.getCurrentBalance());
	    System.out.println("Transfer Limit: " + cBank.getTransferLimit());
	    System.out.println("Overdraft Limit: " + cBank.getOverdraftLimit());

	    System.out.println("========================================\n");
	}
	
}
