/**
 * 
 */
package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase temporal para generar hashes BCrypt con jBCrypt. Ejecutar una sola vez,
 * copiar los hashes al script SQL y borrar.
 */
public class HashGenerator {

	public static void main(String[] args) {

		String hash = "campico1234";
		hash = org.mindrot.jbcrypt.BCrypt.hashpw(hash, org.mindrot.jbcrypt.BCrypt.gensalt(10));
		System.out.println(hash);

		
	}
}
