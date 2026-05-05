package controller;

import java.sql.SQLException;

import DAO.BankAccountDAO;
import DAO.CustomerDAO;
import Model.BankAccount;
import Model.Customer;
import Model.TransactionTypes.Bizum;
import businessLogic.BankAccountManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import exceptions.DecimalNotAcceptedException;
import exceptions.ExitException;
import messageService.Messages;
import views.customer.CustomerView;
import views.customer.subMenuBankingOperationView;

public class CustomerController {

	private Customer c;
	private CustomerView cView;
	private BankAccount cBankAccount;
	private CustomerDAO cDao;
	private BankAccountManager bankManager;
	private TransactionManager tManager;
	private BankAccountDAO bDao;

	public CustomerController(Customer customer, CustomerView cView, CustomerDAO cusDAO, BankAccount cBankAccount) {
		super();
		this.c = customer;
		this.cView = cView;
		this.cDao = cusDAO;
		this.cBankAccount = cBankAccount;
		this.bankManager = new BankAccountManager();
		this.bDao= new BankAccountDAO();
		this.tManager = new TransactionManager(bDao);
		
	}

	public void executeCustomer() { // todo implementar la vista del cliente
		while (true) {
		int option = cView.showMenuAndIntBack(customerMenu(), Messages.MENU_CHOOSE_INT);
		
		switch (option) {
		case 1 -> bankingOperations();
		// case 2: -> accountManagement();
		// case 3: -> support();
		case 0 -> { cView.showMessage(Messages.EXITING);
					return;
				}
		default -> throw new IllegalArgumentException("Unexpected value: " + option);
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

		int id = c.getIdUser();
		String iban = cBankAccount.getIBAN();
		double balance = cBankAccount.getAccountBalance();
		return () -> cView.showCustomerMenu(id, iban, balance);
		// lambda que crea un ejecutable con las variables
	}

	private void showUserStats() {
		cView.showUserStats(c, cBankAccount);
	}
	private void bankingOperations() {
		int option=0;
		while (true) {
			
		try {
			subMenuBankingOperationView subBanking = new subMenuBankingOperationView();
			option=cView.showMenuAndIntBack(subBanking::showBankingOperationsMenu, Messages.MENU_CHOOSE_INT);
			switch (option) {
			case 1 -> deposit();
			case 2 -> withdraw();
			case 3 -> transfer();
			case 4 -> bizum();
			case 5 -> showUserStats();
			case 0 -> { cView.showMessage(Messages.EXITING);
						return;
					}
			default-> cView.showMessage(Messages.NOT_VALID_OPTION);
			}
		} catch (BusinessException e) {
			cView.showMessage(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
			
		}
		
	
	private void deposit() throws SQLException {
		tManager.makeDeposit(cView.getIntAmountorExit(), cBankAccount.getIBAN());
	}
	private void withdraw() throws SQLException{
		tManager.makeWithdraw(cView.getIntAmountorExit(), cBankAccount.getIBAN());
	}
	private void transfer() throws SQLException{
		tManager.transferService(cView.getIntAmountorExit(),
				 cView.askForIBAN(Messages.ASK_FOR_ORIGIN_IBAN)
				,cView.askForIBAN(Messages.ASK_FOR_DESTINY_IBAN));
	}
	private void bizum() throws SQLException{
		tManager.transferService(cView.getIntAmountorExit(), 
				bDao.getIBANbyphone(cView.askForPhoneNumber(Messages.ASK_FOR_ORIGIN_PHONE)), 
				bDao.getIBANbyphone(cView.askForPhoneNumber(Messages.ASK_FOR_DESTINY_PHONE)));
	}
}

	

