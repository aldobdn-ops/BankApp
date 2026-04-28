package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import DB.connectionDB;

class cnTest {

	@Test
	void Connectiontest() {
		
	}
	@Test
	void shouldFindProperties() {
	    InputStream is = getClass()
	        .getClassLoader()
	        .getResourceAsStream("DBconfig.properties");

	    assertNotNull(is, "No encuentra el properties");
	}
	
	@Test
	void shouldLoadPropertiesValues() throws Exception {

	    Properties prop = new Properties();

	    InputStream is = getClass()
	        .getClassLoader()
	        .getResourceAsStream("DBconfig.properties");

	    assertNotNull(is);

	    prop.load(is);

	    assertNotNull(prop.getProperty("dbURL"));
	    assertNotNull(prop.getProperty("dbUser"));
	    assertNotNull(prop.getProperty("dbPass"));
	}
	@Test
	void shouldConnectToDatabase() {

	    try (Connection cn = connectionDB.connect()) {

	        assertNotNull(cn, "La conexión es null");
	        assertFalse(cn.isClosed(), "La conexión está cerrada");

	    } catch (Exception e) {
	        fail("Error conectando a la BD: " + e.getMessage());
	    }
	}
}
