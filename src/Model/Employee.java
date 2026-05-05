package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee extends User {

	private double salary;
	private LocalDate hireDate;

	
	public Employee(int idUser, String nIE, String name, String address, String phoneNumber, String email,
			String password, int passwordAttempts, LocalDateTime lastConnection, LocalDateTime registerDate,
			Role role,double salary, LocalDate hireDate, Position position, Status status) {
		super(idUser, nIE, name, address, phoneNumber, email, password, passwordAttempts, lastConnection, registerDate, role);
		this.salary = salary;
		this.hireDate = hireDate;
		this.position = position;
		this.status = status;
	}
	public enum Position {
		BRANCH_MANAGER, ASSISTANT_BRANCH_MANAGER, BANK_TELLER, SALES_REPRESENTATIVE
	}
	public Position position;
	public enum Status {
		ACTIVE, ONLEAVE, TERMINATED
	}
	public Status status;
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
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
