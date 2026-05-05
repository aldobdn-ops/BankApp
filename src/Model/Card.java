package Model;

import java.time.LocalDate;

public class Card {

	private int idCard;
	private int idAccount;
	private String cardNumber;
	private String hashCVV;
	private LocalDate creationDate;
	private LocalDate expirationDate;
	private String hashPin;
	private double dailyLimit;

	public enum cardType {
		DEBIT, CREDIT, PREPAID
	}

	private cardType cardType;

	public enum status {
		ACTIVE, INACTIVE, BLOCKED, EXPIRED, DISABLED
	}

	private status cardStatus;

	public Card() {
	}

	public Card(int idCard,int idAccount, String cardNumber, String hashCVV, LocalDate creationDate, LocalDate expirationDate,
			String hashPin, double dailyLimit, cardType cardType, status cardStatus) {

		this.idCard = idCard;
		this.idAccount = idAccount;
		this.cardNumber = cardNumber;
		this.hashCVV = hashCVV;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.hashPin = hashPin;
		this.dailyLimit = dailyLimit;
		this.cardType = cardType;
		this.cardStatus = cardStatus;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCVV() {
		return hashCVV;
	}

	public void setCVV(String cVV) {
		hashCVV = cVV;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate( LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardPin() {
		return hashPin;
	}

	public void setCardPin(String cardPin) {
		this.hashPin = cardPin;
	}

	public double getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

}
