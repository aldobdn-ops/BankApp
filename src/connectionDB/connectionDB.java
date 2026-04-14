package connectionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDB {

	private static final String URL =	"jdbc:mysql//localhost:3306/bankapp";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static Connection connect() {
		try {
			return DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			System.out.println("Failed connecting to database");
			return null;
		}
		
	}
}
