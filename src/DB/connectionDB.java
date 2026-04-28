package DB;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionDB {

	private static Properties prop = new Properties(); //guardara el archivo properties que tenemos en resources
	private static Connection cn;
	static { //bloque que carga automaticamente cuando se llame un objeto de la clase
		try {
			InputStream input = connectionDB.class //referencia a la clase
					.getClassLoader() //acciona cargador de la clase
					.getResourceAsStream("DBconfig.properties");//busca el recurso y lo abre
			if(input == null) {
				throw new RuntimeException("Can't find DBconfig.properties");
			}
			prop.load(input); //cargamos properties en la clase
		} catch (IOException e) {
			throw new RuntimeException("Failed loading DB properties");
		}
		
	}
	
	public static Connection connect() { 
		if(cn!=null) {
			return cn;
		}
		else {
		try {
			String URL = prop.getProperty("dbURL");
			String User = prop.getProperty("dbUser");
			String Pass = prop.getProperty("dbPass");
			if (URL == null || User == null || Pass == null) { //controlamos si se ha introducido mal un key
			    System.err.println("Wrong keys introduced");
			    throw new RuntimeException("Missing DB configuration");
			}
			cn = DriverManager.getConnection(URL,User,Pass);
			return cn;
		} catch (SQLException e) {
			System.out.println("Failed connecting to database");
			return null;
		}
		//return cn;
		
	}
	}
}
