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
		try {
			
			while (!sc.hasNextInt()) {
				
				System.out.println("Invalid input. Try again: ");
				sc.next();
			}
			int result = sc.nextInt();
			sc.nextLine();
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Fatal error reading int");
		}
		
		
	}
	/**
	 * funcion para leer un string que introduzca el usuario
	 * @param input
	 * @return el string introducido por el usuario
	 */
	public static String readString(String input) {
		try {
			if (input.trim().equalsIgnoreCase("exit")) {
				throw new ExitRequestedException();
			}
			System.out.println(input);
			String result = sc.nextLine();
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Fatal error reading String");
		}
		
	}
}
