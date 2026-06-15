package DB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class connectionDB {

	private static Properties prop = new Properties();
	// Cambiamos el objeto Connection único por el Pool de Hikari
	private static HikariDataSource ds = null; 

	static { 
		// 1. Cargamos tu archivo properties exactamente igual que antes
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "warn");
		try (InputStream input = connectionDB.class.getClassLoader().getResourceAsStream("DBconfig.properties")) {
			if(input == null) {
				throw new RuntimeException("Can't find DBconfig.properties");
			}
			prop.load(input); 
		} catch (IOException e) {
			throw new RuntimeException("Failed loading DB properties");
		}
		
		// 2. Configuramos Hikari usando los datos del archivo que acabas de cargar
		HikariConfig config = new HikariConfig();
		
		String url = prop.getProperty("dbURL");
		String user = prop.getProperty("dbUser");
		String pass = prop.getProperty("dbPass");
		
		if (url == null || user == null || pass == null) { 
		    throw new RuntimeException("Missing DB configuration keys in properties file");
		}
		
		config.setJdbcUrl(url);
		config.setUsername(user);
		config.setPassword(pass);

		// Ajustes del pool para tu banco
		config.setMaximumPoolSize(10); // Hasta 10 clientes simultáneos
		config.setMinimumIdle(2);      // 2 conexiones siempre listas
		config.setConnectionTimeout(30000); // 30 segundos de espera máx
		config.setPoolName("BankApp-Pool");

		// Inicializamos el pool
		ds = new HikariDataSource(config);
		System.out.println("✅ HikariCP Pool iniciado correctamente desde DBconfig.properties");
	}
	
	// 3. Tu método connect() ahora pide una conexión rápida al pool
	public static Connection connect() throws SQLException { 
		if (ds == null || ds.isClosed()) {
			throw new SQLException("Connection pool is not initialized.");
		}
		return ds.getConnection(); // Devuelve una conexión libre en menos de 1ms
	}

	// Método extra para cerrar el pool limpiamente al salir de la app
	public static void closePool() {
		if (ds != null && !ds.isClosed()) {
			ds.close();
			System.out.println("Pool de conexiones cerrado de forma segura.");
		}
	}
}