package controller;

import DAO.EmployeeDAO;
import Model.Employee;
import messageService.Messages;
import views.employee.EmployeeView;

public class EmployeeController {

	private EmployeeDAO eDao;
	private EmployeeView eView;
	private Employee e;
	
	public EmployeeController(EmployeeDAO eDao, EmployeeView eView, Employee e) {
		super();
		this.eDao = eDao;
		this.eView = eView;
		this.e = e;
	}
	public void executeEmployee() {
		int option = eView.showMenuAndIntBack(employeeMenu(), Messages.MENU_CHOOSE_INT);
		switch (option) {
			// case 1 -> CustomerInquires();
			// case 2:-> OperationManagement();
			// case 3:-> TechnicalSupport();
			// case 4:-> logout();
		 default->
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
	}
	private Runnable employeeMenu() {
	
			String Ename = e.getName();
			int Eid = e.getIdUser();
			
		return () -> eView.showEmployeeMenu(Ename, Eid);

	}
	
}

