package auth;

import DAO.UserDAO;
import Model.User;
/**
 * Clase destinada a la verificación de los datos introducidos por el usuario para el login
 */

	
public class AuthService {
	
	private UserDAO uDao;
	
	public AuthService(UserDAO udao) {
	        this.uDao = udao;
	    }
	public boolean login(User userBD, String userInput, String passwordInput) {

        // ❌ usuario no existe
        if (userBD == null) {
            return false;
        }

        // 🔎 comprobar que el usuario coincide
        if (!userBD.getNIE().equals(userInput)) {
            return false;
        }

        // 🔐 comprobar contraseña
        if (!userBD.getPassword().equals(passwordInput)) {
            return false;
        }

        // ✔️ todo correcto
        return true;
    }
		/**
		 * funcion para llamar al servicio de login
		 * @param Username
		 * @param Password
		 * @return Account en caso de hacer login con exito
		 */
		//public User userLogin(String Username, String Password) {
			
			//Account loginAccount = uDao.

}
