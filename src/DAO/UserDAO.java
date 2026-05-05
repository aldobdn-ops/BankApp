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

}
