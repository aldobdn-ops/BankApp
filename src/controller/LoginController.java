package controller;
import views.LoginView;
import views.admin.AdminView;
import views.customer.CustomerView;
import views.employee.EmployeeView;

import java.sql.SQLException;
import DAO.AdminDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.UserDAO;
import Model.Admin;
import Model.BankAccount;
import Model.Customer;
import Model.Employee;
import Model.User;
import auth.AuthService;
import businessLogic.AccountManager;
import businessLogic.BankAccountManager;
import messageService.*;
import controller.CustomerController;
import exceptions.BusinessException;

public class LoginController {
	private final LoginView lView;
	private final UserDAO uDAO;
	private final AuthService authService;
	private final BankAccountManager bankManager;
	private final AccountManager Amanager;

	/**
	 * Clase para gestionar el login del usuario, encargada de instanciar el tipo de
	 * usuario inicie sesion
	 * 
	 * @param loginView
	 * @param userDAO
	 * @param authService
	 * @param bA
	 * @param aManager
	 */
	public LoginController(LoginView loginView, UserDAO userDAO, AuthService authService, BankAccountManager bA,
			AccountManager aManager) {
		this.lView = loginView;
		this.uDAO = userDAO;
		this.authService = authService;
		this.bankManager = bA;
		this.Amanager = aManager;
	}

	/**
	 * funcion para gestionar el login
	 */
	public void LoginEntry() {
		int option = lView.showMenuAndIntBack(lView::loginMenu, Messages.MENU_CHOOSE_INT);
		switch (option) {
		case 1 -> signIn();
		// case 2 -> forgotPassword();
		// case 3 -> exit();
		default -> throw new IllegalArgumentException("Unexpected value entry");
		}
	}

	/**
	 * funcion para controlar directamente la introducción y verificación de usuario
	 * y contraseña
	 */
	public void signIn() {
		while (true) {
			String user = (lView.showMenuAndStringBack(lView::enterNIEView, Messages.LOGIN_ASK_NIE)).trim();
			String password = lView.showMenuAndStringBack(lView::enterPasswordView, Messages.LOGIN_ASK_PASSWORD).trim();
			try {
				User u = uDAO.findUserByNIE(user);
				Amanager.validAccount(u);
				authService.login(u, password);
				switch (u.getRole()) {
				case CUSTOMER:
					CustomerView cView = new CustomerView();
					CustomerDAO cDAO = new CustomerDAO();
					Customer c = null;
					BankAccount uBank = cDAO.getCustomerBankAccountbyId(u.getIdUser());
					bankManager.validBankAccount(uBank);
					if (u instanceof Customer) {
						c = (Customer) u;
					}
					CustomerController cController = new CustomerController(c, cView, cDAO, uBank);
					cController.executeCustomer();// añadir bankaccount
					break;
				case EMPLOYEE:
					EmployeeView eView = new EmployeeView();
					EmployeeDAO eDao = new EmployeeDAO();
					Employee e = null;
					if (u instanceof Employee) {
						e = (Employee) u;
					}
					e = eDao.buildSpecificEmployee(e);
					EmployeeController eController = new EmployeeController(eDao, eView, e);
					eController.executeEmployee();

					break;
				case MANAGER:
					AdminView aView = new AdminView();
					AdminDAO aDAO = new AdminDAO();
					Admin a = null;
					if (u instanceof Admin) {
						a = (Admin) u;
					}
					AdminController aController = new AdminController(aDAO, aView, a);
					aController.mainAdminMenu();
					break;

				default:
					throw new IllegalArgumentException("Unexpected value: " + u.getRole());
				}
			} catch (BusinessException e) {
				lView.showMessage(e.getMessage());
			} catch (SQLException e) {
				lView.showMessage("Problem with database");
				e.printStackTrace();
			}
		}
	}
}
