package views;

import java.util.List;

import Interface.IBankView;
import Model.BankAccount;
import Model.Customer;
import Model.Transaction;
import exceptions.DecimalNotAcceptedException;
import exceptions.ExitException;
import exceptions.InvalidAmountException;
import exceptions.InvalidIBANException;
import exceptions.InvalidNIEexception;
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
		String IBAN = (askandGetString(message)).replaceAll("\\s+", "").toUpperCase();
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
	public String askForNIE(String message) {
		String regexNIE = "^([XYZ]\\d{7}|\\d{8})[A-Z]$";
		String NIE=(askandGetString(message)).replaceAll("\\s+", "").toUpperCase();
		if(!NIE.matches(regexNIE)) {
			throw new InvalidNIEexception();
		}
		else {
			return NIE;
		}
	}
	public String askForPassword(String message) {
		String Password = (askandGetString(message)).trim();
		return Password;
	}
	public String askForPhoneNumber(String message){
		String phone = (askandGetString(message)).replaceAll(" ", "");
		if (!phone.matches("^[6789]\\d{8}$")){
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
	public int getIntAmount() {
		String amount = (askandGetString(Messages.ASK_FOR_AMOUNT)).trim();
		if (amount.matches("\\d+")) {
			return Integer.parseInt(amount);
		} else if (amount.matches("\\d+\\.\\d+")) {
			throw new DecimalNotAcceptedException();
		} else {
			throw new InvalidAmountException();	
		}
	}
	public double getDoubleAmount() {
		String amount = (askandGetString(Messages.ASK_FOR_AMOUNT)).trim();
		amount = amount.replace(",", ".");

		if (amount.matches("\\d+(\\.\\d+)?")) {
			return Double.parseDouble(amount);
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
    public void showUserStats(Customer c) {

    	System.out.println("\n========================================");
    	System.out.println("         BANK ACCOUNT DETAILS           ");
    	System.out.println("========================================");

    	System.out.println("Name: " + c.getName());

    	System.out.println("========================================");

		for (BankAccount cBank : c.getBankAccounts()) {

			System.out.println("----------------------------------------");

			System.out.println("IBAN: " + cBank.getIBAN());

			System.out.println("Current Balance: " + cBank.getCurrentBalance() + " €");

			System.out.println("Transfer Limit: " + cBank.getTransferLimit() + " €");

			System.out.println("Overdraft Limit: " + cBank.getOverdraftLimit() + " €");

			System.out.println("Bizum Phone: " + cBank.getBizumPhone());
		}

		System.out.println("========================================\n");
	}

	/**
	 * Muestra en pantalla el historial de transacciones formateado como una tabla.
	 * @param transactions Lista de transacciones a mostrar
	 * @param iban El IBAN de la cuenta consultada
	 */
	public void showTransactionHistory(List<Transaction> transactions, String iban) {
		System.out.println("\n========================================================");
		System.out.println("          TRANSACTION HISTORY FOR IBAN: " + iban);
		System.out.println("========================================================");
		if (transactions.isEmpty()) {
			System.out.println("  No transactions found for this account.");
		} else {
			System.out.printf("%-19s | %-12s | %-12s | %-22s | %-22s | %-10s%n", 
				"Date", "Type", "Amount", "Origin", "Destiny", "Status");
			System.out.println("------------------------------------------------------------------------------------------------------");
			for (Transaction t : transactions) {
				String dateStr = t.getCreatedAt() != null ? t.getCreatedAt().toString().replace('T', ' ').substring(0, 19) : "N/A";
				String typeStr = t.gettType() != null ? t.gettType().name() : "N/A";
				String statusStr = t.gettStatus() != null ? t.gettStatus().name() : "N/A";
				double amount = t.getAmount();
				
				String origin = t.getOriginIBAN() != null ? t.getOriginIBAN() : (t.getOriginPhone() != null ? t.getOriginPhone() : "-");
				String destiny = t.getDestinyIBAN() != null ? t.getDestinyIBAN() : (t.getDestinyPhone() != null ? t.getDestinyPhone() : "-");
				
				System.out.printf("%-19s | %-12s | %10.2f€  | %-22s | %-22s | %-10s%n", 
					dateStr, typeStr, amount, origin, destiny, statusStr);
			}
		}
		System.out.println("========================================================\n");
	}

	/**
	 * Muestra por pantalla la información detallada de una cuenta bancaria individual.
	 * @param b La cuenta bancaria a mostrar
	 * @param ownerName Nombre del titular de la cuenta
	 */
	public void showBankAccountStats(BankAccount b, String ownerName) {
		System.out.println("\n========================================");
		System.out.println("         BANK ACCOUNT DETAILS           ");
		System.out.println("========================================");
		System.out.println("Owner: " + ownerName);
		System.out.println("IBAN: " + b.getIBAN());
		System.out.println("Current Balance: " + b.getCurrentBalance() + " €");
		System.out.println("Transfer Limit: " + b.getTransferLimit() + " €");
		System.out.println("Overdraft Limit: " + b.getOverdraftLimit() + " €");
		System.out.println("Bizum Phone: " + b.getBizumPhone());
		System.out.println("========================================\n");
	}
}
