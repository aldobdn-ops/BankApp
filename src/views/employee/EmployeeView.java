package views.employee;

import views.BaseView;

public class EmployeeView extends BaseView {

	public void showEmployeeMenu(String employeeName, int employeeId) {
		
		System.out.println("\n========================================================");
		System.out.println("                BANKAPP - EMPLOYEE PANEL                ");
		System.out.println("========================================================");
		System.out.println("  Employee: " + employeeName + " [ID: " + employeeId + "]");
		System.out.println("--------------------------------------------------------");

		System.out.println("  1. CUSTOMER INQUIRIES");
		System.out.println("     1.1 Search Holder by IBAN");
		System.out.println("     1.2 Check Balance and Account Details");

		System.out.println();

		System.out.println("  2. BANKING OPERATIONS");
		System.out.println("     2.1 Perform Transfer on Behalf of Customer");
		System.out.println("     2.2 Deposit Money");
		System.out.println("     2.3 Withdraw Money");
		System.out.println("     2.4 View Transaction History");
		System.out.println("     2.5 Issue Transaction Receipt");
		System.out.println("     2.6 Check Transaction Status");

		System.out.println();

		System.out.println("  3. TECHNICAL SUPPORT");
		System.out.println("     3.1 Report System Incident");

		System.out.println("--------------------------------------------------------");
		System.out.println("  0. LOGOUT");
		System.out.println("========================================================");
		System.out.print("  Select an option: ");
	}
	public void showCIMenu() {

        System.out.println("\n========================================");
        System.out.println("         CUSTOMER INQUIRIES");
        System.out.println("========================================");
        System.out.println("  1. Search Holder by IBAN");
        System.out.println("  2. Check Balance and Account Details");
        System.out.println("  0. Exit");
        System.out.println("========================================");

	}
	public void OperationManagerMenu() {
		System.out.println("\n=================================================");
		System.out.println("               2.OPERATION MANAGEMENT              ");
		System.out.println("=================================================");
		System.out.println("  1. Perform Transfer on Behalf of Customer");
		System.out.println("  2. Deposit Money");
		System.out.println("  3. Withdraw Money");
		System.out.println("  4. View Transaction History");
		System.out.println("  5. Issue Transaction Receipt");
		System.out.println("  6. Check Transaction Status");
		System.out.println("  0. Return to Previous Menu");
		System.out.println("=================================================");
	}
	
}
