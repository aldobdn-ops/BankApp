package controller;

import java.sql.SQLException;

import DAO.AdminDAO;
import DAO.BankAccountDAO;
import DAO.EmployeeDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Model.Admin;
import Model.Customer;
import Model.Employee;
import Model.User;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.AccountManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import views.TransactionView;
import views.admin.AdminView;
import views.customer.CustomerView;
import views.employee.EmployeeView;

/**
 * Fábrica encargada de iniciar y coordinar la sesión específica de cada usuario
 * (Cliente, Empleado, Administrador) basándose en su rol.
 */
public class UserSessionFactory {

	private final BankAccountManager bManager;
	private final CustomerManager cManager;
	private final AccountManager aManager;
	private final UserDAO uDao;

	public UserSessionFactory(BankAccountManager bAccMa, AccountManager aManager, UserDAO uDao) {
		this.bManager = bAccMa;
		this.aManager = aManager;
		this.uDao = uDao;
		this.cManager = new CustomerManager(uDao);
	}

	/**
	 * Redirecciona al usuario a la sesión correspondiente basada en su rol.
	 * 
	 * @param u El usuario autenticado
	 * @throws BusinessException Si hay violaciones en las reglas de negocio al
	 *                           abrir la sesión
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void launch(User u) throws BusinessException, SQLException {
		switch (u.getRole()) {
			case CUSTOMER -> launchCustomer(u);
			case EMPLOYEE -> launchEmployee(u);
			case MANAGER -> launchAdmin(u);
			default -> throw new IllegalArgumentException("Unknown Role :" + u.getRole());
		}
	}

	/**
	 * Configura y lanza el panel de control del cliente.
	 * 
	 * @param u El usuario de tipo Cliente
	 * @throws BusinessException Si hay errores al validar la cuenta del cliente
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void launchCustomer(User u) throws BusinessException, SQLException {
		CustomerView cView = new CustomerView();
		TransactionView tView = new TransactionView();
		Customer C = (Customer) u;
		C.setBankAccounts(cManager.getCustomerBankAccountsAndCards(C.getIdUser()));
		bManager.validBankAccount(C);
		TransactionManager tManager = new TransactionManager();
		CustomerController CController = new CustomerController(C, cView, bManager, tView, cManager, tManager);
		CController.executeCustomer();
	}

	/**
	 * Configura y lanza el panel de control del empleado.
	 * 
	 * @param u El usuario de tipo Empleado
	 * @throws BusinessException Si hay errores al validar datos del empleado
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void launchEmployee(User u) throws BusinessException, SQLException {
		EmployeeDAO eDAO = new EmployeeDAO();
		EmployeeView eView = new EmployeeView();
		Employee e = eDAO.buildSpecificEmployee((Employee) u);
		TransactionView tView = new TransactionView();
		TransactionManager tManager = new TransactionManager();
		EmployeeController eController = new EmployeeController(eView, e, bManager, tView, tManager, this.cManager);
		eController.executeEmployee();
	}

	/**
	 * Configura y lanza el panel de control de administración.
	 * 
	 * @param u El usuario de tipo Administrador
	 * @throws BusinessException Si hay errores al validar datos de administración
	 * @throws SQLException      Si ocurre algún fallo de base de datos
	 */
	public void launchAdmin(User u) throws BusinessException, SQLException {
		AdminDAO aDAO = new AdminDAO();
		AdminView aView = new AdminView();
		TransactionManager tManager = new TransactionManager();
		Admin a = (Admin) u;
		a = aDAO.buildSpecificAdmin(a);
		AdminController aController = new AdminController(aView, a, this.aManager, this.cManager, tManager);
		aController.executeAdmin();
	}
}
