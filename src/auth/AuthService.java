package auth;

import java.sql.SQLException;

import DAO.UserDAO;
import Model.LoginCredentials;
import Model.User;
import exceptions.AccountBlockedException;
import exceptions.InvalidPasswordException;
import util.PasswordManager;
/**
 * Clase destinada a la verificación de los datos introducidos por el usuario para el login
 */

	
public class AuthService {
	
	UserDAO uDAO;

	public AuthService(UserDAO uDAO) {
		this.uDAO= uDAO;
	}

	public void login(User u,LoginCredentials lC) throws SQLException {
		if (u.getPasswordAttempts() >= 3) {		
	        throw new AccountBlockedException();
	    }
		if (!PasswordManager.verify(lC.getPassword(), u.getPassword())) {
			uDAO.raiseAttempts(lC.getNie());
			u.setPasswordAttempts(u.getPasswordAttempts()+1);
			if(u.getPasswordAttempts()==3) {
				throw new AccountBlockedException();
			}
			else {
				throw new InvalidPasswordException();
			}
			
		}
		uDAO.resetAttempts(u.getNIE());
		
	}
}
/**
 * funcion para llamar al servicio de login
 * 
 * @param Username
 * @param Password
 * @return Account en caso de hacer login con exito
 */
// public User userLogin(String Username, String Password) {

// Account loginAccount = uDao.
