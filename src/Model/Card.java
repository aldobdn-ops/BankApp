package Model;

import java.time.LocalDate;

public class Card {

	private String cardNumber;
	private String hashCVV;
	private LocalDate creationDate;
	private LocalDate expirationDate;
	private String hashPin;
	private double dailyLimit;

	private enum cardType {
		DEBIT, CREDIT, PREPAID
	}

	private cardType cardType;

	private enum status {
		ACTIVE, INACTIVE, BLOCKED, EXPIRED, DISABLED
	}

	private status cardStatus;

	public Card() {
	}

	public Card(String cardNumber, String cVV, LocalDate creationDate ,LocalDate expirationDate, String cardPin, double dailyLimit) {
		super();
		this.cardNumber = cardNumber;
		this.hashCVV=cVV;
		this.creationDate=creationDate;
		this.expirationDate = expirationDate;
		this.hashPin = cardPin;
		this.dailyLimit = dailyLimit;
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
