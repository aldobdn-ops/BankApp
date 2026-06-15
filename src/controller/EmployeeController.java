package controller;

import java.sql.SQLException;

import Model.Customer;
import Model.CustomerAccountDetails;
import Model.Employee;
import Model.TransactionTypes.CashOperationRequest;
import Model.TransactionTypes.TransferRequest;
import businessLogic.BankAccountManager;
import businessLogic.CustomerManager;
import businessLogic.TransactionManager;
import exceptions.BusinessException;
import messageService.Messages;
import views.employee.EmployeeView;
import views.TransactionView;
/**
 * controller de empleado coordina flujo de menus y operaciones
 */
public class EmployeeController {

	private EmployeeView eView;
	private Employee e;
	private BankAccountManager bManager;
	private TransactionView tView;
	private TransactionManager tManager;
	private CustomerManager cManager;
	
	public EmployeeController(EmployeeView eView, Employee e,BankAccountManager bManager) {
		this.eView = eView;
		this.e = e;
		this.bManager=bManager;
		this.tView=new TransactionView();
		this.tManager=new TransactionManager();
		this.cManager=new CustomerManager();
	}
	public void executeEmployee() throws SQLException {
			int option=0;
			do {
			option = eView.showMenuAndIntBack(employeeMenu(), Messages.MENU_CHOOSE_INT);
			switch (option) {
				 case 1 -> executeCInquires();
				 case 2 -> OperationManagement();
				// case 3:-> TechnicalSupport();
				case 4 -> eView.showMessage(Messages.THANKS_FOR_USING);
			 default->
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
			} while(option!=0);
			}
		
	
	private Runnable employeeMenu() {
			String Ename = e.getName();
			int Eid = e.getIdUser();
		return () -> eView.showEmployeeMenu(Ename, Eid);
	}

	public void executeCInquires() throws BusinessException, SQLException {
		int option = 0;

		do {
			try {
				option = eView.showMenuAndIntBack(eView::showCIMenu, Messages.MENU_CHOOSE_INT);
				switch (option) {
				case 1 -> searchOwnerByIBAN();
				case 2 -> viewCustomerAccountDetails();
				case 0 -> {
							eView.showMessage(Messages.EXITING);
							return; 
							}
				}
			} catch (BusinessException e) {
				eView.showMessage(e.getMessage());
			} catch (SQLException e) {
				eView.showMessage(Messages.PROBLEM_WITH_DB);
				e.printStackTrace();
			}
		} while (option != 0);
	}
			
	public void searchOwnerByIBAN() throws BusinessException,SQLException {
		String owner =bManager.searchAccountHolderbyIban(eView.askForIBAN(Messages.ASK_FOR_IBAN));
		eView.showMessage(Messages.SHOW_BACC_NAME+owner);
	}
	public void viewCustomerAccountDetails() throws SQLException {
		String Nie = eView.askForNIE(Messages.ASK_FOR_NIE);
		Customer c = cManager.getUserifItsCustomer(Nie);
		eView.showUserStats(c);
		
	}
	public void OperationManagement() throws SQLException {
		int option=eView.showMenuAndIntBack(eView::OperationManagerMenu,Messages.MENU_CHOOSE_INT);
		switch(option) {
		case 1 -> TransferBehalfUser();
		case 2 -> Deposit();
		case 3 -> Withdraw();
		//case 4 -> Transaction history();
		}
	}
	public void TransferBehalfUser() throws SQLException {
		TransferRequest tR = tView.askTransferRequest();
		tManager.transferService(tR);
	}
	public void Deposit() throws BusinessException,SQLException {
		CashOperationRequest cR = tView.askCashOperationRequest();
		tManager.makeDeposit(cR);
	}
	public void Withdraw() throws BusinessException,SQLException{
		CashOperationRequest cR = tView.askCashOperationRequest();
		tManager.makeWithdraw(cR);
		
	}
	
}

