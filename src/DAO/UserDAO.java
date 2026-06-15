package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import Model.Admin;
import Model.Customer;
import Model.Employee;
import Model.User;
import DB.connectionDB;

public class UserDAO {

	
	public User findUserByNIE(String NIE) throws SQLException {
		String query = "SELECT * FROM app_user WHERE NIE = ?";
		try (Connection cn = connectionDB.connect(); PreparedStatement pstmt = cn.prepareStatement(query)) {
			pstmt.setString(1, NIE);
			ResultSet rs = pstmt.executeQuery();
			User user = null;
			if (rs.next()) {
				user = mapUser(rs);
			}
			return user;
		}
	}
	public User findSimpleUserByNIE(String NIE) throws SQLException {
		String query = "SELECT * FROM app_user_simple WHERE NIE = ?";
		try (Connection cn = connectionDB.connect(); PreparedStatement pstmt = cn.prepareStatement(query)) {
			pstmt.setString(1, NIE);
			ResultSet rs = pstmt.executeQuery();
			User user = null;
			if (rs.next()) {
				user = mapSimpleUser(rs);
			}
			return user;
		}
	}

	private User mapUser(ResultSet rs) throws SQLException {
		int idUser = rs.getInt("id_user");
		String NIE = rs.getString("nie");
		String name = rs.getString("name");
		String address = rs.getString("address");
		String phoneNumber = rs.getString("phone_number");
		String email = rs.getString("email");
		String password = rs.getString("password");
		int passwordAttempts = rs.getInt("password_attempts");

		Timestamp lastConnTs = rs.getTimestamp("last_connection");
		LocalDateTime lastConnection = (lastConnTs != null) ? lastConnTs.toLocalDateTime() : null;

		Timestamp regDateTs = rs.getTimestamp("register_date");
		LocalDateTime registerDate = (regDateTs != null) ? regDateTs.toLocalDateTime() : null;

		User.Role role = User.Role.valueOf(rs.getString("role"));
		User user = null;

		switch (role) {
		case CUSTOMER:

			user = new Customer(idUser, NIE, name, address, phoneNumber, email, password, passwordAttempts,
					lastConnection, registerDate, role, 0.0);
			break;

		case EMPLOYEE:
			user = new Employee(idUser, NIE, name, address, phoneNumber, email, password, passwordAttempts,
					lastConnection, registerDate, role, 0.0, // salary
					null, // hireDate
					null, // position
					null); // status
			break;

		case MANAGER:
			user = new Admin(idUser, NIE, name, address, phoneNumber, email, password, passwordAttempts, lastConnection,
					registerDate, role, null);
			break;

		default:
			throw new IllegalArgumentException("Rol desconocido: " + role);
		}

		return user;
	}
	private User mapSimpleUser(ResultSet rs) throws SQLException {
		int idUser = rs.getInt("id_user");
		String NIE = rs.getString("nie");
		String name = rs.getString("name");
		String password = rs.getString("password");
		int passwordAttempts = rs.getInt("password_attempts");
		User.Role role = User.Role.valueOf(rs.getString("role"));
		User user = null;

		switch (role) {
		case CUSTOMER:

			user = new Customer(idUser, NIE, name,password, passwordAttempts,role);
			break;

		case EMPLOYEE:
			user = new Employee(idUser, NIE, name,password,passwordAttempts,role);
					
			break;

		case MANAGER:
			user = new Admin(idUser, NIE, name,password, passwordAttempts, role);
			break;

		default:
			throw new IllegalArgumentException("Rol desconocido: " + role);
		}

		return user;
	}
	public void raiseAttempts(String nie) throws SQLException {
	    String sqlFullTable = "UPDATE app_user SET password_attempts = password_attempts + 1 WHERE nie = ?";
	    String sqlLightTable = "UPDATE app_user_simple SET PASSWORD_ATTEMPTS = PASSWORD_ATTEMPTS + 1 WHERE NIE = ?";
	    
	    try (Connection cn = connectionDB.connect()) {
	        
	        // Ejecutamos en la tabla grande (app_user)
	        try (PreparedStatement psFull = cn.prepareStatement(sqlFullTable)) {
	            psFull.setString(1, nie);
	            psFull.executeUpdate();
	        }
	        
	        // Ejecutamos en la tabla ligera (app_user_simple)
	        try (PreparedStatement psLigera = cn.prepareStatement(sqlLightTable)) {
	            psLigera.setString(1, nie);
	            psLigera.executeUpdate();
	        }
	    }
	}

	// 2. Método para resetear el contador a 0 cuando el login es correcto
	public void resetAttempts(String nie) throws SQLException {
	    String sqlFullTable = "UPDATE app_user SET password_attempts = 0 WHERE nie = ?";
	    String sqlLightTable = "UPDATE app_user_simple SET PASSWORD_ATTEMPTS = 0 WHERE NIE = ?";
	    
	    try (Connection cn = connectionDB.connect()) {
	        
	        // Reseteamos en la tabla grande
	        try (PreparedStatement psGrande = cn.prepareStatement(sqlFullTable)) {
	            psGrande.setString(1, nie);
	            psGrande.executeUpdate();
	        }
	        
	        // Reseteamos en la tabla ligera
	        try (PreparedStatement psLigera = cn.prepareStatement(sqlLightTable)) {
	            psLigera.setString(1, nie);
	            psLigera.executeUpdate();
	        }
	    }
	}

}
