package controller;

import java.sql.SQLException;
import java.util.List;
import Model.BankAccount;
import Model.Customer;
import Model.Transaction;
import Model.TransactionTypes.BizumRequest;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import exceptions.ExitRequestedException;
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
			TransactionView tView, CustomerManager cManager, TransactionManager tManager) {
		super();
		this.c = customer;
		this.cView = cView;
		this.tManager = tManager;
		this.tView = tView;
		this.cManager = cManager;
	}

	/**
	 * Ejecuta el bucle principal del menú de cliente, gestionando opciones y
	 * cancelaciones.
	 * 
	 * @throws SQLException Si ocurre algún error en las operaciones de base de
	 *                      datos
	 */
	public void executeCustomer() throws SQLException { // TODO: implementar la vista del cliente

		while (true) {
			// cView.showMenuAndIntBack(customerMenu(), Messages.MENU_CHOOSE_INT);
			c.setBankAccounts(cManager.getCustomerBankAccountsAndCards(c.getIdUser()));
			while (true) {
				int option = 0;
				try {
					option = cView.showMenuAndIntBack(customerMenu(), Messages.MENU_CHOOSE_INT);
				} catch (ExitRequestedException e) {
					// Escribir 'exit' en el menú principal del cliente vuelve al menú de inicio de sesión
					return;
				}

				try {
					switch (option) {
						case 1 -> deposit();
						case 2 -> withdraw();
						case 3 -> transfer();
						case 4 -> bizum();
						case 5 -> showUserStats();
						case 6 -> viewTransactionHistory();
						case 0 -> {
							cView.showMessage(Messages.EXITING);
							return;
						}
						default -> cView.showMessage(Messages.NOT_VALID_OPTION);
					}
				} catch (ExitRequestedException e) {
					cView.showMessage("Operation cancelled.");
				} catch (BusinessException e) {
					cView.showMessage(e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Crea un ejecutable (Runnable) con la vista del menú del cliente.
	 * 
	 * @return Un objeto Runnable que renderiza el menú.
	 */
	private Runnable customerMenu() {
		List<BankAccount> BankList = c.getBankAccounts();
		return () -> cView.showCustomerMenu(BankList);
		// lambda que crea un ejecutable con las variables
	}

	/**
	 * Muestra por consola las estadísticas del usuario logueado.
	 */
	private void showUserStats() {
		cView.showUserStats(c);
	}

	private void viewTransactionHistory() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		String iban = cView.askForIBAN(Messages.ASK_FOR_IBAN);
		cManager.checkOwnerbyIBAN(c, iban);

		List<Transaction> transactions = tManager.getTransactionsByIBAN(iban);

		cView.showTransactionHistory(transactions, iban);
	}

	/**
	 * Realiza un depósito en efectivo solicitando el importe.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void deposit() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		CashOperationRequest cR = tView.askCashOperationRequest();
		cManager.checkOwnerbyIBAN(c, cR.getIban());
		tManager.makeDeposit(cR);
		cManager.updateCustomerBankInApp(c);
	}

	/**
	 * Realiza un retiro en efectivo solicitando el importe.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void withdraw() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		CashOperationRequest cR = tView.askCashOperationRequest();
		cManager.checkOwnerbyIBAN(c, cR.getIban());
		tManager.makeWithdraw(cR);
		cManager.updateCustomerBankInApp(c);
	}

	/**
	 * Inicia una transferencia bancaria a otra cuenta de destino.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void transfer() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		TransferRequest tR = tView.askTransferRequest();
		cManager.checkOwnerbyIBAN(c, tR.getOriginIBAN());
		tManager.transferService(tR);
		cManager.updateCustomerBankInApp(c);
	}

	/**
	 * Envía un Bizum a otro número de teléfono asociado.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void bizum() throws SQLException {
		cView.showCustomerBankAccounts(c.getBankAccounts());
		BizumRequest bR = tView.askBizumRequest();
		tManager.bizumService(bR);
		cManager.updateCustomerBankInApp(c);

	}
}
