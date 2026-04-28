package Model;

import java.time.LocalDateTime;

public class Employee extends User {

	private double salary;
	private String hireDate;

	
	public Employee(int idUser, String nIE, String name, String address, String phoneNumber, String email,
			String password, int passwordAttempts, LocalDateTime lastConnection, LocalDateTime registerDate,
			Role role,double salary, String hireDate, Position position, Status status) {
		super(idUser, nIE, name, address, phoneNumber, email, password, passwordAttempts, lastConnection, registerDate, role);
		this.salary = salary;
		this.hireDate = hireDate;
		this.position = position;
		this.status = status;
	}
	private enum Position {
		BRANCH_MANAGER, ASSISTANT_BRANCH_MANAGER, BANK_TELLER, SALES_REPRESENTATIVE
	}
	private Position position;
	private enum Status {
		ACTIVE, ONLEAVE, TERMINATED
	}
	private Status status;
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	
}
