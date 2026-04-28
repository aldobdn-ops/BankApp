package util;
import java.util.Random;
public class randomNumberBuilder {
	/**
	 * funcion para generar CVV aleatorio
	 * @return cvv aleatorio
	 */
	public String randomCVVBuilder() {
		Random random = new Random();
		int numero= random.nextInt(1000);
        return String.format("%03d", numero);
	}
	/**
	 * funcion para generar numero de tarjeta aleatorio
	 * @return numero de tarjeta aleatorio
	 */
	public String randomCardNumberBuilder() {
		String cardNumber="";
		Random random=new Random();			
		for (int x=1;x<=16;x++) {
			int randomNumber=random.nextInt(10);
			cardNumber=cardNumber+randomNumber;
		}
		return cardNumber;
	}
	/**
	 * funcion para generar un pin aleatorio
	 * @return un numero de pin aleatorio
	 */
	public String randomPINBuilder() {
		String PIN="";
		Random random = new Random();
		for(int x=1;x<=4;x++) {
			int pinNumber = random.nextInt(10);
			PIN =PIN+pinNumber;
		}
		return PIN;
	}
	
}
