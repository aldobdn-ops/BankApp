package views.employee;

import views.BaseView;

public class EmployeeView extends BaseView {

	public void showEmployeeMenu(String employeeName, int employeeId) {
		
	
		System.out.println("\n========================================================");
	    System.out.println("                BANKAPP - EMPLOYEE PANEL                ");
	    System.out.println("========================================================");
	    System.out.println("  Employee: " + employeeName + " [ID: " + employeeId + "]");
	    System.out.println("--------------------------------------------------------");
	    
	    // [1] Customer Inquiries
	    System.out.println("  1. CUSTOMER INQUIRIES");
	    System.out.println("     1a. Search Holder by IBAN");
	    System.out.println("     1b. Check Balance and Account Details");
	    
	    // [2] Assisted Operations
	    System.out.println("  2. OPERATION MANAGEMENT");
	    System.out.println("     2a. Perform Transfer on behalf of Customer");
	    System.out.println("     2b. Issue Transaction Receipt");
	    
	    // [3] Internal Support
	    System.out.println("  3. TECHNICAL SUPPORT");
	    System.out.println("     3a. Report System Incident");
	    
	    System.out.println("--------------------------------------------------------");
	    System.out.println("  0. LOGOUT");
	    System.out.println("========================================================");
	    System.out.print("  Select an option (e.g., 1a, 2b, 0): ");
	}
}
