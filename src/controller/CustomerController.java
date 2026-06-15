package controller;

import java.sql.SQLException;
import java.util.List;
import Model.BankAccount;
import Model.Customer;
import Model.TransactionTypes.BizumRequest;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import messageService.Messages;
import views.TransactionView;
import views.customer.CustomerView;

public class CustomerController {

	private Customer c;
	private CustomerView cView;
	private TransactionManager tManager;
	private TransactionView tView;
	private CustomerManager cManager;

	public CustomerController(Customer customer, CustomerView cView, BankAccountManager BAccManager,
			TransactionView tView, CustomerManager cManager) {
		super();
		this.c = customer;
		this.cView = cView;
		this.tManager = new TransactionManager();
		this.tView = tView;
		this.cManager = cManager;
	}

	public void executeCustomer() throws SQLException { // todo implementar la vista del cliente

		while (true) {
			//cView.showMenuAndIntBack(customerMenu(), Messages.MENU_CHOOSE_INT);
			c.setBankAccounts(cManager.getCustomerBankAccountsAndCards(c.getIdUser()));
			while (true) {
				int option=0;
				try {
					option = cView.showMenuAndIntBack(customerMenu(), Messages.MENU_CHOOSE_INT);
					switch (option) {
					case 1 -> deposit();
					case 2 -> withdraw();
					case 3 -> transfer();
					case 4 -> bizum();
					case 5 -> showUserStats();
					case 0 -> {
						cView.showMessage(Messages.EXITING);
						return;
					}
					default -> cView.showMessage(Messages.NOT_VALID_OPTION);
					}
				} catch (BusinessException e) {
					cView.showMessage(e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * // funcion para crear el menu ejecutable con los datos del cliente que se
	 * muestran // en el menu
	 * 
	 * @return
	 */
	private Runnable customerMenu() {
		List<BankAccount> BankList = c.getBankAccounts();
		return () -> cView.showCustomerMenu(BankList);
		// lambda que crea un ejecutable con las variables
	}

	private void showUserStats() {
		cView.showUserStats(c);
	}

	private void deposit() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		CashOperationRequest cR = tView.askCashOperationRequest();
		cManager.checkOwnerbyIBAN(c, cR.getIban());
		tManager.makeDeposit(cR);
		cManager.updateCustomerBankInApp(c);
	}

	private void withdraw() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		CashOperationRequest cR = tView.askCashOperationRequest();
		cManager.checkOwnerbyIBAN(c, cR.getIban());
		tManager.makeWithdraw(cR);
		cManager.updateCustomerBankInApp(c);
	}

	private void transfer() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		TransferRequest tR = tView.askTransferRequest();
		cManager.checkOwnerbyIBAN(c, tR.getOriginIBAN());
		tManager.transferService(tR);
		cManager.updateCustomerBankInApp(c);
	}

	private void bizum() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		BizumRequest bR = tView.askBizumRequest();
		tManager.bizumService(bR);
		cManager.updateCustomerBankInApp(c);

	}
}

	

