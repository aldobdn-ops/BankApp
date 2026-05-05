package exceptions;

public class EmployeeNotFoundException extends BusinessException {

	public EmployeeNotFoundException(String message) {
		super("Employee not found");
	}
	public EmployeeNotFoundException() {
		super("Employee not found");
	}
	
}
