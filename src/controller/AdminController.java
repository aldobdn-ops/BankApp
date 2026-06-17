package controller;

import java.sql.SQLException;
import java.util.List;

import Model.Admin;
import Model.BankAccount;
import Model.Customer;
import Model.Transaction;
import Model.User;
import businessLogic.AccountManager;
import businessLogic.CustomerManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import exceptions.ExitRequestedException;
import exceptions.UserNotFoundException;
import messageService.Messages;
import views.admin.AdminView;

/**
 * Controlador de administrador del sistema.
 * Recibe todas sus dependencias por constructor. Gestiona el menú unificado de
 * administración,
 * permitiendo auditar cuentas, consultar historiales de transacciones y
 * restablecer intentos de contraseña.
 */
public class AdminController {

	private AdminView aView;
	private Admin a;
	private AccountManager accManager;
	private CustomerManager cManager;
	private TransactionManager tManager;

	public AdminController(AdminView aView, Admin a, AccountManager accManager, CustomerManager cManager,
			TransactionManager tManager) {
		super();
		this.aView = aView;
		this.a = a;
		this.accManager = accManager;
		this.cManager = cManager;
		this.tManager = tManager;
	}

	/**
	 * Ejecuta el bucle principal de control y manejo de excepciones para el
	 * administrador.
	 * 
	 * @throws SQLException Si ocurre algún fallo crítico en la base de datos
	 */
	public void executeAdmin() throws SQLException {
		int option = 0;
		do {
			try {
				option = aView.showMenuAndIntBack(adminMenu(), Messages.MENU_CHOOSE_INT);
			} catch (ExitRequestedException e) {
				return;
			}
			try {
				switch (option) {
					case 1 -> resetPasswordAttempts();
					case 2 -> checkCustomerAccountsAndTransactions();
					case 0 -> {
						aView.showMessage(Messages.THANKS_FOR_USING);
						return;
					}
					default -> aView.showMessage(Messages.NOT_VALID_OPTION);
				}
			} catch (ExitRequestedException e) {
				aView.showMessage("Operation cancelled.");
			} catch (BusinessException e) {
				aView.showMessage(e.getMessage());
			} catch (SQLException e) {
				aView.showMessage(Messages.PROBLEM_WITH_DB);
				e.printStackTrace();
			}
		} while (option != 0);
	}

	/**
	 * Crea un ejecutable para pintar el menú principal del administrador.
	 * 
	 * @return Un objeto Runnable que renderiza el menú.
	 */
	private Runnable adminMenu() {
		return () -> aView.displayAdminMenu(a.getName());
	}

	/**
	 * Restablece los intentos de contraseña a 0 para el usuario especificado por
	 * NIE/DNI.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void resetPasswordAttempts() throws SQLException {
		String nie = aView.askForNIE(Messages.ASK_FOR_NIE + " del usuario a desbloquear");
		User u = accManager.findSimpleUserByNIE(nie);
		if (u == null) {
			throw new UserNotFoundException();
		}
		accManager.resetUserPasswordAttempts(nie);
		aView.showMessage("Intentos de contraseña reiniciados a 0 para: " + u.getName());
	}

	/**
	 * Comprueba todas las cuentas bancarias de un cliente por su DNI/NIE y muestra
	 * su
	 * información general seguida del historial completo de transacciones de cada
	 * cuenta.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	private void checkCustomerAccountsAndTransactions() throws SQLException {
		String nie = aView.askForNIE(Messages.ASK_FOR_NIE + " del cliente");
		Customer c = cManager.getUserifItsCustomer(nie);

		// 1. Mostrar información general de todas sus cuentas
		aView.showUserStats(c);

		// 2. Mostrar transacciones de cada una de sus cuentas
		for (BankAccount account : c.getBankAccounts()) {
			List<Transaction> transactions = tManager.getTransactionsByAccountId(account.getIdBankAccount());
			aView.showTransactionHistory(transactions, account.getIBAN());
		}
	}
}
