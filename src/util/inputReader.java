package util;

import java.util.Scanner;

import exceptions.ExitRequestedException;


public class inputReader {

	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * funcion para mostrar pregunta leer un entero
	 * @param question
	 * @return devuelve el entero introducido por el usuario
	 */
	public static int readInteger(String question) {
		System.out.println(question);
		while (true) {
			try {
				String line = sc.nextLine().trim();
				if (line.equalsIgnoreCase("exit")) {
					throw new ExitRequestedException();
				}
				return Integer.parseInt(line);
			} catch (ExitRequestedException e) {
				throw e;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again: ");
			} catch (Exception e) {
				throw new RuntimeException("Fatal error reading int");
			}
		}
	}
	/**
	 * funcion para leer un string que introduzca el usuario
	 * @param question
	 * @return el string introducido por el usuario
	 */
	public static String readString(String question) {
		try {
			System.out.println(question);
			String result = sc.nextLine();
			if (result.trim().equalsIgnoreCase("exit")) {
				throw new ExitRequestedException();
			}
			return result;
		} catch (ExitRequestedException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Fatal error reading String");
		}
	}
}
