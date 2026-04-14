package Model;

public class Account {

	private User user;
	private String password;
	private int passwordAttempts;
	private String userIdLogin;
	private String lastConnection;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getUserIdLogin() {
		return userIdLogin;
	}

	public void setUserIdLogin(String userIdLogin) {
		this.userIdLogin = userIdLogin;
	}

	public String getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
	}

}
