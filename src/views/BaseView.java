package views;

import Interface.IBankView;
import util.inputReader;
/**
 * Clase abstracta que comparte todos los metodos comunes de las vistas como mostrar mensajes
 * o pedir datos al usuario
 */
public abstract class BaseView implements IBankView {


	protected BaseView() {

	}
	/**
	 * muestra menu y recoge un int del usuario
	 * @param showMenu
	 * @param msg
	 * @return int
	 */
	
	public int showMenuAndIntBack(Runnable showMenu,String msg) {
		showMenu.run();
		return inputReader.readEntero(msg);
	}
	/**
	 * pregunta al usuario y recibe respuesta en formato String
	 * @param keyQuestion
	 * @return String
	 */
    public String askCustomerAndGetString(String Question) {
    		return inputReader.readString(Question);
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
