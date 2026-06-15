package Model.TransactionTypes;
import Model.Transaction.TransactionStatus;
import Model.Transaction.TransactionType;

/**
 * almacena los datos necesarios para ejecutar una transferencia
 */
public class TransferRequest {

	private String originIBAN;
	private String destinyIBAN;
	private double amount;
	private TransactionType tType;
	public TransactionStatus tStatus;
	
	public TransactionStatus gettStatus() {
		return tStatus;
	}

	public void settStatus(TransactionStatus tStatus) {
		this.tStatus = tStatus;
	}

	public TransferRequest(String originIBAN, String destinyIBAN, double amount) {

		this.originIBAN = originIBAN;
		this.destinyIBAN = destinyIBAN;
		this.amount = amount;
		this.tType = TransactionType.TRANSFER;
	}

	public String getOriginIBAN() {
		return originIBAN;
	}

	public void setOriginIBAN(String originIBAN) {
		this.originIBAN = originIBAN;
	}

	public String getDestinyIBAN() {
		return destinyIBAN;
	}

	public void setDestinyIBAN(String destinyIBAN) {
		this.destinyIBAN = destinyIBAN;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public TransactionType gettType() {
		return tType;
	}

	public void settType(TransactionType tType) {
		this.tType = tType;
	}
}
