package auth;

import DAO.UserDAO;
import Model.User;
import exceptions.InvalidPasswordException;
/**
 * Clase destinada a la verificación de los datos introducidos por el usuario para el login
 */

	
public class AuthService {
	private UserDAO uDao;

	public AuthService() {
	}

	public void login(User userBD, String passwordInput) {
		if (!userBD.getPassword().equals(passwordInput)) {
			throw new InvalidPasswordException();
		}
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
