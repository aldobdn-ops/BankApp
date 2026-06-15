package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import DAO.CustomerDAO;
import DAO.UserDAO;
import DB.connectionDB;
import Model.BankAccount;
import Model.Customer;
import messageService.Messages;
import views.employee.EmployeeView;

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
	@Test
	void getAccountOwnerByIBAN() throws Exception {
		try {
		String IBAN = "ES9121000418450200051332";
		CustomerDAO cDAO = new CustomerDAO();
		cDAO.getAccountOwnerByIBAN(IBAN);
		} catch(SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
			
	}
	@Test
	public void viewCustomerAccountDetails() throws SQLException {
		EmployeeView eView = new EmployeeView();
		UserDAO uDAO = new UserDAO();
		CustomerDAO cDAO = new CustomerDAO();
		String Nie = eView.askForNIE(Messages.ASK_FOR_NIE);
		Customer c = (Customer)uDAO.findUserByNIE(Nie);
		BankAccount b = cDAO.getBankAccbyId(c.getIdUser());
		eView.showUserStats(c, b);
		
	}
	
}
