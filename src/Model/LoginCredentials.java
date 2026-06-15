package Model;

public class LoginCredentials {

	private final String nie;
	private final String password;
	
	public LoginCredentials(String nie,String ps) {
		this.nie=nie;
		this.password=ps;
	}

	public String getNie() {
		return nie;
	}

	public String getPassword() {
		return password;
	}
	
}
