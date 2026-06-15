package Model.TransactionTypes;
/**
 * almacena los datos necesarios para ejecutar un bizum
 */
public class BizumRequest {

	private String originPhoneNumber;
	private String destinyPhoneNumber;
	private double amount;
	
	public BizumRequest(String originPhoneNumber, String destinyPhoneNumber, double amount) {
		this.originPhoneNumber = originPhoneNumber;
		this.destinyPhoneNumber = destinyPhoneNumber;
		this.amount = amount;
	}

	public String getOriginPhoneNumber() {
		return originPhoneNumber;
	}

	public void setOriginPhoneNumber(String originPhoneNumber) {
		this.originPhoneNumber = originPhoneNumber;
	}

	public String getDestinyPhoneNumber() {
		return destinyPhoneNumber;
	}

	public void setDestinyPhoneNumber(String destinyPhoneNumber) {
		this.destinyPhoneNumber = destinyPhoneNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
