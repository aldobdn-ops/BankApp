package controller;

import java.sql.SQLException;

import DAO.AdminDAO;
import DAO.EmployeeDAO;
import Model.Admin;
import Model.Customer;
import Model.Employee;
import Model.User;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import exceptions.BusinessException;
import views.TransactionView;
import views.customer.CustomerView;
import views.employee.EmployeeView;

public class UserSessionFactory {

	private final BankAccountManager bManager;
	private final CustomerManager cManager;
	
	public UserSessionFactory(BankAccountManager bAccMa) {
		this.bManager=bAccMa;
		this.cManager=new CustomerManager();
	}
	public void launch(User u) throws BusinessException,SQLException{
		switch (u.getRole()) {
		case CUSTOMER -> launchCustomer(u);
		case EMPLOYEE -> launchEmployee(u);
		case MANAGER -> launchAdmin(u);
		default -> throw new IllegalArgumentException("Unknown Role :"+u.getRole());
		}
	}
	public void launchCustomer(User u) throws BusinessException,SQLException{
		CustomerView cView = new CustomerView();
		TransactionView tView = new TransactionView();
		Customer C =(Customer)u;
		C.setBankAccounts(cManager.getCustomerBankAccountsAndCards(C.getIdUser()));
		bManager.validBankAccount(C);
		CustomerController CController=new CustomerController(C,cView,bManager,tView,cManager);
		CController.executeCustomer();
	}
	public void launchEmployee(User u) throws BusinessException,SQLException{
		EmployeeDAO eDAO = new EmployeeDAO();
		EmployeeView eView=new EmployeeView();
		Employee e = eDAO.buildSpecificEmployee((Employee)u);
		EmployeeController eController = new EmployeeController(eView,e,bManager);
		eController.executeEmployee();
	}
	public void launchAdmin(User u) throws BusinessException,SQLException{
		AdminDAO aDAO = new AdminDAO();
		Admin a = (Admin)u;
		a = aDAO.buildSpecificAdmin(a);
		
		
		
	}
}
