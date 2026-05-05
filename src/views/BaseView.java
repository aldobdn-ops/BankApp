package views;

import Interface.IBankView;
import exceptions.DecimalNotAcceptedException;
import exceptions.ExitException;
import exceptions.InvalidAmountException;
import exceptions.InvalidIBANException;
import exceptions.InvalidPhoneException;
import messageService.Messages;
import util.inputReader;
/**
 * Clase abstracta que comparte todos los metodos comunes de las vistas como mostrar mensajes
 * o pedir datos al usuario
 */
public abstract class BaseView implements IBankView {


	protected BaseView() {

	}
	public String askForIBAN(String message){
		String regexIBAN = "^ES\\d{22}$";
		String IBAN = (askandGetString(Messages.ASK_FOR_ORIGIN_IBAN)).replaceAll("\\s+", "").toUpperCase();
		if (IBAN.equalsIgnoreCase("exit")) {
			throw new ExitException();
		}
		else if (!IBAN.matches(regexIBAN)){
			throw new InvalidIBANException();
		}
		else {
			return IBAN;
		}
	}
	public String askForPhoneNumber(String message){
		String phone = (askandGetString(message)).replaceAll(" ", "");
		if (phone.equalsIgnoreCase("exit")) {
			throw new ExitException();
		}
		else if (!phone.matches("^[6789]\\d{8}$")){
			throw new InvalidPhoneException();
		}
		else {
			return phone;
		}
	}
	public int askAndGetInt(String Question) {
 		return inputReader.readInteger(Question);
 }
	/**
	 * muestra menu y recoge un int del usuario
	 * @param showMenu
	 * @param msg
	 * @return int
	 */
	
	public int showMenuAndIntBack(Runnable showMenu,String msg) {
		showMenu.run();
		return inputReader.readInteger(msg);
	}
	/**
	 * pregunta al usuario y recibe respuesta en formato String
	 * @param keyQuestion
	 * @return String
	 */
    
	public String askandGetString(String Question) {
    		return inputReader.readString(Question);
    }
	/**
	 * recoge un entero, si no lo es tira excepcion segun el fallo
	 * @return numero entero
	 */
	public int getIntAmountorExit() {
		String amount = (askandGetString(Messages.ASK_FOR_AMOUNT)).trim();
		if(amount.equalsIgnoreCase("exit")) {
			throw new ExitException();
		}
		if (amount.matches("\\d+")) {
			return Integer.parseInt(amount);
		} else if (amount.matches("\\d+\\.\\d+")) {
			throw new DecimalNotAcceptedException();
		} else {
			throw new InvalidAmountException();	
		}
	}
	
    /**
     * muestra mensaje al usuario
     * @param keyMsg
     */
    public void showMessage(String msg) {
    		System.out.println(msg);
    }
    /**
     * muestra menu al usuario y recibe respuesta en formato String
	 * @param keyQuestion
	 * @return String
     */
    public String showMenuAndStringBack(Runnable showMenu,String msg) {
		showMenu.run();
		return inputReader.readString(msg);
	}
}
