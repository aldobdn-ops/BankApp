package Model;

public abstract class User {

	private String NIE;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;

	private enum role {
		CUSTOMER, MANAGER, EMPLOYEE
	}

	private String password;
	private String registerDate;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

}
