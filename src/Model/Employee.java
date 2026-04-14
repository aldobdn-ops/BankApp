package Model;

public class Employee extends User {

	private String employeeId;
	private double salary;
	private String hirementDate;

	private enum Position {
		BRANCH_MANAGER, ASSITANT_BRANCH_MANAGER, BANK_TELLER, SALES_REPRESENTATIVE
	}

	private enum Status {
		ACTIVE, ONLEAVE, TERMINATED
	}

}
