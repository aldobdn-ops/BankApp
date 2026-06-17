package Model;

import java.time.LocalDateTime;

/**
 * Clase abstracta que representa a un usuario base del sistema.
 * Contiene información común como NIE, nombre, email, contraseña, intentos de contraseña y rol.
 */
public abstract class User {
	protected int idUser;
	protected String NIE;
	protected String name;
	protected String address;
	protected String email;
	protected String password;
	protected int passwordAttempts;
	protected LocalDateTime lastConnection;
	protected LocalDateTime registerDate;
	protected String phone;

	
	public enum Role {
		CUSTOMER, MANAGER, EMPLOYEE
	}
	protected Role role;
//	
	public int getIdUser() {
		return idUser;
	}
	public User(int idUser, String nIE, String name, String address,String phone,String email, String password,
		int passwordAttempts, LocalDateTime lastConnection, LocalDateTime registerDate, Role role) {
	this.idUser = idUser;
	this.NIE= nIE;
	this.name = name;
	this.address = address;
	this.phone=phone;
	this.email = email;
	this.password = password;
	this.passwordAttempts = passwordAttempts;
	this.lastConnection = lastConnection;
	this.registerDate = registerDate;
	this.role = role;
	}
	
	public User(int idUser, String nIE, String name, String password, int passwordAttempts, Role role) {
		super();
		this.idUser = idUser;
		NIE = nIE;
		this.name = name;
		this.password = password;
		this.passwordAttempts = passwordAttempts;
		this.role = role;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNIE() {
		return NIE;
	}
	public void setNIE(String nIE) {
		NIE = nIE;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPasswordAttempts() {
		return passwordAttempts;
	}
	public void setPasswordAttempts(int passwordAttempts) {
		this.passwordAttempts = passwordAttempts;
	}
	public LocalDateTime getLastConnection() {
		return lastConnection;
	}
	public void setLastConnection(LocalDateTime lastConnection) {
		this.lastConnection = lastConnection;
	}
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
