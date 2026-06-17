package controller;

import java.sql.SQLException;
import java.util.List;

import Model.Customer;
import Model.CustomerAccountDetails;
import Model.Employee;
import Model.Transaction;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import exceptions.ExitRequestedException;
import messageService.Messages;
import views.employee.EmployeeView;
import views.TransactionView;

/**
 * Controlador de empleado.
 * Coordina el menú unificado de acciones del empleado, capturando y
 * mostrando los errores de negocio y base de datos para mantener la sesión
 * activa.
 */
public class EmployeeController {

	private EmployeeView eView;
	private Employee e;
	private BankAccountManager bManager;
	private TransactionView tView;
	private TransactionManager tManager;
	private CustomerManager cManager;

	public EmployeeController(EmployeeView eView, Employee e, BankAccountManager bManager, TransactionView tView,
			TransactionManager tManager, CustomerManager cManager) {
		this.eView = eView;
		this.e = e;
		this.bManager = bManager;
		this.tView = tView;
		this.tManager = tManager;
		this.cManager = cManager;
	}

	/**
	 * Ejecuta el bucle principal de opciones y navegación simplificado para el
	 * empleado.
	 * 
	 * @throws SQLException Si ocurre algún fallo crítico en la base de datos
	 */
	public void executeEmployee() throws SQLException {
		int option = 0;
		do {
			try {
				option = eView.showMenuAndIntBack(employeeMenu(), Messages.MENU_CHOOSE_INT);
			} catch (ExitRequestedException e) {
				return;
			}
			try {
				switch (option) {
					case 1 -> searchOwnerByIBAN();
					case 2 -> viewCustomerAccountDetails();
					case 3 -> viewBankAccountDetailsByIBAN();
					case 4 -> TransferBehalfUser();
					case 5 -> Deposit();
					case 6 -> Withdraw();
					case 7 -> viewTransactionHistory();
					case 0 -> {
						eView.showMessage(Messages.THANKS_FOR_USING);
						return;
					}
					default -> eView.showMessage(Messages.NOT_VALID_OPTION);
				}
			} catch (ExitRequestedException e) {
				eView.showMessage("Operation cancelled.");
			} catch (BusinessException e) {
				eView.showMessage(e.getMessage());
			} catch (SQLException e) {
				eView.showMessage(Messages.PROBLEM_WITH_DB);
				e.printStackTrace();
			}
		} while (option != 0);
	}

	/**
	 * Crea un ejecutable para pintar el menú principal del empleado con sus datos.
	 * 
	 * @return Un objeto Runnable que renderiza el menú del empleado.
	 */
	private Runnable employeeMenu() {
		String Ename = e.getName();
		int Eid = e.getIdUser();
		return () -> eView.showEmployeeMenu(Ename, Eid);
	}

	/**
	 * Busca y muestra el nombre del titular asociado a un IBAN solicitado.
	 * 
	 * @throws BusinessException Si el IBAN es incorrecto o no existe
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void searchOwnerByIBAN() throws BusinessException, SQLException {
		String owner = bManager.searchAccountHolderbyIban(eView.askForIBAN(Messages.ASK_FOR_IBAN));
		eView.showMessage(Messages.ANSWER_USER_NAME + owner);
	}

	/**
	 * Muestra la información y saldos de las cuentas de un cliente buscando por su
	 * NIE.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public void viewCustomerAccountDetails() throws SQLException {
		String Nie = eView.askForNIE(Messages.ASK_FOR_NIE);
		Customer c = cManager.getUserifItsCustomer(Nie);
		eView.showUserStats(c);
	}

	/**
	 * Muestra la información detallada de una cuenta bancaria buscando por su IBAN.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public void viewBankAccountDetailsByIBAN() throws BusinessException, SQLException {
		String iban = eView.askForIBAN(Messages.ASK_FOR_IBAN);
		Model.BankAccount b = bManager.getBankAccountByIBAN(iban);
		String owner = bManager.searchAccountHolderbyIban(iban);
		eView.showBankAccountStats(b, owner);
	}

	/**
	 * Muestra el historial de transacciones de una cuenta bancaria buscando por su
	 * IBAN.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public void viewTransactionHistory() throws SQLException {
		String iban = eView.askForIBAN(Messages.ASK_FOR_IBAN);
		List<Transaction> transactions = tManager.getTransactionsByIBAN(iban);
		eView.showTransactionHistory(transactions, iban);
	}

	/**
	 * Realiza una transferencia de dinero entre cuentas en nombre de un cliente.
	 * 
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public void TransferBehalfUser() throws SQLException {
		TransferRequest tR = tView.askTransferRequest();
		tManager.transferService(tR);
	}

	/**
	 * Realiza un depósito de efectivo en la cuenta especificada.
	 * 
	 * @throws BusinessException Si hay fallos de validación o lógica
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void Deposit() throws BusinessException, SQLException {
		CashOperationRequest cR = tView.askCashOperationRequest();
		tManager.makeDeposit(cR);
	}

	/**
	 * Realiza un retiro de dinero en efectivo de la cuenta especificada.
	 * 
	 * @throws BusinessException Si hay fallos de validación o lógica
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void Withdraw() throws BusinessException, SQLException {
		CashOperationRequest cR = tView.askCashOperationRequest();
		tManager.makeWithdraw(cR);
	}
}
