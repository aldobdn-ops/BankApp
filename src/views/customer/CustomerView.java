package views.customer;
import java.util.List;

import Model.BankAccount;
import Model.Transaction;
/**
 * vistas propias de cliente
 */
import views.BaseView;;

public class CustomerView extends BaseView {

	
	public void showCustomerMenu(List<BankAccount> accounts) {

		System.out.println("\n========================================================");
		System.out.println("                BANKAPP - CUSTOMER AREA                 ");
		System.out.println("========================================================");


		System.out.println("--------------------------------------------------------");
		System.out.println("                    CUSTOMER ACCOUNTS                   ");
		System.out.println("--------------------------------------------------------");

		for (BankAccount account : accounts) {

			System.out.println("IBAN: " + account.getIBAN());

			System.out.println("Bizum Phone: " + account.getBizumPhone());

			System.out.println("--------------------------------------------------------");
		}
		showBankingOperationsMenu();
		
	}
	public void showCustomerBankAccounts(List<BankAccount> cAccounts) {
		for (BankAccount cAccount:cAccounts) {
			System.out.println(
					"IBAN: " + cAccount.getIBAN()
					+ " | Balance: "
					+ cAccount.getCurrentBalance()
					+ "€"
					+ " | Bizum Phone: "
					+ cAccount.getBizumPhone()
			);
		}
	}
	public void showBankingOperationsMenu() {
	    System.out.println("\n========================================================");
	    System.out.println("              BANKAPP - BANKING OPERATIONS             ");
	    System.out.println("========================================================");
	    System.out.println("  1. Deposit money");
	    System.out.println("  2. Withdraw money");
	    System.out.println("  3. Transfer money");
	    System.out.println("  4. Bizum");
	    System.out.println("  5. View account details");
	    System.out.println("  6. View transaction history");
	    System.out.println("--------------------------------------------------------");
	    System.out.println("  0. Back");
	    System.out.println("========================================================");
	    
	}
}
