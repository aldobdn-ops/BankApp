/**
 * 
 */
package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
	// Coste del algoritmo: 2^10 = 1024 iteraciones internas.
	// Rango recomendado: 10-12. No bajar de 10 en producción.
	private static final int STRENGTH = 10;

	/**
	 * Genera el hash BCrypt de una contraseña en texto plano.
	 *
	 * Se usa en el momento del registro o del cambio de contraseña. NUNCA se
	 * almacena la contraseña original, solo este hash.
	 *
	 * @param passwordPlano la contraseña tal como la escribe el usuario
	 * @return el hash BCrypt listo para guardar en la base de datos
	 */
	public static String hash(String passwordPlano) {
		return BCrypt.hashpw(passwordPlano, BCrypt.gensalt(STRENGTH));
	}

	/**
	 * Comprueba si una contraseña en texto plano coincide con su hash.
	 *
	 * Se usa en el login. BCrypt extrae el salt del hash guardado, recalcula
	 * internamente y devuelve true si coinciden.
	 *
	 * @param passwordPlano la contraseña introducida por el usuario
	 * @param hashGuardado  el hash recuperado de la base de datos
	 * @return true si la contraseña es correcta, false si no lo es
	 */
	public static boolean verify(String passwordPlano, String hashGuardado) {
		return BCrypt.checkpw(passwordPlano, hashGuardado);
	}
}
