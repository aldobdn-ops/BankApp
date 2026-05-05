package views.admin;

import views.BaseView;

public class AdminView extends BaseView {

	public void displayAdminMenu(String adminName) {
	    System.out.println("\n========================================================");
	    System.out.println("              BANKAPP - SYSTEM ADMINISTRATION           ");
	    System.out.println("========================================================");
	    System.out.println("  Administrator: " + adminName);
	    System.out.println("--------------------------------------------------------");
	    
	    // [1] Security & Roles
	    System.out.println("  1. SECURITY & ROLES");
	    System.out.println("     1a. Assign/Modify Permissions by Role");
	    System.out.println("     1b. Register New User (Admin/Employee/Customer)");
	    System.out.println("     1c. Deactivate or Delete Accounts");
	    System.out.println("     1d. Generate Password Recovery Code");
	    
	    // [2] Maintenance
	    System.out.println("  2. DATABASE MAINTENANCE");
	    System.out.println("     2a. Bulk IBAN Validation");
	    System.out.println("     2b. Account Database Audit");
	    System.out.println("     2c. View Reported Incidents Log");
	    
	    // [3] Global Settings
	    System.out.println("  3. SYSTEM CONFIGURATION");
	    System.out.println("     3a. Update Global Limits (Transfers/Bizum)");
	    
	    System.out.println("--------------------------------------------------------");
	    System.out.println("  0. LOGOUT");
	    System.out.println("========================================================");
	    System.out.print("  Select an option (e.g., 1a, 3a, 0): ");
	}
}
