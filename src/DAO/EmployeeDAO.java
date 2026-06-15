package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import Model.Employee.Position;
import Model.Employee.Status;
import Model.User;
import exceptions.EmployeeNotFoundException;
import DB.connectionDB;
import Model.Employee;

public class EmployeeDAO {

	public Employee buildSpecificEmployee (Employee e) throws SQLException {
		int idUser= e.getIdUser();
		String query = "SELECT * FROM EMPLOYEE WHERE ID_USER = ?";
		try(Connection cn= connectionDB.connect();
				PreparedStatement pstmt = cn.prepareStatement(query)){
			pstmt.setInt(1, idUser);
			ResultSet rSet= pstmt.executeQuery();
			  if (!rSet.next()) {
		            throw new EmployeeNotFoundException();
		        }
			e.setHireDate(rSet.getDate("hire_date").toLocalDate());
			e.setPosition(Position.valueOf(rSet.getString("position")));
			e.setStatus(Status.valueOf(rSet.getString("status")));
			return e;	
		}
	}
}
