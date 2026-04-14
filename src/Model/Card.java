package Model;

public class Card {

	private String cardNumber;
	private String CVV;
	private String expirationDate;
	private String cardPin;
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

	public Card(String cardNumber, String cVV, String expirationDate, String cardPin, double dailyLimit) {
		super();
		this.cardNumber = cardNumber;
		CVV = cVV;
		this.expirationDate = expirationDate;
		this.cardPin = cardPin;
		this.dailyLimit = dailyLimit;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardPin() {
		return cardPin;
	}

	public void setCardPin(String cardPin) {
		this.cardPin = cardPin;
	}

	public double getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(double dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

}
