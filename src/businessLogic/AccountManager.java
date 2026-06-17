package businessLogic;

import java.sql.SQLException;
import DAO.UserDAO;
import Model.User;
import exceptions.UserNotFoundException;

/**
 * Gestor básico para la validación y control de cuentas de usuario general.
 */
public class AccountManager {

	private final UserDAO uDao;

	public AccountManager(UserDAO uDao) {
		this.uDao = uDao;
	}

	/**
	 * Valida si la cuenta de usuario no es nula.
	 * @param u El usuario a validar
	 */
	public void validAccount(User u) {
		if (u == null) {
			throw new UserNotFoundException();
		}
	}

	/**
	 * Busca un usuario simple por su NIE.
	 * @param nie El NIE del usuario
	 * @return El objeto User
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public User findSimpleUserByNIE(String nie) throws SQLException {
		return uDao.findSimpleUserByNIE(nie);
	}

	/**
	 * Restablece los intentos de contraseña a 0 para el usuario especificado.
	 * @param nie El NIE del usuario
	 * @throws SQLException Si ocurre algún fallo de base de datos
	 */
	public void resetUserPasswordAttempts(String nie) throws SQLException {
		uDao.resetAttempts(nie);
	}
}
